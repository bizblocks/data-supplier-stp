package com.groupstp.datasupplier.web.gui.xml.layout.loaders;

import com.groupstp.datasupplier.web.gui.components.AutocompleteTextField;
import com.haulmont.cuba.gui.xml.layout.loaders.TextFieldLoader;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

/**
 * Web autocomplete text field loader
 *
 * @author adiatullin
 */
public class AutocompleteTextFieldLoader extends TextFieldLoader {

    @Override
    public void createComponent() {
        resultComponent = factory.createComponent(AutocompleteTextField.class);
        loadId(resultComponent, element);

    }

    @Override
    public void loadComponent() {
        super.loadComponent();

        loadMinSearchStringLength((AutocompleteTextField) resultComponent, element);
        loadSuggestionsLimit((AutocompleteTextField) resultComponent, element);
        loadAsyncSearchDelayMs((AutocompleteTextField) resultComponent, element);
    }

    protected void loadMinSearchStringLength(AutocompleteTextField component, Element element) {
        String value = element.attributeValue("minSearchStringLength");
        if (StringUtils.isNotEmpty(value)) {
            component.setMinSearchStringLength(Integer.parseInt(value));
        }
    }

    protected void loadSuggestionsLimit(AutocompleteTextField component, Element element) {
        String value = element.attributeValue("suggestionsLimit");
        if (StringUtils.isNotEmpty(value)) {
            component.setSuggestionsLimit(Integer.parseInt(value));
        }
    }

    protected void loadAsyncSearchDelayMs(AutocompleteTextField component, Element element) {
        String value = element.attributeValue("asyncSearchDelayMs");
        if (StringUtils.isNotEmpty(value)) {
            component.setAsyncSearchDelayMs(Integer.parseInt(value));
        }
    }
}
