/*
 * Copyright 2023-2024 the original author or authors.
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;

/**
 * BigDecimals
 *
 * <p>
 * BigDecimal 工具类
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 */
public class BigDecimals {

    public static boolean equalsValue(@Nullable BigDecimal a, @Nullable BigDecimal b) {
        return (a == b) || (a != null && b != null && a.compareTo(b) == 0);
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        AssertTools.checkNotNull(a, "Parameter could not be null.");
        AssertTools.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) > 0);
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        return gt(a, b) || equalsValue(a, b);
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        AssertTools.checkNotNull(a, "Parameter could not be null.");
        AssertTools.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) < 0);
    }

    public static boolean le(BigDecimal a, BigDecimal b) {
        return lt(a, b) || equalsValue(a, b);
    }

    public static BigDecimal sum(final BigDecimal... numbers) {
        if (ArrayTools.isNullOrEmpty(numbers)) {
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

    @Nonnull
    public static BigDecimal nullToZero(@Nullable final BigDecimal val) {
        return val != null ? val : BigDecimal.ZERO;
    }

    @StaticFactoryMethod(BigDecimal.class)
    public static BigDecimal of(final String val) {
        return (StringTools.isNotBlank(val)) ? new BigDecimal(val) : BigDecimal.ZERO;
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
