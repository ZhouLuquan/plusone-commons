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

package xyz.zhouxy.plusone.commons.exception;

/**
 * 数据不存在异常
 *
 * @author ZhouXY
 * @since 1.0.0
 */
public final class DataNotExistsException extends Exception {

    private static final long serialVersionUID = 6536955800679703111L;

    /**
     * 使用默认 message 构造新的 {@code DataNotExistsException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     */
    public DataNotExistsException() {
        super();
    }

    /**
     * 使用指定的 {@code message} 构造新的 {@code DataNotExistsException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    public DataNotExistsException(String message) {
        super(message);
    }

    /**
     * 使用指定的 {@code cause} 构造新的 {@code DataNotExistsException}。
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    public DataNotExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 构造新的 {@code DataNotExistsException}。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    public DataNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
