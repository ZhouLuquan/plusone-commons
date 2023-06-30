package xyz.zhouxy.plusone.commons.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;

import xyz.zhouxy.plusone.commons.collection.SafeConcurrentHashMap;

import xyz.zhouxy.plusone.commons.collection.MapWrapper;

public class DateTimeUtil {

    private static final MapWrapper<String, DateTimeFormatter> DATE_TIME_FORMATTER_CACHE = MapWrapper
            .<String, DateTimeFormatter>wrap(new SafeConcurrentHashMap<>())
            .keyChecker(pattern -> Assert.isNotBlank(pattern, "The pattern could not be blank."))
            .valueChecker(formatter -> Assert.notNull(formatter, "The formatter could not be null."))
            .build();

    public static DateTimeFormatter getDateTimeFormatter(String pattern) {
        return DATE_TIME_FORMATTER_CACHE.computeIfAbsent(pattern, DateTimeFormatter::ofPattern);
    }

    public static String toString(String pattern, ZonedDateTime dateTime) {
        return getDateTimeFormatter(pattern).format(dateTime);
    }

    public static String toString(String pattern, Instant instant) {
        ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
        return toString(pattern, dateTime);
    }

    public static String toString(String pattern, Instant instant, ZoneId zone) {
        ZonedDateTime dateTime = instant.atZone(zone);
        return toString(pattern, dateTime);
    }

    public static String nowStr(String pattern) {
        return toString(pattern, ZonedDateTime.now());
    }

    public static String nowStr(String pattern, ZoneId zone) {
        return toString(pattern, Instant.now().atZone(zone));
    }

    // toDate

    public static Date toDate(long timeMillis) {
        return Date.from(Instant.ofEpochMilli(timeMillis));
    }

