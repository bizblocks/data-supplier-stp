package com.groupstp.datasupplier.core.bean.federal_tax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.groupstp.datasupplier.core.bean.FederalTaxWorker;
import com.groupstp.datasupplier.core.bean.MessageableBean;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.FnsEgrResponse;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.entity.ActionTypeDTO;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.entity.FnsContragent;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.entity.IndividualDTO;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.entity.OrganisationDTO;
import com.groupstp.datasupplier.core.config.FederalTaxConfig;
import com.groupstp.datasupplier.core.mbean.FederalTaxMBean;
import com.groupstp.datasupplier.core.util.RestInterceptor;
import com.groupstp.datasupplier.entity.ImContragent;
import com.groupstp.datasupplier.entity.ImContragentApiCall;
import com.groupstp.datasupplier.entity.ImContragentType;
import com.groupstp.datasupplier.exception.FederalTaxException;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.View;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Federal Tax Service integration
 *
 * @author adiatullin
 */
@Component(FederalTaxWorker.NAME)
public class FederalTaxWorkerBean extends MessageableBean implements FederalTaxWorker, FederalTaxMBean {

    private static final Logger log = LoggerFactory.getLogger(FederalTaxWorkerBean.class);

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Inject
    private FederalTaxConfig config;

    private ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Nullable
    @Override
    public ImContragent getContragent(String regNumber) throws FederalTaxException {
        if (!StringUtils.isEmpty(regNumber)) {
            ImContragentApiCall contragentApiCall = getApiCall(regNumber);
            if (contragentApiCall != null && !StringUtils.isEmpty(contragentApiCall.getResponseBody())) {
                try {
                    FnsEgrResponse cachedRes = new ObjectMapper().readValue(contragentApiCall.getResponseBody(), FnsEgrResponse.class);

                    return convert(cachedRes, regNumber);
                } catch (Exception e) {
                    log.error("Failed to read cached FNS api result", e);
                    return null;
                }
            } else {
                return getContragentFromExternal(regNumber);
            }
        }
        return null;
    }

    @Inject
    private Persistence persistence;


