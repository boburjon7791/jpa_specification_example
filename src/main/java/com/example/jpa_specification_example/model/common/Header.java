package com.example.jpa_specification_example.model.common;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Header<T> {
    @NotNull
    private LocalDateTime transactionTime;
    private String responseMsg;
    private String responseCode;
    @Getter(AccessLevel.PUBLIC)
    private T data;
    private PaginationData pagination;
    /**
     * SUCCESS responses
     * */
    public static <T> Header<T> ok(){
        return ok(null);
    }
    public static <T> Header<T> ok(T data){
        return Header.<T>builder()
                .data(data)
                .transactionTime(LocalDateTime.now())
                .responseMsg("OK")
                .responseCode(ResponseCodes.OK)
                .build();
    }

    /**
    * ERROR responses
    * */
    public static <T> Header<T> error(){
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .responseMsg("SERVER_ERROR")
                .responseCode(ResponseCodes.SERVER_ERROR)
                .build();
    }

    /**
     * instance methods
    * */
    public Header<T> message(String message){
        this.setResponseMsg(message);
        return this;
    }
    public Header<T> responseCode(String responseCodes){
        this.setResponseCode(responseCodes);
        return this;
    }
    public Header<T> pagination(PaginationData pagination){
        this.setPagination(pagination);
        return this;
    }

}
