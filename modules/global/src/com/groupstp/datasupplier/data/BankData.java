package com.groupstp.datasupplier.data;

import java.io.Serializable;

/**
 * Bank data representation class
 *
 * @author adiatullin
 */
public class BankData implements Serializable {
    private static final long serialVersionUID = 8226655194413280377L;

    private String address;
    private String fullAddress;
    private String officialAddress;
    private String bic;
    private String swift;
    private String registrationNumber;
    private String correspondentAccount;
    private String okpo;
    private String name;
    private String fullName;
    private String phone;
    private BankStatus status;

    public enum BankStatus {
        ACTIVE, LIQUIDATING, INACTIVE
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getOfficialAddress() {
        return officialAddress;
    }

    public void setOfficialAddress(String officialAddress) {
        this.officialAddress = officialAddress;
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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BankStatus getStatus() {
        return status;
    }

    public void setStatus(BankStatus status) {
        this.status = status;
    }
}
