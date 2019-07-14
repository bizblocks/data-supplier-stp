package com.groupstp.datasupplier.core.bean.datasupplier.provider.bank;

import com.groupstp.datasupplier.data.BankData;
import org.springframework.core.Ordered;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Bank details data provider delegate
 *
 * @author adiatullin
 */
public interface BankDataProviderDelegate extends Ordered {

    /**
     * Get the suggestion list of suitable banks
     *
     * @param nameBicSwiftOrAddress expected banks name, bic, swift code or address
     * @param count                 number of expected results
     * @return found banks details
     */
    @Nullable
    List<BankData> getSuggestionsBanksDetails(String nameBicSwiftOrAddress, int count);
}
