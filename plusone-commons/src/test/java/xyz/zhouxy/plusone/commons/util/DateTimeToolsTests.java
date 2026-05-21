/*
 * Copyright 2023-2025 the original author or authors.
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

import xyz.zhouxy.plusone.commons.time.Quarter;
import xyz.zhouxy.plusone.commons.time.YearQuarter;

class DateTimeToolsTests {

    private static final Logger log = LoggerFactory.getLogger(DateTimeToolsTests.class);

    // Java
    static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2024, 12, 29, 12, 58, 30, 333000000);
    static final LocalDate LOCAL_DATE = LOCAL_DATE_TIME.toLocalDate();
    static final LocalTime LOCAL_TIME = LOCAL_DATE_TIME.toLocalTime();

    // Java - 2024-12-29 12:58:30.333333333 SystemDefaultZone
    static final ZoneId SYS_ZONE_ID = ZoneId.systemDefault();
    static final ZonedDateTime ZONED_DATE_TIME_WITH_SYS_ZONE = LOCAL_DATE_TIME.atZone(SYS_ZONE_ID);
    static final Instant INSTANT_WITH_SYS_ZONE = ZONED_DATE_TIME_WITH_SYS_ZONE.toInstant();
    static final long INSTANT_MILLIS = INSTANT_WITH_SYS_ZONE.toEpochMilli();

    static final TimeZone SYS_TIME_ZONE = TimeZone.getDefault();
    static final Date SYS_DATE = Date.from(INSTANT_WITH_SYS_ZONE);
    static final Calendar SYS_CALENDAR = Calendar.getInstance(SYS_TIME_ZONE);
    static {
        SYS_CALENDAR.setTime(SYS_DATE);
    }

    // Java - 2024-12-29 12:58:30.333333333 GMT+04:00
    static final ZoneId ZONE_ID = ZoneId.of("GMT+04:00");
    static final ZonedDateTime ZONED_DATE_TIME = LOCAL_DATE_TIME.atZone(ZONE_ID);
    static final Instant INSTANT = ZONED_DATE_TIME.toInstant();
    static final long MILLIS = INSTANT.toEpochMilli();

    static final TimeZone TIME_ZONE = TimeZone.getTimeZone(ZONE_ID);
    static final Date DATE = Date.from(INSTANT);
    static final Calendar CALENDAR = Calendar.getInstance(TIME_ZONE);
    static {
        CALENDAR.setTime(DATE);
    }

    // ================================
    // #region - toDate
    // ================================

    @Test
    void toDate_timeMillis() {
        assertNotEquals(SYS_DATE, DATE);
        log.info("SYS_DATE: {}, DATE: {}", SYS_DATE, DATE);
        assertEquals(SYS_DATE, DateTimeTools.toDate(INSTANT_MILLIS));
        assertEquals(DATE, DateTimeTools.toDate(MILLIS));
    }

    @Test
    void toDate_calendar() {
        assertEquals(SYS_DATE, DateTimeTools.toDate(SYS_CALENDAR));
        assertEquals(DATE, DateTimeTools.toDate(CALENDAR));
    }

    @Test
    void toDate_instant() {
        assertEquals(SYS_DATE, DateTimeTools.toDate(INSTANT_WITH_SYS_ZONE));
        assertEquals(DATE, DateTimeTools.toDate(INSTANT));
    }

    @Test
    void toDate_ZoneDateTime() {
        assertEquals(SYS_DATE, DateTimeTools.toDate(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(DATE, DateTimeTools.toDate(ZONED_DATE_TIME));
    }

    @Test
    void toDate_LocalDateTimeAndZoneId() {
        assertEquals(SYS_DATE, DateTimeTools.toDate(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(DATE, DateTimeTools.toDate(LOCAL_DATE_TIME, ZONE_ID));
        assertEquals(SYS_DATE, DateTimeTools.toDate(LOCAL_DATE, LOCAL_TIME, SYS_ZONE_ID));
        assertEquals(DATE, DateTimeTools.toDate(LOCAL_DATE, LOCAL_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toDate
    // ================================

    // ================================
    // #region - toInstant
    // ================================

    @Test
    void toInstant_timeMillis() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toInstant(INSTANT_MILLIS));
        assertEquals(INSTANT, DateTimeTools.toInstant(MILLIS));
    }

    @Test
    void toInstant_Date() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toInstant(SYS_DATE));
        assertEquals(INSTANT, DateTimeTools.toInstant(DATE));
    }

    @Test
    void toInstant_Calendar() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toInstant(SYS_CALENDAR));
        assertEquals(INSTANT, DateTimeTools.toInstant(CALENDAR));
    }

    @Test
    void toInstant_ZonedDateTime() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toInstant(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(INSTANT, DateTimeTools.toInstant(ZONED_DATE_TIME));
    }

    @Test
    void toInstant_LocalDateTimeAndZone() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toInstant(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(INSTANT, DateTimeTools.toInstant(LOCAL_DATE_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toInstant
    // ================================

    // ================================
    // #region - toZonedDateTime
    // ================================

    @Test
    void toZonedDateTime_TimeMillisAndZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(INSTANT_MILLIS, SYS_ZONE_ID));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(MILLIS, ZONE_ID));
    }

    @Test
    void toZonedDateTime_DateAndZoneId() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(SYS_DATE, SYS_ZONE_ID));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(DATE, ZONE_ID));
    }

    @Test
    void toZonedDateTime_DateAndTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(SYS_DATE, SYS_TIME_ZONE));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(DATE, TIME_ZONE));
    }

    @Test
    void toZonedDateTime_Calendar() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(SYS_CALENDAR));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(CALENDAR));
    }

    @Test
    void toZonedDateTime_CalendarAndZoneId() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(SYS_CALENDAR, SYS_ZONE_ID));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(CALENDAR, ZONE_ID));
    }

    @Test
    void toZonedDateTime_CalendarAndTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(SYS_CALENDAR, SYS_TIME_ZONE));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(CALENDAR, TIME_ZONE));
    }

    @Test
    void toZonedDateTime_LocalDateTimeAndZoneId() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(LOCAL_DATE_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toZonedDateTime
    // ================================

    // ================================
    // #region - toLocalDateTime
    // ================================

    @Test
    void toLocalDateTime_TimeMillisAndZoneId() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(INSTANT_MILLIS, SYS_ZONE_ID));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(MILLIS, ZONE_ID));
    }

    @Test
    void toLocalDateTime_DateAndZoneId() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(SYS_DATE, SYS_ZONE_ID));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(DATE, ZONE_ID));
    }

    @Test
    void toLocalDateTime_DateAndTimeZone() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(SYS_DATE, SYS_TIME_ZONE));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(DATE, TIME_ZONE));
    }

    @Test
    void toLocalDateTime_CalendarAndZoneId() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(SYS_CALENDAR, SYS_ZONE_ID));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(CALENDAR, ZONE_ID));
    }

    @Test
    void toLocalDateTime_CalendarAndTimeZone() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(SYS_CALENDAR, SYS_TIME_ZONE));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(CALENDAR, TIME_ZONE));
    }

    @Test
    void toLocalDateTime_ZonedDateTimeAndZoneId() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(ZONED_DATE_TIME_WITH_SYS_ZONE, SYS_ZONE_ID));
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toLocalDateTime(ZONED_DATE_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toLocalDateTime
    // ================================

    // ================================
    // #region - YearQuarter & Quarter
    // ================================

    @Test
    void getQuarter() {
        YearQuarter expectedYearQuarter = YearQuarter.of(2024, 4);
        assertEquals(expectedYearQuarter, DateTimeTools.getQuarter(SYS_DATE));
        assertEquals(expectedYearQuarter, DateTimeTools.getQuarter(SYS_CALENDAR));
        assertEquals(Quarter.Q4, DateTimeTools.getQuarter(Month.DECEMBER));
        assertEquals(expectedYearQuarter, DateTimeTools.getQuarter(2024, Month.DECEMBER));
        assertEquals(expectedYearQuarter, DateTimeTools.getQuarter(YearMonth.of(2024, Month.DECEMBER)));
        assertEquals(expectedYearQuarter, DateTimeTools.getQuarter(LOCAL_DATE));

        Date date = DateTimeTools.toDate(LocalDate.of(2026, 1, 1).atStartOfDay(ZoneId.of("GMT+8")));
        assertEquals(YearQuarter.of(2026, 1), DateTimeTools.getQuarter(date, TimeZone.getTimeZone("GMT+8")));
        assertEquals(YearQuarter.of(2026, 1), DateTimeTools.getQuarter(date, ZoneId.of("GMT+8")));
        assertEquals(YearQuarter.of(2025, 4), DateTimeTools.getQuarter(date, TimeZone.getTimeZone("GMT+0")));
        assertEquals(YearQuarter.of(2025, 4), DateTimeTools.getQuarter(date, ZoneId.of("GMT+0")));
    }

    // ================================
    // #endregion - YearQuarter & Quarter
    // ================================

    // ================================
    // #region - start & end
    // ================================

    @Test
    void testStartAndEndDateOfYear() {
        assertEquals(LocalDate.of(2008, 1, 1), DateTimeTools.startDateOfYear(2008));
        assertEquals(LocalDate.of(2008, 12, 31), DateTimeTools.endDateOfYear(2008));
    }

    @Test
    void testStartOfNextDate() {
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0),
                DateTimeTools.startOfNextDate(LOCAL_DATE));
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0).atZone(SYS_ZONE_ID),
                DateTimeTools.startOfNextDate(LOCAL_DATE, SYS_ZONE_ID));
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0).atZone(ZONE_ID),
                DateTimeTools.startOfNextDate(LOCAL_DATE, ZONE_ID));
    }

    // ================================
    // #endregion - start & end
    // ================================

    // ================================
    // #region - isFuture & isPast
    // ================================

    @Test
    void test_isFuture_And_isPast_WhenFuture() {
        Date date = new Date(Instant.now().plusSeconds(10).toEpochMilli());
        assertTrue(DateTimeTools.isFuture(date));
        assertFalse(DateTimeTools.isPast(date));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+01:00"));
        calendar.add(Calendar.SECOND, 10);
        assertTrue(DateTimeTools.isFuture(calendar));
        assertFalse(DateTimeTools.isPast(calendar));

        Instant instant = Instant.now().plusSeconds(10);
        assertTrue(DateTimeTools.isFuture(instant));
        assertFalse(DateTimeTools.isPast(instant));

        long timeMillis = Instant.now().plusSeconds(10).toEpochMilli();
        assertTrue(DateTimeTools.isFuture(timeMillis));
        assertFalse(DateTimeTools.isPast(timeMillis));

        LocalDate localDate = LocalDate.now().plusDays(1);
        assertTrue(DateTimeTools.isFuture(localDate));
        assertFalse(DateTimeTools.isPast(localDate));

        LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(10);
        assertTrue(DateTimeTools.isFuture(localDateTime));
        assertFalse(DateTimeTools.isPast(localDateTime));

        ZonedDateTime zonedDateTime = Instant.now().plusSeconds(10).atZone(ZoneId.of("+01:00"));
        assertTrue(DateTimeTools.isFuture(zonedDateTime));
        assertFalse(DateTimeTools.isPast(zonedDateTime));
    }

    @Test
    void test_isFuture_And_isPast_WhenPast() {
        Date date = new Date(Instant.now().minusSeconds(10).toEpochMilli());
        assertFalse(DateTimeTools.isFuture(date));
        assertTrue(DateTimeTools.isPast(date));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("+01:00"));
        calendar.add(Calendar.SECOND, -10);
        assertFalse(DateTimeTools.isFuture(calendar));
        assertTrue(DateTimeTools.isPast(calendar));

        Instant instant = Instant.now().minusSeconds(10);
        assertFalse(DateTimeTools.isFuture(instant));
        assertTrue(DateTimeTools.isPast(instant));

        long timeMillis = Instant.now().minusSeconds(10).toEpochMilli();
        assertFalse(DateTimeTools.isFuture(timeMillis));
        assertTrue(DateTimeTools.isPast(timeMillis));

        LocalDate localDate = LocalDate.now().minusDays(1);
        assertFalse(DateTimeTools.isFuture(localDate));
        assertTrue(DateTimeTools.isPast(localDate));

        LocalDateTime localDateTime = LocalDateTime.now().minusSeconds(10);
        assertFalse(DateTimeTools.isFuture(localDateTime));
        assertTrue(DateTimeTools.isPast(localDateTime));

        ZonedDateTime zonedDateTime = Instant.now().minusSeconds(10).atZone(ZoneId.of("+01:00"));
        assertFalse(DateTimeTools.isFuture(zonedDateTime));
        assertTrue(DateTimeTools.isPast(zonedDateTime));
    }

    // ================================
    // #endregion - isFuture & isPast
    // ================================

    // ================================
    // #region - range
    // ================================

    @Test
    void toDateTimeRange_specifiedDate() {
        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(LOCAL_DATE);
        assertEquals(LOCAL_DATE.atStartOfDay(), localDateTimeRange.lowerEndpoint());
        assertEquals(LocalDate.of(2024, 12, 30).atStartOfDay(), localDateTimeRange.upperEndpoint());
        assertTrue(localDateTimeRange.contains(LOCAL_DATE.atStartOfDay()));
        assertFalse(localDateTimeRange.contains(LocalDate.of(2024, 12, 30).atStartOfDay()));

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(LOCAL_DATE, SYS_ZONE_ID);
        assertEquals(LOCAL_DATE.atStartOfDay().atZone(SYS_ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertEquals(LocalDate.of(2024, 12, 30).atStartOfDay().atZone(SYS_ZONE_ID), zonedDateTimeRange.upperEndpoint());
        assertTrue(zonedDateTimeRange.contains(LOCAL_DATE.atStartOfDay().atZone(SYS_ZONE_ID)));
        assertFalse(zonedDateTimeRange.contains(LocalDate.of(2024, 12, 30).atStartOfDay().atZone(SYS_ZONE_ID)));
    }

    @Test
    void toDateTimeRange_dateRange_openRange() {
        // (2000-01-01..2025-10-01) -> [2000-01-02T00:00..2025-10-01T00:00)
        Range<LocalDate> dateRange = Range.open(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2025, 10, 1));

        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(dateRange);
        assertEquals(BoundType.CLOSED, localDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 2, 0, 0), localDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, localDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 1, 0, 0), localDateTimeRange.upperEndpoint());
        log.info(localDateTimeRange.toString());

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(dateRange, ZONE_ID);
        assertEquals(BoundType.CLOSED, zonedDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 2, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, zonedDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 1, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.upperEndpoint());
        log.info(zonedDateTimeRange.toString());
    }

    @Test
    void toDateTimeRange_dateRange_openClosedRange() {
        // (2000-01-01..2025-10-01] -> [2025-01-02T00-00..2025-10-02 00:00)
        Range<LocalDate> dateRange = Range.openClosed(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2025, 10, 1));

        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(dateRange);
        assertEquals(BoundType.CLOSED, localDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 2, 0, 0), localDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, localDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 2, 0, 0), localDateTimeRange.upperEndpoint());
        log.info(localDateTimeRange.toString());

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(dateRange, ZONE_ID);
        assertEquals(BoundType.CLOSED, zonedDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 2, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, zonedDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 2, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.upperEndpoint());
        log.info(zonedDateTimeRange.toString());
    }

    @Test
    void toDateTimeRange_dateRange_closedRange() {
        // [2000-01-01..2025-10-01] -> [2025-01-01T00-00..2025-10-02 00:00)
        Range<LocalDate> dateRange = Range.closed(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2025, 10, 1));

        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(dateRange);
        assertEquals(BoundType.CLOSED, localDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0), localDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, localDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 2, 0, 0), localDateTimeRange.upperEndpoint());
        log.info(localDateTimeRange.toString());

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(dateRange, ZONE_ID);
        assertEquals(BoundType.CLOSED, zonedDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, zonedDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 2, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.upperEndpoint());
        log.info(zonedDateTimeRange.toString());
    }

    @Test
    void toDateTimeRange_dateRange_closedOpenRange() {
        // [2025-01-01..2025-10-01) -> [2025-01-01T00-00..2025-10-01 00:00)
        Range<LocalDate> dateRange = Range.closedOpen(
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2025, 10, 1));

        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(dateRange);
        assertEquals(BoundType.CLOSED, localDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0), localDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, localDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 1, 0, 0), localDateTimeRange.upperEndpoint());
        log.info(localDateTimeRange.toString());

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(dateRange, ZONE_ID);
        assertEquals(BoundType.CLOSED, zonedDateTimeRange.lowerBoundType());
        assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertEquals(BoundType.OPEN, zonedDateTimeRange.upperBoundType());
        assertEquals(LocalDateTime.of(2025, 10, 1, 0, 0).atZone(ZONE_ID), zonedDateTimeRange.upperEndpoint());
        log.info(zonedDateTimeRange.toString());
    }

    // ================================
    // #endregion - range
    // ================================

    // ================================
    // #region - toString
    // ================================

    @Test
    void testToString() {
        assertEquals("2024", DateTimeTools.toYearString(2024));
        assertEquals("999999999", DateTimeTools.toYearString(Year.MAX_VALUE));
        assertEquals("-999999999", DateTimeTools.toYearString(Year.MIN_VALUE));
        assertThrows(DateTimeException.class, () -> DateTimeTools.toYearString(Year.MIN_VALUE - 1));
        assertThrows(DateTimeException.class, () -> DateTimeTools.toYearString(Year.MAX_VALUE + 1));

        assertEquals("2024", DateTimeTools.toYearString(Year.of(2024)));
        assertEquals("999999999", DateTimeTools.toYearString(Year.of(Year.MAX_VALUE)));
        assertEquals("-999999999", DateTimeTools.toYearString(Year.of(Year.MIN_VALUE)));

        assertEquals("01", DateTimeTools.toMonthStringMM(1));
        assertEquals("02", DateTimeTools.toMonthStringMM(2));
        assertEquals("3", DateTimeTools.toMonthStringM(3));
        assertEquals("04", DateTimeTools.toMonthStringMM(Month.APRIL));
        assertEquals("05", DateTimeTools.toMonthStringMM(Month.MAY));
        assertEquals("6", DateTimeTools.toMonthStringM(Month.JUNE));

        assertThrows(DateTimeException.class, () -> DateTimeTools.toMonthStringM(0));
        assertThrows(DateTimeException.class, () -> DateTimeTools.toMonthStringMM(0));
        assertThrows(DateTimeException.class, () -> DateTimeTools.toMonthStringM(13));
        assertThrows(DateTimeException.class, () -> DateTimeTools.toMonthStringMM(13));
    }

    // ================================
    // #endregion - toString
    // ================================

    // ================================
    // #region - invoke constructor
    // ================================

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = DateTimeTools.class.getDeclaredConstructors();
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

    // ================================
    // #endregion - invoke constructor
    // ================================

}
