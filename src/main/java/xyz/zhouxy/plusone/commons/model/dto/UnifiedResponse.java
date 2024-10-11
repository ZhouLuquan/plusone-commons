/*
 * Copyright 2023-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.model.dto;

import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * 统一结果，对返回给前端的数据进行封装。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public abstract class UnifiedResponse {

    private Object status;
    private String message;

    private @Nullable Object data;

    public static UnifiedResponse success() {
        return new SuccessResult();
    }

    public static UnifiedResponse success(@Nullable String message) {
        return new SuccessResult(message);
    }

    public static UnifiedResponse success(@Nullable String message, @Nullable Object data) {
        return new SuccessResult(message, data);
    }

    public static UnifiedResponse error(@Nullable String message) {
        return new ErrorResult(message);
    }

    public static UnifiedResponse error(@Nullable String message, @Nullable Object data) {
        return new ErrorResult(message, data);
    }

    public static UnifiedResponse error(Object status, @Nullable String message) {
        return new ErrorResult(status, message);
    }

    public static UnifiedResponse error(Object status, @Nullable String message, @Nullable Object data) {
        return new ErrorResult(status, message, data);
    }

    public static UnifiedResponse error(Object status, Throwable e) {
        return new ErrorResult(status, e);
    }

    public static UnifiedResponse of(Object status, @Nullable String message) {
        return new CustomResult(status, message);
    }

    public static UnifiedResponse of(Object status, @Nullable String message, @Nullable Object data) {
        return new CustomResult(status, message, data);
    }

    public static UnifiedResponse of(final boolean isSuccess,
            final Supplier<SuccessResult> successResult, final Supplier<ErrorResult> errorResult) {
        Preconditions.checkNotNull(successResult, "Success supplier must not be null.");
        Preconditions.checkNotNull(errorResult, "Error supplier must not be null.");
        return isSuccess ? successResult.get() : errorResult.get();
    }

    public static UnifiedResponse of(final BooleanSupplier isSuccess,
            final Supplier<SuccessResult> successResult, final Supplier<ErrorResult> errorResult) {
        Preconditions.checkNotNull(isSuccess, "Conditions for success must not be null.");
        Preconditions.checkNotNull(successResult, "Success supplier must not be null.");
        Preconditions.checkNotNull(errorResult, "Error supplier must not be null.");
        return isSuccess.getAsBoolean() ? successResult.get() : errorResult.get();
    }

    protected UnifiedResponse(Object status, @Nullable String message) {
        setStatus(status);
        setMessage(message);
    }

    protected UnifiedResponse(Object status, @Nullable String message, @Nullable Object data) {
        setStatus(status);
        setMessage(message);
        setData(data);
    }

    private void setStatus(Object status) {
        this.status = Objects.requireNonNull(status);
    }

    private void setMessage(@Nullable String message) {
        this.message = message == null ? "" : message;
    }

    private void setData(@Nullable Object data) {
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

    @Nullable
    public Object getData() {
        return data;
    }

    // Getters end

    @Override
    public String toString() {
        return String.format("{status: %s, message: \"%s\", data: %s}",
                transValue(this.status), this.message, transValue(this.data));
    }

    private static String transValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }

    protected static class SuccessResult extends UnifiedResponse {
        public static final String SUCCESS_STATUS = "2000000";

        private static final String DEFAULT_SUCCESS_MSG = "SUCCESS";

        SuccessResult() {
            super(SUCCESS_STATUS, DEFAULT_SUCCESS_MSG);
        }

        SuccessResult(@Nullable String message) {
            super(SUCCESS_STATUS, message);
        }

        SuccessResult(@Nullable String message, @Nullable Object data) {
            super(SUCCESS_STATUS, message, data);
        }
    }

    protected static class ErrorResult extends UnifiedResponse {
        public static final String DEFAULT_ERROR_STATUS = "9999999";

        ErrorResult(@Nullable String message) {
            super(DEFAULT_ERROR_STATUS, message);
        }

        ErrorResult(@Nullable String message, @Nullable Object data) {
            super(DEFAULT_ERROR_STATUS, message, data);
        }

        ErrorResult(Object status, @Nullable String message) {
            super(status, message);
        }

        ErrorResult(Object status, @Nullable String message, @Nullable Object data) {
            super(status, message, data);
        }

        ErrorResult(Object status, Throwable e) {
            super(status, Objects.requireNonNull(e).getMessage());
        }
    }

    /**
     * 自定义结果
     *
     * @author zhouxy
     */
    protected static class CustomResult extends UnifiedResponse {

        CustomResult(Object status, @Nullable String message) {
            super(status, message);
        }

        CustomResult(Object status, @Nullable String message, @Nullable Object data) {
            super(status, message, data);
        }
    }
}
