package com.example.jpa_specification_example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.jpa_specification_example.model.common.Header;
import com.example.jpa_specification_example.model.common.ResponseCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Header<?> handleException(Exception e) {
        log.error("handled exception: {0}", e);
        return Header.error(e.toString(), ResponseCodes.SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Header<?> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("handle no resource found exception: {0}", e);
        return Header.error(e.toString(), ResponseCodes.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Header<?> handleBindException(Exception e) {
        log.error("handle bind exception: {0}", e);
        return Header.error(e.toString(), ResponseCodes.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Header<?> handleApiException(ApiException e) {
        log.error("handle api exception: {0}", e);
        return Header.error(e.getMessage(), e.getResponseCode());
    }
}
