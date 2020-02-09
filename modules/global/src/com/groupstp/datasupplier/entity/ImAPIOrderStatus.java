package com.groupstp.datasupplier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum ImAPIOrderStatus implements EnumClass<String> {

    NEW("новый"),
    WAITING("ожидание"),
    PAYMENT("оплата"),
    LOADING("загрузка"),
    APPLY("расшифровка"),
    ERROR("ошибка"),
    CANCELED("отмена");

    private String id;

    ImAPIOrderStatus(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ImAPIOrderStatus fromId(String id) {
        for (ImAPIOrderStatus at : ImAPIOrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}