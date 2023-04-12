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

    private final Object status;
    private final String message;
    private final Object data;

    private RestfulResult(final Object status, final String message) {
        this(status, message, null);
    }

    public static RestfulResult success() {
        return new RestfulResult(SUCCESS_STATUS, "操作成功");
    }

    public static RestfulResult success(final String message) {
        return new RestfulResult(SUCCESS_STATUS, message);
    }

    public static RestfulResult success(final String message, final Object data) {
        return new RestfulResult(SUCCESS_STATUS, message, data);
    }

    public static RestfulResult error() {
        return new RestfulResult(DEFAULT_ERROR_STATUS, "未知错误");
    }

    public static RestfulResult error(final Object status, final String message) {
        return new RestfulResult(status, message);
    }

    public static RestfulResult error(final Object status, final String message, final Object data) {
        return new RestfulResult(status, message, data);
    }

    public static RestfulResult error(final Object status, final Throwable e) {
        return new RestfulResult(status, e.getMessage());
    }

    public static RestfulResult of(
            final boolean isSuccess,
            final Supplier<RestfulResult> success,
            final Supplier<RestfulResult> error) {
        return isSuccess ? success.get() : error.get();
    }

    public static RestfulResult of(
            final BooleanSupplier isSuccess,
            final Supplier<RestfulResult> success,
            final Supplier<RestfulResult> error) {
        return isSuccess.getAsBoolean() ? success.get() : error.get();
    }

    // Constructors

    private RestfulResult(Object status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Constructors end

    // Getters

    public Object getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    // Getters end

    @Override
    public String toString() {
        return "RestfulResult [status=" + status + ", message=" + message + ", data=" + data + "]";
    }

    // Builder
    public static Builder successIf(final boolean condition) {
        return successIf(() -> condition);
    }

    public static Builder successIf(final BooleanSupplier booleanSupplier) {
        return new Builder(booleanSupplier, () -> success());
    }

    public static Builder successIf(final boolean condition, final String msg) {
        return new Builder(() -> condition, () -> success(msg));
    }

    public static Builder successIf(final BooleanSupplier booleanSupplier, final String msg) {
        return new Builder(booleanSupplier, () -> success(msg));
    }

    public static Builder successIf(final boolean condition, final String msg, final Object data) {
        return new Builder(() -> condition, () -> success(msg, data));
    }

    public static Builder successIf(final BooleanSupplier booleanSupplier, final String msg, final Object data) {
        return new Builder(booleanSupplier, () -> success(msg, data));
    }

    public static class Builder {
        private final BooleanSupplier booleanSupplier;
        private final Supplier<RestfulResult> success;

        public Builder(final BooleanSupplier booleanSupplier, final Supplier<RestfulResult> success) {
            this.booleanSupplier = booleanSupplier;
            this.success = success;
        }

        public RestfulResult orError() {
            return this.booleanSupplier.getAsBoolean()
                    ? this.success.get()
                    : RestfulResult.error();
        }

        public RestfulResult orError(final Object status, final String msg) {
            return orError(status, msg, null);
        }

        public RestfulResult orError(final Object status, final Throwable e) {
            return orError(status, e.getMessage());
        }

        public RestfulResult orError(final Object status, final String msg, final Object data) {
            return this.booleanSupplier.getAsBoolean()
                    ? this.success.get()
                    : RestfulResult.error(status, msg, data);
        }
    }
}
