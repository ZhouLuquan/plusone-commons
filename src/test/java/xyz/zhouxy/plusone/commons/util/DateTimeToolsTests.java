/*
 * Copyright 2023-2024 the original author or authors.
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    // Joda
    static final org.joda.time.LocalDateTime JODA_LOCAL_DATE_TIME
            = new org.joda.time.LocalDateTime(2024, 12, 29, 12, 58, 30, 333);
    static final org.joda.time.LocalDate JODA_LOCAL_DATE = JODA_LOCAL_DATE_TIME.toLocalDate();
    static final org.joda.time.LocalTime JODA_LOCAL_TIME = JODA_LOCAL_DATE_TIME.toLocalTime();

    // Joda - 2024-12-29 12:58:30.333 SystemDefaultZone
    static final org.joda.time.DateTimeZone JODA_SYS_ZONE = org.joda.time.DateTimeZone.getDefault();
    static final org.joda.time.DateTime JODA_DATE_TIME_WITH_SYS_ZONE = JODA_LOCAL_DATE_TIME.toDateTime(JODA_SYS_ZONE);
    static final org.joda.time.Instant JODA_INSTANT_WITH_SYS_ZONE = JODA_DATE_TIME_WITH_SYS_ZONE.toInstant();
    static final long JODA_INSTANT_MILLIS = JODA_INSTANT_WITH_SYS_ZONE.getMillis();

    // Joda - 2024-12-29 12:58:30.333 GMT+04:00
    static final org.joda.time.DateTimeZone JODA_ZONE = org.joda.time.DateTimeZone.forID("GMT+04:00");
    static final org.joda.time.DateTime JODA_DATE_TIME = JODA_LOCAL_DATE_TIME.toDateTime(JODA_ZONE);
    static final org.joda.time.Instant JODA_INSTANT = JODA_DATE_TIME.toInstant();
    static final long JODA_MILLIS = JODA_INSTANT.getMillis();

    // ================================
    // #region - toDate
    // ================================

    @Test
    void toDate_timeMillis() {
        assertNotEquals(SYS_DATE, DATE);
        log.info("SYS_DATE: {}, DATE: {}", SYS_DATE, DATE);
        assertEquals(SYS_DATE, JODA_DATE_TIME_WITH_SYS_ZONE.toDate());
        assertEquals(SYS_DATE, DateTimeTools.toDate(INSTANT_MILLIS));
        assertEquals(DATE, JODA_DATE_TIME.toDate());
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
    // #region - toJodaInstant
    // ================================

    @Test
    void toJodaInstant_JavaInstant() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, DateTimeTools.toJodaInstant(INSTANT_WITH_SYS_ZONE));
        assertEquals(JODA_INSTANT, DateTimeTools.toJodaInstant(INSTANT));
    }

    @Test
    void toJodaInstant_ZonedDateTime() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, DateTimeTools.toJodaInstant(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(JODA_INSTANT, DateTimeTools.toJodaInstant(ZONED_DATE_TIME));
    }

    @Test
    void toJodaInstant_LocalDateTimeAndZoneId() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, DateTimeTools.toJodaInstant(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(JODA_INSTANT, DateTimeTools.toJodaInstant(LOCAL_DATE_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toJodaInstant
    // ================================

    // ================================
    // #region - toJavaInstant
    // ================================

    @Test
    void toJavaInstant_JodaInstant() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toJavaInstant(JODA_INSTANT_WITH_SYS_ZONE));
        assertEquals(INSTANT, DateTimeTools.toJavaInstant(JODA_INSTANT));
    }

    @Test
    void toJavaInstant_JodaDateTime() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toJavaInstant(JODA_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(INSTANT, DateTimeTools.toJavaInstant(JODA_DATE_TIME));
    }

    @Test
    void toJavaInstant_JodaLocalDateTimeAndJodaDateTimeZone() {
        assertEquals(INSTANT_WITH_SYS_ZONE, DateTimeTools.toJavaInstant(JODA_LOCAL_DATE_TIME, JODA_SYS_ZONE));
        assertEquals(INSTANT, DateTimeTools.toJavaInstant(JODA_LOCAL_DATE_TIME, JODA_ZONE));
    }

    // ================================
    // #endregion - toJavaInstant
    // ================================

    // ================================
    // #region - toJodaDateTime
    // ================================

    @Test
    void toJodaDateTime_ZonedDateTime() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toJodaDateTime(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(JODA_DATE_TIME, DateTimeTools.toJodaDateTime(ZONED_DATE_TIME));
    }

    @Test
    void toJodaDateTime_LocalDateTimeAndZoneId() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toJodaDateTime(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(JODA_DATE_TIME, DateTimeTools.toJodaDateTime(LOCAL_DATE_TIME, ZONE_ID));
    }

    @Test
    void toJodaDateTime_InstantAndZoneId() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toJodaDateTime(INSTANT_WITH_SYS_ZONE, SYS_ZONE_ID));
        assertEquals(JODA_DATE_TIME, DateTimeTools.toJodaDateTime(INSTANT, ZONE_ID));
    }

    // ================================
    // #endregion - toJodaDateTime
    // ================================

    // ================================
    // #region - toZonedDateTime
    // ================================

    @Test
    void toZonedDateTime_JodaDateTime() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(JODA_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(JODA_DATE_TIME));
    }

    @Test
    void toZonedDateTime_JodaLocalDateTimeAndJodaDateTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(JODA_LOCAL_DATE_TIME, JODA_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(JODA_LOCAL_DATE_TIME, JODA_ZONE));
    }

    @Test
    void toZonedDateTime_JodaInstantAndJodaDateTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, DateTimeTools.toZonedDateTime(JODA_INSTANT_WITH_SYS_ZONE, JODA_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, DateTimeTools.toZonedDateTime(JODA_INSTANT, JODA_ZONE));
    }

    // ================================
    // #endregion - toZonedDateTime
    // ================================

    // ================================
    // #region - toJodaLocalDateTime
    // ================================

    @Test
    void toJodaLocalDateTime_JavaLocalDateTime() {
        assertEquals(JODA_LOCAL_DATE_TIME, DateTimeTools.toJodaLocalDateTime(LOCAL_DATE_TIME));
    }

    @Test
    void toJavaLocalDateTime_JodaLocalDateTime() {
        assertEquals(LOCAL_DATE_TIME, DateTimeTools.toJavaLocalDateTime(JODA_LOCAL_DATE_TIME));
    }

    // ================================
    // #endregion - toJodaLocalDateTime
    // ================================

    // ================================
    // #region - ZondId <--> DateTimeZone
    // ================================

    @Test
    void convertJavaZoneIdAndJodaDateTimeZone() {
        assertEquals(SYS_ZONE_ID, DateTimeTools.toJavaZone(JODA_SYS_ZONE));
        assertEquals(ZONE_ID, DateTimeTools.toJavaZone(JODA_ZONE));
        assertEquals(JODA_SYS_ZONE, DateTimeTools.toJodaZone(SYS_ZONE_ID));
        assertEquals(JODA_ZONE, DateTimeTools.toJodaZone(ZONE_ID));
    }

    // ================================
    // #endregion - ZondId <--> DateTimeZone
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
    }

    // ================================
    // #endregion - YearQuarter & Quarter
    // ================================

    // ================================
    // #region - others
    // ================================

    @Test
    void startDateOfYear() {
        assertEquals(LocalDate.of(2008, 1, 1), DateTimeTools.startDateOfYear(2008));
        assertEquals(LocalDate.of(2008, 12, 31), DateTimeTools.endDateOfYear(2008));
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0),
                DateTimeTools.startOfNextDate(LOCAL_DATE));
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0).atZone(SYS_ZONE_ID),
                DateTimeTools.startOfNextDate(LOCAL_DATE, SYS_ZONE_ID));
        assertEquals(LocalDateTime.of(2024, 12, 30, 0, 0, 0).atZone(ZONE_ID),
                DateTimeTools.startOfNextDate(LOCAL_DATE, ZONE_ID));

        Range<LocalDateTime> localDateTimeRange = DateTimeTools.toDateTimeRange(LOCAL_DATE);
        assertEquals(LOCAL_DATE.atStartOfDay(), localDateTimeRange.lowerEndpoint());
        assertTrue(localDateTimeRange.contains(LOCAL_DATE.atStartOfDay()));
        assertEquals(LocalDate.of(2024, 12, 30).atStartOfDay(), localDateTimeRange.upperEndpoint());
        assertEquals(LocalDate.of(2024, 12, 30).atStartOfDay(), localDateTimeRange.upperEndpoint());
        assertFalse(localDateTimeRange.contains(LocalDate.of(2024, 12, 30).atStartOfDay()));

        Range<ZonedDateTime> zonedDateTimeRange = DateTimeTools.toDateTimeRange(LOCAL_DATE, SYS_ZONE_ID);
        assertEquals(LOCAL_DATE.atStartOfDay().atZone(SYS_ZONE_ID), zonedDateTimeRange.lowerEndpoint());
        assertTrue(zonedDateTimeRange.contains(LOCAL_DATE.atStartOfDay().atZone(SYS_ZONE_ID)));
        assertEquals(ZonedDateTime.of(LocalDate.of(2024, 12, 30).atStartOfDay(), SYS_ZONE_ID), zonedDateTimeRange.upperEndpoint());
        assertEquals(ZonedDateTime.of(LocalDate.of(2024, 12, 30).atStartOfDay(), SYS_ZONE_ID), zonedDateTimeRange.upperEndpoint());
        assertFalse(zonedDateTimeRange.contains(LocalDate.of(2024, 12, 30).atStartOfDay().atZone(SYS_ZONE_ID)));
    }

    // ================================
    // #endregion - others
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
}
