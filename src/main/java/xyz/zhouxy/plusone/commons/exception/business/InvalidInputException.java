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

package xyz.zhouxy.plusone.commons.exception.business;

import javax.annotation.Nonnull;

import xyz.zhouxy.plusone.commons.base.IWithCode;

/**
 * InvalidInputException
 *
 * <p>
 * 用户输入内容非法
 * </p>
 *
 * <p>
 * <b>NOTE: 属业务异常</b>
 * </p>
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public final class InvalidInputException extends RequestParamsException {

    private final Type type;

    private InvalidInputException(Type type) {
        super(type.getDefaultMsg());
        this.type = type;
    }

    private InvalidInputException(Type type, String msg) {
        super(msg);
        this.type = type;
    }

    private InvalidInputException(Type type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    private InvalidInputException(Type type, String msg, Throwable cause) {
        super(msg, cause);
        this.type = type;
    }

    public InvalidInputException() {
        this(Type.DEFAULT);
    }

    public InvalidInputException(String msg) {
        this(Type.DEFAULT, msg);
    }

    public InvalidInputException(Throwable e) {
        this(Type.DEFAULT, e);
    }

    public InvalidInputException(String msg, Throwable e) {
        this(Type.DEFAULT, msg, e);
    }

    public static InvalidInputException of(Type type) {
        return new InvalidInputException(type);
    }

    public static InvalidInputException of(Type type, String msg) {
        return new InvalidInputException(type, msg);
    }

    public static InvalidInputException of(Type type, Throwable e) {
        return new InvalidInputException(type, e);
    }

    public static InvalidInputException of(Type type, String msg, Throwable e) {
        return new InvalidInputException(type, msg, e);
    }

    public static InvalidInputException of(Throwable e) {
        return new InvalidInputException(Type.DEFAULT, e.getMessage(), e);
    }

    public static InvalidInputException of(String msg, Throwable e) {
        return new InvalidInputException(Type.DEFAULT, msg, e);
    }

    public Type getType() {
        return type;
    }

    public enum Type implements IWithCode<String> {
        DEFAULT("00", "用户输入内容非法"),
        CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS("01", "包含非法恶意跳转链接"),
        CONTAINS_ILLEGAL_WORDS("02", "包含违禁敏感词"),
        PICTURE_CONTAINS_ILLEGAL_INFORMATION("03", "图片包含违禁信息"),
        INFRINGE_COPYRIGHT("04", "文件侵犯版权"),
        ;

        final String code;
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
