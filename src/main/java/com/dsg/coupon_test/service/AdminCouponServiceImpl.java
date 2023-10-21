package com.dsg.coupon_test.service;

import com.dsg.coupon_test.dto.request.CouponCreateRequest;
import com.dsg.coupon_test.dto.request.CouponSearchRequest;
import com.dsg.coupon_test.dto.response.CouponResponse;
import com.dsg.coupon_test.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {

    private final CouponRepository couponRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<CouponResponse> getCouponList(CouponSearchRequest requestDto) {
        log.info("coupon list");

        return couponRepository.finListWithQuerydsl(requestDto)
                .map(CouponResponse::from);
    }

    @Transactional
    @Override
    public void createCoupon(CouponCreateRequest request) {
        couponRepository.save(request.toEntity());
    }


}
