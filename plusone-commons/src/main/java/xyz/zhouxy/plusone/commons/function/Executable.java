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
 * Executable
 *
 * <p>
 * 表示一个无入参无返回值的操作，可抛出异常。
 *
 * @param <E> 可抛出的异常类型
 *
 * @author ZhouXY
 * @since 1.0.0
 */
@FunctionalInterface
public interface Executable<E extends Throwable> {

    /**
     * 执行
     *
     * @throws E 可抛出的异常
     */
    void execute() throws E;

}
