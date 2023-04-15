/*
 * Copyright 2022-2023 the original author or authors.
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

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 对返回给前端的数据进行封装
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestfulResult {

    public static final int SUCCESS_STATUS = 2000000;
    public static final int DEFAULT_ERROR_STATUS = 9999999;

    @Nonnull
    private final Object status;
    @Nonnull
    private final String message;
    @Nullable
    private final Object data;

    private RestfulResult(@Nonnull final Object status, @Nonnull final String message) {
        this(status, message, null);
    }

    @Nonnull
    public static RestfulResult success() {
        return new RestfulResult(SUCCESS_STATUS, "操作成功");
    }

    @Nonnull
    public static RestfulResult success(@Nonnull final String message) {
        return new RestfulResult(SUCCESS_STATUS, message);
    }

    @Nonnull
    public static RestfulResult success(
            @Nonnull final String message,
            @Nullable final Object data) {
        return new RestfulResult(SUCCESS_STATUS, message, data);
    }

    @Nonnull
    public static RestfulResult error() {
        return new RestfulResult(DEFAULT_ERROR_STATUS, "未知错误");
    }

    @Nonnull
    public static RestfulResult error(
            @Nonnull final Object status,
            @Nonnull final String message) {
        return new RestfulResult(status, message);
    }

    @Nonnull
    public static RestfulResult error(
            @Nonnull final Object status,
            @Nonnull final String message,
            @Nullable final Object data) {
        return new RestfulResult(status, message, data);
    }

    @Nonnull
    public static RestfulResult error(@Nonnull final Object status, @Nonnull final Throwable e) {
        String msg = e.getMessage();
        if (msg == null) {
            msg = "";
        }
        return new RestfulResult(status, msg);
    }

    public static RestfulResult of(
            final boolean isSuccess,
            @Nonnull final Supplier<RestfulResult> success,
            @Nonnull final Supplier<RestfulResult> error) {
        return isSuccess ? success.get() : error.get();
    }

    public static RestfulResult of(
            @Nonnull final BooleanSupplier isSuccess,
            @Nonnull final Supplier<RestfulResult> success,
            @Nonnull final Supplier<RestfulResult> error) {
        return isSuccess.getAsBoolean() ? success.get() : error.get();
    }

    // Constructors

    private RestfulResult(
            @Nonnull final Object status,
            @Nonnull final String message,
            @Nullable final Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Constructors end

    // Getters

    @Nonnull
    public Object getStatus() {
        return status;
    }

    @Nonnull
    public String getMessage() {
        return message;
    }

    @Nullable
    public Object getData() {
        return data;
    }

    // Getters end

    @Override
    public String toString() {
        return "RestfulResult [status=" + status + ", message=" + message + ", data=" + data + "]";
    }
}
