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

/**
 * 解析失败异常
 *
 * <p>
 * 解析失败的不一定是客户传的参数，也可能是其它来源的数据解析失败
 * 如果表示用户传参造成的解析失败，可使用 RequestParamsException(Throwable cause)，
 * 将 ParsingFailureException 包装成 {@link RequestParamsException} 再抛出
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

    public Type getType() {
        return type;
    }

    public enum Type {
        DEFAULT("4010500", "解析失败"),
        NUMBER_PARSING_FAILURE("4010501", "数字转换失败"),
        DATE_TIME_PARSING_FAILURE("4010502", "时间解析失败"),
        JSON_PARSING_FAILURE("4010503", "JSON 解析失败"),
        XML_PARSING_FAILURE("4010504", "XML 解析失败"),
        ;

        final String code;
        final String defaultMsg;

        Type(String code, String defaultMsg) {
            this.code = code;
            this.defaultMsg = defaultMsg;
        }

        public String getCode() {
            return code;
        }

        public String getDefaultMsg() {
            return defaultMsg;
        }
    }
}
