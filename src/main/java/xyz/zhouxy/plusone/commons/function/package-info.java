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
 * 函数式编程
 *
 * <h2>PredicateTools</h2>
 * <p>
 * {@link PredicateTools} 用于 {@link java.util.function.Predicate} 的相关操作。
 * </p>
 *
 * <h2>Functional interfaces</h2>
 * <p>
 * 补充一些 JDK 没有，而项目中可能用得上的函数式接口：
 * <pre>
 * | Group         | FunctionalInterface  | method                           |
 * | ------------- | -------------------- | -------------------------------- |
 * | UnaryOperator | BoolUnaryOperator    | boolean applyAsBool (boolean)    |
 * | UnaryOperator | CharUnaryOperator    | char applyAsChar(char)           |
 * | Throwing      | Executable           | void execute() throws E          |
 * | Throwing      | ThrowingConsumer     | void accept(T) throws E          |
 * | Throwing      | ThrowingFunction     | R apply(T) throws E              |
 * | Throwing      | ThrowingPredicate    | boolean test(T) throws E         |
 * | Throwing      | ThrowingSupplier     | T get() throws E                 |
 * | Optional      | OptionalSupplier     | Optional&lt;T&gt; get() throws E       |
 * | Optional      | ToOptionalBiFunction | Optional&lt;R&gt; apply(T,U)           |
 * | Optional      | ToOptionalFunction   | Optional&lt;R&gt; apply(T)             |
 * </pre>
 * </p>
 */
package xyz.zhouxy.plusone.commons.function;
