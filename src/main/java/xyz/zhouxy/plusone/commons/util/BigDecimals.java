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
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

public class BigDecimals {

    public static final BigDecimal ZERO = new BigDecimal("0.00");

    public static boolean equalsValue(@Nullable BigDecimal a, @Nullable BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) > 0);
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        return gt(a, b) || equalsValue(a, b);
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) < 0);
    }

    public static boolean le(BigDecimal a, BigDecimal b) {
        return lt(a, b) || equalsValue(a, b);
    }

    public static BigDecimal of(final String val) {
        return (StringTools.isNotBlank(val)) ? new BigDecimal(val) : ZERO;
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
