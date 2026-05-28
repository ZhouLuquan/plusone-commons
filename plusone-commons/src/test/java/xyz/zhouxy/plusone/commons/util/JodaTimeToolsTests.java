/*
 * Copyright 2025-present ZhouXY
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

public class JodaTimeToolsTests {

    // Java
    static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2024, 12, 29, 12, 58, 30, 333 * 1_000_000);
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
    // #region - toJodaInstant
    // ================================

    @Test
    void toJodaInstant_JavaInstant() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJodaInstant(INSTANT_WITH_SYS_ZONE));
        assertEquals(JODA_INSTANT, JodaTimeTools.toJodaInstant(INSTANT));
    }

    @Test
    void toJodaInstant_ZonedDateTime() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJodaInstant(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(JODA_INSTANT, JodaTimeTools.toJodaInstant(ZONED_DATE_TIME));
    }

    @Test
    void toJodaInstant_LocalDateTimeAndZoneId() {
        assertEquals(JODA_INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJodaInstant(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(JODA_INSTANT, JodaTimeTools.toJodaInstant(LOCAL_DATE_TIME, ZONE_ID));
    }

    // ================================
    // #endregion - toJodaInstant
    // ================================

    // ================================
    // #region - toJavaInstant
    // ================================

    @Test
    void toJavaInstant_JodaInstant() {
        assertEquals(INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJavaInstant(JODA_INSTANT_WITH_SYS_ZONE));
        assertEquals(INSTANT, JodaTimeTools.toJavaInstant(JODA_INSTANT));
    }

    @Test
    void toJavaInstant_JodaDateTime() {
        assertEquals(INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJavaInstant(JODA_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(INSTANT, JodaTimeTools.toJavaInstant(JODA_DATE_TIME));
    }

    @Test
    void toJavaInstant_JodaLocalDateTimeAndJodaDateTimeZone() {
        assertEquals(INSTANT_WITH_SYS_ZONE, JodaTimeTools.toJavaInstant(JODA_LOCAL_DATE_TIME, JODA_SYS_ZONE));
        assertEquals(INSTANT, JodaTimeTools.toJavaInstant(JODA_LOCAL_DATE_TIME, JODA_ZONE));
    }

    // ================================
    // #endregion - toJavaInstant
    // ================================

    // ================================
    // #region - toJodaDateTime
    // ================================

    @Test
    void toJodaDateTime_ZonedDateTime() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toJodaDateTime(ZONED_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(JODA_DATE_TIME, JodaTimeTools.toJodaDateTime(ZONED_DATE_TIME));
    }

    @Test
    void toJodaDateTime_LocalDateTimeAndZoneId() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toJodaDateTime(LOCAL_DATE_TIME, SYS_ZONE_ID));
        assertEquals(JODA_DATE_TIME, JodaTimeTools.toJodaDateTime(LOCAL_DATE_TIME, ZONE_ID));
    }

    @Test
    void toJodaDateTime_InstantAndZoneId() {
        assertEquals(JODA_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toJodaDateTime(INSTANT_WITH_SYS_ZONE, SYS_ZONE_ID));
        assertEquals(JODA_DATE_TIME, JodaTimeTools.toJodaDateTime(INSTANT, ZONE_ID));
    }

    // ================================
    // #endregion - toJodaDateTime
    // ================================

    // ================================
    // #region - toZonedDateTime
    // ================================

    @Test
    void toZonedDateTime_JodaDateTime() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toZonedDateTime(JODA_DATE_TIME_WITH_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, JodaTimeTools.toZonedDateTime(JODA_DATE_TIME));
    }

    @Test
    void toZonedDateTime_JodaLocalDateTimeAndJodaDateTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toZonedDateTime(JODA_LOCAL_DATE_TIME, JODA_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, JodaTimeTools.toZonedDateTime(JODA_LOCAL_DATE_TIME, JODA_ZONE));
    }

    @Test
    void toZonedDateTime_JodaInstantAndJodaDateTimeZone() {
        assertEquals(ZONED_DATE_TIME_WITH_SYS_ZONE, JodaTimeTools.toZonedDateTime(JODA_INSTANT_WITH_SYS_ZONE, JODA_SYS_ZONE));
        assertEquals(ZONED_DATE_TIME, JodaTimeTools.toZonedDateTime(JODA_INSTANT, JODA_ZONE));
    }

    // ================================
    // #endregion - toZonedDateTime
    // ================================

    // ================================
    // #region - toJodaLocalDateTime
    // ================================

    @Test
    void toJodaLocalDateTime_JavaLocalDateTime() {
        assertEquals(JODA_LOCAL_DATE_TIME, JodaTimeTools.toJodaLocalDateTime(LOCAL_DATE_TIME));
    }

    @Test
    void toJavaLocalDateTime_JodaLocalDateTime() {
        assertEquals(LOCAL_DATE_TIME, JodaTimeTools.toJavaLocalDateTime(JODA_LOCAL_DATE_TIME));
    }

    // ================================
    // #endregion - toJodaLocalDateTime
    // ================================

    // ================================
    // #region - ZoneId <--> DateTimeZone
    // ================================

    @Test
    void convertJavaZoneIdAndJodaDateTimeZone() {
        assertEquals(SYS_ZONE_ID, JodaTimeTools.toJavaZone(JODA_SYS_ZONE));
        assertEquals(ZONE_ID, JodaTimeTools.toJavaZone(JODA_ZONE));
        assertEquals(JODA_SYS_ZONE, JodaTimeTools.toJodaZone(SYS_ZONE_ID));
        assertEquals(JODA_ZONE, JodaTimeTools.toJodaZone(ZONE_ID));
    }

    // ================================
    // #endregion - ZoneId <--> DateTimeZone
    // ================================

}
