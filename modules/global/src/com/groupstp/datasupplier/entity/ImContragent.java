package com.groupstp.datasupplier.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.*;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import org.hibernate.validator.constraints.Email;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_contragent")
@Entity(name = "rtneoimport$ImContragent")
public class ImContragent extends StandardEntity implements ImControllableEntity {
    private static final long serialVersionUID = -4384797474295142910L;

    @Column(name = "PAY_LIMIT_DAY")
    protected Integer payLimitDay;

    @Column(name = "ORGANIZATION_TYPE")
    protected Integer organizationType;

    @Column(name = "PERSONAL_ACCOUNT")
    protected String personalAccount;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "SHORT_NAME")
    protected String shortName;

    @Column(name = "CREATION_DATE")
    protected String creationDate;

    @Lob
    @Column(name = "LEGAL_ADDRESS")
    protected String legalAddress;

    @Lob
    @Column(name = "ACTUAL_ADDRESS")
    protected String actualAddress;

    @Lob
    @Column(name = "MAILING_ADDRESS")
    protected String mailingAddress;

    @Column(name = "OKPO", length = 10)
    protected String okpo;

    @Column(name = "OKOPF")
    protected String okopf;

    @Column(name = "INN")
    protected String inn;

    @Column(name = "KPP", length = 9)
    protected String kpp;

    @Column(name = "OGRN", length = 15)
    protected String ogrn;

    @Column(name = "BIK", length = 9)
    protected String bik;

    @Column(name = "CORRESPONDENT_ACCOUNT", length = 20)
    protected String correspondentAccount;

    @Column(name = "BANK_NAME")
    protected String bankName;

    @Column(name = "CHECKING_ACCOUNT", length = 20)
    protected String checkingAccount;

    @Column(name = "HEAD_PERSON_NAME")
    protected String headPersonName;

    @Column(name = "HEAD_PERSON_POST")
    protected String headPersonPost;

    @Lob
    @Column(name = "HEAD_PERSON_REASON_DOC")
    protected String headPersonReasonDoc;

    @Column(name = "HEAD_PERSON_PHONE")
    protected String headPersonPhone;

    @Column(name = "PHONE")
    protected String phone;

    @Column(name = "HEAD_PERSON_FAX")
    protected String headPersonFax;

    @Email
    @Column(name = "HEAD_PERSON_EMAIL")
    protected String headPersonEmail;

    @Column(name = "HEAD_PERSON_INN", length = 12)
    protected String headPersonINN;

    @Column(name = "CONTACT_PERSON_NAME")
    protected String contactPersonName;

    @Column(name = "CONTACT_PERSON_PHONE")
    protected String contactPersonPhone;

    @Column(name = "CONTACT_PERSON_PHONE_VERIFIED")
    protected Boolean contactPersonPhoneVerified;

    @Column(name = "CONTACT_PERSON_FAX")
    protected String contactPersonFax;

    @Email
    @Column(name = "CONTACT_PERSON_EMAIL")
    protected String contactPersonEmail;

    @Column(name = "OKVED")
    protected String okved;

    @Column(name = "AVG_NUMBER")
    protected Integer avgNumber;

    @Column(name = "AVG_NUMBER_FROM_USER")
    protected Integer avgNumberFromUser;

    @Lob
    @Column(name = "ADD_OKVED")
    protected String addOkved;

    @Column(name = "AVG_INCOME")
    protected BigDecimal avgIncome;

    @Column(name = "PASSPORT_SERIES", length = 10)
    protected String passportSeries;

    @Column(name = "PASSPORT_NUMBER", length = 10)
    protected String passportNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "PASSPORT_GIVEN_DATE")
    protected Date passportGivenDate;

    @Lob
    @Column(name = "REGISTRATION_ADDRESS")
    protected String registrationAddress;

    @Column(name = "HAS_WASTE_GENERATION_PROJECT")
    protected Boolean hasWasteGenerationProject;

    @Column(name = "NOT_USE_REDUCTION_FACTOR")
    protected Boolean notUseReductionFactor;

    @Column(name = "NOT_DOING_BUSINESS")
    protected Boolean notDoingBusiness;

    @Column(name = "TECHNICAL_PASSPORT_ALLOWED")
    protected Boolean technicalPassportAllowed = false;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "contragent")
    protected List<ImContragentRealEstate> realEstates;

    @OneToMany(mappedBy = "contragent")
    protected List<ImContragentFile> files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZONE_ID")
    protected ImZone zone;

    @Column(name = "CHECK_PROCESSING")
    protected Boolean checkProcessing;

    @Column(name = "CHECK_COMMENT")
    protected String checkComment;

    @NotNull
    @Column(name = "TYPE_", nullable = false)
    protected Integer type;

    public void setOrganizationType(ImOrganizationType organizationType) {
        this.organizationType = organizationType == null ? null : organizationType.getId();
    }

    public ImOrganizationType getOrganizationType() {
        return organizationType == null ? null : ImOrganizationType.fromId(organizationType);
    }


    public void setType(ImContragentType type) {
        this.type = type == null ? null : type.getId();
    }

    public ImContragentType getType() {
        return ImContragentType.fromId(type);
    }

    public void setPayLimitDay(Integer payLimitDay) {
        this.payLimitDay = payLimitDay;
    }

    public Integer getPayLimitDay() {
        return payLimitDay;
    }

    public void setNotDoingBusiness(Boolean notDoingBusiness) {
        this.notDoingBusiness = notDoingBusiness;
    }

    public Boolean getNotDoingBusiness() {
        return notDoingBusiness;
    }

    public Boolean getTechnicalPassportAllowed() {
        return technicalPassportAllowed;
    }

    public void setTechnicalPassportAllowed(Boolean technicalPassportAllowed) {
        this.technicalPassportAllowed = technicalPassportAllowed;
    }

    public void setCheckProcessing(Boolean checkProcessing) {
        this.checkProcessing = checkProcessing;
    }

    public Boolean getCheckProcessing() {
        return checkProcessing;
    }

    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment;
    }

    public String getCheckComment() {
        return checkComment;
    }


    public void setOkopf(String okopf) {
        this.okopf = okopf;
    }

    public String getOkopf() {
        return okopf;
    }

    public void setContactPersonPhoneVerified(Boolean contactPersonPhoneVerified) {
        this.contactPersonPhoneVerified = contactPersonPhoneVerified;
    }

    public Boolean getContactPersonPhoneVerified() {
        return contactPersonPhoneVerified;
    }

    public void setNotUseReductionFactor(Boolean notUseReductionFactor) {
        this.notUseReductionFactor = notUseReductionFactor;
    }

    public Boolean getNotUseReductionFactor() {
        return notUseReductionFactor;
    }


    public void setZone(ImZone zone) {
        this.zone = zone;
    }

    public ImZone getZone() {
        return zone;
    }


    public void setAvgNumberFromUser(Integer avgNumberFromUser) {
        this.avgNumberFromUser = avgNumberFromUser;
    }

    public Integer getAvgNumberFromUser() {
        return avgNumberFromUser;
    }

    public void setHasWasteGenerationProject(Boolean hasWasteGenerationProject) {
        this.hasWasteGenerationProject = hasWasteGenerationProject;
    }

    public Boolean getHasWasteGenerationProject() {
        return hasWasteGenerationProject;
    }


    public void setFiles(List<ImContragentFile> files) {
        this.files = files;
    }

    public List<ImContragentFile> getFiles() {
        return files;
    }


    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setHeadPersonINN(String headPersonINN) {
        this.headPersonINN = headPersonINN;
    }

    public String getHeadPersonINN() {
        return headPersonINN;
    }

    public void setAddOkved(String addOkved) {
        this.addOkved = addOkved;
    }

    public String getAddOkved() {
        return addOkved;
    }

    public String getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(String personalAccount) {
        this.personalAccount = personalAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo;
    }

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

    public String getBik() {
        return bik;
    }

    public void setBik(String bik) {
        this.bik = bik;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(String checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public String getHeadPersonName() {
        return headPersonName;
    }

    public void setHeadPersonName(String headPersonName) {
        this.headPersonName = headPersonName;
    }

    public String getHeadPersonPost() {
        return headPersonPost;
    }

    public void setHeadPersonPost(String headPersonPost) {
        this.headPersonPost = headPersonPost;
    }

    public String getHeadPersonReasonDoc() {
        return headPersonReasonDoc;
    }

    public void setHeadPersonReasonDoc(String headPersonReasonDoc) {
        this.headPersonReasonDoc = headPersonReasonDoc;
    }

    public String getHeadPersonPhone() {
        return headPersonPhone;
    }

    public void setHeadPersonPhone(String headPersonPhone) {
        this.headPersonPhone = headPersonPhone;
    }

    public String getHeadPersonFax() {
        return headPersonFax;
    }

    public void setHeadPersonFax(String headPersonFax) {
        this.headPersonFax = headPersonFax;
    }

    public String getHeadPersonEmail() {
        return headPersonEmail;
    }

    public void setHeadPersonEmail(String headPersonEmail) {
        this.headPersonEmail = headPersonEmail;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getContactPersonFax() {
        return contactPersonFax;
    }

    public void setContactPersonFax(String contactPersonFax) {
        this.contactPersonFax = contactPersonFax;
    }

    public String getContactPersonEmail() {
        return contactPersonEmail;
    }

    public void setContactPersonEmail(String contactPersonEmail) {
        this.contactPersonEmail = contactPersonEmail;
    }

    public String getOkved() {
        return okved;
    }

    public void setOkved(String okved) {
        this.okved = okved;
    }

    public Integer getAvgNumber() {
        return avgNumber;
    }

    public void setAvgNumber(Integer avgNumber) {
        this.avgNumber = avgNumber;
    }

    public BigDecimal getAvgIncome() {
        return avgIncome;
    }

    public void setAvgIncome(BigDecimal avgIncome) {
        this.avgIncome = avgIncome;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Date getPassportGivenDate() {
        return passportGivenDate;
    }

    public void setPassportGivenDate(Date passportGivenDate) {
        this.passportGivenDate = passportGivenDate;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public List<ImContragentRealEstate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(List<ImContragentRealEstate> realEstates) {
        this.realEstates = realEstates;
    }

}