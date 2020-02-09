package com.groupstp.datasupplier.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.*;
import com.haulmont.cuba.core.global.DesignSupport;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_zone")
@Entity(name = "rtneoimport$ImZone")
public class ImZone extends StandardEntity {
    private static final long serialVersionUID = -4360040090485507465L;

    @Column(name = "NAME", length = 20)
    protected String name;

    @Lob
    @Column(name = "KEYWORDS")
    protected String keywords;

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}