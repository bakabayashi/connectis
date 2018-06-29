package com.connectis.codingtask.model.dto;

import lombok.Getter;

public class ErrorDTO {
    @Getter
    private final String errorCode;

    @Getter
    private final String message;

    public ErrorDTO(ErrorType errorType, String message) {
        this.errorCode = errorType.getCode();
        this.message = message;
    }
}
