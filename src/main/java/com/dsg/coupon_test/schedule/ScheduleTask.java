package com.dsg.coupon_test.schedule;

import com.dsg.coupon_test.service.AdminCouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleTask {

    private final AdminCouponService adminCouponService;
    @Scheduled(cron = "0 0 0 * * *")       // 매일 자정 12시에 check
    public void couponExpireCheck() {
        log.info("coupon expire check run...");
    }

}
