package com.groupstp.datasupplier.core.bean.datasupplier.provider.bank.dadata.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groupstp.datasupplier.core.bean.datasupplier.provider.address.dadata.dto.DaDataAddress;

import java.io.Serializable;

/**
 * DaData bank provider receiving data transfer object
 *
 * @author adiatullin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaDataBank implements Serializable {
    private static final long serialVersionUID = -6128225188786226823L;

    @JsonProperty("name")
    private DaDataBankName name;
    @JsonProperty("bic")
    private String bic;
    @JsonProperty("swift")
    private String swift;
    @JsonProperty("okpo")
    private String okpo;
    @JsonProperty("correspondent_account")
    private String correspondentAccount;
    @JsonProperty("registration_number")
    private String registrationNumber;
    @JsonProperty("address")
    private DaDataBankAddress address;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("state")
    private DaDataBankState state;


    public DaDataBankName getName() {
        return name;
    }

    public void setName(DaDataBankName name) {
        this.name = name;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public DaDataBankAddress getAddress() {
        return address;
    }

    public void setAddress(DaDataBankAddress address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DaDataBankState getState() {
        return state;
    }

    public void setState(DaDataBankState state) {
        this.state = state;
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DaDataBankName implements Serializable {
        private static final long serialVersionUID = 2100827644238954359L;

        @JsonProperty("payment")
        private String paymentName;
        @JsonProperty("full")
        private String fullName;
        @JsonProperty("short")
        private String shortName;


        public String getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(String paymentName) {
            this.paymentName = paymentName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DaDataBankAddress implements Serializable {
        private static final long serialVersionUID = 2100827644238954359L;

        @JsonProperty("value")
        private String value;
        @JsonProperty("unrestricted_value")
        private String unrestrictedValue;
        @JsonProperty("source")
        private String source;
        @JsonProperty("data")
        private DaDataAddress data;


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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public DaDataAddress getData() {
            return data;
        }

        public void setData(DaDataAddress data) {
            this.data = data;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DaDataBankState implements Serializable {
        private static final long serialVersionUID = 7201795006478475918L;

        @JsonProperty("status")
        private String status;
        @JsonProperty("actuality_date")
        private Long actualityDate;
        @JsonProperty("registration_date")
        private Long registrationDate;
        @JsonProperty("liquidation_date")
        private Long liquidationDate;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Long getActualityDate() {
            return actualityDate;
        }

        public void setActualityDate(Long actualityDate) {
            this.actualityDate = actualityDate;
        }

        public Long getRegistrationDate() {
            return registrationDate;
        }

        public void setRegistrationDate(Long registrationDate) {
            this.registrationDate = registrationDate;
        }

        public Long getLiquidationDate() {
            return liquidationDate;
        }

        public void setLiquidationDate(Long liquidationDate) {
            this.liquidationDate = liquidationDate;
        }
    }
}
