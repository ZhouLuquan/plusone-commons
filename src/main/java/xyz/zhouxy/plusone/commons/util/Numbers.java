/*
 * Copyright 2022-2024 the original author or authors.
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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 数字工具类
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public class Numbers {

    // #region - sum

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static int sum(final short... numbers) {
        int result = 0;
        for (short number : numbers) {
            result += number;
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static long sum(final int... numbers) {
        long result = 0L;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static long sum(final long... numbers) {
        long result = 0L;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static double sum(final float... numbers) {
        double result = 0.00;
        for (float number : numbers) {
            result += number;
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static double sum(final double... numbers) {
        double result = 0.00;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static BigInteger sum(final BigInteger... numbers) {
        if (ArrayTools.isNullOrEmpty(numbers)) {
            return BigInteger.ZERO;
        }
        BigInteger result = Numbers.nullToZero(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            BigInteger value = numbers[i];
            if (value != null) {
                result = result.add(value);
            }
        }
        return result;
    }

    /**
     * 求和
     *
     * @param numbers 数据数组
     * @return 求和结果
     */
    public static BigDecimal sum(final BigDecimal... numbers) {
        return BigDecimals.sum(numbers);
    }

    // #endregion

    // #region - nullToZero

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static byte nullToZero(@Nullable final Byte val) {
        return val != null ? val : 0;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static short nullToZero(@Nullable final Short val) {
        return val != null ? val : 0;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static int nullToZero(@Nullable final Integer val) {
        return val != null ? val : 0;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static long nullToZero(@Nullable final Long val) {
        return val != null ? val : 0L;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static float nullToZero(@Nullable final Float val) {
        return val != null ? val : 0.0F;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    public static double nullToZero(@Nullable final Double val) {
        return val != null ? val : 0.0;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    @Nonnull
    public static BigInteger nullToZero(@Nullable final BigInteger val) {
        return val != null ? val : BigInteger.ZERO;
    }

    /**
     * 将 {@code null} 转换为 {@code 0}
     *
     * @param val 待转换的值
     * @return 如果 {@code val} 不为 {@code null}，则返回该值；如果值为 {@code null}，则返回 {@code 0}
     */
    @Nonnull
    public static BigDecimal nullToZero(@Nullable final BigDecimal val) {
        return BigDecimals.nullToZero(val);
    }

    // #endregion

    private Numbers() {
        throw new IllegalStateException("Utility class");
    }
}
