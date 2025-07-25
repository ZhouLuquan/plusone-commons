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

package xyz.zhouxy.plusone.commons.exception.system;

/**
 * NoAvailableMacFoundException
 *
 * <p>
 * 在无法找到可访问的 Mac 地址时抛出
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public class NoAvailableMacFoundException extends SysException {
    private static final long serialVersionUID = 152827098461071551L;

    /**
     * 使用默认 message 构造新的 {@code NoAvailableMacFoundException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     */
    public NoAvailableMacFoundException() {
        super();
    }

    /**
     * 使用指定的 {@code message} 构造新的 {@code NoAvailableMacFoundException}。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    public NoAvailableMacFoundException(String message) {
        super(message);
    }

    /**
     * 使用指定的 {@code cause} 构造新的 {@code NoAvailableMacFoundException}。
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    public NoAvailableMacFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 构造新的 {@code NoAvailableMacFoundException}。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    public NoAvailableMacFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
