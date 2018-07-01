package com.connectis.codingtask.model.exception;

import java.util.Set;

import static com.connectis.codingtask.validator.ErrorMessageUtils.formatToPrint;

public class TradeDataValidationException extends RuntimeException {
    private static final String VALIDATION_EXCEPTION_MESSAGE = "Following validation errors occured:\n(%s)";

    public TradeDataValidationException(Set<String> errors) {
        super(String.format(VALIDATION_EXCEPTION_MESSAGE, formatToPrint(errors)));
    }
}
