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

package xyz.zhouxy.plusone.commons.exception.business;

import javax.annotation.Nonnull;

import xyz.zhouxy.plusone.commons.exception.MultiTypesException.ExceptionType;
import xyz.zhouxy.plusone.commons.exception.MultiTypesException;

/**
 * InvalidInputException
 *
 * <p>
 * 用户输入内容非法
 *
 * <p>
 * <b>NOTE: 属业务异常</b>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 */
public final class InvalidInputException
        extends RequestParamsException
        implements MultiTypesException<InvalidInputException, InvalidInputException.Type> {

    private final Type type;

    private InvalidInputException(Type type) {
        super(type.getDefaultMessage());
        this.type = type;
    }

    private InvalidInputException(Type type, String message) {
        super(message);
        this.type = type;
    }

    private InvalidInputException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    private InvalidInputException(Type type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    /**
     * 创建默认类型的 {@code InvalidInputException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code message} 为 {@link Type#DEFAULT} 的默认信息。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     */
    public InvalidInputException() {
        this(Type.DEFAULT);
    }

    /**
     * 使用指定 {@code message} 创建默认类型的 {@code InvalidInputException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    public InvalidInputException(String message) {
        this(Type.DEFAULT, message);
    }

    /**
     * 使用指定的 {@code cause} 创建默认类型的 {@code InvalidInputException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    public InvalidInputException(Throwable cause) {
        this(Type.DEFAULT, cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 创建默认类型的 {@code InvalidInputException}。
     * {@code type} 为 {@link Type#DEFAULT}。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    public InvalidInputException(String message, Throwable cause) {
        this(Type.DEFAULT, message, cause);
    }

    @Override
    @Nonnull
    public Type getType() {
        return this.type;
    }

    public enum Type implements ExceptionType<InvalidInputException> {
        DEFAULT("00", "用户输入内容非法"),
        CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS("01", "包含非法恶意跳转链接"),
        CONTAINS_ILLEGAL_WORDS("02", "包含违禁敏感词"),
        PICTURE_CONTAINS_ILLEGAL_INFORMATION("03", "图片包含违禁信息"),
        INFRINGE_COPYRIGHT("04", "文件侵犯版权"),
        ;

        @Nonnull
        private final String code;
        @Nonnull
        private final String defaultMessage;

        Type(String code, String defaultMsg) {
            this.code = code;
            this.defaultMessage = defaultMsg;
        }

        @Override
        public @Nonnull String getCode() {
            return code;
        }

        @Override
        public @Nonnull String getDefaultMessage() {
            return defaultMessage;
        }

        @Override
        public @Nonnull InvalidInputException create() {
            return new InvalidInputException(this);
        }

        @Override
        public @Nonnull InvalidInputException create(String message) {
            return new InvalidInputException(this, message);
        }

        @Override
        public @Nonnull InvalidInputException create(Throwable cause) {
            return new InvalidInputException(this, cause);
        }

        @Override
        public @Nonnull InvalidInputException create(String message, Throwable cause) {
            return new InvalidInputException(this, message, cause);
        }
    }
}
