package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * FNS system open data representation
 *
 * @author adiatullin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpenDataDTO implements Serializable {
    private static final long serialVersionUID = -4726005834914714887L;

    @JsonProperty("КолРаб")
    private String employeeCount;
    @JsonProperty("СведСНР")
    private String taxMode;
    @JsonProperty("СумДоход")
    private String income;
    @JsonProperty("СумРасход")
    private String outcome;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();


    public String getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(String employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getTaxMode() {
        return taxMode;
    }

    public void setTaxMode(String taxMode) {
        this.taxMode = taxMode;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
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
