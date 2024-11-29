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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Chinese2ndGenIDCardNumberTests {

    @Test
    void testPattern() {
        Matcher matcher = Chinese2ndGenIDCardNumber.PATTERN.matcher("11010520000101111X");
        assertTrue(matcher.matches());
        for (int i = 0; i < matcher.groupCount(); i++) {
            log.info("{}: {}", i, matcher.group(i));
        }
    }

    @Test
    void test() {
        Chinese2ndGenIDCardNumber idCardNumber = Chinese2ndGenIDCardNumber.of("11010520000101111X");
        assertEquals("11010520000101111X", idCardNumber.getValue());
        assertEquals(LocalDate.of(2000, 1, 1), idCardNumber.getBirthDate());
        assertEquals(Gender.MALE, idCardNumber.getGender());
        assertEquals("110105", idCardNumber.getCountyCode());
        assertEquals("110105000000", idCardNumber.getFullCountyCode());

        assertEquals("1101", idCardNumber.getCityCode());
        assertEquals("110100000000", idCardNumber.getFullCityCode());

        assertEquals("11", idCardNumber.getProvinceCode());
        assertEquals("110000000000", idCardNumber.getFullProvinceCode());

        assertEquals("北京", idCardNumber.getProvinceName());

        assertThrows(IllegalArgumentException.class,
                () -> Chinese2ndGenIDCardNumber.of("1101520000101111"));

        assertThrows(IllegalArgumentException.class,
                () -> Chinese2ndGenIDCardNumber.of("11010520002101111X"));

        try {
            Chinese2ndGenIDCardNumber.of("11010520002101111X");
        }
        catch (IllegalArgumentException e) {
            assertTrue(e.getCause() instanceof DateTimeParseException);
        }
    }
}
