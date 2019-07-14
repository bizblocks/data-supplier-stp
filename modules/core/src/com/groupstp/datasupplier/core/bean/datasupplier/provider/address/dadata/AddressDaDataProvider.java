package com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata;

import com.groupstp.datasupplier.core.bean.dadata.DaDataTools;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.AddressDataProviderDelegate;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.dto.DaDataAddress;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.dto.DaDataAddressGeoCodingRequest;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.dto.DaDataAddressSuggestRequest;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.dto.DaDataAddressSuggestionResponse;
import com.groupstp.datasupplier.data.AddressData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * DaData addresses data provider service bean
 *
 * @author adiatullin
 */
@Component("dsstp_AddressDaDataProvider")
public class AddressDaDataProvider implements AddressDataProviderDelegate {
    private static final Logger log = LoggerFactory.getLogger(AddressDaDataProvider.class);

    protected static final String ENDPOINT_CLEAN_ADDRESS = "/clean/address";
    protected static final String ENDPOINT_SUGGEST_ADDRESS = "/suggest/address";
    protected static final String ENDPOINT_GEOLOCATE_ADDRESS = "/geolocate/address";
    protected static final String ENDPOINT_SUGGEST_SELECT_ADDRESS = "/findById/address";

    @Inject
    protected DaDataTools tools;

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public AddressData getFormattedAddressDetails(String rawAddress) {
        StopWatch sw = new Slf4JStopWatch(log);
        try {
            DaDataAddress[] items = tools.doRequest(ENDPOINT_CLEAN_ADDRESS, HttpMethod.POST, new String[]{rawAddress}, DaDataAddress[].class, false);
            if (items != null && items.length > 0) {
                for (DaDataAddress item : items) {
                    if (item.getResult() != null) {
                        AddressData address = new AddressData();
                        address.setAddress(item.getResult());
                        address.setPostalCode(item.getPostalCode());
                        address.setFiasCode(item.getFiasCode());
                        address.setFiasId(item.getFiasId());
                        address.setKladrId(item.getKladrId());
                        address.setLatitude(parseCoordinateSafely(item.getLatitude()));
                        address.setLongitude(parseCoordinateSafely(item.getLongitude()));
                        address.setSource(item.getSource());
                        address.setFiasLevel(item.getFiasLevel());
                        address.setFiasActualityState(item.getFiasActualityState());
                        address.setQcGeo(item.getQcGeo());
                        address.setQcHouse(item.getQcHouse());
                        address.setQc(item.getQc());
                        address.setUnparsedParts(item.getUnparsedParts());

                        return address;
                    }
                }
            }
        } finally {
            sw.stop("AddressDaDataProvider", "Address clean finished");
        }
        return null;
    }

    @Nullable
    @Override
    public AddressData getExtendedSuggestionAddressDetails(AddressData selected) {
        //https://confluence.hflabs.ru/pages/viewpage.action?pageId=312016944
        StopWatch sw = new Slf4JStopWatch(log);
        try {
            if (selected != null) {
                String id = selected.getFiasId();
                if (StringUtils.isEmpty(id)) {
                    id = selected.getKladrId();
                }
                if (!StringUtils.isEmpty(id)) {
                    //search detailed suggestion by id code
                    DaDataAddressSuggestRequest request = new DaDataAddressSuggestRequest();
                    request.setQuery(id);

                    DaDataAddressSuggestionResponse res = tools.doRequest(ENDPOINT_SUGGEST_SELECT_ADDRESS, HttpMethod.POST, request, DaDataAddressSuggestionResponse.class, true);
                    if (res != null && !CollectionUtils.isEmpty(res.getSuggestions())) {
                        DaDataAddressSuggestionResponse.DaDataAddressSuggestion extended = res.getSuggestions().iterator().next();
                        if (extended.getData() != null) {
                            if (StringUtils.isBlank(selected.getFiasCode())) {
                                selected.setFiasCode(extended.getData().getFiasCode());
                            }
                            if (selected.getLatitude() == null) {
                                selected.setLatitude(parseCoordinateSafely(extended.getData().getLatitude()));
                            }
                            if (selected.getLongitude() == null) {
                                selected.setLongitude(parseCoordinateSafely(extended.getData().getLongitude()));
                            }
                            if (selected.getSource() == null) {
                                selected.setSource(extended.getData().getSource());
                            }
                            if (selected.getFiasLevel() == null) {
                                selected.setFiasLevel(extended.getData().getFiasLevel());
                            }
                            if (selected.getFiasActualityState() == null) {
                                selected.setFiasActualityState(extended.getData().getFiasActualityState());
                            }
                            if (selected.getQcGeo() == null) {
                                selected.setQcGeo(extended.getData().getQcGeo());
                            }
                            if (selected.getQcHouse() == null) {
                                selected.setQcHouse(extended.getData().getQcHouse());
                            }
                            if (selected.getQc() == null) {
                                selected.setQc(extended.getData().getQc());
                            }
                            if (selected.getUnparsedParts() == null) {
                                selected.setUnparsedParts(extended.getData().getUnparsedParts());
                            }
                        }
                    }
                }
            }
        } finally {
            sw.stop("AddressDaDataProvider", "Detailed selected suggestion finished");
        }
        return selected;
    }

