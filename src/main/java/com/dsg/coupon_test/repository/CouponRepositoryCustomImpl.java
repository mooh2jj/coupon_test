package com.dsg.coupon_test.repository;

import com.dsg.coupon_test.dto.request.CouponSearchRequest;
import com.dsg.coupon_test.entity.Coupon;
import com.dsg.coupon_test.entity.QCoupon;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.dsg.coupon_test.entity.QCoupon.*;

@RequiredArgsConstructor
public class CouponRepositoryCustomImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Coupon> finListWithQuerydsl(CouponSearchRequest requestDto) {

        List<Coupon> couponList = queryFactory
                .selectFrom(coupon)
                .where(coupon.name.eq(requestDto.getName()))
                .offset(requestDto.getPageable().getOffset())
                .limit(requestDto.getPageable().getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(coupon.count())
                .from(coupon)
                .where(coupon.name.eq(requestDto.getName()))
                ;

        return PageableExecutionUtils.getPage(couponList, requestDto.getPageable(), countQuery::fetchOne);
    }
}
