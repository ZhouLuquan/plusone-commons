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

package xyz.zhouxy.plusone.commons.function;

/**
 * ThrowingSupplier
 *
 * <p>
 * 允许抛出异常的 Supplier 接口。
 *
 * @param <T> 结果类型
 * @param <E> 异常类型
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 * @see java.util.function.Supplier
 */
@FunctionalInterface
public interface ThrowingSupplier<T, E extends Throwable> {

    /**
     * 获取一个结果，允许抛出异常。
     *
     * @return 结果
     */
    T get() throws E;

}
