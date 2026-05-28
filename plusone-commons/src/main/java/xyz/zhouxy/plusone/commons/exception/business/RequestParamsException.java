/*
 * Copyright 2024-present ZhouXY
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

package xyz.zhouxy.plusone.commons.exception.business;

/**
 * RequestParamsException
 *
 * <p>
 * 用户请求参数错误
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public class RequestParamsException extends BizException {
    private static final long serialVersionUID = 448337090625192516L;

    private static final String DEFAULT_MSG = "用户请求参数错误";

    /**
     * 使用默认 message 构造新的 {@code RequestParamsException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     */
    public RequestParamsException() {
        super(DEFAULT_MSG);
    }

    /**
     * 使用指定的 {@code message} 构造新的 {@code RequestParamsException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    public RequestParamsException(String message) {
        super(message);
    }

    /**
     * 使用指定的 {@code cause} 构造新的 {@code RequestParamsException}。
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    public RequestParamsException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 构造新的 {@code RequestParamsException}。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    public RequestParamsException(String message, Throwable cause) {
        super(message, cause);
    }

}
