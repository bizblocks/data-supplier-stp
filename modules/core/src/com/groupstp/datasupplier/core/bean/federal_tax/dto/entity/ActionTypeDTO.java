package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * FNS system contragent action type representation
 *
 * @author adiatullin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionTypeDTO implements Serializable {
    private static final long serialVersionUID = -4426924556229841619L;

    @JsonProperty("Код")
    private String code;
    @JsonProperty("Текст")
    private String description;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }
}
