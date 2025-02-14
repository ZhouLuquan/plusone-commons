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
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import org.junit.jupiter.api.Test;

/**
 * {@link OptionalTools} 单元测试
 */
public
class OptionalToolsTests {

    @Test
    void optionalOf_NullInteger_ReturnsEmptyOptionalInt() {
        OptionalInt result = OptionalTools.optionalOf((Integer) null);
        assertFalse(result.isPresent());
    }

    @Test
    void optionalOf_ValidInteger_ReturnsOptionalIntWithValue() {
        OptionalInt result = OptionalTools.optionalOf(10);
        assertTrue(result.isPresent());
        assertEquals(10, result.getAsInt());
    }

    @Test
    void toOptionalInt_NullOptionalInteger_ReturnsEmptyOptionalInt() {
        OptionalInt result = OptionalTools.toOptionalInt(Optional.ofNullable(null));
        assertFalse(result.isPresent());
    }

    @Test
    void toOptionalInt_ValidOptionalInteger_ReturnsOptionalIntWithValue() {
        OptionalInt result = OptionalTools.toOptionalInt(Optional.of(10));
        assertTrue(result.isPresent());
        assertEquals(10, result.getAsInt());
    }

    @Test
    void optionalOf_NullLong_ReturnsEmptyOptionalLong() {
        OptionalLong result = OptionalTools.optionalOf((Long) null);
        assertFalse(result.isPresent());
    }

    @Test
    void optionalOf_ValidLong_ReturnsOptionalLongWithValue() {
        OptionalLong result = OptionalTools.optionalOf(10L);
        assertTrue(result.isPresent());
        assertEquals(10L, result.getAsLong());
    }

    @Test
    void toOptionalLong_NullOptionalLong_ReturnsEmptyOptionalLong() {
        OptionalLong result = OptionalTools.toOptionalLong(Optional.ofNullable(null));
        assertFalse(result.isPresent());
    }

    @Test
    void toOptionalLong_ValidOptionalLong_ReturnsOptionalLongWithValue() {
        OptionalLong result = OptionalTools.toOptionalLong(Optional.of(10L));
        assertTrue(result.isPresent());
        assertEquals(10L, result.getAsLong());
    }

    @Test
    void optionalOf_NullDouble_ReturnsEmptyOptionalDouble() {
        OptionalDouble result = OptionalTools.optionalOf((Double) null);
        assertFalse(result.isPresent());
    }

    @Test
    void optionalOf_ValidDouble_ReturnsOptionalDoubleWithValue() {
        OptionalDouble result = OptionalTools.optionalOf(10.0);
        assertTrue(result.isPresent());
        assertEquals(10.0, result.getAsDouble(), 0.0001);
    }

    @Test
    void toOptionalDouble_NullOptionalDouble_ReturnsEmptyOptionalDouble() {
        OptionalDouble result = OptionalTools.toOptionalDouble(Optional.ofNullable(null));
        assertFalse(result.isPresent());
    }

    @Test
    void toOptionalDouble_ValidOptionalDouble_ReturnsOptionalDoubleWithValue() {
        OptionalDouble result = OptionalTools.toOptionalDouble(Optional.of(10.0));
        assertTrue(result.isPresent());
        assertEquals(10.0, result.getAsDouble(), 0.0001);
    }

    @Test
    void orElseNull_NullOptional_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> OptionalTools.orElseNull(null));
    }

    @Test
    void orElseNull_EmptyOptional_ReturnsNull() {
        Object result = OptionalTools.orElseNull(Optional.empty());
        assertNull(result);
    }

    @Test
    void orElseNull_PresentOptional_ReturnsValue() {
        Object result = OptionalTools.orElseNull(Optional.of("test"));
        assertEquals("test", result);
    }

    @Test
    void toInteger_NullOptionalInt_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> OptionalTools.toInteger(null));
    }

    @Test
    void toInteger_EmptyOptionalInt_ReturnsNull() {
        Integer result = OptionalTools.toInteger(OptionalInt.empty());
        assertNull(result);
    }

    @Test
    void toInteger_PresentOptionalInt_ReturnsValue() {
        Integer result = OptionalTools.toInteger(OptionalInt.of(10));
        assertEquals(10, result);
    }

    @Test
    void toLong_NullOptionalLong_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> OptionalTools.toLong(null));
    }

    @Test
    void toLong_EmptyOptionalLong_ReturnsNull() {
        Long result = OptionalTools.toLong(OptionalLong.empty());
        assertNull(result);
    }

    @Test
    void toLong_PresentOptionalLong_ReturnsValue() {
        Long result = OptionalTools.toLong(OptionalLong.of(10L));
        assertEquals(10L, result);
    }

    @Test
    void toDouble_NullOptionalDouble_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class,
                () -> OptionalTools.toDouble(null));
    }

    @Test
    void toDouble_EmptyOptionalDouble_ReturnsNull() {
        Double result = OptionalTools.toDouble(OptionalDouble.empty());
        assertNull(result);
    }

    @Test
    void toDouble_PresentOptionalDouble_ReturnsValue() {
        Double result = OptionalTools.toDouble(OptionalDouble.of(10.0));
        assertEquals(10.0, result, 0.0001);
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = OptionalTools.class.getDeclaredConstructors();
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
