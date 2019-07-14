package com.groupstp.datasupplier.web.util;

import com.groupstp.datasupplier.data.BankData;
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
     * @return official bank name
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
                BankData current = cache.get(field.getValue());
                BankData previous = previousDataHolder[0];

                previousDataHolder[0] = current;

                listener.onBankSelect(field, current, previous);
            });
        }
    }

    /**
     * Setup bank suggestion field
     *
     * @param field UI autocomplete field
     */
    public static void showBankSuggestions(AutocompleteTextField field) {
        showBankSuggestions(field, null);
    }

    /**
     * Setup bank suggestion field with select listener
     *
     * @param field    UI autocomplete field
     * @param listener bank select listener
     */
    public static void showBankSuggestions(AutocompleteTextField field, @Nullable BankSelectListener listener) {
        DataSupplierWebConfig config = ((Configuration) AppBeans.get(Configuration.NAME)).getConfig(DataSupplierWebConfig.class);

        showBankSuggestions(field, config.getDelayMs(), config.getMinSearchLength(), config.getFetchLimit(), listener);
    }


    /**
     * Setup bank suggestion field
     *
     * @param field           UI autocomplete field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     */
    public static void showBankSuggestions(AutocompleteTextField field, int delayMs, int minSearchLength, int count) {
        showBankSuggestions(field, delayMs, minSearchLength, count, null);
    }

    /**
     * Setup bank suggestion field
     *
     * @param field           UI autocomplete field
     * @param delayMs         delay in ms between user entering data and searching suggestions
     * @param minSearchLength minimum search length when suggestions should appear
     * @param count           maximum count of suggestions
     * @param listener        bank select listener
     */
    public static void showBankSuggestions(AutocompleteTextField field, int delayMs, int minSearchLength, int count, @Nullable BankSelectListener listener) {
        BankDataSupplierService service = AppBeans.get(BankDataSupplierService.NAME);

        field.setAsyncSearchDelayMs(delayMs);
        field.setMinSearchStringLength(minSearchLength);
        field.setSuggestionsLimit(count);

        Map<String, BankData> cache = new HashMap<>();
        field.setSuggestionProvider((currentValue, limit) -> {
            cache.clear();

            List<String> result = Collections.emptyList();
            List<BankData> items = service.getSuggestionsBanksDetails(currentValue, limit);
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
                BankData current = cache.get(field.getValue());
                BankData previous = previousDataHolder[0];

                previousDataHolder[0] = current;

                listener.onBankSelect(field, current, previous);
            });
        }
    }
}
