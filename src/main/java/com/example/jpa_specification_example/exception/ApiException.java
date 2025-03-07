package com.example.jpa_specification_example.exception;

import com.example.jpa_specification_example.model.common.ResponseCodes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {
    private String responseCode= ResponseCodes.SERVER_ERROR;


    public ApiException(String message, String responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}
