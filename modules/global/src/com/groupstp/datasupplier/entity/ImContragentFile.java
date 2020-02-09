package com.groupstp.datasupplier.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.groupstp.datasupplier.entity.ImContragent;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|name")
@Table(name = "rtneoimport_im_contragent_file")
@Entity(name = "rtneoimport$ImContragentFile")
public class ImContragentFile extends StandardEntity {
    private static final long serialVersionUID = -4644199151214450990L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTRAGENT_ID")
    protected ImContragent contragent;

    @NotNull
    @Column(name = "NAME")
    protected String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    protected FileDescriptor file;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    public FileDescriptor getFile() {
        return file;
    }


    public void setContragent(ImContragent contragent) {
        this.contragent = contragent;
    }

    public ImContragent getContragent() {
        return contragent;
    }
}