    public static Date toDate(Calendar calendar) {
        return calendar.getTime();
    }

    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    public static Date toDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime, ZoneId zone) {
        return Date.from(ZonedDateTime.of(localDateTime, zone).toInstant());
    }

    public static Date toDate(LocalDate localDate, LocalTime localTime, ZoneId zone) {
        return Date.from(ZonedDateTime.of(localDate, localTime, zone).toInstant());
    }

    // toInstant

    public static Instant toInstant(long timeMillis) {
        return Instant.ofEpochMilli(timeMillis);
    }

    public static Instant toInstant(Date date) {
        return date.toInstant();
    }

    public static Instant toInstant(Calendar calendar) {
        return calendar.toInstant();
    }

    /**
     * 获取 {@link Instant} 对象
     * 
     * @param zonedDateTime 带时区信息的地区时间
     * @return 时间点
     * 
     * @deprecated 使用 {@link ZonedDateTime#toInstant()}
     */
    @Deprecated
    public static Instant toInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }

    public static Instant toInstant(LocalDateTime localDateTime, ZoneId zone) {
        return ZonedDateTime.of(localDateTime, zone).toInstant();
    }

    // toZonedDateTime

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

    public static ZonedDateTime toZonedDateTime(Calendar calendar, ZoneId zone) {
        return calendar.toInstant().atZone(zone);
    }

    public static ZonedDateTime toZonedDateTime(Calendar calendar, TimeZone zone) {
        return calendar.toInstant().atZone(zone.toZoneId());
    }

    /**
     * 创建带时区的地区时间
     * 
     * @param localDateTime 地区时间
     * @param zone          时区
     * @return 带时区的地区时间
     * 
     * @deprecated 使用 {@link ZonedDateTime#of(LocalDateTime, ZoneId)}
     */
    @Deprecated
    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId zone) {
        return ZonedDateTime.of(localDateTime, zone);
    }

    // toLocalDateTime

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

    public static LocalDateTime toLocalDateTime(Calendar calendar, ZoneId zone) {
        return LocalDateTime.ofInstant(calendar.toInstant(), zone);
    }

    public static LocalDateTime toLocalDateTime(Calendar calendar, TimeZone zone) {
        return LocalDateTime.ofInstant(calendar.toInstant(), zone.toZoneId());
    }

    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime, ZoneId zone) {
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), zone);
    }

    // ====================

    // toJodaInstant

    public static org.joda.time.Instant toJodaInstant(java.time.Instant instant) {
        return new org.joda.time.Instant(instant.toEpochMilli());
    }

    public static org.joda.time.Instant toJodaInstant(java.time.ZonedDateTime zonedDateTime) {
        return toJodaInstant(zonedDateTime.toInstant());
    }

    public static org.joda.time.Instant toJodaInstant(java.time.LocalDateTime localDateTime, java.time.ZoneId zone) {
        return toJodaInstant(java.time.ZonedDateTime.of(localDateTime, zone));
    }

    // toJavaInstant

    public static java.time.Instant toJavaInstant(org.joda.time.Instant instant) {
        return toInstant(instant.getMillis());
    }

    public static java.time.Instant toJavaInstant(org.joda.time.DateTime dateTime) {
        return toInstant(dateTime.getMillis());
    }

    public static java.time.Instant toJavaInstant(
            org.joda.time.LocalDateTime localDateTime,
            org.joda.time.DateTimeZone zone) {
        return toJavaInstant(localDateTime.toDateTime(zone));
    }

    // toJodaDateTime

    public static org.joda.time.DateTime toJodaDateTime(java.time.ZonedDateTime zonedDateTime) {
        org.joda.time.DateTimeZone zone = org.joda.time.DateTimeZone.forID(zonedDateTime.getZone().getId());
        return toJodaInstant(zonedDateTime.toInstant()).toDateTime(zone);
    }

    public static org.joda.time.DateTime toJodaDateTime(
            java.time.LocalDateTime localDateTime,
            java.time.ZoneId zone) {
        org.joda.time.DateTimeZone dateTimeZone = toJodaTime(zone);
        return toJodaInstant(ZonedDateTime.of(localDateTime, zone).toInstant()).toDateTime(dateTimeZone);
    }

    public static org.joda.time.DateTime toJodaDateTime(
            java.time.Instant instant,
            java.time.ZoneId zone) {
        org.joda.time.DateTimeZone dateTimeZone = toJodaTime(zone);
        return toJodaInstant(instant).toDateTime(dateTimeZone);
    }

    // toZonedDateTime

    public static java.time.ZonedDateTime toZonedDateTime(org.joda.time.DateTime dateTime) {
        java.time.ZoneId zone = dateTime.getZone().toTimeZone().toZoneId();
        return toJavaInstant(dateTime.toInstant()).atZone(zone);
    }

    public static java.time.ZonedDateTime toZonedDateTime(
            org.joda.time.LocalDateTime localDateTime,
            org.joda.time.DateTimeZone dateTimeZone) {
        java.time.ZoneId zone = toJavaZone(dateTimeZone);
        return toJavaInstant(localDateTime, dateTimeZone).atZone(zone);
    }

    public static java.time.ZonedDateTime toZonedDateTime(
            org.joda.time.Instant instant,
            org.joda.time.DateTimeZone dateTimeZone) {
        java.time.ZoneId zone = toJavaZone(dateTimeZone);
        return toJavaInstant(instant).atZone(zone);
    }

    // toJodaLocalDateTime

    public static org.joda.time.LocalDateTime toJodaLocalDateTime(java.time.LocalDateTime localDateTime) {
        java.time.ZoneId javaZone = java.time.ZoneId.systemDefault();
        org.joda.time.DateTimeZone jodaZone = toJodaTime(javaZone);
        return toJodaInstant(localDateTime, javaZone).toDateTime(jodaZone).toLocalDateTime();
    }

    // toJavaLocalDateTime

    public static java.time.LocalDateTime toJavaLocalDateTime(org.joda.time.LocalDateTime localDateTime) {
        org.joda.time.DateTimeZone jodaZone = org.joda.time.DateTimeZone.getDefault();
        java.time.ZoneId javaZone = toJavaZone(jodaZone);
        return toJavaInstant(localDateTime, jodaZone).atZone(javaZone).toLocalDateTime();
    }

    public static java.time.ZoneId toJavaZone(org.joda.time.DateTimeZone jodaZone) {
        return jodaZone.toTimeZone().toZoneId();
    }

    public static DateTimeZone toJodaTime(java.time.ZoneId zone) {
        return org.joda.time.DateTimeZone.forID(zone.getId());
    }

    private DateTimeUtil() {
        throw new IllegalStateException("Utility class");
    }
}
