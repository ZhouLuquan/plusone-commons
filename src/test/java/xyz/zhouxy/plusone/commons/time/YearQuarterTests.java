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

package xyz.zhouxy.plusone.commons.time;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class YearQuarterTests {

    @Test
    void of_ValidYearAndQuarter_CreatesYearQuarter() {
        int year = 2023;
        Quarter quarter = Quarter.Q1;

        YearQuarter expected = YearQuarter.of(year, quarter);
        YearQuarter actual = YearQuarter.of(LocalDate.of(year, 2, 28));

        assertEquals(expected, actual);

        assertEquals("2023 Q1", actual.toString());
    }

    @Test
    @SuppressWarnings("null")
    void of_InvalidQuarter_ThrowsNullPointerException() {
        int year = 2023;
        Quarter quarter = null;

        assertThrows(NullPointerException.class, () -> YearQuarter.of(year, quarter));
    }

    @Test
    void of_ValidYearQuarter_GetsCorrectStartAndEndDate() {

        for (int year = 1990; year <= 2024; year++) {
            for (int quarterValue = 1; quarterValue <= 4; quarterValue++) {
                Quarter quarter = Quarter.of(quarterValue);
                YearQuarter yearQuarter = YearQuarter.of(year, quarter);

                LocalDate expectedStartDate = quarter.firstMonthDay().atYear(year);
                log.info("{} - expectedStartDate: {}", yearQuarter, expectedStartDate);
                LocalDate expectedEndDate = quarter.lastMonthDay().atYear(year);
                log.info("{} - expectedEndDate: {}", yearQuarter, expectedEndDate);

                assertEquals(expectedStartDate, yearQuarter.firstDate());
                assertEquals(expectedEndDate, yearQuarter.lastDate());
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {
            2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009,
            2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019,
            2020, 2021, 2022, 2023, 2024
    })
    void testFirstYearMonth(int year) {
        YearQuarter yq;

        yq = YearQuarter.of(year, Quarter.Q1);
        assertEquals(YearMonth.of(year, 1), yq.firstYearMonth());
        yq = YearQuarter.of(year, Quarter.Q2);
        assertEquals(YearMonth.of(year, 4), yq.firstYearMonth());
        yq = YearQuarter.of(year, Quarter.Q3);
        assertEquals(YearMonth.of(year, 7), yq.firstYearMonth());
        yq = YearQuarter.of(year, Quarter.Q4);
        assertEquals(YearMonth.of(year, 10), yq.firstYearMonth());

        yq = YearQuarter.of(year, Quarter.Q1);
        assertEquals(YearMonth.of(year, 3), yq.lastYearMonth());
        yq = YearQuarter.of(year, Quarter.Q2);
        assertEquals(YearMonth.of(year, 6), yq.lastYearMonth());
        yq = YearQuarter.of(year, Quarter.Q3);
        assertEquals(YearMonth.of(year, 9), yq.lastYearMonth());
        yq = YearQuarter.of(year, Quarter.Q4);
        assertEquals(YearMonth.of(year, 12), yq.lastYearMonth());
    }

    @Test
    void testCompareTo() {
        int year1;
        int quarter1;
        YearQuarter yearQuarter1;

        year1 = 2024;
        quarter1 = 1;
        yearQuarter1 = YearQuarter.of(year1, Quarter.of(quarter1));

        for (int year2 = 2000; year2 <= 2050; year2++) {
            for (int quarter2 = 1; quarter2 <= 4; quarter2++) {
                YearQuarter yearQuarter2 = YearQuarter.of(year2, Quarter.of(quarter2));

                if (year1 == year2) {
                    // 同年
                    assertEquals(quarter1 - quarter2, yearQuarter1.compareTo(yearQuarter2));

                    if (quarter1 == quarter2) {
                        // 同年同季度
                        assertEquals(yearQuarter1, yearQuarter2);
                        assertEquals(0, yearQuarter1.compareTo(yearQuarter2));
                    }
                    else if (quarter1 < quarter2) {
                        assertNotEquals(yearQuarter1, yearQuarter2);
                        assertTrue(yearQuarter1.isBefore(yearQuarter2));
                        assertFalse(yearQuarter1.isAfter(yearQuarter2));
                        assertFalse(yearQuarter2.isBefore(yearQuarter1));
                        assertTrue(yearQuarter2.isAfter(yearQuarter1));
                    }
                    else if (quarter1 > quarter2) {
                        assertNotEquals(yearQuarter1, yearQuarter2);
                        assertFalse(yearQuarter1.isBefore(yearQuarter2));
                        assertTrue(yearQuarter1.isAfter(yearQuarter2));
                        assertTrue(yearQuarter2.isBefore(yearQuarter1));
                        assertFalse(yearQuarter2.isAfter(yearQuarter1));
                    }
                }
                else {
                    // 不同年
                    assertEquals(year1 - year2, yearQuarter1.compareTo(yearQuarter2));
                    assertNotEquals(0, yearQuarter1.compareTo(yearQuarter2));
                    if (year1 < year2) {
                        assertNotEquals(yearQuarter1, yearQuarter2);
                        assertTrue(yearQuarter1.isBefore(yearQuarter2));
                        assertFalse(yearQuarter1.isAfter(yearQuarter2));
                        assertFalse(yearQuarter2.isBefore(yearQuarter1));
                        assertTrue(yearQuarter2.isAfter(yearQuarter1));
                    }
                    else if (year1 > year2) {
                        assertNotEquals(yearQuarter1, yearQuarter2);
                        assertFalse(yearQuarter1.isBefore(yearQuarter2));
                        assertTrue(yearQuarter1.isAfter(yearQuarter2));
                        assertTrue(yearQuarter2.isBefore(yearQuarter1));
                        assertFalse(yearQuarter2.isAfter(yearQuarter1));
                    }
                }
            }
        }
    }
}
