package com.taeyun.flashsale.domain.product;

import com.taeyun.flashsale.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

/**
 * 판매될 상품의 SKU 정보.
 * <p>
 * 지금 당장은(2026-08-21) 사용자 정보를 생략하고, 테이블을 간략화한다.
 */
@Entity
@Table(name = "product_skus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductSKU extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Check(constraints = "additional_price >= 0")
    @Column(name = "additional_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal additionalPrice;

    @Check(constraints = "stock_quantity >= 0")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
}
