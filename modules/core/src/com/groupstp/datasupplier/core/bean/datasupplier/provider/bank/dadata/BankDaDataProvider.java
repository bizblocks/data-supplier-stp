package com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata;

import com.groupstp.datasupplier.core.bean.dadata.DaDataTools;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.BankDataProviderDelegate;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.dto.DaDataBank;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.dto.DaDataBankRequest;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.dto.DaDataBankResponse;
import com.groupstp.datasupplier.data.BankData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * DaData banks data provider service bean
 *
 * @author adiatullin
 */
@Component("dsstp_BankDaDataProvider")
public class BankDaDataProvider implements BankDataProviderDelegate {
    private static final Logger log = LoggerFactory.getLogger(BankDaDataProvider.class);

    protected static final String ENDPOINT_SUGGEST_BANK = "/suggest/bank";

    @Inject
    private DaDataTools tools;

    @Override
    public int getOrder() {
        return 10;
    }

    @Override
    public List<BankData> getSuggestionsBanksDetails(String nameBicSwiftOrAddress, int count) {
        StopWatch sw = new Slf4JStopWatch(log);
        try {
            DaDataBankRequest request = new DaDataBankRequest();
            request.setQuery(nameBicSwiftOrAddress);
            request.setCount(count < 1 ? 10 : count);

            DaDataBankResponse res = tools.doRequest(ENDPOINT_SUGGEST_BANK, HttpMethod.POST, request, DaDataBankResponse.class, true);
            if (res != null && !CollectionUtils.isEmpty(res.getSuggestions())) {
                List<BankData> result = new ArrayList<>(res.getSuggestions().size());
                for (DaDataBankResponse.DaDataBankSuggestion item : res.getSuggestions()) {
                    if (!StringUtils.isBlank(item.getValue()) && item.getData() != null) {
                        DaDataBank data = item.getData();

                        BankData bank = new BankData();
                        bank.setAddress(data.getAddress() == null ? null : data.getAddress().getValue());
                        bank.setFullAddress(data.getAddress() == null ? null : data.getAddress().getUnrestrictedValue());
                        bank.setOfficialAddress(data.getAddress() == null ? null : data.getAddress().getSource());
                        bank.setBic(data.getBic());
                        bank.setSwift(data.getSwift());
                        bank.setRegistrationNumber(data.getRegistrationNumber());
                        bank.setCorrespondentAccount(data.getCorrespondentAccount());
                        bank.setOkpo(data.getOkpo());
                        bank.setName(item.getValue());
                        bank.setFullName(data.getName() == null ? null : data.getName().getFullName());
                        bank.setPhone(data.getPhone());
                        bank.setStatus(data.getState() == null ? null : parseStatus(data.getState().getStatus()));

                        result.add(bank);
                    }
                }
                result.sort(Comparator.comparing(BankData::getName));

                return result;
            }
        } finally {
            sw.stop("BankDaDataProvider", "Suggestion banks finished");
        }
        return null;
    }

    @Nullable
    private BankData.BankStatus parseStatus(String status) {
        if (!StringUtils.isEmpty(status)) {
            status = status.replaceAll("\\s", StringUtils.EMPTY).toUpperCase();
            switch (status) {
                case "ACTIVE": {
                    return BankData.BankStatus.ACTIVE;
                }
                case "LIQUIDATING": {
                    return BankData.BankStatus.LIQUIDATING;
                }
                case "LIQUIDATED": {
                    return BankData.BankStatus.INACTIVE;
                }
            }
        }
        return null;
    }
}
