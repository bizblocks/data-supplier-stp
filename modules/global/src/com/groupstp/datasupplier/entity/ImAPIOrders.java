package com.groupstp.datasupplier.entity;

import javax.persistence.*;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;

import java.util.Date;
import java.util.UUID;

import com.haulmont.cuba.core.entity.BaseStringIdEntity;
import com.haulmont.cuba.core.entity.HasUuid;
import com.haulmont.cuba.core.entity.Versioned;
import com.haulmont.cuba.core.entity.SoftDelete;
import com.haulmont.cuba.core.entity.Updatable;
import com.haulmont.cuba.core.entity.Creatable;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|kn")
@Table(name = "rtneoimport_im_api_orders")
@Entity(name = "rtneoimport$ImAPIOrders")
public class ImAPIOrders extends BaseStringIdEntity implements HasUuid, Versioned, SoftDelete, Updatable, Creatable {
    private static final long serialVersionUID = -7351388356846051193L;

    @Id
    @Column(name = "KN", nullable = false, length = 51)
    protected String kn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PDF_ID")
    protected FileDescriptor pdf;

    @Lob
    @Column(name = "ERROR_MESSAGE")
    protected String errorMessage;

    @Column(name = "ORDERNUM", length = 20)
    protected String ordernum;

    @Column(name = "DOCUMENT", length = 20)
    protected String document;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_CHECK")
    protected Date lastCheck;

    @Column(name = "STATUS", length = 11)
    protected String status;

    @Column(name = "TRANSACTION_", length = 20)
    protected String transaction;

    @Lob
    @Column(name = "ENCODED_DOCUMENT")
    protected String encoded_document;

    @Column(name = "PAID")
    protected Boolean paid;

    @Lob
    @Column(name = "ENCODED_OBJECT")
    protected String encoded_object;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    protected FileDescriptor file;

    @Column(name = "UUID")
    protected UUID uuid;

    @Column(name = "CREATE_TS")
    protected Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy;

    @Column(name = "UPDATE_TS")
    protected Date updateTs;

    @Column(name = "UPDATED_BY", length = 50)
    protected String updatedBy;

    @Column(name = "DELETE_TS")
    protected Date deleteTs;

    @Column(name = "DELETED_BY", length = 50)
    protected String deletedBy;

    @Version
    @Column(name = "VERSION", nullable = false)
    protected Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    protected ImAddress address;

    public void setAddress(ImAddress address) {
        this.address = address;
    }

    public ImAddress getAddress() {
        return address;
    }


    @Override
    public Boolean isDeleted() {
        return deleteTs != null;
    }


    @Override
    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    @Override
    public Date getCreateTs() {
        return createTs;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setUpdateTs(Date updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public Date getUpdateTs() {
        return updateTs;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setDeleteTs(Date deleteTs) {
        this.deleteTs = deleteTs;
    }

    @Override
    public Date getDeleteTs() {
        return deleteTs;
    }

    @Override
    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    @Override
    public String getDeletedBy() {
        return deletedBy;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public Integer getVersion() {
        return version;
    }


    public void setPdf(FileDescriptor pdf) {
        this.pdf = pdf;
    }

    public FileDescriptor getPdf() {
        return pdf;
    }


    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public void setDocument(String document) {
        this.document = document;
    }

    public String getDocument() {
        return document;
    }


    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setStatus(ImAPIOrderStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public ImAPIOrderStatus getStatus() {
        return ImAPIOrderStatus.fromId(status);
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setEncoded_document(String encoded_document) {
        this.encoded_document = encoded_document;
    }

    public String getEncoded_document() {
        return encoded_document;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setEncoded_object(String encoded_object) {
        this.encoded_object = encoded_object;
    }

    public String getEncoded_object() {
        return encoded_object;
    }

    public void setFile(FileDescriptor file) {
        this.file = file;
    }

    public FileDescriptor getFile() {
        return file;
    }


    public void setKn(String kn) {
        this.kn = kn;
    }

    public String getKn() {
        return kn;
    }

    @Override
    public String getId() {
        return kn;
    }

    @Override
    public void setId(String id) {
        this.kn = id;
        if (this.status == null) {
            this.status = ImAPIOrderStatus.NEW.getId();
        }
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}