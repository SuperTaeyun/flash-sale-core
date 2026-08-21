package com.taeyun.flashsale.domain.order;

import com.taeyun.flashsale.domain.product.Product;
import com.taeyun.flashsale.domain.product.ProductSKU;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 주문({@link Order}) 테이블 참조.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Product

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "product_name_snapshot", nullable = false, length = 50)
    private String productNameSnapshot;

    @Check(constraints = "product_base_price_snapshot > 0")
    @Column(name = "product_base_price_snapshot", nullable = false, precision = 15, scale = 2)
    private BigDecimal productBasePriceSnapshot;

    // SKU

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sku_id", nullable = false)
    private ProductSKU productSKU;

    @Column(name = "sku_name_snapshot", nullable = false, length = 50)
    private String skuNameSnapshot;

    @Check(constraints = "sku_additional_price_snapshot >= 0")
    @Column(name = "sku_additional_price_snapshot", nullable = false, precision = 15, scale = 2)
    private BigDecimal skuAdditionalPriceSnapshot;

    @Check(constraints = "quantity > 0")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public OrderItem(Order order, Product product, String productNameSnapshot, BigDecimal productBasePriceSnapshot, ProductSKU productSKU, String skuNameSnapshot, BigDecimal skuAdditionalPriceSnapshot, Integer quantity) {
        this.order = order;
        this.product = product;
        this.productNameSnapshot = productNameSnapshot;
        this.productBasePriceSnapshot = productBasePriceSnapshot;
        this.productSKU = productSKU;
        this.skuNameSnapshot = skuNameSnapshot;
        this.skuAdditionalPriceSnapshot = skuAdditionalPriceSnapshot;
        this.quantity = quantity;
    }
}
