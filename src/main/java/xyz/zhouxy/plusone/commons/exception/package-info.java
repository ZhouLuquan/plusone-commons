/*
 * Copyright 2025 the original author or authors.
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

/**
 * <h1>异常<h1>
 *
 * <h2>1. {@link MultiTypesException} - 多类型异常</h2>
 * <p>
 * 异常在不同场景下被抛出，可以用不同的枚举值，表示不同的场景类型。
 * </p>
 * <p>
 * 异常实现 {@link MultiTypesException} 的 {@link MultiTypesException#getType} 方法，返回对应的场景类型。
 * </p>
 * <p>
 * 表示场景类型的枚举实现 {@link MultiTypesException.ExceptionType}，其中的工厂方法用于创建类型对象。
 * </p>
 *
 * <pre>
 * public final class LoginException
 *         extends RuntimeException
 *         implements MultiTypesException&lt;LoginException, LoginException.Type&gt; {
 *     private final Type type;
 *     private LoginException(&#64;Nonnull Type type, &#64;Nonnull String message) {
 *         super(message);
 *         this.type = type;
 *     }
 *
 *     private LoginException(&#64;Nonnull Type type, &#64;Nonnull Throwable cause) {
 *         super(cause);
 *         this.type = type;
 *     }
 *
 *     private LoginException(&#64;Nonnull Type type,
 *                            &#64;Nonnull String message,
 *                            &#64;Nonnull Throwable cause) {
 *         super(message, cause);
 *         this.type = type;
 *     }
 *
 *     &#64;Override
 *     public &#64;Nonnull Type getType() {
 *         return this.type;
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
 *         Type(&#64;Nonnull String code, &#64;Nonnull String defaultMessage) {
 *             this.code = code;
 *             this.defaultMessage = defaultMessage;
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull String getCode() {
 *             return code;
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull String getDefaultMessage() {
 *             return defaultMessage;
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull LoginException create() {
 *             return new LoginException(this, this.defaultMessage);
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull LoginException create(String message) {
 *             return new LoginException(this, message);
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull LoginException create(Throwable cause) {
 *             return new LoginException(this, cause);
 *         }
 *
 *         &#64;Override
 *         public &#64;Nonnull LoginException create(String message, Throwable cause) {
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
 * <h2>2. 业务异常</h2>
 * 预设常见的业务异常。可继承 {@link BizException} 自定义业务异常。
 *
 * <h2>3. 系统异常</h2>
 * 预设常见的系统异常。可继承 {@link SysException} 自定义系统异常。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
package xyz.zhouxy.plusone.commons.exception;

import xyz.zhouxy.plusone.commons.exception.business.*;
import xyz.zhouxy.plusone.commons.exception.system.*;
