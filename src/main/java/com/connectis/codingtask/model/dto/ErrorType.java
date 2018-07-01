package com.connectis.codingtask.model.dto;

import lombok.Getter;

public enum ErrorType {
    GENERAL_UNEXPECTED_ERROR("CT_GEN"),
    GENERAL_VALIDATION_ERROR("CT_VAL"),
    TRADE_DATA_VALIDATION_ERROR("CT_1");

    @Getter
    private final String code;

    ErrorType(String code) {
        this.code = code;
    }
}
