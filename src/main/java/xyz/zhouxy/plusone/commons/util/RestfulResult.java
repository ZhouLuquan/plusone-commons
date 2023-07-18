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

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Preconditions;

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

    private final @Nullable Object data;

    private RestfulResult(final Object status, final String message) {
        this(status, message, null);
    }

    public static RestfulResult success() {
        return new RestfulResult(SUCCESS_STATUS, "操作成功");
    }

    public static RestfulResult success(final String message) {
        Preconditions.checkNotNull(message, "Message must not be null.");
        return new RestfulResult(SUCCESS_STATUS, message);
    }
    
    public static RestfulResult success(final String message, @Nullable final Object data) {
        Preconditions.checkNotNull(message, "Message must not be null.");
        return new RestfulResult(SUCCESS_STATUS, message, data);
    }
    
    public static RestfulResult error() {
        return new RestfulResult(DEFAULT_ERROR_STATUS, "未知错误");
    }
    
    public static RestfulResult error(final Object status, final String message) {
        Preconditions.checkNotNull(status, "Status must not be null.");
        Preconditions.checkNotNull(message, "Message must not be null.");
        return new RestfulResult(status, message);
    }

    public static RestfulResult error(final Object status, final String message, @Nullable final Object data) {
        Preconditions.checkNotNull(status, "Status must not be null.");
        Preconditions.checkNotNull(message, "Message must not be null.");
        return new RestfulResult(status, message, data);
    }

    public static RestfulResult error(final Object status, final Throwable e) {
        Preconditions.checkNotNull(status, "Status must not be null.");
        Preconditions.checkNotNull(e, "Exception must not be null.");
        String msg = e.getMessage();
        if (msg == null) {
            msg = "";
        }
        return new RestfulResult(status, msg);
    }

    public static RestfulResult of(final boolean isSuccess,
            final Supplier<RestfulResult> success, final Supplier<RestfulResult> error) {
        Preconditions.checkNotNull(success, "Success supplier must not be null.");
        Preconditions.checkNotNull(error, "Error supplier must not be null.");
        return isSuccess ? success.get() : error.get();
    }

    public static RestfulResult of(final BooleanSupplier isSuccess,
            final Supplier<RestfulResult> success, final Supplier<RestfulResult> error) {
        Preconditions.checkNotNull(isSuccess, "Conditions for success must not be null.");
        Preconditions.checkNotNull(success, "Success supplier must not be null.");
        Preconditions.checkNotNull(error, "Error supplier must not be null.");
        return isSuccess.getAsBoolean() ? success.get() : error.get();
    }

    // Constructors

    private RestfulResult(final Object status, final String message, @Nullable final Object data) {
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
