package com.groupstp.datasupplier.service;

import com.groupstp.datasupplier.core.bean.DataSupplierWorker;
import com.groupstp.datasupplier.data.BankData;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;

/**
 * Basic bank data supplier service
 *
 * @author adiatullin
 */
@Service(BankDataSupplierService.NAME)
public class BankDataSupplierServiceBean implements BankDataSupplierService {

    @Inject
    protected DataSupplierWorker worker;

    @Override
    public List<String> getSuggestionBanks(String nameBicSwiftOrAddress, int count) {
        return worker.getSuggestionBanks(nameBicSwiftOrAddress, count);
    }

    @Override
    public List<BankData> getSuggestionsBanksDetails(String nameBicSwiftOrAddress, int count) {
        return worker.getSuggestionsBanksDetails(nameBicSwiftOrAddress, count);
    }

    @Nullable
    @Override
    public BankData getSuggestionBankDetails(String nameBicSwiftOrAddress) {
        List<BankData> data = worker.getSuggestionsBanksDetails(nameBicSwiftOrAddress, 1);
        if (!CollectionUtils.isEmpty(data)) {
            return data.get(0);
        }
        return null;
    }
}
