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
        return Header.error().message(e.toString());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Header<?> handleNoResourceFoundException(NoResourceFoundException e) {
        log.error("handle no resource found exception: {0}", e);
        return Header.error().message(e.toString()).responseCode(ResponseCodes.NOT_FOUND);
    }

    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Header<?> handleBindException(Exception e) {
        log.error("handle bind exception: {0}", e);
        return Header.error().message(e.toString()).responseCode(ResponseCodes.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Header<?>> handleApiException(ApiException e) {
        log.error("handle api exception: {0}", e);
        return new ResponseEntity<>(Header.error().message(e.toString()).responseCode(ResponseCodes.BAD_REQUEST), e.getStatus());
    }
}
