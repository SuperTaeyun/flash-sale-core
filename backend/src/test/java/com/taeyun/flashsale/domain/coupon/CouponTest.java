package com.taeyun.flashsale.domain.coupon;

import com.taeyun.flashsale.config.JpaTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaTestConfig.class)
class CouponTest {

    @Autowired
    private CouponRepository couponRepository;

    @Test
    @DisplayName("Coupon을 정상적으로 저장할 수 있다")
    void save_coupon_successfully() {
        // given
        var coupon = new Coupon(
                "TEST_CODE",
                "TEST",
                DiscountType.AMOUNT,
                BigDecimal.valueOf(1000)
        );
        // when
        var saved = couponRepository.saveAndFlush(coupon);
        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCode()).isEqualTo("TEST_CODE");
        assertThat(saved.getName()).isEqualTo("TEST");
        assertThat(saved.getDiscountType()).isEqualTo(DiscountType.AMOUNT);
        assertThat(saved.getDiscountAmount()).isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    @DisplayName("coupon_code는 중복될 수 없다")
    void coupon_code_must_be_unique() {
        // given
        var coupon1 = new Coupon(
                "TEST_CODE",
                "1",
                DiscountType.AMOUNT,
                BigDecimal.valueOf(1000)
        );
        var coupon2 = new Coupon(
                "TEST_CODE",
                "2",
                DiscountType.AMOUNT,
                BigDecimal.valueOf(1000)
        );
        couponRepository.saveAndFlush(coupon1);
        // when & then
        assertThatThrownBy(() -> couponRepository.saveAndFlush(coupon2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("discount_amount는 0보다 커야한다")
    void discount_amount_must_be_greater_then_zero() {
        // given
        var coupon = new Coupon(
                "TEST_CODE",
                "TEST",
                DiscountType.AMOUNT,
                BigDecimal.ZERO
        );
        // when & then
        assertThatThrownBy(() -> couponRepository.saveAndFlush(coupon))
                .isInstanceOf(JpaSystemException.class);
    }
}