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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgumentNotNull;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * 包含 JSR-310 相关数据类型的 {@code TypeAdapter}
 *
 * @author ZhouXY
 * @since 1.1.0
 * @see TypeAdapter
 * @see com.google.gson.GsonBuilder
 */
public class JSR310TypeAdapters {

    /**
     * {@code LocalDate} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalDate} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalDate> localDateTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(LocalDate::from, formatter);
    }

    /**
     * {@code LocalDate} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalDate} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalDate> localDateTypeAdapter() {
        return localDateTypeAdapter(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * {@code LocalDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalDateTime> localDateTimeTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(LocalDateTime::from, formatter);
    }

    /**
     * {@code LocalDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalDateTime> localDateTimeTypeAdapter() {
        return localDateTimeTypeAdapter(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * {@code LocalTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalTime> localTimeTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(LocalTime::from, formatter);
    }

    /**
     * {@code LocalTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code LocalTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<LocalTime> localTimeTypeAdapter() {
        return localTimeTypeAdapter(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    /**
     * {@code ZonedDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code ZonedDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<ZonedDateTime> zonedDateTimeTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(ZonedDateTime::from, formatter);
    }

    /**
     * {@code ZonedDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code ZonedDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<ZonedDateTime> zonedDateTimeTypeAdapter() {
        return zonedDateTimeTypeAdapter(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * {@code Instant} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code Instant} 进行相互转换。
     *
     * <p><b>注意：</b>{@link Instant} 的序列化与反序列化要求 {@link DateTimeFormatter}
     * 包含时区信息（如 {@link DateTimeFormatter#ISO_INSTANT}），
     * 传入不包含时区的格式化器可能导致序列化或反序列化失败。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     *
     * @param formatter 用于序列化 {@link Instant} 的格式化器，不可为 {@code null}，
     *                  且必须包含时区信息
     */
    public static TypeAdapter<Instant> instantTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(Instant::from, formatter);
    }

    /**
     * {@code Instant} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code Instant} 进行相互转换。
     *
     * <p>使用 {@link DateTimeFormatter#ISO_INSTANT} 进行 {@link Instant} 的序列化与反序列化。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<Instant> instantTypeAdapter() {
        return instantTypeAdapter(DateTimeFormatter.ISO_INSTANT);
    }

    /**
     * {@code OffsetDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code OffsetDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<OffsetDateTime> offsetDateTimeTypeAdapter(DateTimeFormatter formatter) {
        return createAdapter(OffsetDateTime::from, formatter);
    }

    /**
     * {@code OffsetDateTime} 的 {@code TypeAdapter}，
     * 用于 Gson 对 {@code OffsetDateTime} 进行相互转换。
     *
     * <p><b>注意：</b>返回的 {@link TypeAdapter} 默认<em>不是</em> null-safe 的。
     * 如需支持 {@code null} 值的序列化与反序列化，请调用 {@link TypeAdapter#nullSafe()}。
     */
    public static TypeAdapter<OffsetDateTime> offsetDateTimeTypeAdapter() {
        return offsetDateTimeTypeAdapter(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    /**
     * 创建一个 {@link TemporalAccessorTypeAdapter} 实例。
     *
     * @param <T>            {@link TemporalAccessor} 的子类型
     * @param temporalQuery  用于从解析结果中查询目标类型的查询器
     * @param formatter      用于序列化与反序列化的格式化器，不可为 {@code null}
     * @return 新的 {@link TypeAdapter} 实例
     */
    private static <T extends TemporalAccessor> TypeAdapter<T> createAdapter(
            TemporalQuery<T> temporalQuery, DateTimeFormatter formatter) {
        return new TemporalAccessorTypeAdapter<T>(temporalQuery, formatter) {
        };
    }

    private abstract static class TemporalAccessorTypeAdapter<T extends TemporalAccessor>
            extends TypeAdapter<T> {

        private final TemporalQuery<T> temporalQuery;

        private final DateTimeFormatter dateTimeFormatter;

        protected TemporalAccessorTypeAdapter(
                TemporalQuery<T> temporalQuery, DateTimeFormatter dateTimeFormatter) {
            checkArgumentNotNull(dateTimeFormatter, "formatter must not be null.");
            this.temporalQuery = temporalQuery;
            this.dateTimeFormatter = dateTimeFormatter;
        }

        /** {@inheritDoc} */
        @Override
        public void write(JsonWriter out, T value) throws IOException {
            out.value(dateTimeFormatter.format(value));
        }

        /** {@inheritDoc} */
        @Override
        public T read(JsonReader in) throws IOException {
            return dateTimeFormatter.parse(in.nextString(), temporalQuery);
        }

    }

    private JSR310TypeAdapters() {
        throw new IllegalStateException("Utility class");
    }
}
