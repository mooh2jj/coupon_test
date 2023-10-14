package com.dsg.coupon_test.dto.request;

import com.dsg.coupon_test.entity.Coupon;
import com.dsg.coupon_test.enums.CouponStatus;
import com.dsg.coupon_test.enums.CouponType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CouponCreateRequest {

    private String name;
    private String code;

    private CouponType type;

    private CouponStatus status;

    // 유효 기간
    private LocalDate startDate;
    private LocalDate endDate;

    public Coupon toEntity() {
        return Coupon.builder()
                .name(name)
                .code(code)
                .type(type)
                .status(CouponStatus.PUBLIC)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }


    // dto -> entity
    // entity -> entity



}
