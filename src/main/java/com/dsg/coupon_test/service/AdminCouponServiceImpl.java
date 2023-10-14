package com.dsg.coupon_test.service;

import com.dsg.coupon_test.dto.request.CouponCreateRequest;
import com.dsg.coupon_test.dto.response.CouponResponse;
import com.dsg.coupon_test.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {

    private final CouponRepository couponRepository;


    @Transactional(readOnly = true)
    @Override
    public List<CouponResponse> getCouponList() {
        log.info("coupon list");

        return couponRepository.findAll().stream()
                .map(CouponResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void createCoupon(CouponCreateRequest request) {
        couponRepository.save(request.toEntity());
    }


}
