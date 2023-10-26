package com.dsg.coupon_test.service;

import com.dsg.coupon_test.repository.CouponRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminCouponRecordServiceImpl implements AdminCouponRecordService {

    private final CouponRecordRepository couponRecordRepository;

    @Transactional
    @Override
    public void expireCouponRecord() {
        log.info("coupon admin service expireCoupon run...");
        couponRecordRepository.findAllByStatusAndExpireAtBeforeNow()
                .forEach(couponRecord -> {
                    log.info("couponRecord: {}", couponRecord);
                    couponRecord.changeExpireStatus();
                    log.info("couponRecord change expire status complete!");
                });

    }

}
