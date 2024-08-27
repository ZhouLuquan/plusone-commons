/*
 * Copyright 2022-2023 the original author or authors.
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
import java.util.Objects;

/**
 * 带错误码的异常。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public abstract class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6345888403567792664L;

    @Nonnull
    private final String type;

    protected BaseRuntimeException(String type, String msg) {
        super(msg);
        this.type = Objects.requireNonNull(type);
    }

    protected BaseRuntimeException(String type, Throwable cause) {
        super(cause);
        this.type = Objects.requireNonNull(type);
    }

    protected BaseRuntimeException(String type, String msg, Throwable cause) {
        super(msg, cause);
        this.type = Objects.requireNonNull(type);
    }

    @Nonnull
    public final String getType() {
        return this.type;
    }
}
