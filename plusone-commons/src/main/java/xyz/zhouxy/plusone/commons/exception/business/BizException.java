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
 * BizException
 *
 * <p>
 * 业务异常
 *
 * <p>
 * <b>NOTE: 通常表示业务中的意外情况。如：用户错误输入、缺失必填字段、用户余额不足等。</b>
 *
 * @author ZhouXY
 * @since 1.0.0
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 982585090625482416L;

    private static final String DEFAULT_MSG = "业务异常";

    /**
     * 使用指定的 {@code message} 构造新的业务异常。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    protected BizException(String message) {
        super(message);
    }

    /**
     * 使用指定的 {@code cause} 构造新的业务异常。
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    protected BizException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 构造新的业务异常。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    protected BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BizException of() {
        return new BizException(DEFAULT_MSG);
    }

    public static BizException of(String message) {
        return new BizException(message);
    }

    public static BizException of(String errorMessageFormat, Object... errorMessageArgs) {
        return new BizException(String.format(errorMessageFormat, errorMessageArgs));
    }

    public static BizException of(Throwable cause) {
        return new BizException(cause);
    }

    public static BizException of(String message, Throwable cause) {
        return new BizException(message, cause);
    }
}
