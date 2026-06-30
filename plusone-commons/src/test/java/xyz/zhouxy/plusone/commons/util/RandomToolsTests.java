/*
 * Copyright 2024-present ZhouXY
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.common.collect.Range;

@SuppressWarnings("null")
class RandomToolsTests {

    private static Random random;
    private static SecureRandom secureRandom;
    private static char[] sourceCharactersArray;
    private static String sourceCharactersString;

    @BeforeAll
    static void setUp() {
        random = new Random();
        secureRandom = new SecureRandom();
        sourceCharactersArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        sourceCharactersString = "abcdefghijklmnopqrstuvwxyz";
    }

    @Test
    void randomStr_NullRandom_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersArray, 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersString, 5));
    }

    @Test
    void randomStr_NullSourceCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(random, (char[]) null, 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(random, (String) null, 5));
    }

    @Test
    void randomStr_NegativeLength_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(random, sourceCharactersArray, -1));
    }

    @Test
    void randomStr_EmptySourceCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(random, new char[0], 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(random, "", 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(new char[0], 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr("", 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.secureRandomStr(new char[0], 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.secureRandomStr("", 5));
    }

    @Test
    void randomStr_ZeroLength_ReturnsEmptyString() {
        assertAll(
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersString, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersString, 0)));
    }

    @Test
    void randomStr_PositiveLength_ReturnsRandomString() {
        assertAll(
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersString, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersString, 5).length()));
    }

    @Test
    void randomStr_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    void randomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }

    @Test
    void secureRandomStr_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    void secureRandomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }

    @Test
    void randomInt_WithMinAndMax() {
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, -2, 3);
            assertTrue(r >= -2 && r <= 3);
        }
    }

    @Test
    void randomInt_WithClosedOpenRange() {
        Range<Integer> co = Range.closedOpen(-2, 3);
        for (int i = 0; i < 1000; i++) {
            int rco = RandomTools.randomInt(random, co);
            assertTrue(rco >= -2 && rco < 3);
        }
    }

    @Test
    void randomInt_WithClosedRange() {
        Range<Integer> cc = Range.closed(-2, 3);
        for (int i = 0; i < 1000; i++) {
            int rcc = RandomTools.randomInt(random, cc);
            assertTrue(rcc >= -2 && rcc <= 3);
        }
    }

    @Test
    void randomInt_WithOpenClosedRange() {
        Range<Integer> oc = Range.openClosed(-2, 3);
        for (int i = 0; i < 1000; i++) {
            int roc = RandomTools.randomInt(random, oc);
            assertTrue(roc > -2 && roc <= 3);
        }
    }

    @Test
    void randomInt_WithOpenRange() {
        Range<Integer> oo = Range.open(-2, 3);
        for (int i = 0; i < 1000; i++) {
            int roo = RandomTools.randomInt(random, oo);
            assertTrue(roo > -2 && roo < 3);
        }
    }

    // ================================
    // #region - randomInt with unbounded ranges
    // ================================

    @Test
    void randomInt_Unbounded_AtLeast() {
        Range<Integer> atLeast = Range.atLeast(-100);
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, atLeast);
            assertTrue(r >= -100, "Expected >= -100 but got " + r);
            assertTrue(r <= Integer.MAX_VALUE, "Expected <= Integer.MAX_VALUE but got " + r);
        }
    }

    @Test
    void randomInt_Unbounded_AtMost() {
        Range<Integer> atMost = Range.atMost(100);
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, atMost);
            assertTrue(r >= Integer.MIN_VALUE, "Expected >= Integer.MIN_VALUE but got " + r);
            assertTrue(r <= 100, "Expected <= 100 but got " + r);
        }
    }

    @Test
    void randomInt_Unbounded_All() {
        Range<Integer> all = Range.all();
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, all);
            // 只验证不抛异常、返回的是合法的 int
            assertTrue(r >= Integer.MIN_VALUE && r <= Integer.MAX_VALUE);
        }
    }

    @Test
    void randomInt_Unbounded_GreaterThan() {
        Range<Integer> greaterThan = Range.greaterThan(0);
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, greaterThan);
            assertTrue(r > 0, "Expected > 0 but got " + r);
            assertTrue(r <= Integer.MAX_VALUE, "Expected <= Integer.MAX_VALUE but got " + r);
        }
    }

    @Test
    void randomInt_Unbounded_LessThan() {
        Range<Integer> lessThan = Range.lessThan(0);
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, lessThan);
            assertTrue(r >= Integer.MIN_VALUE, "Expected >= Integer.MIN_VALUE but got " + r);
            assertTrue(r < 0, "Expected < 0 but got " + r);
        }
    }

    // ================================
    // #endregion - randomInt with unbounded ranges
    // ================================

    // ================================
    // #region - randomInt with large ranges (nextLong path)
    // ================================

    @Test
    void randomInt_LargeRange_FullIntRange() {
        // [Integer.MIN_VALUE, Integer.MAX_VALUE)，range = 2^32 - 1 > Integer.MAX_VALUE
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, Integer.MIN_VALUE, Integer.MAX_VALUE);
            assertTrue(r >= Integer.MIN_VALUE && r <= Integer.MAX_VALUE);
        }
    }

    @Test
    void randomInt_LargeRange_ClosedFullIntRange() {
        // [Integer.MIN_VALUE, Integer.MAX_VALUE]，rangeSize = 2^32 > Integer.MAX_VALUE
        Range<Integer> full = Range.closed(Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, full);
            assertTrue(r >= Integer.MIN_VALUE && r <= Integer.MAX_VALUE);
        }
    }

    @Test
    void randomInt_LargeRange_WideNegativeToPositive() {
        // [Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2)，range 仍然可能超过 Integer.MAX_VALUE
        int start = Integer.MIN_VALUE / 2;
        int end = Integer.MAX_VALUE / 2;
        for (int i = 0; i < 1000; i++) {
            int r = RandomTools.randomInt(random, start, end);
            assertTrue(r >= start && r <= end);
        }
    }

    // ================================
    // #endregion - randomInt with large ranges
    // ================================

    // ================================
    // #region - randomInt empty range validation
    // ================================

    @Test
    void randomInt_EmptyRange_Open_ThrowsException() {
        // (0, 1) 不包含任何整数
        Range<Integer> emptyOpen = Range.open(0, 1);
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomInt(random, emptyOpen));
    }

    @Test
    void randomInt_EmptyRange_OpenClosed_ThrowsException() {
        // (0, 0] 不包含任何整数
        Range<Integer> emptyOpenClosed = Range.openClosed(0, 0);
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomInt(random, emptyOpenClosed));
    }

    @Test
    void randomInt_EmptyRange_LessThanMinValue_ThrowsException() {
        // (-∞, Integer.MIN_VALUE) 不包含任何整数，且 endInclusive 计算可能溢出
        Range<Integer> lessThanMin = Range.lessThan(Integer.MIN_VALUE);
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomInt(random, lessThanMin));
    }

    @Test
    void randomInt_EmptyRange_GreaterThanMaxValue_ThrowsException() {
        // (Integer.MAX_VALUE, +∞) 不包含任何整数，且 startInclusive 计算可能溢出
        Range<Integer> greaterThanMax = Range.greaterThan(Integer.MAX_VALUE);
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomInt(random, greaterThanMax));
    }

    // ================================
    // #endregion - randomInt empty range validation
    // ================================

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = RandomTools.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }
}