    @Override
    public List<AddressData> getSuggestionAddressesDetails(String rawAddress, int count) {
        StopWatch sw = new Slf4JStopWatch(log);
        try {
            DaDataAddressSuggestRequest request = new DaDataAddressSuggestRequest();
            request.setQuery(rawAddress);
            request.setCount(count < 1 ? 10 : count);

            DaDataAddressSuggestionResponse res = tools.doRequest(ENDPOINT_SUGGEST_ADDRESS, HttpMethod.POST, request, DaDataAddressSuggestionResponse.class, true);
            if (res != null && !CollectionUtils.isEmpty(res.getSuggestions())) {
                List<AddressData> result = new ArrayList<>(res.getSuggestions().size());
                for (DaDataAddressSuggestionResponse.DaDataAddressSuggestion item : res.getSuggestions()) {
                    if (!StringUtils.isBlank(item.getValue()) && item.getData() != null) {
                        AddressData address = new AddressData();
                        address.setAddress(item.getValue());
                        address.setPostalCode(item.getData().getPostalCode());
                        address.setFiasId(item.getData().getFiasId());
                        address.setFiasCode(item.getData().getFiasCode());
                        address.setKladrId(item.getData().getKladrId());
                        address.setLatitude(parseCoordinateSafely(item.getData().getLatitude()));
                        address.setLongitude(parseCoordinateSafely(item.getData().getLongitude()));
                        address.setSource(item.getData().getSource());
                        address.setFiasLevel(item.getData().getFiasLevel());
                        address.setFiasActualityState(item.getData().getFiasActualityState());
                        address.setQcGeo(item.getData().getQcGeo());
                        address.setQcHouse(item.getData().getQcHouse());
                        address.setQc(item.getData().getQc());
                        address.setUnparsedParts(item.getData().getUnparsedParts());

                        result.add(address);
                    }
                }
                result.sort(Comparator.comparing(AddressData::getAddress));

                if (count == 1 && result.size() == 1) {//as it is only one, prepare it as more detailed
                    try {
                        AddressData selected = result.get(0);
                        if (selected.getLatitude() == null || selected.getLongitude() == null) {
                            getExtendedSuggestionAddressDetails(selected);
                        }
                    } catch (Exception e) {
                        log.warn("Failed to prepare detailed one selected suggestion");
                    }
                }

                return result;
            }
        } finally {
            sw.stop("AddressDaDataProvider", "Suggestion addresses finished");
        }
        return null;
    }

    @Nullable
    @Override
    public List<AddressData> getSuggestionAddressesDetails(double latitude, double longitude, int count) {
        StopWatch sw = new Slf4JStopWatch(log);
        try {
            DaDataAddressGeoCodingRequest request = new DaDataAddressGeoCodingRequest();
            request.setLatitude(latitude);
            request.setLongitude(longitude);

            DaDataAddressSuggestionResponse res = tools.doRequest(ENDPOINT_GEOLOCATE_ADDRESS, HttpMethod.POST, request, DaDataAddressSuggestionResponse.class, true);
            if (res != null && !CollectionUtils.isEmpty(res.getSuggestions())) {
                List<AddressData> result = new ArrayList<>(res.getSuggestions().size() > count ? count : res.getSuggestions().size());
                for (DaDataAddressSuggestionResponse.DaDataAddressSuggestion item : res.getSuggestions()) {
                    if (!StringUtils.isBlank(item.getValue()) && item.getData() != null) {
                        AddressData address = new AddressData();
                        address.setAddress(item.getValue());
                        address.setPostalCode(item.getData().getPostalCode());
                        address.setFiasId(item.getData().getFiasId());
                        address.setFiasCode(item.getData().getFiasCode());
                        address.setKladrId(item.getData().getKladrId());
                        address.setLatitude(parseCoordinateSafely(item.getData().getLatitude()));
                        address.setLongitude(parseCoordinateSafely(item.getData().getLongitude()));
                        address.setSource(item.getData().getSource());
                        address.setFiasLevel(item.getData().getFiasLevel());
                        address.setFiasActualityState(item.getData().getFiasActualityState());
                        address.setQcGeo(item.getData().getQcGeo());
                        address.setQcHouse(item.getData().getQcHouse());
                        address.setQc(item.getData().getQc());
                        address.setUnparsedParts(item.getData().getUnparsedParts());

                        result.add(address);

                        if (result.size() >= count) {
                            break;
                        }
                    }
                }
                return result;
            }
        } finally {
            sw.stop("AddressDaDataProvider", "Suggestion addresses by coordinates finished");
        }
        return null;
    }

    @Nullable
    protected Double parseCoordinateSafely(String text) {
        if (!StringUtils.isBlank(text)) {
            try {
                return Double.valueOf(text.trim().replace(",", "."));
            } catch (Exception ignore) {
            }
        }
        return null;
    }
}
