package com.taeyun.flashsale.domain.coupon;

import com.taeyun.flashsale.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 발급된 쿠폰의 정보.
 * <p>
 * 지금 당장은(2026-08-21) 사용자 정보를 생략하고, 간략화한다.
 */
@Entity
@Table(name = "issued_coupons")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssuedCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CouponStatus status = CouponStatus.ISSUED;

    public IssuedCoupon(Coupon coupon, CouponStatus status) {
        this.coupon = coupon;
        this.status = status;
    }
}
