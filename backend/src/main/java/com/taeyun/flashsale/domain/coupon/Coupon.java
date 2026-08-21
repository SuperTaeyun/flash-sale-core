package com.taeyun.flashsale.domain.coupon;

import com.taeyun.flashsale.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

/**
 * 쿠폰의 기본적인 정보를 저장하는 테이블.
 * <p>
 * 지금 당장은(2026-08-21) 간략화하여 작성한다.
 */
@Entity
@Table(
        name = "coupons",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code", name = "uk_coupons_code")
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 쿠폰의 코드(ex: "SUMMER_SALE_2026_08"). 이 값은 중복될 수 없다.
     */
    @Column(unique = true, nullable = false, length = 50)
    private String code;

    /**
     * 쿠폰의 이름(ex: "여름맞이 쿠폰").
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * 할인 타입.
     *
     * @see DiscountType
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    /**
     * 할인 금액/퍼센트. discountType에 따라 해석이 달라진다.
     */
    @Check(constraints = "discount_amount > 0")
    @Column(name = "discount_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal discountAmount;

    public Coupon(String code, String name, DiscountType discountType, BigDecimal discountAmount) {
        this.code = code;
        this.name = name;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
    }
}
