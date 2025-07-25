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
 * @author ZhouXY108 <luquanlion@outlook.com>
 */
public class UnifiedResponse<T> {

    private final String code;
    private final String message;

    private final @Nullable T data;

    // ================================
    // #region - Constructors
    // ================================

    /**
     * 构造 {@code UnifiedResponse}
     *
     * @param code    状态码
     * @param message 响应信息
     */
    UnifiedResponse(String code, @Nullable String message) {
        this(code, message, null);
    }

    /**
     * 构造 {@code UnifiedResponse}
     *
     * @param code    状态码
     * @param message 响应信息
     * @param data    响应数据
     */
    UnifiedResponse(String code, @Nullable String message, @Nullable T data) {
        this.code = Objects.requireNonNull(code);
        this.message = message == null ? "" : message;
        this.data = data;
    }

    // ================================
    // #endregion - Constructors
    // ================================

    // ================================
    // #region - Getters
    // ================================

    /**
     * 状态码
     *
     * @return 状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 响应信息
     *
     * @return 响应信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 响应数据
     *
     * @return 响应数据
     */
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

    private static String transValue(@Nullable Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return "\"" + value + "\"";
        }
        return String.valueOf(value);
    }
}
