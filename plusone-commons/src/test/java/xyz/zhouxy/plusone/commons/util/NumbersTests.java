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

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public
class NumbersTests {

    @Test
    public void sum_ShortArray_ReturnsCorrectSum() {
        short[] numbers = {1, 2, 3, 4};
        int result = Numbers.sum(numbers);
        assertEquals(10, result);
    }

    @Test
    public void sum_IntArray_ReturnsCorrectSum() {
        int[] numbers = {1, 2, 3, 4};
        long result = Numbers.sum(numbers);
        assertEquals(10L, result);
    }

    @Test
    public void sum_LongArray_ReturnsCorrectSum() {
        long[] numbers = {1, 2, 3, 4};
        long result = Numbers.sum(numbers);
        assertEquals(10L, result);
    }

    @Test
    public void sum_FloatArray_ReturnsCorrectSum() {
        float[] numbers = {1.5f, 2.5f, 3.5f};
        double result = Numbers.sum(numbers);
        assertEquals(7.5, result);
    }

    @Test
    public void sum_DoubleArray_ReturnsCorrectSum() {
        double[] numbers = {1.5, 2.5, 3.5};
        double result = Numbers.sum(numbers);
        assertEquals(7.5, result);
    }

    @Test
    public void sum_BigIntegerArray_ReturnsCorrectSum() {
        BigInteger[] numbers = {new BigInteger("1"), new BigInteger("2"), new BigInteger("3")};
        BigInteger result = Numbers.sum(numbers);
        assertEquals(new BigInteger("6"), result);
    }

    @Test
    public void sum_BigDecimalArray_ReturnsCorrectSum() {
        BigDecimal[] numbers = {new BigDecimal("1.5"), new BigDecimal("2.5"), new BigDecimal("3.5")};
        BigDecimal result = Numbers.sum(numbers);
        assertEquals(new BigDecimal("7.5"), result);
    }

    @Test
    public void nullToZero_ByteNotNull_ReturnsSameValue() {
        Byte value = 5;
        byte result = Numbers.nullToZero(value);
        assertEquals(5, result);
    }

    @Test
    public void nullToZero_ByteNull_ReturnsZero() {
        Byte value = null;
        byte result = Numbers.nullToZero(value);
        assertEquals(0, result);
    }

    @Test
    public void nullToZero_ShortNotNull_ReturnsSameValue() {
        Short value = 5;
        short result = Numbers.nullToZero(value);
        assertEquals(5, result);
    }

    @Test
    public void nullToZero_ShortNull_ReturnsZero() {
        Short value = null;
        short result = Numbers.nullToZero(value);
        assertEquals(0, result);
    }

    @Test
    public void nullToZero_IntegerNotNull_ReturnsSameValue() {
        Integer value = 5;
        int result = Numbers.nullToZero(value);
        assertEquals(5, result);
    }

    @Test
    public void nullToZero_IntegerNull_ReturnsZero() {
        Integer value = null;
        int result = Numbers.nullToZero(value);
        assertEquals(0, result);
    }

    @Test
    public void nullToZero_LongNotNull_ReturnsSameValue() {
        Long value = 5L;
        long result = Numbers.nullToZero(value);
        assertEquals(5L, result);
    }

    @Test
    public void nullToZero_LongNull_ReturnsZero() {
        Long value = null;
        long result = Numbers.nullToZero(value);
        assertEquals(0L, result);
    }

    @Test
    public void nullToZero_FloatNotNull_ReturnsSameValue() {
        Float value = 5.0F;
        float result = Numbers.nullToZero(value);
        assertEquals(5.0F, result);
    }

    @Test
    public void nullToZero_FloatNull_ReturnsZero() {
        Float value = null;
        float result = Numbers.nullToZero(value);
        assertEquals(0.0F, result);
    }

    @Test
    public void nullToZero_DoubleNotNull_ReturnsSameValue() {
        Double value = 5.0;
        double result = Numbers.nullToZero(value);
        assertEquals(5.0, result);
    }

    @Test
    public void nullToZero_DoubleNull_ReturnsZero() {
        Double value = null;
        double result = Numbers.nullToZero(value);
        assertEquals(0.0, result);
    }

    @Test
    public void nullToZero_BigIntegerNotNull_ReturnsSameValue() {
        BigInteger value = new BigInteger("5");
        BigInteger result = Numbers.nullToZero(value);
        assertEquals(new BigInteger("5"), result);
    }

    @Test
    public void nullToZero_BigIntegerNull_ReturnsZero() {
        BigInteger value = null;
        BigInteger result = Numbers.nullToZero(value);
        assertEquals(BigInteger.ZERO, result);
    }

    @Test
    public void nullToZero_BigDecimalNotNull_ReturnsSameValue() {
        BigDecimal value = new BigDecimal("5.0");
        BigDecimal result = Numbers.nullToZero(value);
        assertEquals(new BigDecimal("5.0"), result);
    }

