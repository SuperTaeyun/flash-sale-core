package com.taeyun.flashsale.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 테스트 설정. 이 곳에서 {@link EnableJpaAuditing}를 활성화 하지 않으면 거의 모든 JPA 테스트가 실패한다.
 */
@TestConfiguration
@EnableJpaAuditing
public class JpaTestConfig {
}
