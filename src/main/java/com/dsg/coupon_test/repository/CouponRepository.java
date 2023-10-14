package com.dsg.coupon_test.repository;

import com.dsg.coupon_test.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
