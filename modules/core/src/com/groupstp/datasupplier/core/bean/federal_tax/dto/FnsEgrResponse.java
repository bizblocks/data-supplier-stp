package com.groupstp.datasupplier.core.bean.federal_tax.dto;

import com.fasterxml.jackson.annotation.*;
import com.groupstp.datasupplier.core.bean.federal_tax.dto.entity.FnsContragent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FNS system EGR(UL/IP) call response
 *
 * @author adiatullin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FnsEgrResponse implements Serializable {
    private static final long serialVersionUID = -2846858694913143681L;

    @JsonProperty("items")
    private List<FnsContragent> items;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();


    public List<FnsContragent> getItems() {
        return items;
    }

    public void setItems(List<FnsContragent> items) {
        this.items = items;
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
