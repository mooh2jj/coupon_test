package com.dsg.coupon_test.repository;

import com.dsg.coupon_test.dto.request.CouponSearchRequest;
import com.dsg.coupon_test.entity.Coupon;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CouponRepositoryCustom {
    Page<Coupon> finListWithQuerydsl(CouponSearchRequest requestDto);
}
