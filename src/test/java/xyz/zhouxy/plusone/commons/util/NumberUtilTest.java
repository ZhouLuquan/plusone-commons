package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class NumberUtilTest {
    @Test
    void testSum() {
        long result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Short.MAX_VALUE;
        }
        log.info("Integer.MAX_VALUE: {}", Integer.MAX_VALUE);
        log.info("result: {}", result);
        assertFalse(Integer.MAX_VALUE > result);

        result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Short.MAX_VALUE;
        }
        log.info("Long.MAX_VALUE: {}", Long.MAX_VALUE);
        log.info("result: {}", result);
        assertTrue(Long.MAX_VALUE > result);

        result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Integer.MAX_VALUE;
        }
        log.info("Long.MAX_VALUE: {}", Long.MAX_VALUE);
        log.info("result: {}", result);
        assertTrue(Long.MAX_VALUE > result);
    }
}
