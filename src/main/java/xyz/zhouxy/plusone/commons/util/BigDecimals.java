package xyz.zhouxy.plusone.commons.util;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public class BigDecimals {

    public static final BigDecimal ZERO = new BigDecimal("0.00");

    public static boolean equals(@Nullable BigDecimal a, @Nullable BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    public static boolean gt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) > 0);
    }

    public static boolean ge(BigDecimal a, BigDecimal b) {
        return gt(a, b) || equals(a, b);
    }

    public static boolean lt(BigDecimal a, BigDecimal b) {
        Preconditions.checkNotNull(a, "Parameter could not be null.");
        Preconditions.checkNotNull(b, "Parameter could not be null.");
        return (a != b) && (a.compareTo(b) < 0);
    }

    public static boolean le(BigDecimal a, BigDecimal b) {
        return lt(a, b) || equals(a, b);
    }

    public static BigDecimal of(final String val) {
        return (StringUtils.isBlank(val)) ? ZERO : new BigDecimal(val);
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
