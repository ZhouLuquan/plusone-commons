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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("deprecation")
public
class EnumToolsTests {

    private enum MyEnum {
        VALUE_0,
        VALUE_1,
        VALUE_2,
        VALUE_3,
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    void testCheckOrdinal_success(int ordinal) {
        assertEquals(ordinal, EnumTools.checkOrdinal(MyEnum.class, ordinal));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testCheckOrdinal_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class,
                () -> EnumTools.checkOrdinal(MyEnum.class, ordinal));
    }

    @Test
    void testCheckOrdinal_null() {
        assertThrows(NullPointerException.class,
                () -> EnumTools.checkOrdinal(MyEnum.class, null));
        assertThrows(NullPointerException.class,
                () -> EnumTools.checkOrdinal(null, 0));
        assertThrows(NullPointerException.class,
                () -> EnumTools.checkOrdinal(null, null));
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    void testCheckOrdinalNullable_success(int ordinal) {
        assertEquals(ordinal, EnumTools.checkOrdinalNullable(MyEnum.class, ordinal));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testCheckOrdinalNullable_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.checkOrdinalNullable(MyEnum.class, ordinal);
        });
    }

    @Test
    void testCheckOrdinalNullable_null() {
        assertNull(EnumTools.checkOrdinalNullable(MyEnum.class, null));

        assertThrows(NullPointerException.class, () -> {
            EnumTools.checkOrdinalNullable(null, 0);
        });

        assertThrows(NullPointerException.class, () -> {
            EnumTools.checkOrdinalNullable(null, null);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 2, 3 })
    void testCheckOrdinalOrDefault_0To3(int ordinal) {
        assertEquals(ordinal, EnumTools.checkOrdinalOrDefault(MyEnum.class, ordinal));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testCheckOrdinalOrDefault_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.checkOrdinalOrDefault(MyEnum.class, ordinal);
        });
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.checkOrdinalOrDefault(MyEnum.class, null, ordinal);
        });
    }

    @Test
    void testCheckOrdinalOrDefault_null() {
        assertEquals(0, EnumTools.checkOrdinalOrDefault(MyEnum.class, null));
        assertEquals(1, EnumTools.checkOrdinalOrDefault(MyEnum.class, null, 1));
        assertNull(EnumTools.checkOrdinalOrDefault(MyEnum.class, null, null));
    }

    @Test
    void testGetValueNullable_0To3() {
        assertSame(MyEnum.VALUE_0, EnumTools.getValueNullable(MyEnum.class, 0));
        assertSame(MyEnum.VALUE_1, EnumTools.getValueNullable(MyEnum.class, 1));
        assertSame(MyEnum.VALUE_2, EnumTools.getValueNullable(MyEnum.class, 2));
        assertSame(MyEnum.VALUE_3, EnumTools.getValueNullable(MyEnum.class, 3));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testGetValueNullable_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.getValueNullable(MyEnum.class, ordinal);
        });
    }

    @Test
    void testGetValueNullable_null() {
        assertNull(EnumTools.getValueNullable(MyEnum.class, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueNullable(null, 0));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueNullable(null, null));
    }

    @Test
    void testGetValueOrDefault_0To3() {
        assertSame(MyEnum.VALUE_0, EnumTools.getValueOrDefault(MyEnum.class, 0));
        assertSame(MyEnum.VALUE_1, EnumTools.getValueOrDefault(MyEnum.class, 1));
        assertSame(MyEnum.VALUE_2, EnumTools.getValueOrDefault(MyEnum.class, 2));
        assertSame(MyEnum.VALUE_3, EnumTools.getValueOrDefault(MyEnum.class, 3));

        assertSame(MyEnum.VALUE_0, EnumTools.getValueOrDefault(MyEnum.class, 0, () -> MyEnum.VALUE_1));
        assertSame(MyEnum.VALUE_1, EnumTools.getValueOrDefault(MyEnum.class, 1, () -> MyEnum.VALUE_0));
        assertSame(MyEnum.VALUE_2, EnumTools.getValueOrDefault(MyEnum.class, 2, () -> MyEnum.VALUE_0));
        assertSame(MyEnum.VALUE_3, EnumTools.getValueOrDefault(MyEnum.class, 3, () -> MyEnum.VALUE_0));

        assertSame(MyEnum.VALUE_0, EnumTools.getValueOrDefault(MyEnum.class, 0, () -> null));
        assertSame(MyEnum.VALUE_1, EnumTools.getValueOrDefault(MyEnum.class, 1, () -> null));
        assertSame(MyEnum.VALUE_2, EnumTools.getValueOrDefault(MyEnum.class, 2, () -> null));
        assertSame(MyEnum.VALUE_3, EnumTools.getValueOrDefault(MyEnum.class, 3, () -> null));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testGetValueOrDefault_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.getValueOrDefault(MyEnum.class, ordinal);
        });
        assertThrows(EnumConstantNotPresentException.class, () -> {
            EnumTools.getValueOrDefault(MyEnum.class, ordinal, () -> MyEnum.VALUE_0);
        });
    }

    @Test
    void testGetValueOrDefault_null() {
        assertSame(MyEnum.VALUE_0, EnumTools.getValueOrDefault(MyEnum.class, null));
        assertSame(MyEnum.VALUE_0, EnumTools.getValueOrDefault(MyEnum.class, null, () -> MyEnum.VALUE_0));
        assertNull(EnumTools.getValueOrDefault(MyEnum.class, null, () -> null));

        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, -1));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 0));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 4));

        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, null, () -> MyEnum.VALUE_0));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, -1, () -> MyEnum.VALUE_1));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 0, () -> MyEnum.VALUE_1));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 4, () -> MyEnum.VALUE_0));

        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(MyEnum.class, null, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(MyEnum.class, -1, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(MyEnum.class, 0, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(MyEnum.class, 4, null));

        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, null, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, -1, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 0, null));
        assertThrows(NullPointerException.class, () -> EnumTools.getValueOrDefault(null, 4, null));

    }

    @Test
    void testValueOf_0To3() {
        assertSame(MyEnum.VALUE_0, EnumTools.valueOf(MyEnum.class, 0));
        assertSame(MyEnum.VALUE_1, EnumTools.valueOf(MyEnum.class, 1));
        assertSame(MyEnum.VALUE_2, EnumTools.valueOf(MyEnum.class, 2));
        assertSame(MyEnum.VALUE_3, EnumTools.valueOf(MyEnum.class, 3));

        assertSame(MyEnum.VALUE_0, EnumTools.valueOf(MyEnum.class, 0, MyEnum.VALUE_1));
        assertSame(MyEnum.VALUE_1, EnumTools.valueOf(MyEnum.class, 1, MyEnum.VALUE_0));
        assertSame(MyEnum.VALUE_2, EnumTools.valueOf(MyEnum.class, 2, MyEnum.VALUE_0));
        assertSame(MyEnum.VALUE_3, EnumTools.valueOf(MyEnum.class, 3, MyEnum.VALUE_0));

        assertSame(MyEnum.VALUE_0, EnumTools.valueOf(MyEnum.class, 0, null));
        assertSame(MyEnum.VALUE_1, EnumTools.valueOf(MyEnum.class, 1, null));
        assertSame(MyEnum.VALUE_2, EnumTools.valueOf(MyEnum.class, 2, null));
        assertSame(MyEnum.VALUE_3, EnumTools.valueOf(MyEnum.class, 3, null));
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 4 })
    void testValueOf_EnumConstantNotPresentException(int ordinal) {
        assertThrows(EnumConstantNotPresentException.class, () -> EnumTools.valueOf(MyEnum.class, ordinal));
        assertThrows(EnumConstantNotPresentException.class, () -> EnumTools.valueOf(MyEnum.class, ordinal, MyEnum.VALUE_0));
        assertThrows(EnumConstantNotPresentException.class, () -> EnumTools.valueOf(MyEnum.class, ordinal, null));
    }

    @Test
    void testValueOf_null() {
        assertThrows(NullPointerException.class, () -> EnumTools.valueOf(null, 0));
        assertSame(MyEnum.VALUE_0, EnumTools.valueOf(MyEnum.class, null, MyEnum.VALUE_0));
        assertNull(EnumTools.valueOf(MyEnum.class, null, null));
    }
}
