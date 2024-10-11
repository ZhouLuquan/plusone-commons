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

package xyz.zhouxy.plusone.commons.util;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * 断言工具
 * 
 * <p>
 * 不封装过多判断逻辑，鼓励充分使用项目中的工具类进行逻辑判断。
 * 本工具类基本仅对表达式进行判断，并在表达式为 {@code false} 时抛出对应异常。
 */
public class AssertTools {

    public static <T> void checkParameterNotNull(String paramName, T value) {
        checkCondition(value != null,
                () -> new IllegalArgumentException(String.format("The parameter \"%s\" cannot be null.", paramName)));
    }

    public static <T> void checkParameterNotNull(T obj, String errMsg) {
        checkCondition(obj != null, () -> new IllegalArgumentException(errMsg));
    }

    public static <T> void checkParameterNotNull(T obj, String format, Object... args) {
        checkCondition(obj != null, () -> new IllegalArgumentException(String.format(format, args)));
    }

    public static void checkParameter(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalArgumentException(errMsg));
    }

    public static void checkParameter(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalArgumentException(String.format(format, args)));
    }

    public static void checkState(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalStateException(errMsg));
    }

    public static void checkState(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalStateException(String.format(format, args)));
    }

    public static <T extends Exception> void checkCondition(boolean condition, @Nonnull Supplier<T> e)
            throws T {
        if (!condition) {
            throw e.get();
        }
    }

    private AssertTools() {
        throw new IllegalStateException("Utility class");
    }
}
