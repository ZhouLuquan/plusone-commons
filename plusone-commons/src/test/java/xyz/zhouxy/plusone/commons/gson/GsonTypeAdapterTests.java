/*
 * Copyright 2025 the original author or authors.
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
package xyz.zhouxy.plusone.commons.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class GsonTypeAdapterTests {

    final Gson gsonWithDefaultFormatter = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter().nullSafe())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter().nullSafe())
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter().nullSafe())
            .create();

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    final DateTimeFormatter localDateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .toFormatter();
    final DateTimeFormatter zonedDateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("yyyy/MM/dd HH:mm:ss")
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .appendZoneId()
            .toFormatter();
    final Gson gsonWithSpecifiedFormatter = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter(dateFormatter).nullSafe())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter(localDateTimeFormatter).nullSafe())
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter(zonedDateTimeFormatter).nullSafe())
            .create();

    final LocalDate date = LocalDate.of(2025, 6, 6);
    final LocalDateTime localDateTime = date.atTime(6, 6, 6, 666000000);
    final ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("+08:00"));
    final Instant instant = zonedDateTime.toInstant();

    @DisplayName("测试使用 TypeAdapter 中默认的 formatter 进行序列化")
    @Test
    void test_serialize_defaultFormatter() {
        Foo foo = new Foo();
        foo.localDate = date;
        foo.localDateTime = localDateTime;
        foo.zonedDateTime = zonedDateTime;
        foo.instant = instant;

        String json = String.format(
            "{\"localDate\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"instant\":\"%s\"}",
            DateTimeFormatter.ISO_LOCAL_DATE.format(date),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime),
            DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime),
            DateTimeFormatter.ISO_INSTANT.format(instant)
        );

        assertEquals(json, gsonWithDefaultFormatter.toJson(foo));
    }

    @DisplayName("测试指定 formatter 进行序列化")
    @Test
    void test_serialize_specifiedFormatter() {
        Foo foo = new Foo();
        foo.localDate = date;
        foo.localDateTime = localDateTime;
        foo.zonedDateTime = zonedDateTime;

        String json = String.format(
            "{\"localDate\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\"}",
            dateFormatter.format(date),
            localDateTimeFormatter.format(localDateTime),
            zonedDateTimeFormatter.format(zonedDateTime)
        );

        assertEquals(json, gsonWithSpecifiedFormatter.toJson(foo));
    }

    @DisplayName("测试使用 TypeAdapter 中默认的 formatter 进行反序列化")
    @Test
    void test_deserialize_defaultFormatter() {
        String json = String.format(
            "{\"localDate\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\",\"instant\":\"%s\"}",
            DateTimeFormatter.ISO_LOCAL_DATE.format(date),
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime),
            DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime),
            DateTimeFormatter.ISO_INSTANT.format(instant)
        );
        Foo foo = gsonWithDefaultFormatter.fromJson(json, Foo.class);
        assertEquals(date, foo.localDate);
        assertEquals(localDateTime, foo.localDateTime);
        assertEquals(zonedDateTime, foo.zonedDateTime);
        assertEquals(instant, foo.instant);
    }

    @DisplayName("测试指定 formatter 进行反序列化")
    @Test
    void test_deserialize_specifiedFormatter() {
        String json = String.format(
            "{\"localDate\":\"%s\",\"localDateTime\":\"%s\",\"zonedDateTime\":\"%s\"}",
            dateFormatter.format(date),
            localDateTimeFormatter.format(localDateTime),
            zonedDateTimeFormatter.format(zonedDateTime)
        );
        Foo foo = gsonWithSpecifiedFormatter.fromJson(json, Foo.class);
        assertEquals(date, foo.localDate);
        assertEquals(localDateTime, foo.localDateTime);
        assertEquals(zonedDateTime, foo.zonedDateTime);
    }

    static class Foo {
        LocalDate localDate;
        LocalDateTime localDateTime;
        ZonedDateTime zonedDateTime;
        Instant instant;
    }
}
