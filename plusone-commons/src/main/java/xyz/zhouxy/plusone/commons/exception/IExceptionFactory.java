/*
 * Copyright 2025-present ZhouXY
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

/**
 * 异常工厂
 *
 * @param <X> 异常类型
 * @author ZhouXY
 */
public interface IExceptionFactory<X extends Exception> {
    /**
    * 创建异常
    *
    * @return 异常对象
    */
    @Nonnull
    X create();

    /**
     * 使用指定 {@code message} 创建异常
     *
     * @param message 异常信息
     * @return 异常对象
     */
    @Nonnull
    X create(String message);

    /**
     * 使用指定 {@code cause} 创建异常
     *
     * @param cause 包装的异常
     * @return 异常对象
     */
    @Nonnull
    X create(Throwable cause);

    /**
     * 使用指定 {@code message} 和 {@code cause} 创建异常
     *
     * @param message 异常信息
     * @param cause   包装的异常
     * @return 异常对象
     */
    @Nonnull
    X create(String message, Throwable cause);
}
