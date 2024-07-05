package com.example.jpa_specification_example.exception;

import com.example.jpa_specification_example.model.common.ResponseCodes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter(value = AccessLevel.PRIVATE)
@Getter(value = AccessLevel.PACKAGE)
public class ApiException extends RuntimeException {
    private HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
    private String responseCode= ResponseCodes.SERVER_ERROR;
    private String message="SERVER_ERROR";

    public ApiException(){}

    @Override
    public String getMessage() {
        return message;
    }

    public ApiException message(String message){
        this.setMessage(message);
        return this;
    }
    public ApiException status(HttpStatus status){
        this.setStatus(status);
        return this;
    }
    public ApiException responseCode(String responseCode){
        this.setResponseCode(responseCode);
        return this;
    }
}
