package com.dsg.coupon_test.repository;

import com.dsg.coupon_test.entity.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long>
, CouponRepositoryCustom {

    @Query("select c from Coupon c where c.name = :name and c.code = :code")
    Page<Coupon> findListBySearch(@Param("name") String name, @Param("code") String code, Pageable pageable);
}
