package com.groupstp.datasupplier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ImControlMethod implements EnumClass<Integer> {

    DIRECT(1),
    UK(2);

    private Integer id;

    ImControlMethod(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static ImControlMethod fromId(Integer id) {
        for (ImControlMethod at : ImControlMethod.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}