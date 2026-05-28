/*
 * Copyright 2024-present ZhouXY
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
 * ThrowingPredicate
 *
 * <p>
 * 接收一个参数，返回一个布尔值，可抛出异常。
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 * @see java.util.function.Predicate
 */
@FunctionalInterface
public interface ThrowingPredicate<T, E extends Throwable> {

    /**
     * 对给定的参数进行评估
     *
     * @param t 入参
     * @return 入参符合条件时返回 {@code true}，否则返回 {@code false}
     */
    boolean test(T t) throws E;
}
