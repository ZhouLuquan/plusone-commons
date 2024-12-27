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

package xyz.zhouxy.plusone.commons.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Chinese2ndGenIDCardNumberTests {

    @Test
    void testOf_success() {
        Chinese2ndGenIDCardNumber idCardNumber = Chinese2ndGenIDCardNumber.of("11010520000101111X");
        assertEquals("11010520000101111X", idCardNumber.value());
        assertEquals(LocalDate.of(2000, 1, 1), idCardNumber.getBirthDate());
        assertSame(Gender.MALE, idCardNumber.getGender());
        assertEquals("110105", idCardNumber.getCountyCode());
        assertEquals("110105000000", idCardNumber.getFullCountyCode());

        assertEquals("1101", idCardNumber.getCityCode());
        assertEquals("110100000000", idCardNumber.getFullCityCode());

        assertEquals("11", idCardNumber.getProvinceCode());
        assertEquals("110000000000", idCardNumber.getFullProvinceCode());

        assertEquals("北京", idCardNumber.getProvinceName());

        assertEquals("1***************1X", idCardNumber.toDesensitizedString());
        assertEquals("110***********111X", idCardNumber.toDesensitizedString(3, 4));
        assertEquals("11############111X", idCardNumber.toDesensitizedString('#', 2, 4));
    }

    @Test
    void testOf_blankValue() {
        String[] strings = { null, "", " " };
        for (String value : strings) {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                    () -> Chinese2ndGenIDCardNumber.of(value));
            assertEquals("二代居民身份证校验失败：号码为空", e.getMessage());
        }
    }
    @ParameterizedTest
    @ValueSource(strings = { "1101520000101111", "110A0520000101111X", "110105220000101111X" })
    void testOf_mismatched(String value) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Chinese2ndGenIDCardNumber.of(value));
        assertEquals("二代居民身份证校验失败：" + value, e.getMessage());
    }

    @Test
    void testOf_wrongBirthDate() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Chinese2ndGenIDCardNumber.of("11010520002101111X"));
        assertTrue(e.getCause() instanceof DateTimeParseException);
    }

    @Test
    void testOf_wrongProvince() {
        assertThrows(IllegalArgumentException.class,
                () -> Chinese2ndGenIDCardNumber.of("99010520000101111X"));
    }
}
