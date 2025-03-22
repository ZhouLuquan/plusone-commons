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

import static java.time.temporal.ChronoField.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.IsoChronology;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.google.common.collect.Range;

import xyz.zhouxy.plusone.commons.time.Quarter;
import xyz.zhouxy.plusone.commons.time.YearQuarter;

/**
 * 日期时间工具类
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public class DateTimeTools {

    // ================================
    // #region - toString
    // ================================

    public static String toYearString(int year) {
        return Integer.toString(YEAR.checkValidIntValue(year));
    }

    public static String toYearString(Year year) {
        return year.toString();
    }

    public static String toMonthStringM(int monthValue) {
        return Integer.toString(MONTH_OF_YEAR.checkValidIntValue(monthValue));
    }

    public static String toMonthStringMM(int monthValue) {
        return String.format("%02d", MONTH_OF_YEAR.checkValidIntValue(monthValue));
    }

    public static String toMonthStringM(Month month) {
        return Integer.toString(month.getValue());
    }

    public static String toMonthStringMM(Month month) {
        return String.format("%02d", month.getValue());
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toDate
    // ================================

    /**
     * 将时间戳转换为 {@link Date} 对象
     *
     * @param timeMillis 时间戳
     * @return {@link Date} 对象
     */
    public static Date toDate(long timeMillis) {
        return Date.from(Instant.ofEpochMilli(timeMillis));
    }

    /**
     * 将 {@link Calendar} 对象转换为 {@link Date} 对象
     *
     * @param calendar {@link Calendar} 对象
     * @return {@link Date} 对象
     */
    public static Date toDate(Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * 将 {@link Instant} 对象转换为 {@link Date} 对象
     *
     * @param instant {@link Instant} 对象
     * @return {@link Date} 对象
     */
    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    /**
     * 将 {@link ZonedDateTime} 对象转换为 {@link Date} 对象
     *
     * @param zonedDateTime {@link ZonedDateTime} 对象
     * @return {@link Date} 对象
     */
    public static Date toDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 使用指定时区，将 {@link LocalDateTime} 对象转换为 {@link Date} 对象
     *
     * @param localDateTime {@link LocalDateTime} 对象
     * @param zone          时区
     * @return {@link Date} 对象
     */
    public static Date toDate(LocalDateTime localDateTime, ZoneId zone) {
        return Date.from(ZonedDateTime.of(localDateTime, zone).toInstant());
    }

    /**
     * 使用指定时区，将 {@link LocalDate} 和 {@link LocalTime} 对象转换为 {@link Date} 对象
     *
     * @param localDate {@link LocalDate} 对象
     * @param localTime {@link LocalTime} 对象
     * @param zone      时区
     * @return {@link Date} 对象
     */
    public static Date toDate(LocalDate localDate, LocalTime localTime, ZoneId zone) {
        return Date.from(ZonedDateTime.of(localDate, localTime, zone).toInstant());
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toInstant
    // ================================

    /**
     * 将时间戳转换为 {@link Instant} 对象
     *
     * @param timeMillis 时间戳
     * @return {@link Instant} 对象
     */
    public static Instant toInstant(long timeMillis) {
        return Instant.ofEpochMilli(timeMillis);
    }

    /**
     * 将 {@link Date} 对象转换为 {@link Instant} 对象
     *
     * @param date {@link Date} 对象
     * @return {@link Instant} 对象
     */
    public static Instant toInstant(Date date) {
        return date.toInstant();
    }

    /**
     * 将 {@link Calendar} 对象转换为 {@link Instant} 对象
     *
     * @param calendar {@link Calendar} 对象
     * @return {@link Instant} 对象
     */
    public static Instant toInstant(Calendar calendar) {
        return calendar.toInstant();
    }

    /**
     * 将 {@link ZonedDateTime} 对象转换为 {@link Instant} 对象
     *
     * @param zonedDateTime {@link ZonedDateTime} 对象
     * @return {@link Instant} 对象
     */
    public static Instant toInstant(ZonedDateTime zonedDateTime) { // NOSONAR
        return zonedDateTime.toInstant();
    }

    /**
     * 使用指定时区，将 {@link LocalDateTime} 对象转换为 {@link Instant} 对象
     *
     * @param LocalDateTime {@link LocalDateTime} 对象
     * @param zone          时区
     * @return {@link Instant} 对象
     */
    public static Instant toInstant(LocalDateTime localDateTime, ZoneId zone) {
        return ZonedDateTime.of(localDateTime, zone).toInstant();
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toZonedDateTime
    // ================================

    /**
     * 获取时间戳在指定时区的地区时间。
     * <p>
     * 传入不同 {@link ZoneId}，获取到的 {@link ZonedDateTime} 对象实际上还是同一时间戳，
     * 只是不同时区的表示。
     * </p>
     *
     * @param timeMillis 时间戳
     * @param zone       时区
     * @return 带时区信息的地区时间
     */
    public static ZonedDateTime toZonedDateTime(long timeMillis, ZoneId zone) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), zone);
    }

    /**
     * 获取 {@link Date} 所表示的时间戳，在指定时区的地区时间。
     * <p>
     * 传入不同 {@link ZoneId}，获取到的 {@link ZonedDateTime} 对象实际上还是同一时间戳，
     * 只是不同时区的表示。
     * </p>
     *
     * @param dateTime {@link Date} 对象
     * @param zone     时区
     * @return 带时区信息的地区时间
     */
    public static ZonedDateTime toZonedDateTime(Date dateTime, ZoneId zone) {
        return ZonedDateTime.ofInstant(dateTime.toInstant(), zone);
    }

    /**
     * 获取 {@link Date} 所表示的时间戳，在指定时区的地区时间。
     * <p>
     * 传入不同 {@link ZoneId}，获取到的 {@link ZonedDateTime} 对象实际上表示的还是还是同一时间戳的时间，
     * 只是不同时区的表示。
     * </p>
     *
     * @param dateTime {@link Date} 对象
     * @param timeZone 时区
     * @return 带时区信息的地区时间
     */
    public static ZonedDateTime toZonedDateTime(Date dateTime, TimeZone timeZone) {
        return ZonedDateTime.ofInstant(dateTime.toInstant(), timeZone.toZoneId());
    }

    /**
     * 使用 {@code calendar} 对象的时区信息，将 {@link Calendar} 对象转换为 {@link ZonedDateTime}
     * 对象。
     *
     * @param calendar{@link Calendar} 对象
     * @return {@link ZonedDateTime} 对象
     */
    public static ZonedDateTime toZonedDateTime(Calendar calendar) {
        return calendar.toInstant().atZone(calendar.getTimeZone().toZoneId());
    }

    /**
     * 使用指定的时区，将 {@link Calendar} 对象转换为 {@link ZonedDateTime} 对象。
     *
     * @param calendar {@link Calendar} 对象
     * @param zone     时区
     * @return {@link ZonedDateTime} 对象
     */
    public static ZonedDateTime toZonedDateTime(Calendar calendar, ZoneId zone) {
        return calendar.toInstant().atZone(zone);
    }

    /**
     * 使用指定的时区，将 {@link Calendar} 对象转换为 {@link ZonedDateTime} 对象。
     *
     * @param calendar {@link Calendar} 对象
     * @param zone     时区
     * @return {@link ZonedDateTime} 对象
     */
    public static ZonedDateTime toZonedDateTime(Calendar calendar, TimeZone zone) {
        return calendar.toInstant().atZone(zone.toZoneId());
    }

    /**
     * 创建带时区的地区时间
     *
     * @param localDateTime 地区时间
     * @param zone          时区
     * @return 带时区的地区时间
     */
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId zone) {
        return ZonedDateTime.of(localDateTime, zone);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toLocalDateTime
    // ================================

    /**
     * 获取时间戳在指定时区的地区时间。
     *
     * @param timeMillis 时间戳
     * @param zone       时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(long timeMillis, ZoneId zone) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeMillis), zone);
    }

    /**
     * 获取 {@link Date} 所表示的时间戳，在指定时区的地区时间。
     *
     * @param dateTime {@link Date} 对象
     * @param zone     时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(Date dateTime, ZoneId zone) {
        return LocalDateTime.ofInstant(dateTime.toInstant(), zone);
    }

    /**
     * 获取 {@link Date} 所表示的时间戳，在指定时区的地区时间。
     *
     * @param dateTime {@link Date} 对象
     * @param timeZone 时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(Date dateTime, TimeZone timeZone) {
        return LocalDateTime.ofInstant(dateTime.toInstant(), timeZone.toZoneId());
    }

    /**
     * 获取 {@link Calendar} 所表示的时间戳，在指定时区的地区时间。
     *
     * @param calendar {@link Calendar} 对象
     * @param zone     时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(Calendar calendar, ZoneId zone) {
        return LocalDateTime.ofInstant(calendar.toInstant(), zone);
    }

    /**
     * 获取 {@link Calendar} 所表示的时间戳，在指定时区的地区时间。
     *
     * @param calendar {@link Calendar} 对象
     * @param zone     时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(Calendar calendar, TimeZone zone) {
        return LocalDateTime.ofInstant(calendar.toInstant(), zone.toZoneId());
    }

    /**
     * 获取 {@link ZonedDateTime} 所表示的时间戳，在指定时区的地区时间。
     *
     * @param zonedDateTime {@link ZonedDateTime} 对象
     * @param zone          时区
     * @return 地区时间
     */
    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime, ZoneId zone) {
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), zone);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toJodaInstant
    // ================================

    /**
     * 将 {@link java.time.Instant} 转换为 {@link org.joda.time.Instant}
     *
     * @param instant {@link java.time.Instant} 对象
     * @return {@link org.joda.time.Instant} 对象
     */
    public static org.joda.time.Instant toJodaInstant(java.time.Instant instant) {
        return new org.joda.time.Instant(instant.toEpochMilli());
    }

    /**
     * 将 {@link java.time.ZonedDateTime} 转换为 {@link org.joda.time.Instant}
     *
     * @param zonedDateTime {@link java.time.ZonedDateTime} 对象
     * @return {@link org.joda.time.Instant} 对象
     */
    public static org.joda.time.Instant toJodaInstant(java.time.ZonedDateTime zonedDateTime) {
        return toJodaInstant(zonedDateTime.toInstant());
    }

    /**
     * 计算指定时区的地区时间，对应的时间戳。结果为 {@link org.joda.time.Instant} 对象
     *
     * @param localDateTime {@link java.time.LocalDateTime} 对象
     * @param zone          时区
     * @return {@link org.joda.time.Instant} 对象
     */
    public static org.joda.time.Instant toJodaInstant(java.time.LocalDateTime localDateTime, java.time.ZoneId zone) {
        return toJodaInstant(java.time.ZonedDateTime.of(localDateTime, zone));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toJavaInstant
    // ================================

    /**
     * 将 {@link org.joda.time.Instant} 对象转换为 {@link java.time.Instant} 对象
     *
     * @param instant {@link org.joda.time.Instant} 对象
     * @return {@link java.time.Instant} 对象
     */
    public static java.time.Instant toJavaInstant(org.joda.time.Instant instant) {
        return toInstant(instant.getMillis());
    }

    /**
     * 将 joda-time 中的 {@link org.joda.time.DateTime} 对象转换为 Java 的
     * {@link java.time.Instant} 对象
     *
     * @param dateTime joda-time 中表示日期时间的 {@link org.joda.time.DateTime} 对象
     * @return Java 表示时间戳的 {@link java.time.Instant} 对象
     */
    public static java.time.Instant toJavaInstant(org.joda.time.DateTime dateTime) {
        return toInstant(dateTime.getMillis());
    }

    /**
     * 将 joda-time 中的 {@link org.joda.time.LocalDateTime} 对象和
     * {@link org.joda.time.DateTimeZone} 对象
     * 转换为 Java 中的 {@link java.time.Instant} 对象
     *
     * @param localDateTime
     * @param zone
     * @return
     */
    public static java.time.Instant toJavaInstant(
            org.joda.time.LocalDateTime localDateTime,
            org.joda.time.DateTimeZone zone) {
        return toJavaInstant(localDateTime.toDateTime(zone));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toJodaDateTime
    // ================================

    /**
     * 将 Java 中表示日期时间的 {@link java.time.ZonedDateTime} 对象
     * 转换为 joda-time 的 {@link org.joda.time.DateTime} 对象
     *
     * @param zonedDateTime 日期时间
     * @return joda-time 中对应的 {@link org.joda.time.DateTime} 对象
     */
    public static org.joda.time.DateTime toJodaDateTime(java.time.ZonedDateTime zonedDateTime) {
        org.joda.time.DateTimeZone zone = org.joda.time.DateTimeZone.forID(zonedDateTime.getZone().getId());
        return toJodaInstant(zonedDateTime.toInstant()).toDateTime(zone);
    }

    /**
     * 将 java.time 中表示日期时间的 {@link java.time.LocalDateTime} 对象和表示时区的
     * {@link java.time.ZoneId} 对象转换为 joda-time 中对应的 {@link org.joda.time.DateTime}
     * 对象
     * 转换为 joda-time 中对应的 {@link org.joda.time.DateTime} 对象
     *
     * @param localDateTime 日期时间
     * @param zone          时区
     * @return joda-time 中对应的 {@link org.joda.time.DateTime} 对象
     */
    public static org.joda.time.DateTime toJodaDateTime(
            java.time.LocalDateTime localDateTime,
            java.time.ZoneId zone) {
        org.joda.time.DateTimeZone dateTimeZone = toJodaZone(zone);
        return toJodaInstant(ZonedDateTime.of(localDateTime, zone).toInstant()).toDateTime(dateTimeZone);
    }

    /**
     * 计算时间戳在指定时区对应的时间，结果使用 {@link org.joda.time.DateTime} 表示
     *
     * @param instant java.time 中的时间戳
     * @param zone    java.time 中的时区
     * @return joda-time 中带时区的日期时间
     */
    public static org.joda.time.DateTime toJodaDateTime(
            java.time.Instant instant,
            java.time.ZoneId zone) {
        org.joda.time.DateTimeZone dateTimeZone = toJodaZone(zone);
        return toJodaInstant(instant).toDateTime(dateTimeZone);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toZonedDateTime
    // ================================

    /**
     * 将 joda-time 中带时区的日期时间，转换为 java.time 中带时区的日期时间
     *
     * @param dateTime joda-time 中带时区的日期时间
     * @return java.time 中带时区的日期时间
     */
    public static java.time.ZonedDateTime toZonedDateTime(org.joda.time.DateTime dateTime) {
        java.time.ZoneId zone = dateTime.getZone().toTimeZone().toZoneId();
        return toJavaInstant(dateTime.toInstant()).atZone(zone);
    }

    /**
     * 将 joda-time 中的 {@link org.joda.time.LocalDateTime} 和
     * {@link org.joda.time.DateTimeZone}
     * 转换为 java.time 中的 {@link java.time.ZonedDateTime}
     *
     * @param localDateTime joda-time 中的地区时间
     * @param dateTimeZone  joda-time 中的时区
     * @return java.time 中带时区的日期时间
     */
    public static java.time.ZonedDateTime toZonedDateTime(
            org.joda.time.LocalDateTime localDateTime,
            org.joda.time.DateTimeZone dateTimeZone) {
        java.time.ZoneId zone = toJavaZone(dateTimeZone);
        return toJavaInstant(localDateTime, dateTimeZone).atZone(zone);
    }

    /**
     * 获取 joda-time 中的 {@link org.joda.time.Instant} 在指定时区的时间，用 Java 8+ 的
     * {@link java.time.ZonedDateTime} 表示
     *
     * @param instant      joda-time 中的时间戳
     * @param dateTimeZone joda-time 中的时区
     * @return
     */
    public static java.time.ZonedDateTime toZonedDateTime(
            org.joda.time.Instant instant,
            org.joda.time.DateTimeZone dateTimeZone) {
        java.time.ZoneId zone = toJavaZone(dateTimeZone);
        return toJavaInstant(instant).atZone(zone);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toJodaLocalDateTime
    // ================================

    /**
     * 将 {@link java.time.LocalDateTime} 转换为 {@link org.joda.time.LocalDateTime}
     *
     * @param localDateTime Java 8 LocalDateTime
     * @return joda-time LocalDateTime
     */
    public static org.joda.time.LocalDateTime toJodaLocalDateTime(java.time.LocalDateTime localDateTime) {
        java.time.ZoneId javaZone = java.time.ZoneId.systemDefault();
        org.joda.time.DateTimeZone jodaZone = toJodaZone(javaZone);
        return toJodaInstant(localDateTime, javaZone).toDateTime(jodaZone).toLocalDateTime();
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - toJavaLocalDateTime
    // ================================

    /**
     * 将 {@link org.joda.time.LocalDateTime} 转换为 {@link java.time.LocalDateTime}
     *
     * @param localDateTime joda-time LocalDateTime
     * @return Java 8 LocalDateTime
     */
    public static java.time.LocalDateTime toJavaLocalDateTime(org.joda.time.LocalDateTime localDateTime) {
        org.joda.time.DateTimeZone jodaZone = org.joda.time.DateTimeZone.getDefault();
        java.time.ZoneId javaZone = toJavaZone(jodaZone);
        return toJavaInstant(localDateTime, jodaZone).atZone(javaZone).toLocalDateTime();
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - ZoneId <--> DateTimeZone
    // ================================

    /**
     * 转换 Java API 和 joda-time API 表示时区的对象
     *
     * @param jodaZone joda-time API 中表示时区的对象
     * @return Java API 中表示时区的对象
     */
    public static java.time.ZoneId toJavaZone(org.joda.time.DateTimeZone jodaZone) {
        return jodaZone.toTimeZone().toZoneId();
    }

    /**
     * 转换 Java API 和 joda-time API 表示时区的对象
     *
     * @param zone Java API 中表示时区的对象
     * @return joda-time API 中表示时区的对象
     */
    public static org.joda.time.DateTimeZone toJodaZone(java.time.ZoneId zone) {
        return org.joda.time.DateTimeZone.forID(zone.getId());
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - YearQuarter & Quarter
    // ================================

    /**
     * 获取指定日期所在季度
     *
     * @param date 日期
     * @return 日期所在的季度
     */
    public static YearQuarter getQuarter(Date date) {
        return YearQuarter.of(date);
    }

    /**
     * 获取指定日期所在季度
     *
     * @param date 日期
     * @return 日期所在的季度
     */
    public static YearQuarter getQuarter(Calendar date) {
        return YearQuarter.of(date);
    }

    /**
     * 获取指定月份所在季度
     *
     * @param month 月份
     * @return 季度
     */
    public static Quarter getQuarter(Month month) {
        return Quarter.fromMonth(month);
    }

    /**
     * 获取指定年月所在季度
     *
     * @param year  年
     * @param month 月
     * @return 季度
     */
    public static YearQuarter getQuarter(int year, Month month) {
        return YearQuarter.of(YearMonth.of(year, month));
    }

    /**
     * 获取指定年月所在季度
     *
     * @param yearMonth 年月
     * @return 季度
     */
    public static YearQuarter getQuarter(YearMonth yearMonth) {
        return YearQuarter.of(yearMonth);
    }

    /**
     * 获取指定日期所在季度
     *
     * @param date 日期
     * @return 日期所在的季度
     */
    public static YearQuarter getQuarter(LocalDate date) {
        return YearQuarter.of(date);
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - start & end
    // ================================

    public static LocalDate startDateOfYear(int year) {
        return LocalDate.ofYearDay(year, 1);
    }

    public static LocalDate endDateOfYear(int year) {
        return LocalDate.of(year, 12, 31);
    }

    public static LocalDateTime startOfNextDate(LocalDate date) {
        return date.plusDays(1L).atStartOfDay();
    }

    public static ZonedDateTime startOfNextDate(LocalDate date, ZoneId zone) {
        return date.plusDays(1L).atStartOfDay(zone);
    }

    // ================================
    // #endregion - start & end
    // ================================

    // ================================
    // #region - others
    // ================================

    public static Range<LocalDateTime> toDateTimeRange(LocalDate date) {
        return Range.closedOpen(date.atStartOfDay(), startOfNextDate(date));
    }

    public static Range<ZonedDateTime> toDateTimeRange(LocalDate date, ZoneId zone) {
        return Range.closedOpen(date.atStartOfDay(zone), startOfNextDate(date, zone));
    }

    public static boolean isLeapYear(int year) {
        return IsoChronology.INSTANCE.isLeapYear(year);
    }

    // ================================
    // #endregion - others
    // ================================

    /**
     * 私有构造方法，明确标识该常量类的作用。
     */
    private DateTimeTools() {
        throw new IllegalStateException("Utility class");
    }
}
