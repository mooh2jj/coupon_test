package com.dsg.coupon_test;

import com.dsg.coupon_test.entity.Coupon;
import com.dsg.coupon_test.enums.CouponStatus;
import com.dsg.coupon_test.enums.CouponType;
import com.dsg.coupon_test.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.stream.LongStream;

@Slf4j
@Component
@Profile("test")
@RequiredArgsConstructor
public class TestDataInit {

    private final CouponRepository couponRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        log.info("test data init run...");
        LongStream.rangeClosed(1,3).forEach(i -> {
            couponRepository.save(
                    Coupon.builder()
                            .name("coupon" + i)
                            .code("code" + i)
                            .type(CouponType.DISCOUNT)
                            .status(CouponStatus.PUBLIC)
                            .startDate(LocalDate.now())
                            .endDate(LocalDate.now().plusDays(7))
                            .build()
            );
        });
    }

}
