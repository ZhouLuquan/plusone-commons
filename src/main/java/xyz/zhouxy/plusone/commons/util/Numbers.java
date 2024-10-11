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

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import xyz.zhouxy.plusone.commons.math.IntervalType;

/**
 * Numbers
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public class Numbers {

    // sum

    public static int sum(final short... numbers) {
        int result = 0;
        for (short number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(final int... numbers) {
        long result = 0L;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sum(final long... numbers) {
        long result = 0L;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }

    public static double sum(final float... numbers) {
        double result = 0.00;
        for (float number : numbers) {
            result += number;
        }
        return result;
    }

    public static double sum(final double... numbers) {
        double result = 0.00;
        for (double number : numbers) {
            result += number;
        }
        return result;
    }

    public static BigDecimal sum(final BigDecimal... numbers) {
        BigDecimal result = BigDecimals.of("0.00");
        for (BigDecimal number : numbers) {
            result = result.add(number);
        }
        return result;
    }

    // between

    public static boolean between(int value, int min, int max) {
        return between(value, min, max, IntervalType.CLOSED_OPEN);
    }

    public static boolean between(int value, int min, int max, IntervalType intervalType) {
        final IntervalType intervalTypeToUse = intervalType != null
                ? intervalType
                : IntervalType.CLOSED_OPEN;
        switch (intervalTypeToUse) {
            case OPEN:
                return min < value && value < max;
            case CLOSED:
                return min <= value && value <= max;
            case OPEN_CLOSED:
                return min < value && value <= max;
            case CLOSED_OPEN:
            default:
                return min <= value && value < max;
        }
    }

    public static boolean between(long value, long min, long max) {
        return between(value, min, max, IntervalType.CLOSED_OPEN);
    }

    public static boolean between(long value, long min, long max, IntervalType intervalType) {
        final IntervalType intervalTypeToUse = intervalType != null
                ? intervalType
                : IntervalType.CLOSED_OPEN;
        switch (intervalTypeToUse) {
            case OPEN:
                return min < value && value < max;
            case CLOSED:
                return min <= value && value <= max;
            case OPEN_CLOSED:
                return min < value && value <= max;
            case CLOSED_OPEN:
            default:
                return min <= value && value < max;
        }
    }

    public static boolean between(double value, double min, double max) {
        return between(value, min, max, IntervalType.CLOSED_OPEN);
    }

    public static boolean between(double value, double min, double max, IntervalType intervalType) {
        final IntervalType intervalTypeToUse = intervalType != null
                ? intervalType
                : IntervalType.CLOSED_OPEN;
        switch (intervalTypeToUse) {
            case OPEN:
                return min < value && value < max;
            case CLOSED:
                return min <= value && value <= max;
            case OPEN_CLOSED:
                return min < value && value <= max;
            case CLOSED_OPEN:
            default:
                return min <= value && value < max;
        }
    }

    public static <T extends Comparable<T>> boolean between(@Nonnull T value, T min, T max) {
        return between(value, min, max, IntervalType.CLOSED_OPEN);
    }

    public static <T extends Comparable<T>> boolean between(@Nonnull T value, T min, T max, IntervalType intervalType) {
        Preconditions.checkArgument(value != null, "The value to valid connot be null.");
        IntervalType intervalTypeToUse = intervalType != null
                ? intervalType
                : IntervalType.CLOSED_OPEN;
        switch (intervalTypeToUse) {
            case OPEN:
                return (min == null || min.compareTo(value) < 0)
                        && (max == null || value.compareTo(max) < 0);
            case CLOSED:
                return (min == null || min.compareTo(value) <= 0)
                        && (max == null || value.compareTo(max) <= 0);
            case OPEN_CLOSED:
                return (min == null || min.compareTo(value) < 0)
                        && (max == null || value.compareTo(max) <= 0);
            case CLOSED_OPEN:
            default:
                return (min == null || min.compareTo(value) <= 0)
                        && (max == null || value.compareTo(max) < 0);
        }
    }

    private Numbers() {
        throw new IllegalStateException("Utility class");
    }
}
