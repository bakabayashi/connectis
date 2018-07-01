package com.connectis.codingtask.rest;

import com.connectis.codingtask.model.dto.ErrorDTO;
import com.connectis.codingtask.model.exception.TradeDataValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.connectis.codingtask.model.dto.ErrorType.GENERAL_UNEXPECTED_ERROR;
import static com.connectis.codingtask.model.dto.ErrorType.GENERAL_VALIDATION_ERROR;
import static com.connectis.codingtask.model.dto.ErrorType.TRADE_DATA_VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler({ MethodArgumentNotValidException.class, MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorDTO processAbstractBadRequestException(Exception ex) {
        log.info("Generic badRequest exception handler ", ex);
        return new ErrorDTO(GENERAL_VALIDATION_ERROR, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDTO processUnexpectedInternalException(Exception ex) {
        log.error("Exception handler ", ex);
        return new ErrorDTO(GENERAL_UNEXPECTED_ERROR, ex.getMessage());
    }

    @ExceptionHandler(TradeDataValidationException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorDTO processTradeDataValidationException(Exception ex) {
        log.info("TradeDataValidationException handler ", ex);
        return new ErrorDTO(TRADE_DATA_VALIDATION_ERROR, ex.getMessage());
    }

}