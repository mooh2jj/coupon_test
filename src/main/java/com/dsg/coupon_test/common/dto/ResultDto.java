package com.dsg.coupon_test.common.dto;

import com.dsg.coupon_test.exception.ResponseCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.dsg.coupon_test.exception.ResponseCode.SUCCESS;

@Getter
@Builder
@ToString

public class ResultDto<T> {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;
    private final Integer code; // 200, 400, 500
    private final String message;
    private final String detail;
    private final T data;


    public static <T> ResultDto<T> success() {
        return ResultDto.<T>builder()
                .timestamp(LocalDateTime.now())
                .code(200)
                .message(SUCCESS.getDescription())
                .build();
    }

    public static <T> ResultDto<T> success(T data) {
        return ResultDto.<T>builder()
                .timestamp(LocalDateTime.now())
                .code(200)
                .message(SUCCESS.getDescription())
                .data(data)
                .build();
    }


    public static <T> ResultDto<T> fail(ResponseCode responseCode, String message, T data) {
        return ResultDto.<T>builder()
                .timestamp(LocalDateTime.now())
                .code(responseCode.getCode())
                .message(responseCode.getDescription())
                .detail(message)
                .data(data)
                .build();
    }
}
