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

/**
 * Joda-Time 工具类
 *
 * @author ZhouXY
 */
public class JodaTimeTools {

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
    public static org.joda.time.Instant toJodaInstant(
            java.time.LocalDateTime localDateTime,
            java.time.ZoneId zone) {
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
        return DateTimeTools.toInstant(instant.getMillis());
    }

    /**
     * 将 joda-time 中的 {@link org.joda.time.DateTime} 对象转换为 Java 的
     * {@link java.time.Instant} 对象
     *
     * @param dateTime joda-time 中表示日期时间的 {@link org.joda.time.DateTime} 对象
     * @return Java 表示时间戳的 {@link java.time.Instant} 对象
     */
    public static java.time.Instant toJavaInstant(org.joda.time.DateTime dateTime) {
        return DateTimeTools.toInstant(dateTime.getMillis());
    }

    /**
     * 将 joda-time 中的 {@link org.joda.time.LocalDateTime} 对象和
     * {@link org.joda.time.DateTimeZone} 对象
     * 转换为 Java 中的 {@link java.time.Instant} 对象
     *
     * @param localDateTime {@link org.joda.time.LocalDateTime} 对象
     * @param zone {@link org.joda.time.DateTimeZone} 对象
     * @return Java 表示时间戳的 {@link java.time.Instant} 对象
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
        org.joda.time.LocalDateTime jodaLocalDateTime = toJodaLocalDateTime(localDateTime);
        org.joda.time.DateTimeZone jodaZone = toJodaZone(zone);
        return jodaLocalDateTime.toDateTime(jodaZone);
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
     * @return java.time 中带时区的日期时间
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
        return new org.joda.time.LocalDateTime(
                localDateTime.getYear(),
                localDateTime.getMonthValue(),
                localDateTime.getDayOfMonth(),
                localDateTime.getHour(),
                localDateTime.getMinute(),
                localDateTime.getSecond(),
                localDateTime.getNano() / 1_000_000 // 毫秒转纳秒
        );
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
        return java.time.LocalDateTime.of(
                localDateTime.getYear(),
                localDateTime.getMonthOfYear(),
                localDateTime.getDayOfMonth(),
                localDateTime.getHourOfDay(),
                localDateTime.getMinuteOfHour(),
                localDateTime.getSecondOfMinute(),
                localDateTime.getMillisOfSecond() * 1_000_000 // 毫秒转纳秒
        );
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

    /**
     * 私有构造方法
     */
    private JodaTimeTools() {
        throw new IllegalStateException("Utility class");
    }
}
