package com.dsg.coupon_test.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    private final String errorMessage;
    private final ErrorCode errorCode;

    public CustomApiException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }

    public CustomApiException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription() + errorMessage;
    }

}
