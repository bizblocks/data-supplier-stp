package com.groupstp.datasupplier.entity;

import javax.persistence.*;

import com.groupstp.datasupplier.entity.ImBaseGeoEntity;
import com.haulmont.chile.core.annotations.NamePattern;

import java.util.List;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|address")
@Table(name = "rtneoimport_im_address")
@Entity(name = "rtneoimport$ImAddress")
public class ImAddress extends ImBaseGeoEntity {
    private static final long serialVersionUID = 1752399940762380316L;

    @Column(name = "POSTAL_CODE", length = 6)
    protected String postalCode;

    @OneToMany(mappedBy = "address")
    protected List<ImAPIOrders> apiOrders;

    @Lob
    @Column(name = "ADDRESS")
    protected String address;

    @Column(name = "FIAS_CITY_ID", length = 36)
    protected String fiasCityId;

    @Column(name = "FIAS_REGION_ID", length = 36)
    protected String fiasRegionId;

    @Column(name = "FIAS_ID", length = 36)
    protected String fiasId;

    @OneToMany(mappedBy = "address_")
    protected List<ImRealEstate> realEstates;

    @Column(name = "IS_RR_INFO_EXISTS")
    protected Boolean isRrInfoExists;


    public void setApiOrders(List<ImAPIOrders> apiOrders) {
        this.apiOrders = apiOrders;
    }

    public List<ImAPIOrders> getApiOrders() {
        return apiOrders;
    }


    public void setFiasRegionId(String fiasRegionId) {
        this.fiasRegionId = fiasRegionId;
    }

    public String getFiasRegionId() {
        return fiasRegionId;
    }


    public void setFiasCityId(String fiasCityId) {
        this.fiasCityId = fiasCityId;
    }

    public String getFiasCityId() {
        return fiasCityId;
    }


    public void setIsRrInfoExists(Boolean isRrInfoExists) {
        this.isRrInfoExists = isRrInfoExists;
    }

    public Boolean getIsRrInfoExists() {
        return isRrInfoExists;
    }


    public void setRealEstates(List<ImRealEstate> realEstates) {
        this.realEstates = realEstates;
    }

    public List<ImRealEstate> getRealEstates() {
        return realEstates;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getFiasId() {
        return fiasId;
    }

    public void setFiasId(String fiasId) {
        this.fiasId = fiasId;
    }
}