package com.groupstp.datasupplier.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.*;
import com.haulmont.cuba.core.global.DesignSupport;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_real_estate_type")
@Entity(name = "rtneoimport$ImRealEstateType")
public class ImRealEstateType extends StandardEntity {
    private static final long serialVersionUID = -3023528716382106963L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "IS_APARTMENT_BUILDING")
    protected Boolean isApartmentBuilding;

    @Column(name = "IS_LIVING")
    protected Boolean isLiving;

    @Column(name = "ASSIGNATION_BUILDING")
    protected String assignationBuilding;

    @Column(name = "ASSIGNATION_CODE")
    protected String assignationCode;

    @Column(name = "ASSIGNATION_TYPE")
    protected String assignationType;

    public void setAssignationCode(String assignationCode) {
        this.assignationCode = assignationCode;
    }

    public String getAssignationCode() {
        return assignationCode;
    }

    public void setAssignationType(String assignationType) {
        this.assignationType = assignationType;
    }

    public String getAssignationType() {
        return assignationType;
    }


    public void setAssignationBuilding(String assignationBuilding) {
        this.assignationBuilding = assignationBuilding;
    }

    public String getAssignationBuilding() {
        return assignationBuilding;
    }


    public void setIsLiving(Boolean isLiving) {
        this.isLiving = isLiving;
    }

    public Boolean getIsLiving() {
        return isLiving;
    }


    public void setIsApartmentBuilding(Boolean isApartmentBuilding) {
        this.isApartmentBuilding = isApartmentBuilding;
    }

    public Boolean getIsApartmentBuilding() {
        return isApartmentBuilding;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}