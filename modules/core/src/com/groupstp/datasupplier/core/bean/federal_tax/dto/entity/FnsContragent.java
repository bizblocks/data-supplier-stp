package com.groupstp.datasupplier.core.bean.federal_tax.dto.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * FNS system contragent
 *
 * @author adiatullin
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({ @JsonSubTypes.Type(value = OrganisationDTO.class, name = "ЮЛ"),
        @JsonSubTypes.Type(value = IndividualDTO.class, name = "ИП") })
public interface FnsContragent {
}
