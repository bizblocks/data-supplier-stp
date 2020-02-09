package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * FNS system head person representation
 *
 * @author adiatullin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeadPersonDTO implements Serializable {
    private static final long serialVersionUID = -6183947375431871089L;

    @JsonProperty("ВидДолжн")
    private String positionType;
    @JsonProperty("Должн")
    private String position;
    @JsonProperty("ФИОПолн")
    private String fio;
    @JsonProperty("ИННФЛ")
    private String taxNumber;
    @JsonProperty("Дата")
    private Date date;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();


    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
