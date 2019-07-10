package com.groupstp.datasupplier.web.util;

import com.groupstp.datasupplier.data.AddressData;
import com.groupstp.datasupplier.data.BankData;
import com.groupstp.datasupplier.service.AddressDataSupplierService;
import com.groupstp.datasupplier.service.BankDataSupplierService;
import com.groupstp.datasupplier.web.config.DataSupplierWebConfig;
import com.groupstp.datasupplier.web.gui.components.AutocompleteTextField;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.SuggestionField;
import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nullable;
import java.util.*;

/**
 * Banks data providing UI helper class
 *
 * @author adiatullin
 */
public final class BankUiHelper {
    private BankUiHelper() {
    }

    /**
     * Bank select listener
     */
    public interface BankSelectListener {
        /**
         * User clicked the bank suggestion
         *
         * @param field    field object which are communicated with user
         * @param value    user selected current bank
         * @param previous previous bank value
         */
        void onBankSelect(Field field, @Nullable BankData value, @Nullable BankData previous);
    }

    /**
     * Easy way to receive a bank name
     *
     * @param nameBicSwiftOrAddress bank identification information
     * @return officinal bank name
     */
    @Nullable
    public static String getBankName(String nameBicSwiftOrAddress) {
        BankData data = ((BankDataSupplierService) AppBeans.get(BankDataSupplierService.NAME)).getSuggestionBankDetails(nameBicSwiftOrAddress);
        if (data != null) {
            return data.getName();
        }
        return null;
    }

    /**
     * Setup bank suggestion field
     *
     * @param field UI suggestion field
     */
    public static void showBankSuggestions(SuggestionField field) {
        showBankSuggestions(field, null);
    }

    /**
     * Setup bank suggestion field with listener
     *
     * @param field    UI suggestion field
     * @param listener bank select listener
     */
    public static void showBankSuggestions(SuggestionField field, @Nullable BankSelectListener listener) {
        DataSupplierWebConfig config = ((Configuration) AppBeans.get(Configuration.NAME)).getConfig(DataSupplierWebConfig.class);

        showBankSuggestions(field, config.getDelayMs(), config.getMinSearchLength(), config.getFetchLimit(), listener);
    }


    /**
     * Setup bank suggestion field
     *
     * @param field           UI suggestion field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     */
    public static void showBankSuggestions(SuggestionField field, int delayMs, int minSearchLength, int count) {
        showBankSuggestions(field, delayMs, minSearchLength, count, null);
    }

    /**
     * Setup bank suggestion field with listener
     *
     * @param field           UI suggestion field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     * @param listener        bank select listener
     */
    public static void showBankSuggestions(SuggestionField field, int delayMs, int minSearchLength, int count, @Nullable BankSelectListener listener) {
        BankDataSupplierService service = AppBeans.get(BankDataSupplierService.NAME);

        field.setAsyncSearchDelayMs(delayMs);
        field.setMinSearchStringLength(minSearchLength);
        field.setSuggestionsLimit(count);

        Map<String, BankData> cache = new HashMap<>();
        field.setSearchExecutor((search, params) -> {
            cache.clear();

            List<String> result = Collections.emptyList();
            List<BankData> items = service.getSuggestionsBanksDetails(search, field.getSuggestionsLimit());
            if (!CollectionUtils.isEmpty(items)) {
                result = new ArrayList<>(items.size());
                for (BankData item : items) {
                    cache.put(item.getName(), item);

                    result.add(item.getName());
                }
            }
            return result;
        });
        if (listener != null) {
            BankData[] previousDataHolder = new BankData[1];
            field.addValueChangeListener(e -> {
                String address = field.getValue();

                AddressData current = service.getExtendedSuggestionAddressDetails(cache.get(address));
                AddressData previous = previousDataHolder[0];
                previousDataHolder[0] = current;

                listener.onBankSelect(field, current, previous);
            });
        }
    }

    /**
     * Setup address suggestion field
     *
     * @param field UI autocomplete field
     */
    public static void showAddressSuggestions(AutocompleteTextField field) {
        showAddressSuggestions(field, (AddressUiHelper.AddressSelectListener) null);
    }

    /**
     * Setup address suggestion field with select listener
     *
     * @param field    UI autocomplete field
     * @param listener address select listener
     */
    public static void showAddressSuggestions(AutocompleteTextField field, @Nullable AddressUiHelper.AddressSelectListener listener) {
        DataSupplierWebConfig config = ((Configuration) AppBeans.get(Configuration.NAME)).getConfig(DataSupplierWebConfig.class);

        showAddressSuggestions(field, config.getDelayMs(), config.getMinSearchLength(), config.getFetchLimit(), listener);
    }


    /**
     * Setup address suggestion field
     *
     * @param field           UI autocomplete field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     */
    public static void showAddressSuggestions(AutocompleteTextField field, int delayMs, int minSearchLength, int count) {
        showAddressSuggestions(field, delayMs, minSearchLength, count, null);
    }

    /**
     * Setup address suggestion field
     *
     * @param field           UI autocomplete field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     * @param listener        address select listener
     */
    public static void showAddressSuggestions(AutocompleteTextField field, int delayMs, int minSearchLength, int count, @Nullable AddressUiHelper.AddressSelectListener listener) {
        AddressDataSupplierService service = AppBeans.get(AddressDataSupplierService.NAME);

        field.setAsyncSearchDelayMs(delayMs);
        field.setMinSearchStringLength(minSearchLength);
        field.setSuggestionsLimit(count);

        Map<String, AddressData> cache = new HashMap<>();
        field.setSuggestionProvider((currentValue, limit) -> {
            cache.clear();

            List<String> result = Collections.emptyList();
            List<AddressData> items = service.getSuggestionAddressesDetails(currentValue, limit);
            if (!CollectionUtils.isEmpty(items)) {
                result = new ArrayList<>(items.size());
                for (AddressData item : items) {
                    cache.put(item.getAddress(), item);

                    result.add(item.getAddress());
                }
            }
            return result;
        });
        if (listener != null) {
            AddressData[] previousDataHolder = new AddressData[1];
            field.addValueChangeListener(e -> {
                String address = field.getValue();

                AddressData current = service.getExtendedSuggestionAddressDetails(cache.get(address));
                AddressData previous = previousDataHolder[0];
                previousDataHolder[0] = current;

                listener.onAddressSelect(field, current, previous);
            });
        }
    }
}
