package com.groupstp.datasupplier.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamePattern("%s|regNumber")
@Table(name = "rtneoimport_im_contragent_api_call")
@Entity(name = "rtneoimport$ImContragentApiCall")
public class ImContragentApiCall extends StandardEntity {
    private static final long serialVersionUID = 7814540292867625537L;

    @NotNull
    @Column(name = "REG_NUMBER", nullable = false)
    protected String regNumber;

    @Lob
    @Column(name = "RESPONSE_BODY")
    protected String responseBody;

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseBody() {
        return responseBody;
    }


}