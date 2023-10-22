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

}
