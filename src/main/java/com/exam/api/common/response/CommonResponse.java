package com.exam.api.common.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse<T> {
    
    private String message;
    private int status;
    private T body;
    private String code;


    public CommonResponse(final T body) {
        this(body, HttpStatus.OK);
    }


    public CommonResponse(final T body, final HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.body = body;
        this.code = String.valueOf(httpStatus.value());
        this.message = httpStatus.name();
    }


}
