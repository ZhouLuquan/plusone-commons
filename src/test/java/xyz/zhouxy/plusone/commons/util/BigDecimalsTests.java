/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigDecimalsTests {

    @Test
    void equalsValue_NullValues_ReturnsTrue() {
        assertTrue(BigDecimals.equalsValue(null, null));
    }

    @Test
    void equalsValue_SameNonNullableValues_ReturnsTrue() {
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("10.0");
        assertTrue(BigDecimals.equalsValue(bd1, bd2));
    }

    @Test
    void equalsValue_DifferentNonNullableValues_ReturnsFalse() {
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("20");
        assertFalse(BigDecimals.equalsValue(bd1, bd2));
    }

    @Test
    void equalsValue_OneNullOneNonNullValue_ReturnsFalse() {
        BigDecimal bd = new BigDecimal("10");
        assertFalse(BigDecimals.equalsValue(bd, null));
        assertFalse(BigDecimals.equalsValue(null, bd));
    }

    @Test
    void gt_NullFirstValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.gt(null, bd));
    }

    @Test
    void gt_NullSecondValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.gt(bd, null));
    }

    @Test
    void gt_SameValues_ReturnsFalse() {
        BigDecimal bd = new BigDecimal("10");
        assertFalse(BigDecimals.gt(bd, bd));
    }

    @Test
    void gt_FirstGreaterThanSecond_ReturnsTrue() {
        BigDecimal bd1 = new BigDecimal("20");
        BigDecimal bd2 = new BigDecimal("10");
        assertTrue(BigDecimals.gt(bd1, bd2));
    }

    @Test
    void ge_NullFirstValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.ge(null, bd));
    }

    @Test
    void ge_NullSecondValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.ge(bd, null));
    }

    @Test
    void ge_SameValues_ReturnsTrue() {
        BigDecimal bd = new BigDecimal("10");
        assertTrue(BigDecimals.ge(bd, bd));
    }

    @Test
    void ge_FirstGreaterThanSecond_ReturnsTrue() {
        BigDecimal bd1 = new BigDecimal("20");
        BigDecimal bd2 = new BigDecimal("10");
        assertTrue(BigDecimals.ge(bd1, bd2));
    }

    @Test
    void lt_NullFirstValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.lt(null, bd));
    }

    @Test
    void lt_NullSecondValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.lt(bd, null));
    }

    @Test
    void lt_SameValues_ReturnsFalse() {
        BigDecimal bd = new BigDecimal("10");
        assertFalse(BigDecimals.lt(bd, bd));
    }

    @Test
    void lt_FirstLessThanSecond_ReturnsTrue() {
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("20");
        assertTrue(BigDecimals.lt(bd1, bd2));
    }

    @Test
    void le_NullFirstValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.le(null, bd));
    }

    @Test
    void le_NullSecondValue_ThrowsException() {
        BigDecimal bd = new BigDecimal("10");
        assertThrows(NullPointerException.class, () -> BigDecimals.le(bd, null));
    }

    @Test
    void le_SameValues_ReturnsTrue() {
        BigDecimal bd = new BigDecimal("10");
        assertTrue(BigDecimals.le(bd, bd));
    }

    @Test
    void le_FirstLessThanSecond_ReturnsTrue() {
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("20");
        assertTrue(BigDecimals.le(bd1, bd2));
    }

    @Test
    void sum_NullArray_ReturnsZero() {
        assertEquals(BigDecimal.ZERO, BigDecimals.sum());
    }

    @Test
    void sum_SingleNonNullValue_ReturnsSameValue() {
        BigDecimal bd = new BigDecimal("10");
        assertEquals(bd, BigDecimals.sum(bd));
    }

    @Test
    void sum_SingleNullValue_ReturnsZero() {
        assertEquals(BigDecimal.ZERO, BigDecimals.sum((BigDecimal) null));
    }

    @Test
    void sum_MultipleValues_ReturnsCorrectSum() {
        BigDecimal bd1 = new BigDecimal("10");
        BigDecimal bd2 = new BigDecimal("20");
        BigDecimal bd3 = new BigDecimal("30");
        BigDecimal bd4 = null;
        assertEquals(new BigDecimal("60"), BigDecimals.sum(bd1, bd2, bd3, bd4));
    }

    @Test
    void nullToZero_NullValue_ReturnsZero() {
        assertEquals(BigDecimal.ZERO, BigDecimals.nullToZero(null));
    }

    @Test
    void nullToZero_NonNullValue_ReturnsSameValue() {
        BigDecimal bd = new BigDecimal("10");
        assertEquals(bd, BigDecimals.nullToZero(bd));
    }

    @Test
    void of_BlankString_ReturnsZero() {
        assertEquals(BigDecimal.ZERO, BigDecimals.of(null));
        assertEquals(BigDecimal.ZERO, BigDecimals.of(""));
        assertEquals(BigDecimal.ZERO, BigDecimals.of(" "));
    }

    @Test
    void of_NonBlankString_ReturnsCorrectBigDecimal() {
        BigDecimal bd = new BigDecimal("10");
        assertEquals(bd, BigDecimals.of("10"));
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = BigDecimals.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }
}
