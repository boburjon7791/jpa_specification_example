package com.example.jpa_specification_example.exception;

import com.example.jpa_specification_example.model.common.ResponseCodes;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private String responseCode= ResponseCodes.SERVER_ERROR;


    public ApiException(String message, String responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public ApiException responseCode(String responseCode){
        this.responseCode = responseCode;
        return this;
    }
}
