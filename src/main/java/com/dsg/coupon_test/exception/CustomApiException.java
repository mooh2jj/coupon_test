package com.dsg.coupon_test.exception;

import lombok.Getter;

@Getter
public class CustomApiException extends RuntimeException {

    private final ResponseCode responseCode;
    private final String errorMessage;

    public CustomApiException(ResponseCode responseCode) {
        super(responseCode.getDescription());
        this.responseCode = responseCode;
        this.errorMessage = responseCode.getDescription();
    }

    public CustomApiException(ResponseCode responseCode, String errorMessage) {
        super(responseCode.getDescription());
        this.responseCode = responseCode;
        this.errorMessage = responseCode.getDescription() + errorMessage;
    }

}
