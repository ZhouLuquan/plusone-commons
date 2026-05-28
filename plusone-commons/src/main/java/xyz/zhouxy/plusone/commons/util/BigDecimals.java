/*
 * Copyright 2023-present ZhouXY
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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkNotNull;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * BigDecimals
 *
 * <p>
 * BigDecimal 工具类
 *
 * @author ZhouXY
 * @since 1.0.0
 */
public class BigDecimals {

    /**
     * 判断两个 {@code BigDecimal} 的值是否相等
     *
     * @param a 第一个 {@code BigDecimal}
     * @param b 第二个 {@code BigDecimal}
     * @return 当两个 {@code BigDecimal} 的值相等时返回 {@code true}
     */
    public static boolean equalsValue(@Nullable BigDecimal a, @Nullable BigDecimal b) {
        return (a == b) || (a != null && b != null && a.compareTo(b) == 0);
    }

    /**
     * 判断两个 {@code BigDecimal} 的值 - 大于
     *
     * @param a 第一个 {@code BigDecimal}
     * @param b 第二个 {@code BigDecimal}
     * @return 当 {@code a} 大于 {@code b} 时返回 {@code true}
     */
    public static boolean gt(BigDecimal a, BigDecimal b) {
        checkNotNull(a, "Parameter could not be null.");
        checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) > 0);
    }

    /**
     * 判断两个 {@code BigDecimal} 的值 - 大于等于
     *
     * @param a 第一个 {@code BigDecimal}
     * @param b 第二个 {@code BigDecimal}
     * @return 当 {@code a} 大于等于 {@code b} 时返回 {@code true}
     */
    public static boolean ge(BigDecimal a, BigDecimal b) {
        checkNotNull(a, "Parameter could not be null.");
        checkNotNull(b, "Parameter could not be null.");
        return (a == b) || (a.compareTo(b) >= 0);
    }

    /**
     * 判断两个 {@code BigDecimal} 的值 - 小于
     *
     * @param a 第一个 {@code BigDecimal}
     * @param b 第二个 {@code BigDecimal}
     * @return 当 {@code a} 小于 {@code b} 时返回 {@code true}
     */
    public static boolean lt(BigDecimal a, BigDecimal b) {
        checkNotNull(a, "Parameter could not be null.");
        checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) < 0);
    }

    /**
     * 判断两个 {@code BigDecimal} 的值 - 小于等于
     *
     * @param a 第一个 {@code BigDecimal}
     * @param b 第二个 {@code BigDecimal}
     * @return 当 {@code a} 小于等于 {@code b} 时返回 {@code true}
     */
    public static boolean le(BigDecimal a, BigDecimal b) {
        checkNotNull(a, "Parameter could not be null.");
        checkNotNull(b, "Parameter could not be null.");
        return (a == b) || (a.compareTo(b) <= 0);
    }

    /**
     * 求和
     *
     * @param numbers {@code BigDecimal} 数组
     * @return 求和结果
     */
    public static BigDecimal sum(final BigDecimal... numbers) {
        if (ArrayTools.isEmpty(numbers)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = BigDecimals.nullToZero(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            BigDecimal value = numbers[i];
            if (value != null) {
                result = result.add(value);
            }
        }
        return result;
    }

    /**
     * 将 {@code null} 转换为 {@link BigDecimal#ZERO}
     *
     * @param val BigDecimal 对象
     * @return 如果 {@code val} 为 {@code null}，则返回 {@link BigDecimal#ZERO}，否则返回 {@code val}
     */
    @Nonnull
    public static BigDecimal nullToZero(@Nullable final BigDecimal val) {
        return val != null ? val : BigDecimal.ZERO;
    }

    /**
     * 获取字符串所表示的数值转换为 {@code BigDecimal}
     *
     * @param val 表示数值的字符串
     * @return {@code BigDecimal} 对象
     */
    @StaticFactoryMethod(BigDecimal.class)
    public static BigDecimal of(@Nullable final String val) {
        return (StringTools.isNotBlank(val)) ? new BigDecimal(val) : BigDecimal.ZERO;
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
