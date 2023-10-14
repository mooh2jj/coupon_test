package com.dsg.coupon_test.dto.response;

import com.dsg.coupon_test.entity.Coupon;
import com.dsg.coupon_test.enums.CouponStatus;
import com.dsg.coupon_test.enums.CouponType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class CouponResponse {

    private final Long id;
    private final String name;
    private final String code;
    private final CouponType type;
    private final CouponStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDate endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private final LocalDateTime modifiedAt;

    public static CouponResponse from(Coupon coupon) {
        return CouponResponse.builder()
                .id(coupon.getId())
                .name(coupon.getName())
                .code(coupon.getCode())
                .type(coupon.getType())
                .status(coupon.getStatus())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .createdAt(coupon.getCreatedAt())
                .modifiedAt(coupon.getModifiedAt())
                .build();
    }
}
