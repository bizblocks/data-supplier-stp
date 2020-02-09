package com.groupstp.datasupplier.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import com.groupstp.datasupplier.entity.ImContragent;
import com.groupstp.datasupplier.entity.ImControllableEntity;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.*;

import java.math.BigDecimal;
import java.util.Date;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "rtneoimport_im_contragent_real_estate")
@Entity(name = "rtneoimport$ImContragentRealEstate")
public class ImContragentRealEstate extends StandardEntity implements ImControllableEntity {
    private static final long serialVersionUID = -8481324577527453048L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRAGENT_ID")
    protected ImContragent contragent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REAL_ESTATE_ID")
    protected ImRealEstate realEstate;

    @Column(name = "SHARE_")
    protected BigDecimal share;

    @Column(name = "COUNT_OF_APARTMENTS")
    protected Integer countOfApartments;

    @Column(name = "COUNT_OF_RESIDENTS")
    protected Integer countOfResidents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID")
    protected ImRealEstateType type;

    @Column(name = "CALCULATION_AMOUNT")
    protected BigDecimal calculationAmount;

    @Column(name = "ENCUMBRANCE")
    protected Boolean encumbrance;

    @Column(name = "LIVING_AREA")
    protected BigDecimal livingArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    protected ImRealEstateCategory category;

    @Column(name = "IN_RENT")
    protected Boolean inRent;

    @Column(name = "REVOTE")
    protected Boolean revote;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_REVOTE_ACCEPTION")
    protected Date dateRevoteAcception;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LANDLORD_ID")
    protected ImContragent landlord;

    @Column(name = "AREA_IN_RENT")
    protected BigDecimal areaInRent;

    @Column(name = "PARTICULAR_AREA")
    protected BigDecimal particularArea;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENT_CONTRACT_ID")
    protected ImContragentFile rentContract;

    @Column(name = "CHECK_PROCESSING")
    protected Boolean checkProcessing;

    @Column(name = "CHECK_COMMENT")
    protected String checkComment;

    @Column(name = "INTRODUCED_WITH_TECH_PASSPORT")
    protected Boolean introducedWithTechPassport = false;

    @Temporal(TemporalType.DATE)
    @Column(name = "VALIDITY_FROM")
    private Date validityFrom;

    @Temporal(TemporalType.DATE)
    @Column(name = "VALIDITY_TO")
    private Date validityTo;

    @Column(name = "OWN_TYPE")
    protected Integer ownType;

    @Column(name = "CONTROL_METHOD")
    protected Integer controlMethod;

    public void setControlMethod(ImControlMethod controlMethod) {
        this.controlMethod = controlMethod == null ? null : controlMethod.getId();
    }

    public ImControlMethod getControlMethod() {
        return controlMethod == null ? null : ImControlMethod.fromId(controlMethod);
    }

    public void setOwnType(ImTypeOwnership ownType) {
        this.ownType = ownType == null ? null : ownType.getId();
    }

    public ImTypeOwnership getOwnType() {
        return ownType == null ? null : ImTypeOwnership.fromId(ownType);
    }

    public void setLandlord(ImContragent landlord) {
        this.landlord = landlord;
    }

    public ImContragent getLandlord() {
        return landlord;
    }

    public void setAreaInRent(BigDecimal areaInRent) {
        this.areaInRent = areaInRent;
    }

    public BigDecimal getAreaInRent() {
        return areaInRent;
    }

    public BigDecimal getParticularArea() {
        return particularArea;
    }

    public void setParticularArea(BigDecimal particularArea) {
        this.particularArea = particularArea;
    }

    public void setRentContract(ImContragentFile rentContract) {
        this.rentContract = rentContract;
    }

    public ImContragentFile getRentContract() {
        return rentContract;
    }

    @Override
    public Boolean getCheckProcessing() {
        return checkProcessing;
    }

    @Override
    public void setCheckProcessing(Boolean checkProcessing) {
        this.checkProcessing = checkProcessing;
    }

    public void setCheckComment(String checkComment) {
        this.checkComment = checkComment;
    }

    public String getCheckComment() {
        return checkComment;
    }

    public void setDateRevoteAcception(Date dateRevoteAcception) {
        this.dateRevoteAcception = dateRevoteAcception;
    }

    public Date getDateRevoteAcception() {
        return dateRevoteAcception;
    }

    public void setRevote(Boolean revote) {
        this.revote = revote;
    }

    public Boolean getRevote() {
        return revote;
    }

    public void setCountOfResidents(Integer countOfResidents) {
        this.countOfResidents = countOfResidents;
    }

    public Integer getCountOfResidents() {
        return countOfResidents;
    }

    public void setCountOfApartments(Integer countOfApartments) {
        this.countOfApartments = countOfApartments;
    }

    public Integer getCountOfApartments() {
        return countOfApartments;
    }

    public BigDecimal getLivingArea() {
        return livingArea;
    }

    public void setLivingArea(BigDecimal livingArea) {
        this.livingArea = livingArea;
    }


    public BigDecimal getCalculationAmount() {
        return calculationAmount;
    }

    public void setCalculationAmount(BigDecimal calculationAmount) {
        this.calculationAmount = calculationAmount;
    }

    public BigDecimal getShare() {
        return share;
    }

    public void setShare(BigDecimal share) {
        this.share = share;
    }

    public void setType(ImRealEstateType type) {
        this.type = type;
    }

    public ImRealEstateType getType() {
        return type;
    }

    public void setEncumbrance(Boolean encumbrance) {
        this.encumbrance = encumbrance;
    }

    public Boolean getEncumbrance() {
        return encumbrance;
    }

    public void setCategory(ImRealEstateCategory category) {
        this.category = category;
    }

    public ImRealEstateCategory getCategory() {
        return category;
    }

    public void setInRent(Boolean inRent) {
        this.inRent = inRent;
    }

    public Boolean getInRent() {
        return inRent;
    }


    public void setRealEstate(ImRealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public ImRealEstate getRealEstate() {
        return realEstate;
    }


    public void setContragent(ImContragent contragent) {
        this.contragent = contragent;
    }

    public ImContragent getContragent() {
        return contragent;
    }

    public Boolean getIntroducedWithTechPassport() {
        return introducedWithTechPassport;
    }

    public void setIntroducedWithTechPassport(Boolean introducedWithTechPassport) {
        this.introducedWithTechPassport = introducedWithTechPassport;
    }

    public Date getValidityFrom() {
        return validityFrom;
    }

    public void setValidityFrom(Date validityFrom) {
        this.validityFrom = validityFrom;
    }

    public Date getValidityTo() {
        return validityTo;
    }

    public void setValidityTo(Date validityTo) {
        this.validityTo = validityTo;
    }

    @MetaProperty
    public BigDecimal getPaymentSum() {
        return BigDecimal.ZERO;
    }


}