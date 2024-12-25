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

import javax.annotation.Nonnull;

import xyz.zhouxy.plusone.commons.base.IWithCode;

/**
 * 异常类型
 *
 * <p>
 * 异常在不同场景下被抛出，可以用不同的枚举值，表示不同的异常类型。
 * 该枚举实现本接口，用于基于不同类型创建异常。
 *
 * <pre>
 * public final class LoginException extends RuntimeException {
 *     private final Type type;
 *     private LoginException(Type type, String message) {
 *         super(message);
 *         this.type = type;
 *     }
 *
 *     private LoginException(Type type, Throwable cause) {
 *         super(cause);
 *         this.type = type;
 *     }
 *
 *     private LoginException(Type type, String message, Throwable cause) {
 *         super(message, cause);
 *         this.type = type;
 *     }
 *
 *     // ...
 *
 *     public enum Type implements ExceptionType<LoginException> {
 *         DEFAULT("00", "当前会话未登录"),
 *         NOT_TOKEN("10", "未提供token"),
 *         INVALID_TOKEN("20", "token无效"),
 *         TOKEN_TIMEOUT("30", "token已过期"),
 *         BE_REPLACED("40", "token已被顶下线"),
 *         KICK_OUT("50", "token已被踢下线"),
 *         ;
 *
 *         &#64;Nonnull
 *         private final String code;
 *         &#64;Nonnull
 *         private final String defaultMessage;
 *
 *         Type(String code, String defaultMessage) {
 *             this.code = code;
 *             this.defaultMessage = defaultMessage;
 *         }
 *
 *         &#64;Override
 *         &#64;Nonnull
 *         public String getCode() {
 *             return code;
 *         }
 *
 *         &#64;Override
 *         public String getDefaultMessage() {
 *             return defaultMessage;
 *         }
 *
 *         &#64;Override
 *         &#64;Nonnull
 *         public LoginException create() {
 *             return new LoginException(this, this.defaultMessage);
 *         }
 *
 *         &#64;Override
 *         &#64;Nonnull
 *         public LoginException create(String message) {
 *             return new LoginException(this, message);
 *         }
 *
 *         &#64;Override
 *         &#64;Nonnull
 *         public LoginException create(Throwable cause) {
 *             return new LoginException(this, cause);
 *         }
 *
 *         &#64;Override
 *         &#64;Nonnull
 *         public LoginException create(String message, Throwable cause) {
 *             return new LoginException(this, message, cause);
 *         }
 *     }
 * }
 * </pre>
 *
 * 使用时，可以使用这种方式创建并抛出异常：
 * <pre>
 * throw LoginException.Type.TOKEN_TIMEOUT.create();
 * </pre>
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108}">ZhouXY</a>
 */
public interface ExceptionType<E extends Exception> extends IWithCode<String> {

    String getDefaultMessage();

    @Nonnull
    E create();

    @Nonnull
    E create(String message);

    @Nonnull
    E create(Throwable cause);

    @Nonnull
    E create(String message, Throwable cause);

}
