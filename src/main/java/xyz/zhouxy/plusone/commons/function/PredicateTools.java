/*
 * Copyright 2023-2024 the original author or authors.
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

import java.util.function.Predicate;

/**
 * PredicateTools
 *
 * <p>
 * {@link Predicate} 相关操作。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 * @see Predicate
 */
public class PredicateTools {

    /**
     * 将 lambda 表达式或者方法引用指明为对应类型的 {@link Predicate} 对象。
     * 如将 {@code Objects::nonNull} 明确地指定为 {@code Predicate&lt;String&gt;}，
     * 使之可以链式调用 {@link Predicate#and(Predicate)}、{@link Predicate#or(Predicate)}
     * 等方法，连接其它 {@code Predicate<? super T>} 对象。
     *
     * <pre>
     * Predicate&lt;String&gt; predicate = PredicateTools.&lt;String&gt;from(Objects::nonNull)
     *         .and(StringUtils::isNotEmpty);
     * </pre>
     *
     * @param <T>       目标类型
     * @param predicate Lambda 表达式
     * @return 传入的表达式自动成为 {@link Predicate} 实例
     */
    public static <T> Predicate<T> from(Predicate<T> predicate) {
        return predicate;
    }

    private PredicateTools() {
        throw new IllegalStateException("Utility class");
    }
}
