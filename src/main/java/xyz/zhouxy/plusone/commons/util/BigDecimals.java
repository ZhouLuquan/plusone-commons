package xyz.zhouxy.plusone.commons.util;

import java.math.BigDecimal;

public class BigDecimals {

    public static boolean equals(BigDecimal a, BigDecimal b) {
        return (a == b) || (a != null && a.compareTo(b) == 0);
    }

    private BigDecimals() {
        throw new IllegalStateException("Utility class");
    }
}
