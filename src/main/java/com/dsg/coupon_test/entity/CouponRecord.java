package com.dsg.coupon_test.entity;

import com.dsg.coupon_test.enums.CouponRecordStatus;
import com.dsg.coupon_test.enums.Deleted;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "coupon_record")
public class CouponRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CouponRecordStatus status;

    @Column(name = "used_datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime usedDatetime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Deleted deleted;

    @Column(name = "issued_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime issuedAt;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.issuedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public CouponRecord(Coupon coupon, CouponRecordStatus status, LocalDateTime usedDatetime, Deleted deleted, LocalDateTime issuedAt, LocalDateTime createdAt) {
        this.coupon = coupon;
        this.status = status;
        this.usedDatetime = usedDatetime;
        this.deleted = deleted;
        this.issuedAt = issuedAt;
        this.createdAt = createdAt;
    }

    public void changeExpireStatus() {
        this.status = CouponRecordStatus.EXPIRED;
    }
}
