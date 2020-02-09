package com.groupstp.datasupplier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ImOrganizationType implements EnumClass<Integer> {

    BUDGETARY(10),
    COMMERCIAL(20);

    private Integer id;

    ImOrganizationType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ImOrganizationType fromId(Integer id) {
        for (ImOrganizationType at : ImOrganizationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}