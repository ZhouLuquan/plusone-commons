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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

@SuppressWarnings("null")
public
class StringToolsTests {

    @Test
    void isNotBlank_NullString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank(null));
    }

    @Test
    void isNotBlank_EmptyString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank(""));
    }

    @Test
    void isNotBlank_WhitespaceString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank("   "));
    }

    @Test
    void isNotBlank_NonWhitespaceString_ReturnsTrue() {
        assertTrue(StringTools.isNotBlank("Hello"));
    }

    @Test
    void repeat_NullString_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            StringTools.repeat(null, 2);
        });
        assertNull(exception.getMessage());
    }

    @Test
    void repeat_EmptyString_ReturnsEmptyString() {
        assertEquals("", StringTools.repeat("", 2));
    }

    @Test
    void repeat_RepeatOnce_ReturnsOriginalString() {
        assertEquals("Hello", StringTools.repeat("Hello", 1));
    }

    @Test
    void repeat_RepeatMultipleTimes_ReturnsRepeatedString() {
        assertEquals("HelloHelloHello", StringTools.repeat("Hello", 3));
    }

    @Test
    void repeat_ExceedsMaxLength_ReturnsTruncatedString() {
        assertEquals("HelloHello", StringTools.repeat("Hello", 2, 14));
        assertEquals("HelloHel", StringTools.repeat("Hello", 2, 8));
        assertEquals("He", StringTools.repeat("Hello", 2, 2));
        assertEquals("", StringTools.repeat("Hello", 0, 2));
        assertThrows(IllegalArgumentException.class, () -> StringTools.repeat("Hello", -1, 2));
        assertThrows(IllegalArgumentException.class, () -> StringTools.repeat("Hello", 5, -1));
    }

    @Test
    void repeat_ZeroTimes_ReturnsEmptyString() {
        assertEquals("", StringTools.repeat("Hello", 0));
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = StringTools.class.getDeclaredConstructors();
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
