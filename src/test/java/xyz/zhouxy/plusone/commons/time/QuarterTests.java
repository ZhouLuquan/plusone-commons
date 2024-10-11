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

import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;
import java.time.MonthDay;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public
class QuarterTests {

    @Test
    void testQ1() {
        Quarter quarter = Quarter.of(1);
        assertSame(Quarter.Q1, quarter);
        assertSame(quarter, Quarter.valueOf("Q1"));
        assertEquals(1, quarter.getValue());
        assertEquals("Q1", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.of(0);
        });

        // ==========

        int firstMonthValue = quarter.firstMonthValue();
        log.info("firstMonthValue: {}", firstMonthValue);
        assertEquals(1, firstMonthValue);

        Month firstMonth = quarter.firstMonth();
        log.info("firstMonth: {}", firstMonth);
        assertEquals(Month.JANUARY, firstMonth);

        assertEquals(firstMonthValue, firstMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(3, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.MARCH, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay firstMonthDay = quarter.firstMonthDay();
        log.info("firstMonthDay: {}", firstMonthDay);
        assertEquals(firstMonthDay, MonthDay.of(1, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(3, 31));
    }

    @Test
    void testQ2() {
        Quarter quarter = Quarter.of(2);
        assertSame(Quarter.Q2, quarter);
        assertSame(quarter, Quarter.valueOf("Q2"));
        assertEquals(2, quarter.getValue());
        assertEquals("Q2", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.of(5);
        });

        // ==========

        int firstMonthValue = quarter.firstMonthValue();
        log.info("firstMonthValue: {}", firstMonthValue);
        assertEquals(4, firstMonthValue);

        Month firstMonth = quarter.firstMonth();
        log.info("firstMonth: {}", firstMonth);
        assertEquals(Month.APRIL, firstMonth);

        assertEquals(firstMonthValue, firstMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(6, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.JUNE, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay firstMonthDay = quarter.firstMonthDay();
        log.info("firstMonthDay: {}", firstMonthDay);
        assertEquals(firstMonthDay, MonthDay.of(4, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(6, 30));
    }

    @Test
    void testQ3() {
        Quarter quarter = Quarter.of(3);
        assertSame(Quarter.Q3, quarter);
        assertSame(quarter, Quarter.valueOf("Q3"));
        assertEquals(3, quarter.getValue());
        assertEquals("Q3", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.valueOf("Abc");
        });

        // ==========

        int firstMonthValue = quarter.firstMonthValue();
        log.info("firstMonthValue: {}", firstMonthValue);
        assertEquals(7, firstMonthValue);

        Month firstMonth = quarter.firstMonth();
        log.info("firstMonth: {}", firstMonth);
        assertEquals(Month.JULY, firstMonth);

        assertEquals(firstMonthValue, firstMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(9, lastMonthValue);

        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.SEPTEMBER, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay firstMonthDay = quarter.firstMonthDay();
        log.info("firstMonthDay: {}", firstMonthDay);
        assertEquals(firstMonthDay, MonthDay.of(7, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(9, 30));
    }

    @Test
    void testQ4() {
        Quarter quarter = Quarter.of(4);
        assertSame(Quarter.Q4, quarter);
        assertSame(quarter, Quarter.valueOf("Q4"));
        assertEquals(4, quarter.getValue());
        assertEquals("Q4", quarter.name());

        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.valueOf("Q5");
        });

        // ==========

        int firstMonthValue = quarter.firstMonthValue();
        log.info("firstMonthValue: {}", firstMonthValue);
        assertEquals(10, firstMonthValue);

        Month firstMonth = quarter.firstMonth();
        log.info("firstMonth: {}", firstMonth);
        assertEquals(Month.OCTOBER, firstMonth);

        assertEquals(firstMonthValue, firstMonth.getValue());

        // ==========

        int lastMonthValue = quarter.lastMonthValue();
        log.info("lastMonthValue: {}", lastMonthValue);
        assertEquals(12, lastMonthValue);
        Month lastMonth = quarter.lastMonth();
        log.info("lastMonth: {}", lastMonth);
        assertEquals(Month.DECEMBER, lastMonth);

        assertEquals(lastMonthValue, lastMonth.getValue());

        // ==========

        MonthDay firstMonthDay = quarter.firstMonthDay();
        log.info("firstMonthDay: {}", firstMonthDay);
        assertEquals(firstMonthDay, MonthDay.of(10, 1));

        MonthDay lastMonthDay = quarter.lastMonthDay();
        log.info("lastMonthDay: {}", lastMonthDay);
        assertEquals(lastMonthDay, MonthDay.of(12, 31));
    }

    @Test
    void testFirstDayOfYear() {
        int firstDayOfYear;

        firstDayOfYear = Quarter.Q1.firstDayOfYear(true);
        assertEquals(1, firstDayOfYear);
        firstDayOfYear = Quarter.Q1.firstDayOfYear(false);
        assertEquals(1, firstDayOfYear);

        firstDayOfYear = Quarter.Q2.firstDayOfYear(true);
        assertEquals(1 + (31 + 29 + 31), firstDayOfYear);
        firstDayOfYear = Quarter.Q2.firstDayOfYear(false);
        assertEquals(1 + (31 + 28 + 31), firstDayOfYear);

        firstDayOfYear = Quarter.Q3.firstDayOfYear(true);
        assertEquals(1 + (31 + 29 + 31) + (30 + 31 + 30), firstDayOfYear);
        firstDayOfYear = Quarter.Q3.firstDayOfYear(false);
        assertEquals(1 + (31 + 28 + 31) + (30 + 31 + 30), firstDayOfYear);

        firstDayOfYear = Quarter.Q4.firstDayOfYear(true);
        assertEquals(1 + (31 + 29 + 31) + (30 + 31 + 30) + (31 + 30 + 31), firstDayOfYear);
        firstDayOfYear = Quarter.Q4.firstDayOfYear(false);
        assertEquals(1 + (31 + 28 + 31) + (30 + 31 + 30) + (31 + 30 + 31), firstDayOfYear);
    }
}
