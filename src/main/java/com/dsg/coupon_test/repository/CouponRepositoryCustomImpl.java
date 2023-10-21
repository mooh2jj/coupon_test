package com.dsg.coupon_test.repository;

import com.dsg.coupon_test.dto.request.CouponSearchRequest;
import com.dsg.coupon_test.entity.Coupon;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.dsg.coupon_test.entity.QCoupon.coupon;

@RequiredArgsConstructor
public class CouponRepositoryCustomImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Coupon> finListWithQuerydsl(CouponSearchRequest requestDto) {

        List<Coupon> couponList = queryFactory
                .selectFrom(coupon)
                .where(
                        eqCouponName(requestDto)
                )
                .offset(requestDto.getPageable().getOffset())
                .limit(requestDto.getPageable().getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(coupon.count())
                .from(coupon)
                .where(
                        eqCouponName(requestDto)
                )
                ;

        return PageableExecutionUtils.getPage(couponList, requestDto.getPageable(), countQuery::fetchOne);
    }

    private static BooleanExpression eqCouponName(CouponSearchRequest requestDto) {
        if(!ObjectUtils.isEmpty(requestDto.getName())) {
            return coupon.name.contains(requestDto.getName());
        }
        return null;
    }
}
