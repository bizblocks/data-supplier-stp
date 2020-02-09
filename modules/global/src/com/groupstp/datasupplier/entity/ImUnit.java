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
import javax.validation.constraints.NotNull;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_unit")
@Entity(name = "rtneoimport$ImUnit")
public class ImUnit extends StandardEntity {
    private static final long serialVersionUID = -4522733351243577350L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;


    @Column(name = "IS_AREA")
    protected Boolean isArea;

    public void setIsArea(Boolean isArea) {
        this.isArea = isArea;
    }

    public Boolean getIsArea() {
        return isArea;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}