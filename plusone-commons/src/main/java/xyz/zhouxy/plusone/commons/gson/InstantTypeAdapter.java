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
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * {@code Instant} 的 {@code TypeAdapter}，
 * 用于 Gson 对 {@code Instant} 进行相互转换。
 *
 * <p>
 * 使用 {@link DateTimeFormatter#ISO_INSTANT} 进行 {@link Instant} 的序列化与反序列化。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.1.0
 * @see TypeAdapter
 * @see com.google.gson.GsonBuilder
 */
public final class InstantTypeAdapter extends TypeAdapter<Instant> {

    /** {@inheritDoc} */
    @Override
    public void write(JsonWriter out, Instant value) throws IOException {
        out.value(DateTimeFormatter.ISO_INSTANT.format(value));
    }

    /** {@inheritDoc} */
    @Override
    public Instant read(JsonReader in) throws IOException {
        return Instant.parse(in.nextString());
    }
}
