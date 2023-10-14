package com.dsg.coupon_test.service;

import com.dsg.coupon_test.entity.Coupon;
import com.dsg.coupon_test.enums.CouponStatus;
import com.dsg.coupon_test.enums.CouponType;
import com.dsg.coupon_test.repository.CouponRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class CouponServiceTest {


    @Autowired
    private AdminCouponService couponService;

    @Autowired
    private CouponRepository couponRepository;


/*    @BeforeEach
    public void beforeEach() {
        couponRepository.deleteAll();  // drop table

        LongStream.rangeClosed(1,10).forEach(i -> {
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
    }*/

//    @AfterEach
//    public void afterEach() {
//        couponRepository.deleteAll();
//    }

    // coupon list 확인
    @Test
    @DisplayName("쿠폰 리스트 조회")
    public void getCouponList() {
        // given
        // when
        // then
        int size = couponService.getCouponList().size();
        log.info("size : {}", size);
        assertThat(size).isEqualTo(3);
    }

}