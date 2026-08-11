package com.taeyun.flashsale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class CiVerificationTest {

    /**
     * 반드시 실패하는 테스트. 필요한 순간을 제외하면 절대로. 반드시 실행되어서는 안된다.
     */
    @Test
    @Disabled
    void intentionallyFailingTest() {
        fail("This test is intentionally failing");
    }

}
