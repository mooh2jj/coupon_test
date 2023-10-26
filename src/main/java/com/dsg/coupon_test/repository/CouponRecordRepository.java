package com.dsg.coupon_test.repository;


import com.dsg.coupon_test.entity.CouponRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRecordRepository extends JpaRepository<CouponRecord, Long> {

    @Query("SELECT cr FROM CouponRecord cr INNER JOIN Coupon c ON cr.coupon.id = c.id WHERE cr.status != 'EXPIRED' AND cr.coupon.status = 'PUBLIC' AND cr.coupon.endDate < CURRENT_DATE")
    List<CouponRecord> findAllByStatusAndExpireAtBeforeNow();
}