    @Test
    public void nullToZero_BigDecimalNull_ReturnsZero() {
        BigDecimal value = null;
        BigDecimal result = Numbers.nullToZero(value);
        assertEquals(BigDecimal.ZERO, result);
    }

    /**
     * Test for {@link Numbers#parseShort(String, Short)}.
     */
    @Test
    public void parseShort() {
        assertEquals((short) 12345, Numbers.parseShort("12345", (short) 5));
        assertEquals((short) 5, Numbers.parseShort("1234.5", (short) 5));
        assertEquals((short) 5, Numbers.parseShort("", (short) 5));
        assertEquals((short) 5, Numbers.parseShort(null, (short) 5));

        assertEquals((short) 12345, Numbers.parseShort("12345", null));
        assertNull(Numbers.parseShort("1234.5", null));
        assertNull(Numbers.parseShort("", null));
        assertNull(Numbers.parseShort(null, null));
    }

    /**
     * Test for {@link Numbers#parseInteger(String, Integer)}.
     */
    @Test
    public void parseInteger() {
        assertEquals(12345, Numbers.parseInteger("12345", 5));
        assertEquals(5, Numbers.parseInteger("1234.5", 5));
        assertEquals(5, Numbers.parseInteger("", 5));
        assertEquals(5, Numbers.parseInteger(null, 5));

        assertEquals(12345, Numbers.parseInteger("12345", null));
        assertNull(Numbers.parseInteger("1234.5", null));
        assertNull(Numbers.parseInteger("", null));
        assertNull(Numbers.parseInteger(null, null));
    }

    /**
     * Test for {@link Numbers#parseLong(String, Long)}.
     */
    @Test
    public void parseLong() {
        assertEquals(12345L, Numbers.parseLong("12345", 5L));
        assertEquals(5L, Numbers.parseLong("1234.5", 5L));
        assertEquals(5L, Numbers.parseLong("", 5L));
        assertEquals(5L, Numbers.parseLong(null, 5L));

        assertEquals(12345L, Numbers.parseLong("12345", null));
        assertNull(Numbers.parseLong("1234.5", null));
        assertNull(Numbers.parseLong("", null));
        assertNull(Numbers.parseLong(null, null));
    }

    /**
     * Test for {@link Numbers#parseFloat(String, Float)}.
     */
    @Test
    public void parseFloat() {
        assertEquals(1.2345f, Numbers.parseFloat("1.2345", 5.1f));
        assertEquals(5.0f, Numbers.parseFloat("a", 5.0f));
        assertEquals(5.0f, Numbers.parseFloat("-001Z.2345", 5.0f));
        assertEquals(5.0f, Numbers.parseFloat("+001AB.2345", 5.0f));
        assertEquals(5.0f, Numbers.parseFloat("001Z.2345", 5.0f));
        assertEquals(5.0f, Numbers.parseFloat("", 5.0f));
        assertEquals(5.0f, Numbers.parseFloat(null, 5.0f));

        assertEquals(1.2345f, Numbers.parseFloat("1.2345", null));
        assertNull(Numbers.parseFloat("a", null));
        assertNull(Numbers.parseFloat("-001Z.2345", null));
        assertNull(Numbers.parseFloat("+001AB.2345", null));
        assertNull(Numbers.parseFloat("001Z.2345", null));
        assertNull(Numbers.parseFloat("", null));
        assertNull(Numbers.parseFloat(null, null));
    }

    /**
     * Test for {@link Numbers#parseDouble(String, Double)}.
     */
    @Test
    public void parseDouble() {
        assertEquals(1.2345d, Numbers.parseDouble("1.2345", 5.1d));
        assertEquals(5.0d, Numbers.parseDouble("a", 5.0d));
        assertEquals(1.2345d, Numbers.parseDouble("001.2345", 5.1d));
        assertEquals(-1.2345d, Numbers.parseDouble("-001.2345", 5.1d));
        assertEquals(1.2345d, Numbers.parseDouble("+001.2345", 5.1d));
        assertEquals(0d, Numbers.parseDouble("000.00", 5.1d));
        assertEquals(5.1d, Numbers.parseDouble("", 5.1d));
        assertEquals(5.1d, Numbers.parseDouble((String) null, 5.1d));

        assertEquals(1.2345d, Numbers.parseDouble("1.2345", null));
        assertEquals(null, Numbers.parseDouble("a", null));
        assertEquals(1.2345d, Numbers.parseDouble("001.2345", null));
        assertEquals(-1.2345d, Numbers.parseDouble("-001.2345", null));
        assertEquals(1.2345d, Numbers.parseDouble("+001.2345", null));
        assertEquals(0d, Numbers.parseDouble("000.00", null));
        assertEquals(null, Numbers.parseDouble("", null));
        assertEquals(null, Numbers.parseDouble((String) null, null));
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = Numbers.class.getDeclaredConstructors();
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
