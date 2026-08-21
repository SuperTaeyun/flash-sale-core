package com.taeyun.flashsale.domain.order;

import com.taeyun.flashsale.domain.BaseEntity;
import com.taeyun.flashsale.domain.coupon.IssuedCoupon;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.List;

/**
 * 결제를 위한 주문 테이블.
 * <p>
 * 지금 당장은(2026-08-21) 사용자 정보를 생략하고, 테이블을 간략화한다.
 */
@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용된 쿠폰. 이 값은 비어있을 수도 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_coupons_id")
    private IssuedCoupon issuedCoupon;

    /**
     * 쿠폰 등이 적용되기 전의 중간 합계.
     */
    @Check(constraints = "subtotal_amount > 0")
    @Column(name = "subtotal_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotalAmount;

    /**
     * 할인된 금액.
     */
    @Check(constraints = "discount_amount >= 0")
    @Column(name = "discount_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal discountAmount;

    /**
     * 최종 합계.
     */
    @Check(constraints = "total_amount > 0")
    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 주문의 진행 상태.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.CREATED;

    /**
     * {@link OrderItem}과의 양방향 설정.
     */
    @OneToMany(
            fetch = FetchType.LAZY, mappedBy = "order",
            cascade = CascadeType.ALL, orphanRemoval = true
    )
    private List<OrderItem> orderItems;

    public Order(IssuedCoupon issuedCoupon, BigDecimal subtotalAmount, BigDecimal discountAmount, BigDecimal totalAmount, OrderStatus status) {
        this.issuedCoupon = issuedCoupon;
        this.subtotalAmount = subtotalAmount;
        this.discountAmount = discountAmount;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}
