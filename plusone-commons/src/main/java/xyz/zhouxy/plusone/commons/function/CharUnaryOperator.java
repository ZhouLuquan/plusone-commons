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

import com.google.common.annotations.Beta;

/**
 * CharUnaryOperator
 *
 * <p>
 * 一个特殊的 {@link java.util.function.UnaryOperator}。
 * 表示对 {@code char} 的一元操作。
 *
 * @author ZhouXY
 * @since 1.0.0
 * @see java.util.function.UnaryOperator
 */
@Beta
@FunctionalInterface
public interface CharUnaryOperator {

    /**
     * 将此函数应用于给定的 {@code char} 参数，返回一个 {@code char} 结果。
     *
     * @param operand 操作数
     * @return 结果
     */
    char applyAsChar(char operand);
}
