package com.dsg.coupon_test.dto.request;

import com.dsg.coupon_test.common.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CouponSearchRequest extends PageRequestDto {

    @NotBlank
    private String name;

    private String code;
}
