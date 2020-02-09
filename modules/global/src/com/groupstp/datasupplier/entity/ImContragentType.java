package com.groupstp.datasupplier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ImContragentType implements EnumClass<Integer> {

    INDIVIDUAL(1),
    ORGANISATION(2),
    PERSONAL(3),
    UK(4);

    private Integer id;

    ImContragentType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ImContragentType fromId(Integer id) {
        for (ImContragentType at : ImContragentType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}