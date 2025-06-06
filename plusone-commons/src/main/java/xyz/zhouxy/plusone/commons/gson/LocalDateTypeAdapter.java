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

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import xyz.zhouxy.plusone.commons.util.AssertTools;

/**
 * {@code LocalDate} 的 {@code TypeAdapter}，
 * 用于 Gson 对 {@code LocalDate} 进行相互转换。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.1.0
 * @see TypeAdapter
 * @see com.google.gson.GsonBuilder
 */
public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    private final DateTimeFormatter dateTimeFormatter;

    /**
     * 默认构造函数，
     * 使用 {@link DateTimeFormatter#ISO_LOCAL_DATE} 进行 {@link LocalDate} 的序列化与反序列化。
     */
    public LocalDateTypeAdapter() {
        this.dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    /**
     * 构造函数，
     * 使用传入的 {@link DateTimeFormatter} 进行 {@link LocalDate} 的序列化与反序列化。
     *
     * @param formatter 用于序列化 {@link LocalDate} 的格式化器，不可为 {@code null}。
     */
    public LocalDateTypeAdapter(DateTimeFormatter formatter) {
        AssertTools.checkArgumentNotNull(formatter, "formatter can not be null.");
        this.dateTimeFormatter = formatter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.value(dateTimeFormatter.format(value));
    }

    /** {@inheritDoc} */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), dateTimeFormatter);
    }
}
