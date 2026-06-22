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
package xyz.zhouxy.plusone.commons.gson.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static xyz.zhouxy.plusone.commons.gson.adapter.JSR310TypeAdapters.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JSR310TypeAdaptersTests {

    final Gson gsonWithDefaultFormatter = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, localDateTypeAdapter().nullSafe())
            .registerTypeAdapter(LocalDateTime.class, localDateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(LocalTime.class, localTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(ZonedDateTime.class, zonedDateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(OffsetDateTime.class, offsetDateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(Instant.class, instantTypeAdapter().nullSafe())
            .create();

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final DateTimeFormatter timeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .toFormatter();
    final DateTimeFormatter localDateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .toFormatter();
    final DateTimeFormatter zonedDateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .appendZoneId()
            .toFormatter();
    final DateTimeFormatter offsetDateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .appendOffsetId()
            .toFormatter();
    final Gson gsonWithSpecifiedFormatter = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, localDateTypeAdapter(dateFormatter).nullSafe())
            .registerTypeAdapter(LocalTime.class, localTimeTypeAdapter(timeFormatter).nullSafe())
            .registerTypeAdapter(LocalDateTime.class, localDateTimeTypeAdapter(localDateTimeFormatter).nullSafe())
            .registerTypeAdapter(ZonedDateTime.class, zonedDateTimeTypeAdapter(zonedDateTimeFormatter).nullSafe())
            .registerTypeAdapter(OffsetDateTime.class, offsetDateTimeTypeAdapter(offsetDateTimeFormatter).nullSafe())
            .create();

    final LocalDate date = LocalDate.of(2025, 6, 6);
    final LocalTime time = LocalTime.of(6, 6, 6, 666000000);
    final LocalDateTime localDateTime = date.atTime(6, 6, 6, 666000000);
    final ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("+08:00"));
    final OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.of("+08:00"));
    final Instant instant = zonedDateTime.toInstant();

    @DisplayName("测试使用 TypeAdapter 中默认的 formatter 进行序列化")
    @Test
    void test_serialize_defaultFormatter() {
        Foo foo = new Foo();
        foo.localDate = date;
        foo.localTime = time;
        foo.localDateTime = localDateTime;
        foo.zonedDateTime = zonedDateTime;
        foo.offsetDateTime = offsetDateTime;
        foo.instant = instant;

        String json = String.format(
            "{\"localDate\":\"%s\",\"localTime\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"offsetDateTime\":\"%s\",\"instant\":\"%s\"}",
            DateTimeFormatter.ISO_LOCAL_DATE.format(date),
            DateTimeFormatter.ISO_LOCAL_TIME.format(time),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime),
            DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime),
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime),
            DateTimeFormatter.ISO_INSTANT.format(instant)
        );

        assertEquals(json, gsonWithDefaultFormatter.toJson(foo));
    }

    @DisplayName("测试指定 formatter 进行序列化")
    @Test
    void test_serialize_specifiedFormatter() {
        Foo foo = new Foo();
        foo.localDate = date;
        foo.localTime = time;
        foo.localDateTime = localDateTime;
        foo.zonedDateTime = zonedDateTime;
        foo.offsetDateTime = offsetDateTime;

        String json = String.format(
            "{\"localDate\":\"%s\",\"localTime\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"offsetDateTime\":\"%s\"}",
            dateFormatter.format(date),
            timeFormatter.format(time),
            localDateTimeFormatter.format(localDateTime),
            zonedDateTimeFormatter.format(zonedDateTime),
            offsetDateTimeFormatter.format(offsetDateTime)
        );

        assertEquals(json, gsonWithSpecifiedFormatter.toJson(foo));
    }

    @DisplayName("测试使用 TypeAdapter 中默认的 formatter 进行反序列化")
    @Test
    void test_deserialize_defaultFormatter() {
        String json = String.format(
            "{\"localDate\":\"%s\",\"localTime\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"offsetDateTime\":\"%s\",\"instant\":\"%s\"}",
            DateTimeFormatter.ISO_LOCAL_DATE.format(date),
            DateTimeFormatter.ISO_LOCAL_TIME.format(time),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime),
            DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime),
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime),
            DateTimeFormatter.ISO_INSTANT.format(instant)
        );
        Foo foo = gsonWithDefaultFormatter.fromJson(json, Foo.class);
        assertEquals(date, foo.localDate);
        assertEquals(time, foo.localTime);
        assertEquals(localDateTime, foo.localDateTime);
        assertEquals(zonedDateTime, foo.zonedDateTime);
        assertEquals(offsetDateTime, foo.offsetDateTime);
        assertEquals(instant, foo.instant);
    }

    @DisplayName("测试指定 formatter 进行反序列化")
    @Test
    void test_deserialize_specifiedFormatter() {
        String json = String.format(
            "{\"localDate\":\"%s\",\"localTime\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"offsetDateTime\":\"%s\"}",
            dateFormatter.format(date),
            timeFormatter.format(time),
            localDateTimeFormatter.format(localDateTime),
            zonedDateTimeFormatter.format(zonedDateTime),
            offsetDateTimeFormatter.format(offsetDateTime)
        );
        Foo foo = gsonWithSpecifiedFormatter.fromJson(json, Foo.class);
        assertEquals(date, foo.localDate);
        assertEquals(time, foo.localTime);
        assertEquals(localDateTime, foo.localDateTime);
        assertEquals(zonedDateTime, foo.zonedDateTime);
        assertEquals(offsetDateTime, foo.offsetDateTime);
    }

    static class Foo {
        LocalDate localDate;
        LocalTime localTime;
        LocalDateTime localDateTime;
        ZonedDateTime zonedDateTime;
        OffsetDateTime offsetDateTime;
        Instant instant;
    }
}
