package com.dsg.coupon_test.service;

import com.dsg.coupon_test.dto.request.CouponCreateRequest;
import com.dsg.coupon_test.dto.response.CouponResponse;

import java.util.List;

public interface AdminCouponService {
    void createCoupon(CouponCreateRequest request);

    List<CouponResponse> getCouponList();
}
