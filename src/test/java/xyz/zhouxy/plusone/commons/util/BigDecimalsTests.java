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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BigDecimalsTests {

    @Test
    void testToPlainString() {

        BigDecimalFormatter formatter = BigDecimalFormatter.builder()
                .setScale(2, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .build();

        assertEquals("8.09", formatter.toPlainString(BigDecimals.of("8.090")));
        assertEquals("8.09", formatter.toPlainString(BigDecimals.of("8.094")));
        assertEquals("8.1", formatter.toPlainString(BigDecimals.of("8.095")));
        assertEquals("8.1", formatter.toPlainString(BigDecimals.of("8.096")));
        assertEquals("8.1", formatter.toPlainString(BigDecimals.of("8.100")));
    }

    @Test
    void test() {
        Object a = 100 % 3.0;
        log.info("a: {}", a);
    }
}

class BigDecimalFormatter {
    private final Function<BigDecimal, BigDecimal> func;

    private BigDecimalFormatter(Function<BigDecimal, BigDecimal> wholeFunc) {
        this.func = wholeFunc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String toPlainString(BigDecimal value) {
        final BigDecimal finalDecimal = func == null ? value : func.apply(value);
        return finalDecimal.toPlainString();
    }

    public String toEngineeringString(BigDecimal value) {
        final BigDecimal finalDecimal = func == null ? value : func.apply(value);
        return finalDecimal.toEngineeringString();
    }

    public static class Builder {
        private Function<BigDecimal, BigDecimal> wholeFunc;

        private Builder() {
        }

        public Builder setScale(int newScale, RoundingMode roundingMode) {
            final Function<BigDecimal, BigDecimal> func = value -> value.setScale(newScale, roundingMode);
            if (wholeFunc == null) {
                wholeFunc = func;
            } else {
                wholeFunc = func.andThen(func);
            }
            return this;
        }

        public Builder stripTrailingZeros() {
            if (wholeFunc == null) {
                wholeFunc = BigDecimal::stripTrailingZeros;
            } else {
                wholeFunc = wholeFunc.andThen(BigDecimal::stripTrailingZeros);
            }
            return this;
        }

        public BigDecimalFormatter build() {
            return new BigDecimalFormatter(wholeFunc);
        }
    }
}
