package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NumberUtilTest {
    @Test
    void testSum() {
        long result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Short.MAX_VALUE;
        }
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("result: " + result);
        assertFalse(Integer.MAX_VALUE > result);

        result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Short.MAX_VALUE;
        }
        System.out.println("Long.MAX_VALUE: " + Long.MAX_VALUE);
        System.out.println("result: " + result);
        assertTrue(Long.MAX_VALUE > result);

        result = 0;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            result += Integer.MAX_VALUE;
        }
        System.out.println("Long.MAX_VALUE: " + Long.MAX_VALUE);
        System.out.println("result: " + result);
        assertTrue(Long.MAX_VALUE > result);
    }
}
