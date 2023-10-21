package com.dsg.coupon_test.service;

import com.dsg.coupon_test.common.PageRequestDto;
import com.dsg.coupon_test.dto.request.CouponCreateRequest;
import com.dsg.coupon_test.dto.request.CouponSearchRequest;
import com.dsg.coupon_test.dto.response.CouponResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCouponService {
    void createCoupon(CouponCreateRequest request);

    Page<CouponResponse> getCouponList(CouponSearchRequest requestDto);
}
