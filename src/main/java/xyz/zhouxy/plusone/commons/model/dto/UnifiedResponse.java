/*
 * Copyright 2023-2025 the original author or authors.
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
import javax.annotation.Nullable;

/**
 * 统一结果，对返回给前端的数据进行封装。
 *
 * <p>
 * <b>SUCCESS: 2000000</b>
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public class UnifiedResponse<T> {

    private String code;
    private String message;

    private @Nullable T data;

    // ================================
    // #region - Constructors
    // ================================

    private UnifiedResponse(String code, @Nullable String message) {
        this(code, message, null);
    }

    private UnifiedResponse(String code, @Nullable String message, @Nullable T data) {
        this.code = Objects.requireNonNull(code);
        this.message = message == null ? "" : message;
        this.data = data;
    }

    // ================================
    // #endregion - Constructors
    // ================================

    public static final String SUCCESS_CODE = "2000000";
    private static final String DEFAULT_SUCCESS_MSG = "SUCCESS";

    // ================================
    // #region - success
    // ================================

    public static UnifiedResponse<Void> success() {
        return new UnifiedResponse<>(SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    public static UnifiedResponse<Void> success(@Nullable String message) {
        return new UnifiedResponse<>(SUCCESS_CODE, message);
    }

    public static <T> UnifiedResponse<T> success(@Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(SUCCESS_CODE, message, data);
    }

    // ================================
    // #endregion - success
    // ================================

    // ================================
    // #region - error
    // ================================

    public static UnifiedResponse<Void> error(String code, @Nullable String message) {
        return new UnifiedResponse<>(code, message);
    }

    public static <T> UnifiedResponse<T> error(String code, @Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(code, message, data);
    }

    public static UnifiedResponse<Void> error(String code, Throwable e) {
        return new UnifiedResponse<>(code, e.getMessage());
    }

    // ================================
    // #endregion - error
    // ================================

    // ================================
    // #region - of
    // ================================

    public static UnifiedResponse<Void> of(String code, @Nullable String message) {
        return new UnifiedResponse<>(code, message);
    }

    public static <T> UnifiedResponse<T> of(String code, @Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(code, message, data);
    }

    // ================================
    // #endregion - of
    // ================================

    // ================================
    // #region - Getters
    // ================================

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public T getData() {
        return data;
    }

    // ================================
    // #endregion - Getters
    // ================================

    @Override
    public String toString() {
        return String.format("{code: \"%s\", message: \"%s\", data: %s}",
                this.code, this.message, transValue(this.data));
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
}
