package com.groupstp.datasupplier.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.annotation.Listeners;

@Listeners("rtneoimport_ImRealEstateListener")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_real_estate")
@Entity(name = "rtneoimport$ImRealEstate")
public class ImRealEstate extends ImBaseGeoEntity implements ImGeoEntity {
    private static final long serialVersionUID = 2603806664923532717L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "IS_LIVING")
    protected Boolean isLiving;

    @NotNull
    @Lob
    @Column(name = "ADDRESS", nullable = false)
    protected String address;

    @NotNull
    @Column(name = "CADASTRAL_NUMBER", nullable = false)
    protected String cadastralNumber;

    @Column(name = "AREA")
    protected BigDecimal area;

    @Lookup(type = LookupType.DROPDOWN, actions = {"lookup", "open", "clear"})
    @OnDeleteInverse(DeletePolicy.DENY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    protected ImRealEstate parent;

    @Column(name = "ADDRESS_FROM_ROSSREESTR")
    protected Boolean addressFromRossreestr;

    @Column(name = "AREA_FROM_ROSSREESTR")
    protected Boolean areaFromRossreestr;

    @Column(name = "COUNT_OF_ORGANIZATIONS")
    protected Integer countOfOrganizations;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "realEstate")
    protected List<ImContragentRealEstate> contragents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZONE_ID")
    protected ImZone zone;

    @Lob
    @Column(name = "STANDARD_ADDRESS")
    protected String standardAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS__ID")
    protected ImAddress address_;

    @Column(name = "CHECK_PROCESSING")
    protected Boolean checkProcessing;

    public void setIsLiving(Boolean isLiving) {
        this.isLiving = isLiving;
    }

    public Boolean getIsLiving() {
        return isLiving;
    }


    public void setCheckProcessing(Boolean checkProcessing) {
        this.checkProcessing = checkProcessing;
    }

    public Boolean getCheckProcessing() {
        return checkProcessing;
    }


    public void setAddress_(ImAddress imAddress_) {
        this.address_ = imAddress_;
    }

    public ImAddress getAddress_() {
        return address_;
    }


    public void setStandardAddress(String standardAddress) {
        this.standardAddress = standardAddress;
    }

    public String getStandardAddress() {
        return standardAddress;
    }


    public void setZone(ImZone zone) {
        this.zone = zone;
    }

    public ImZone getZone() {
        return zone;
    }


    public void setCountOfOrganizations(Integer countOfOrganizations) {
        this.countOfOrganizations = countOfOrganizations;
    }

    public Integer getCountOfOrganizations() {
        return countOfOrganizations;
    }


    public void setAddressFromRossreestr(Boolean addressFromRossreestr) {
        this.addressFromRossreestr = addressFromRossreestr;
    }

    public Boolean getAddressFromRossreestr() {
        return addressFromRossreestr;
    }

    public void setAreaFromRossreestr(Boolean areaFromRossreestr) {
        this.areaFromRossreestr = areaFromRossreestr;
    }

    public Boolean getAreaFromRossreestr() {
        return areaFromRossreestr;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public void setCadastralNumber(String cadastralNumber) {
        this.cadastralNumber = cadastralNumber;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public ImRealEstate getParent() {
        return parent;
    }

    public void setParent(ImRealEstate parent) {
        this.parent = parent;
    }

    public List<ImContragentRealEstate> getContragents() {
        return contragents;
    }

    public void setContragents(List<ImContragentRealEstate> contragents) {
        this.contragents = contragents;
    }


}