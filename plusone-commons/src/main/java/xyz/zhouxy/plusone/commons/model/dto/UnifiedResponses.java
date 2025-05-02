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

package xyz.zhouxy.plusone.commons.model.dto;

import javax.annotation.Nullable;

/**
 * UnifiedResponse 工厂
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 * @see UnifiedResponse
 */
public class UnifiedResponses {

    public static final String SUCCESS_CODE = "2000000";
    public static final String DEFAULT_SUCCESS_MSG = "SUCCESS";

    // ================================
    // #region - success
    // ================================

    /**
     * 默认成功响应结果
     *
     * @return {@code UnifiedResponse} 对象。
     *         {@code code} = "2000000", {@code message} = "SUCCESS", {@code data} = null
     */
    public static UnifiedResponse<Void> success() {
        return new UnifiedResponse<>(SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
    }

    /**
     * 使用指定 {@code message} 创建成功响应结果
     *
     * @param message 成功信息
     * @return {@code UnifiedResponse} 对象。
     *         {@code code} = "2000000", {@code data} = null
     */
    public static UnifiedResponse<Void> success(@Nullable String message) {
        return new UnifiedResponse<>(SUCCESS_CODE, message);
    }

    /**
     * 使用指定 {@code message} 和 {@code data} 创建成功响应结果
     *
     * @param <T>     data 类型
     * @param message 成功信息
     * @param data    携带数据
     * @return {@code UnifiedResponse} 对象。
     *         {@code code} = "2000000"
     */
    public static <T> UnifiedResponse<T> success(@Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(SUCCESS_CODE, message, data);
    }

    // ================================
    // #endregion - success
    // ================================

    // ================================
    // #region - error
    // ================================

    /**
     * 创建错误响应结果
     *
     * @param code    错误码
     * @param message 错误信息
     * @return {@code UnifiedResponse} 对象（{@code data} 为 {@code null}）
     */
    public static UnifiedResponse<Void> error(String code, @Nullable String message) {
        return new UnifiedResponse<>(code, message);
    }

    /**
     * 创建错误响应结果
     *
     * @param <T>     data 类型
     * @param code    错误码
     * @param message 错误信息
     * @param data    携带数据
     * @return {@code UnifiedResponse} 对象
     */
    public static <T> UnifiedResponse<T> error(String code, @Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(code, message, data);
    }

    /**
     * 创建错误响应结果
     *
     * @param code 错误码
     * @param e    异常
     * @return {@code UnifiedResponse} 对象。
     *         {@code message} 为异常的 {@code message}，
     *         {@code data} 为 {@code null}。
     */
    public static UnifiedResponse<Void> error(String code, Throwable e) {
        return new UnifiedResponse<>(code, e.getMessage());
    }

    // ================================
    // #endregion - error
    // ================================

    // ================================
    // #region - of
    // ================================

    /**
     * 创建响应结果
     *
     * @param code    状态码
     * @param message 响应信息
     * @return {@code UnifiedResponse} 对象（{@code data} 为 {@code null}）
     */
    public static UnifiedResponse<Void> of(String code, @Nullable String message) {
        return new UnifiedResponse<>(code, message);
    }

    /**
     * 创建响应结果
     *
     * @param <T>     data 类型
     * @param code    状态码
     * @param message 响应信息
     * @param data    携带数据
     * @return {@code UnifiedResponse} 对象
     */
    public static <T> UnifiedResponse<T> of(String code, @Nullable String message, @Nullable T data) {
        return new UnifiedResponse<>(code, message, data);
    }

    // ================================
    // #endregion - of
    // ================================

    protected UnifiedResponses() {
    }
}
