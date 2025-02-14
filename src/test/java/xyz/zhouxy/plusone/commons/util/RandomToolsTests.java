/*
 * Copyright 2024-2025 the original author or authors.
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

import java.lang.reflect.Constructor;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("null")
public class RandomToolsTests {

    private static Random random;
    private static SecureRandom secureRandom;
    private static char[] sourceCharactersArray;
    private static String sourceCharactersString;

    @BeforeAll
    public static void setUp() {
        random = new Random();
        secureRandom = new SecureRandom();
        sourceCharactersArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        sourceCharactersString = "abcdefghijklmnopqrstuvwxyz";
    }

    @Test
    public void randomStr_NullRandom_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersArray, 5));
        assertThrows(IllegalArgumentException.class,
                () -> RandomTools.randomStr(null, sourceCharactersString, 5));
    }

    @Test
    public void randomStr_NullSourceCharacters_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, (char[]) null, 5));
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, (String) null, 5));
    }

    @Test
    public void randomStr_NegativeLength_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RandomTools.randomStr(random, sourceCharactersArray, -1));
    }

    @Test
    public void randomStr_ZeroLength_ReturnsEmptyString() {
        assertAll(
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(random, sourceCharactersString, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersArray, 0)),
                () -> assertEquals("", RandomTools.randomStr(secureRandom, sourceCharactersString, 0)));
    }

    @Test
    public void randomStr_PositiveLength_ReturnsRandomString() {
        assertAll(
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(random, sourceCharactersString, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersArray, 5).length()),
                () -> assertEquals(5, RandomTools.randomStr(secureRandom, sourceCharactersString, 5).length()));
    }

    @Test
    public void randomStr_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void randomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.randomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void secureRandomStr_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersArray, 5);
        assertEquals(5, result.length());
    }

    @Test
    public void secureRandomStr_StringSourceCharacters_ReturnsRandomString() {
        String result = RandomTools.secureRandomStr(sourceCharactersString, 5);
        assertEquals(5, result.length());
    }

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
