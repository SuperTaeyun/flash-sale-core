package com.taeyun.flashsale.domain.product;

import com.taeyun.flashsale.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

/**
 * 판매될 상품의 정보.
 * <p>
 * 지금 당장은(2026-08-21) 사용자 정보를 생략하고, 테이블을 간략화한다.
 */
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Check(constraints = "base_price > 0")
    @Column(name = "base_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal basePrice;

    public Product(String name, BigDecimal basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }
}