    @Nullable
    @Override
    public ImContragent getContragentFromCache(String regNumber) {
        if (!StringUtils.isEmpty(regNumber)) {
            ImContragentApiCall contragentApiCall = getApiCall(regNumber);
            if (contragentApiCall != null && !StringUtils.isEmpty(contragentApiCall.getResponseBody())) {
                try {
                    FnsEgrResponse cachedRes = new ObjectMapper().readValue(contragentApiCall.getResponseBody(), FnsEgrResponse.class);

                    return convert(cachedRes, regNumber);
                } catch (Exception e) {
                    log.error("Failed to read cached FNS api result", e);
                    return null;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ImContragent getContragentFromExternal(String regNumber) throws FederalTaxException {
        if (!StringUtils.isEmpty(regNumber)) {

            String url = config.getFnsRestEndpoint() + config.getFnsRestEgrulPath();
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            builder.queryParam("req", regNumber);
            builder.queryParam("key", getFnsApiKey());
            url = builder.toUriString();

            StopWatch sw = new Slf4JStopWatch(log);
            try {
                FnsEgrResponse res = getRestTemplate().getForObject(url, FnsEgrResponse.class);
                String resJson = objectWriter.writeValueAsString(res);

                saveApiCall(resJson, regNumber);

                return convert(res, regNumber);
            } catch (Exception e) {
                log.error("Failed FNS get contragent information", e);
                String message = getMessage("FederalTaxWorkerBean.rest.na");
                if (!StringUtils.isEmpty(e.getMessage())) {
                    message = e.getMessage();
                }
                throw new FederalTaxException(String.format(getMessage("FederalTaxWorkerBean.rest.error"), message));
            } finally {
                sw.stop("FederalTaxWorkerBean", "FNS get contragent information finished");
            }
        }
        return null;
    }

    private RestTemplate getRestTemplate() {
        RestTemplate rt = new RestTemplate();
        rt.setInterceptors(Collections.singletonList(new RestInterceptor()));
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(config.getTimeoutMs());
        factory.setReadTimeout(config.getTimeoutMs());
        rt.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
        return rt;
    }

    private void saveApiCall(String response, String regNumber) {
        ImContragentApiCall item = metadata.create(ImContragentApiCall.class);
        item.setRegNumber(regNumber);
        item.setResponseBody(response);
        dataManager.commit(item);
    }

    @Nullable
    @Override
    public ImContragentApiCall getApiCall(String regNumber) {
        return dataManager.load(ImContragentApiCall.class)
                .query("select e from rtneoimport$ImContragentApiCall e where e.regNumber = :regNumber order by e.createTs desc")
                .parameter("regNumber", regNumber)
                .view(View.LOCAL)
                .maxResults(1)
                .optional()
                .orElse(null);
    }

    @Nullable
    private ImContragent convert(FnsEgrResponse response, String regNumber) {
        if (CollectionUtils.isEmpty(response.getItems())) {
            log.warn("From registration number {} no contragent found", regNumber);
            return null;
        }
        if (response.getItems().size() > 1) {
            log.warn("For registration number {} found more than one contragent.", regNumber);
        }
        for (FnsContragent item : response.getItems()) {
            if (item instanceof OrganisationDTO) {
                OrganisationDTO dto = (OrganisationDTO) item;

                if (!isActive(dto.getStatus())) {
                    log.debug("Contragent with reg number '{}' are not active", regNumber);
                    continue;
                }

                ImContragent res = metadata.create(ImContragent.class);
                res.setType(ImContragentType.ORGANISATION);
                res.setName(dto.getFullName());
                res.setShortName(dto.getShortName());
                if (dto.getAddress() != null) {
                    res.setLegalAddress(dto.getAddress().getAddress());
                    res.setActualAddress(dto.getAddress().getAddress());
                    res.setMailingAddress(dto.getAddress().getIndex());
                }
                res.setInn(dto.getInn());
                res.setKpp(dto.getKpp());
                res.setOgrn(dto.getOgrn());
                res.setOkopf(dto.getOkopf());
                res.setContactPersonPhone(dto.getPhoneNumber());
                res.setContactPersonEmail(dto.getEmail());
                if (dto.getHeadPerson() != null) {
                    res.setHeadPersonName(dto.getHeadPerson().getFio());
                    res.setHeadPersonPost(dto.getHeadPerson().getPosition());
                }
                if (dto.getMainActionType() != null) {
                    res.setOkved(dto.getMainActionType().getCode());
                }
                if (!CollectionUtils.isEmpty(dto.getAdditionalActionType())) {
                    res.setAddOkved(dto.getAdditionalActionType().stream()
                            .map(ActionTypeDTO::getCode)
                            .filter(e -> !StringUtils.isBlank(e))
                            .collect(Collectors.joining(", ")));
                }
                if (dto.getOpenData() != null) {
                    res.setAvgNumber(parseNumberSafely(dto.getOpenData().getEmployeeCount(), Integer.class));
                    res.setAvgIncome(parseNumberSafely(dto.getOpenData().getIncome(), BigDecimal.class));
                }
                return res;
            } else if (item instanceof IndividualDTO) {
                IndividualDTO dto = (IndividualDTO) item;

                if (!isActive(dto.getStatus())) {
                    log.debug("Contragent with reg number '{}' are not active", regNumber);
                    continue;
                }

                ImContragent res = metadata.create(ImContragent.class);
                res.setType(ImContragentType.INDIVIDUAL);
                res.setName(dto.getFio());
                res.setShortName(dto.getFio());
                if (dto.getAddress() != null) {
                    res.setLegalAddress(dto.getAddress().getAddress());
                    res.setActualAddress(dto.getAddress().getAddress());
                    res.setMailingAddress(dto.getAddress().getIndex());
                }
                res.setInn(dto.getInn());
                res.setOgrn(dto.getOgrn());
                res.setContactPersonName(dto.getFio());
                res.setContactPersonEmail(dto.getEmail());
                res.setHeadPersonName(dto.getFio());
                res.setHeadPersonEmail(dto.getEmail());
                if (dto.getMainActionType() != null) {
                    res.setOkved(dto.getMainActionType().getCode());
                }
                if (!CollectionUtils.isEmpty(dto.getAdditionalActionType())) {
                    res.setAddOkved(dto.getAdditionalActionType().stream()
                            .map(ActionTypeDTO::getCode)
                            .filter(e -> !StringUtils.isBlank(e))
                            .collect(Collectors.joining(", ")));
                }
                return res;
            } else {
                log.error("Unknown contragent dto class '{}'", item.getClass());
            }
        }
        return null;
    }

    private boolean isActive(@Nullable String status) {
        if (!StringUtils.isEmpty(status)) {
            status = status.toLowerCase().trim();
            if ("прекратило деятельность".equals(status)) {
                return false;
            }
            if ("ликвидировано".equals(status)) {
                return false;
            }
        }
        return true;
    }

    private String getFnsApiKey() {
        String key = config.getFnsApiKey();
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException(getMessage("FederalTaxWorkerBean.fns.apiKeyNotSpecified"));
        }
        return key;
    }

    private <T extends Number> T parseNumberSafely(String value, Class<T> clazz) {
        if (!StringUtils.isEmpty(value)) {
            try {
                return NumberUtils.parseNumber(value, clazz);
            } catch (Exception ignore) {
            }
        }
        return null;
    }
}
