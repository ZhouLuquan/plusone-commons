/*
 * Copyright 2024 the original author or authors.
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
import java.math.BigDecimal;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
