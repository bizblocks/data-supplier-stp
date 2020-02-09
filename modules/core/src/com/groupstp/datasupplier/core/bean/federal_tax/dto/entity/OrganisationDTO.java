package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FNS system organisation contragent representation
 *
 * @author adiatullin
 */
@JsonRootName("ЮЛ")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganisationDTO implements FnsContragent, Serializable {
    private static final long serialVersionUID = 8124791455307076572L;

    @JsonProperty("ИНН")
    private String inn;
    @JsonProperty("КПП")
    private String kpp;
    @JsonProperty("ОГРН")
    private String ogrn;
    @JsonProperty("ДатаРег")
    private Date regDate;
    @JsonProperty("ОКОПФ")
    private String okopf;
    @JsonProperty("Статус")
    private String status;
    @JsonProperty("Руководитель")
    private HeadPersonDTO headPerson;
    @JsonProperty("НаимСокрЮЛ")
    private String shortName;
    @JsonProperty("НаимПолнЮЛ")
    private String fullName;
    @JsonProperty("Адрес")
    private AddressDTO address;
    @JsonProperty("НомТел")
    private String phoneNumber;
    @JsonProperty("E-mail")
    private String email;
    @JsonProperty("ОснВидДеят")
    private ActionTypeDTO mainActionType;
    @JsonProperty("ДопВидДеят")
    private List<ActionTypeDTO> additionalActionType;
    @JsonProperty("ОткрСведения")
    private OpenDataDTO openData;

    @JsonIgnore
    private Map<String, Object> properties = new HashMap<>();

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public String getOkopf() {
        return okopf;
    }

    public void setOkopf(String okopf) {
        this.okopf = okopf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HeadPersonDTO getHeadPerson() {
        return headPerson;
    }

    public void setHeadPerson(HeadPersonDTO headPerson) {
        this.headPerson = headPerson;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public OpenDataDTO getOpenData() {
        return openData;
    }

    public void setOpenData(OpenDataDTO openData) {
        this.openData = openData;
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
