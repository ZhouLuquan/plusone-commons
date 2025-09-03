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

package xyz.zhouxy.plusone.commons.exception;

import java.time.format.DateTimeParseException;

import javax.annotation.Nonnull;

import xyz.zhouxy.plusone.commons.exception.business.RequestParamsException;

/**
 * 解析失败异常
 *
 * <p>
 * 解析失败的不一定是客户传的参数，也可能是其它来源的数据解析失败。
 * 如果表示用户传参造成的解析失败，可使用 {@link RequestParamsException#RequestParamsException(Throwable)}，
 * 将 ParsingFailureException 包装成 {@link RequestParamsException} 再抛出。
 * <pre>
 * throw new RequestParamsException(ParsingFailureException.Type.NUMBER_PARSING_FAILURE.create());
 * </pre>
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public final class ParsingFailureException
        extends Exception
        implements IMultiTypesException<ParsingFailureException.Type> {
    private static final long serialVersionUID = 795996090625132616L;

    private final Type type;

    private ParsingFailureException(Type type, String message) {
        super(message);
        this.type = type;
    }

    private ParsingFailureException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    private ParsingFailureException(Type type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    /**
     * 创建默认类型的 {@code ParsingFailureException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code message} 为 {@link Type#DEFAULT} 的默认信息。
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     */
    public ParsingFailureException() {
        this(Type.DEFAULT, Type.DEFAULT.getDefaultMessage());
    }

    /**
     * 使用指定 {@code message} 创建默认类型的 {@code ParsingFailureException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code cause} 未初始化，后面可能会通过调用 {@link #initCause} 进行初始化。
     *
     * @param message 异常信息
     */
    public ParsingFailureException(String message) {
        this(Type.DEFAULT, message);
    }

    /**
     * 使用指定的 {@code cause} 创建默认类型的 {@code ParsingFailureException}。
     * {@code type} 为 {@link Type#DEFAULT}，
     * {@code message} 为 (cause==null ? null : cause.toString())。
     *
     * @param cause 包装的异常
     */
    public ParsingFailureException(Throwable cause) {
        this(Type.DEFAULT, cause);
    }

    /**
     * 使用指定的 {@code message} 和 {@code cause} 创建默认类型的 {@code ParsingFailureException}。
     * {@code type} 为 {@link Type#DEFAULT}。
     *
     * @param message 异常信息
     * @param cause   包装的异常
     */
    public ParsingFailureException(String message, Throwable cause) {
        this(Type.DEFAULT, message, cause);
    }

    /**
     * 将 {@link DateTimeParseException} 包装为 {@link ParsingFailureException}。
     * {@code type} 为 {@link Type#DATE_TIME_PARSING_FAILURE}。
     *
     * @param cause 包装的 {@code DateTimeParseException}
     * @return ParsingFailureException
     */
    public static ParsingFailureException of(DateTimeParseException cause) {
        if (cause == null) {
            return Type.DATE_TIME_PARSING_FAILURE.create();
        }
        return Type.DATE_TIME_PARSING_FAILURE.create(cause.getMessage(), cause);
    }

    /**
     * 将 {@link DateTimeParseException} 包装为 {@link ParsingFailureException}。
     * {@code type} 为 {@link Type#DATE_TIME_PARSING_FAILURE}。
     *
     * @param message 异常信息
     * @param cause   包装的 {@code DateTimeParseException}
     * @return ParsingFailureException
     */
    public static ParsingFailureException of(String message, DateTimeParseException cause) {
        return Type.DATE_TIME_PARSING_FAILURE.create(message, cause);
    }

    /**
     * 将 {@link NumberFormatException} 包装为 {@link ParsingFailureException}。
     * {@code type} 为 {@link Type#NUMBER_PARSING_FAILURE}。
     *
     * @param cause 包装的 {@code NumberFormatException}
     * @return ParsingFailureException
     */
    public static ParsingFailureException of(NumberFormatException cause) {
        if (cause == null) {
            return Type.NUMBER_PARSING_FAILURE.create();
        }
        return Type.NUMBER_PARSING_FAILURE.create(cause.getMessage(), cause);
    }

    /**
     * 将 {@link NumberFormatException} 包装为 {@link ParsingFailureException}。
     * {@code type} 为 {@link Type#NUMBER_PARSING_FAILURE}。
     *
     * @param message 异常信息
     * @param cause   {@code NumberFormatException}
     * @return ParsingFailureException
     */
    public static ParsingFailureException of(String message, NumberFormatException cause) {
        return Type.NUMBER_PARSING_FAILURE.create(message, cause);
    }

    @Override
    @Nonnull
    public Type getType() {
        return type;
    }

    /** 默认类型 */
    public static final Type DEFAULT = Type.DEFAULT;
    /** 数字转换失败 */
    public static final Type NUMBER_PARSING_FAILURE = Type.NUMBER_PARSING_FAILURE;
    /** 时间解析失败 */
    public static final Type DATE_TIME_PARSING_FAILURE = Type.DATE_TIME_PARSING_FAILURE;
    /** JSON 解析失败 */
    public static final Type JSON_PARSING_FAILURE = Type.JSON_PARSING_FAILURE;
    /** XML 解析失败 */
    public static final Type XML_PARSING_FAILURE = Type.XML_PARSING_FAILURE;

    public enum Type implements IExceptionType<String>, IExceptionFactory<ParsingFailureException> {
        DEFAULT("00", "解析失败"),
        NUMBER_PARSING_FAILURE("10", "数字转换失败"),
        DATE_TIME_PARSING_FAILURE("20", "时间解析失败"),
        JSON_PARSING_FAILURE("30", "JSON 解析失败"),
        XML_PARSING_FAILURE("40", "XML 解析失败"),
        ;

        @Nonnull
        private final String code;
        @Nonnull
        private final String defaultMessage;

        Type(String code, String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
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
        public @Nonnull ParsingFailureException create() {
            return new ParsingFailureException(this, this.defaultMessage);
        }

        @Override
        public @Nonnull ParsingFailureException create(String message) {
            return new ParsingFailureException(this, message);
        }

        @Override
        public @Nonnull ParsingFailureException create(Throwable cause) {
            return new ParsingFailureException(this, cause);
        }

        @Override
        public @Nonnull ParsingFailureException create(String message, Throwable cause) {
            return new ParsingFailureException(this, message, cause);
        }
    }
}
