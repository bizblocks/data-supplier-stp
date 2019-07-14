package com.groupstp.datasupplier.service;

import com.groupstp.datasupplier.data.BankData;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Bank data supplier service
 *
 * @author adiatullin
 */
public interface BankDataSupplierService {
    String NAME = "dsstp_BankDataSupplierService";

    /**
     * Get the suggestion list of suitable banks name
     *
     * @param nameBicSwiftOrAddress expected banks name, bic, swift code or address
     * @param count                 number of expected results
     * @return found banks names
     */
    List<String> getSuggestionBanks(String nameBicSwiftOrAddress, int count);

    /**
     * Get the suggestion list of suitable banks
     *
     * @param nameBicSwiftOrAddress expected banks name, bic, swift code or address
     * @param count                 number of expected results
     * @return found banks details
     */
    List<BankData> getSuggestionsBanksDetails(String nameBicSwiftOrAddress, int count);

    /**
     * Get more suitable bank data
     *
     * @param nameBicSwiftOrAddress expected bank name, bic, swift code or address
     * @return found bank data or null if nothing found
     */
    @Nullable
    BankData getSuggestionBankDetails(String nameBicSwiftOrAddress);
}
