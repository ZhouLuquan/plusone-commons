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
package xyz.zhouxy.plusone.commons.function;

/**
 * ThrowingFunction
 *
 * <p>
 * 接收一个参数，并返回一个结果，可以抛出异常。
 *
 * @param <T> 入参类型
 * @param <R> 返回结果类型
 * @param <E> 异常类型
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0
 * @see java.util.function.Function
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Throwable> {

    /**
     * 接收一个参数，并返回一个结果，可以抛出异常。
     *
     * @param t 入参
     * @return 函数结果
     */
    R apply(T t) throws E;

}
