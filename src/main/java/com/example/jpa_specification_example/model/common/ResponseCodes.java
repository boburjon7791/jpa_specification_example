package com.example.jpa_specification_example.model.common;

public interface ResponseCodes {
    String OK = "200";
    String SERVER_ERROR="500";
    String UNAUTHORIZED="401";
    String BAD_REQUEST="400";
    String NOT_FOUND="404";
    String FORBIDDEN="403";
    String CREATED="201";
    String UPDATED="204";
    String DELETED="204";
}
