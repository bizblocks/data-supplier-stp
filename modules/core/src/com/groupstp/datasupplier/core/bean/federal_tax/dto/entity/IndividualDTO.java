package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FNS system personal contragent representation
 *
 * @author adiatullin
 */
@JsonRootName("ИП")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndividualDTO implements FnsContragent, Serializable {
    private static final long serialVersionUID = 2777104517269331188L;

    @JsonProperty("ФИОПолн")
    private String fio;
    @JsonProperty("ИННФЛ")
    private String inn;
    @JsonProperty("ОГРНИП")
    private String ogrn;
    @JsonProperty("ДатаРег")
    private Date regDate;
    @JsonProperty("ВидИП")
    private String type;
    @JsonProperty("Пол")
    private String gender;
    @JsonProperty("ВидГражд")
    private String nationality;
    @JsonProperty("Адрес")
    private AddressDTO address;
    @JsonProperty("E-mail")
    private String email;
    @JsonProperty("Статус")
    private String status;
    @JsonProperty("ОснВидДеят")
    private ActionTypeDTO mainActionType;
    @JsonProperty("ДопВидДеят")
    private List<ActionTypeDTO> additionalActionType;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ActionTypeDTO getMainActionType() {
        return mainActionType;
    }

    public void setMainActionType(ActionTypeDTO mainActionType) {
        this.mainActionType = mainActionType;
    }

    public List<ActionTypeDTO> getAdditionalActionType() {
        return additionalActionType;
    }

    public void setAdditionalActionType(List<ActionTypeDTO> additionalActionType) {
        this.additionalActionType = additionalActionType;
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
