package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BigDecimalsTests {

    @Test
    void testToPlainString() {
        assertEquals("8.09", BigDecimals.toPlainString(BigDecimals.of("8.090")));
        assertEquals("8.09", BigDecimals.toPlainString(BigDecimals.of("8.094")));
        assertEquals("8.10", BigDecimals.toPlainString(BigDecimals.of("8.095")));
        assertEquals("8.10", BigDecimals.toPlainString(BigDecimals.of("8.096")));
        assertEquals("8.10", BigDecimals.toPlainString(BigDecimals.of("8.100")));
    }
}
