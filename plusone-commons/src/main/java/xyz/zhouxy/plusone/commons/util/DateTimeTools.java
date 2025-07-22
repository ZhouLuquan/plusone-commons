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
     * @param localDateTime {@link LocalDateTime} 对象
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

    /**
     * 获取指定年份的开始日期
     *
     * @param year 年份
     * @return 指定年份的开始日期
     */
    public static LocalDate startDateOfYear(int year) {
        return LocalDate.ofYearDay(year, 1);
    }

    /**
     * 获取指定年份的结束日期
     *
     * @param year 年份
     * @return 指定年份的结束日期
     */
    public static LocalDate endDateOfYear(int year) {
        return LocalDate.of(year, 12, 31);
    }

    /**
     * 获取指定日期的第二天的开始时间
     *
     * @param date 日期
     * @return 指定日期的第二天的开始时间
     */
    public static LocalDateTime startOfNextDate(LocalDate date) {
        return date.plusDays(1L).atStartOfDay();
    }

    /**
     * 获取指定日期的第二天的开始时间
     *
     * @param date 日期
     * @param zone 时区
     * @return 指定日期的第二天的开始时间
     */
    public static ZonedDateTime startOfNextDate(LocalDate date, ZoneId zone) {
        return date.plusDays(1L).atStartOfDay(zone);
    }

    // ================================
    // #endregion - start & end
    // ================================

    // ================================
    // #region - isFuture
    // ================================

    /**
     * 判断指定日期时间是否在将来
     *
     * @param date 日期时间
     * @return 指定日期时间是否在将来
     */
    public static boolean isFuture(Date date) {
        return date.after(new Date());
    }

    /**
     * 判断指定日期时间是否在将来
     *
     * @param calendar 日期时间
     * @return 指定日期时间是否在将来
     */
    public static boolean isFuture(Calendar calendar) {
        return calendar.after(Calendar.getInstance());
    }

    /**
     * 判断指定时刻是否在将来
     *
     * @param instant 时刻
     * @return 指定时刻是否在将来
     */
    public static boolean isFuture(Instant instant) {
        return instant.isAfter(Instant.now());
    }

    /**
     * 判断指定时间戳是否在将来
     *
     * @param timeMillis 时间戳
     * @return 指定时间戳是否在将来
     */
    public static boolean isFuture(long timeMillis) {
        return timeMillis > System.currentTimeMillis();
    }

    /**
     * 判断指定日期是否在将来
     *
     * @param date 日期
     * @return 指定日期是否在将来
     */
    public static boolean isFuture(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    /**
     * 判断指定日期时间是否在将来
     *
     * @param dateTime 日期时间
     * @return 指定日期时间是否在将来
     */
    public static boolean isFuture(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

    /**
     * 判断指定日期时间是否在将来
     *
     * @param dateTime 日期时间
     * @return 指定日期时间是否在将来
     */
    public static boolean isFuture(ZonedDateTime dateTime) {
        return dateTime.isAfter(ZonedDateTime.now());
    }

    // ================================
    // #endregion - isFuture
    // ================================

    // ================================
    // #region - isPast
    // ================================

    /**
     * 判断指定日期时间是否在过去
     *
     * @param date 日期时间
     * @return 指定日期时间是否在过去
     */
    public static boolean isPast(Date date) {
        return date.before(new Date());
    }

    /**
     * 判断指定日期时间是否在过去
     *
     * @param calendar 日期时间
     * @return 指定日期时间是否在过去
     */
    public static boolean isPast(Calendar calendar) {
        return calendar.before(Calendar.getInstance());
    }

    /**
     * 判断指定时刻是否在过去
     *
     * @param instant 时刻
     * @return 指定时刻是否在过去
     */
    public static boolean isPast(Instant instant) {
        return instant.isBefore(Instant.now());
    }

    /**
     * 判断指定时间戳是否在过去
     *
     * @param timeMillis 时间戳
     * @return 指定时间戳是否在过去
     */
    public static boolean isPast(long timeMillis) {
        return timeMillis < System.currentTimeMillis();
    }

    /**
     * 判断指定日期是否在过去
     *
     * @param date 日期
     * @return 指定日期是否在过去
     */
    public static boolean isPast(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    /**
     * 判断指定日期时间是否在过去
     *
     * @param dateTime 日期时间
     * @return 指定日期时间是否在过去
     */
    public static boolean isPast(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }

    /**
     * 判断指定日期时间是否在过去
     *
     * @param dateTime 日期时间
     * @return 指定日期时间是否在过去
     */
    public static boolean isPast(ZonedDateTime dateTime) {
        return dateTime.isBefore(ZonedDateTime.now());
    }

    // ================================
    // #endregion - isPast
    // ================================

    // ================================
    // #region - others
    // ================================

    /**
     * 获取指定日期的时间范围
     *
     * @param date 日期
     * @return 指定日期的时间范围
     */
    public static Range<LocalDateTime> toDateTimeRange(LocalDate date) {
        return Range.closedOpen(date.atStartOfDay(), startOfNextDate(date));
    }

    /**
     * 获取指定日期的时间范围
     *
     * @param date 日期
     * @param zone 时区
     * @return 指定日期的时间范围
     */
    public static Range<ZonedDateTime> toDateTimeRange(LocalDate date, ZoneId zone) {
        return Range.closedOpen(date.atStartOfDay(zone), startOfNextDate(date, zone));
    }

    /**
     * 判断指定年份是否为闰年
     *
     * @param year 年份
     * @return 指定年份是否为闰年
     */
    public static boolean isLeapYear(int year) {
        return IsoChronology.INSTANCE.isLeapYear(year);
    }

    // ================================
    // #endregion - others
    // ================================

    /**
     * 私有构造方法
     */
    private DateTimeTools() {
        throw new IllegalStateException("Utility class");
    }
}
