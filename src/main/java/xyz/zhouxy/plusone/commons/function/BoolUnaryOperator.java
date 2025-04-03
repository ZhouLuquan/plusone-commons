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

package xyz.zhouxy.plusone.commons.function;

import com.google.common.annotations.Beta;

/**
 * BoolUnaryOperator
 *
 * <p>
 * 一个特殊的 {@link java.util.function.UnaryOperator}。
 * 表示对 {@code boolean} 值的一元操作。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0.0
 * @see java.util.function.UnaryOperator
 */
@Beta
@FunctionalInterface
public interface BoolUnaryOperator {

    /**
     * 将此函数应用于给定的 {@code boolean} 参数，返回一个 {@code boolean} 结果。
     *
     * @param operand 操作数
     * @return 结果
     */
    boolean applyAsBool(boolean operand);

    /**
     * 返回一个 {@code BoolUnaryOperator}，该操作符将给定的操作数取反。
     *
     * @return {@code BoolUnaryOperator}
     */
    static BoolUnaryOperator not() {
        return b -> !b;
    }
}
