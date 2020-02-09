package com.groupstp.datasupplier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ImTypeOwnership implements EnumClass<Integer> {

    RENT(1),
    OWN(2),
    OTHER(3);

    private Integer id;

    ImTypeOwnership(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ImTypeOwnership fromId(Integer id) {
        for (ImTypeOwnership at : ImTypeOwnership.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}