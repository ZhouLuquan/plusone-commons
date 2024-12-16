/*
 * Copyright 2024 the original author or authors.
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

import xyz.zhouxy.plusone.commons.base.IWithCode;
import xyz.zhouxy.plusone.commons.exception.business.RequestParamsException;

/**
 * 解析失败异常
 *
 * <p>
 * 解析失败的不一定是客户传的参数，也可能是其它来源的数据解析失败。
 * 如果表示用户传参造成的解析失败，可使用 {@link RequestParamsException#RequestParamsException(Throwable)}，
 * 将 ParsingFailureException 包装成 {@link RequestParamsException} 再抛出。
 * <pre>
 * throw new RequestParamsException(ParsingFailureException.of(ParsingFailureException.Type.NUMBER_PARSING_FAILURE));
 * </pre>
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public final class ParsingFailureException extends RuntimeException {

    private final Type type;

    private ParsingFailureException(Type type) {
        super(type.getDefaultMsg());
        this.type = type;
    }

    private ParsingFailureException(Type type, String msg) {
        super(msg);
        this.type = type;
    }

    private ParsingFailureException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    private ParsingFailureException(Type type, String msg, Throwable cause) {
        super(msg, cause);
        this.type = type;
    }

    public ParsingFailureException() {
        this(Type.DEFAULT);
    }

    public ParsingFailureException(String msg) {
        this(Type.DEFAULT, msg);
    }

    public ParsingFailureException(Throwable e) {
        this(Type.DEFAULT, e);
    }

    public ParsingFailureException(String msg, Throwable e) {
        this(Type.DEFAULT, msg, e);
    }

    public static ParsingFailureException of(Type type) {
        return new ParsingFailureException(type);
    }

    public static ParsingFailureException of(Type type, String msg) {
        return new ParsingFailureException(type, msg);
    }

    public static ParsingFailureException of(Type type, Throwable e) {
        return new ParsingFailureException(type, e);
    }

    public static ParsingFailureException of(Type type, String msg, Throwable e) {
        return new ParsingFailureException(type, msg, e);
    }

    public static ParsingFailureException of(DateTimeParseException e) {
        return new ParsingFailureException(Type.DATE_TIME_PARSING_FAILURE, e.getMessage(), e);
    }

    public static ParsingFailureException of(String msg, DateTimeParseException e) {
        return new ParsingFailureException(Type.DATE_TIME_PARSING_FAILURE, msg, e);
    }

    public static ParsingFailureException of(NumberFormatException e) {
        return new ParsingFailureException(Type.NUMBER_PARSING_FAILURE, e.getMessage(), e);
    }

    public static ParsingFailureException of(String msg, NumberFormatException e) {
        return new ParsingFailureException(Type.NUMBER_PARSING_FAILURE, msg, e);
    }

    public Type getType() {
        return type;
    }

    public enum Type implements IWithCode<String> {
        DEFAULT("00", "解析失败"),
        NUMBER_PARSING_FAILURE("10", "数字转换失败"),
        DATE_TIME_PARSING_FAILURE("20", "时间解析失败"),
        JSON_PARSING_FAILURE("30", "JSON 解析失败"),
        XML_PARSING_FAILURE("40", "XML 解析失败"),
        ;

        @Nonnull
        final String code;
        @Nonnull
        final String defaultMsg;

        Type(String code, String defaultMsg) {
            this.code = code;
            this.defaultMsg = defaultMsg;
        }

        @Override
        @Nonnull
        public String getCode() {
            return code;
        }

        public String getDefaultMsg() {
            return defaultMsg;
        }
    }
}
