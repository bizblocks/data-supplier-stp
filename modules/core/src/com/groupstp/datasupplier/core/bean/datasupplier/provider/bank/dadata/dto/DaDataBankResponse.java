package com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * DaData bank provider suggestion response
 *
 * @author adiatullin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaDataBankResponse implements Serializable {
    private static final long serialVersionUID = 936658226067596406L;

    @JsonProperty("suggestions")
    private List<DaDataBankSuggestion> suggestions;


    public List<DaDataBankSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<DaDataBankSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DaDataBankSuggestion implements Serializable {
        private static final long serialVersionUID = 44255843570252991L;

        @JsonProperty("value")
        private String value;
        @JsonProperty("unrestricted_value")
        private String unrestrictedValue;
        @JsonProperty("data")
        private DaDataBank data;


        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUnrestrictedValue() {
            return unrestrictedValue;
        }

        public void setUnrestrictedValue(String unrestrictedValue) {
            this.unrestrictedValue = unrestrictedValue;
        }

        public DaDataBank getData() {
            return data;
        }

        public void setData(DaDataBank data) {
            this.data = data;
        }
    }
}
