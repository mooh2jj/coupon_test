package com.dsg.coupon_test.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ResultDto<T> {

    private final Integer code; // 200, 400, 500
    private final String message;
    private final T data;

    public static <T> ResultDto<T> success(T data) {
        return ResultDto.<T>builder()
                .code(200)
                .message("success")
                .data(data)
                .build();
    }


    public static <T> ResultDto<T> fail(Integer code, String message, T data) {
        return ResultDto.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .build();
    }
}
