/*
 * Copyright 2024-2025 the original author or authors.
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

package xyz.zhouxy.plusone.commons.exception.system;

/**
 * 系统异常
 *
 * <p>
 * 通常表示应用代码存在问题，或因环境问题，引发异常。
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public class SysException extends RuntimeException {
    private static final long serialVersionUID = -936435090625482516L;

    private static final String DEFAULT_MSG = "系统异常";

    /**
     * 使用指定的 {@code message} 构造新的系统异常。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    protected SysException(String message) {
        super(message);
    }

    /**
     * 使用指定的 {@code cause} 构造新的系统异常。
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    protected SysException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 构造新的系统异常。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    protected SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public static SysException of() {
        return new SysException(DEFAULT_MSG);
    }

    public static SysException of(String message) {
        return new SysException(message);
    }

    public static SysException of(String errorMessageFormat, Object... errorMessageArgs) {
        return new SysException(String.format(errorMessageFormat, errorMessageArgs));
    }

    public static SysException of(Throwable cause) {
        return new SysException(cause);
    }

    public static SysException of(String message, Throwable cause) {
        return new SysException(message, cause);
    }
}
