package com.dsg.coupon_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CouponTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponTestApplication.class, args);
    }

}
