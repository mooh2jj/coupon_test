package com.dsg.coupon_test.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Table(name = "coupon_record")
public class CouponRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coupon_id") // fk
    private Coupon coupon;

    @Column(name = "is_expired",nullable = false)
    private boolean isExpired;

    @Column(name = "is_used", nullable = false)
    private boolean isUsed;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

}
