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

package xyz.zhouxy.plusone.commons.util;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

/**
 * 断言工具
 *
 * <p>
 * 本工具类基本仅对表达式进行判断，并在表达式为 {@code false} 时抛出对应异常。
 * 不封装过多判断逻辑，鼓励充分使用项目中的工具类进行逻辑判断。
 * </p>
 *
 * <pre>
 * AssertTools.checkArgument(StringUtils.hasText(str), "The argument cannot be blank.");
 * AssertTools.checkState(ArrayUtils.isNotEmpty(result), "The result cannot be empty.");
 * AssertTools.checkCondition(!CollectionUtils.isEmpty(roles), () -> new InvalidInputException("The roles cannot be empty."));
 * </pre>
 *
 * @author ZhouXY
 */
public class AssertTools {

    // #region - checkArgument

    public static <T> void checkArgumentNotNull(T argument) {
        checkCondition(argument != null, () -> new IllegalArgumentException("The argument cannot be null."));
    }

    public static <T> void checkArgumentNotNull(T argument, String errMsg) {
        checkCondition(argument != null, () -> new IllegalArgumentException(errMsg));
    }

    public static <T> void checkArgumentNotNull(T argument, Supplier<String> messageSupplier) {
        checkCondition(argument != null, () -> new IllegalArgumentException(messageSupplier.get()));
    }

    public static <T> void checkArgumentNotNull(T argument, String format, Object... args) {
        checkCondition(argument != null, () -> new IllegalArgumentException(String.format(format, args)));
    }

    public static void checkArgument(boolean condition) {
        checkCondition(condition, IllegalArgumentException::new);
    }

    public static void checkArgument(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalArgumentException(errMsg));
    }

    public static void checkArgument(boolean condition, Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalArgumentException(messageSupplier.get()));
    }

    public static void checkArgument(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalArgumentException(String.format(format, args)));
    }

    // #endregion

    // #region - checkState

    public static void checkState(boolean condition) {
        checkCondition(condition, IllegalStateException::new);
    }

    public static void checkState(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalStateException(errMsg));
    }

    public static void checkState(boolean condition, Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalStateException(messageSupplier.get()));
    }

    public static void checkState(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalStateException(String.format(format, args)));
    }

    // #endregion

    // #region - checkCondition

    public static <T extends Exception> void checkCondition(boolean condition, @Nonnull Supplier<T> e)
            throws T {
        if (!condition) {
            throw e.get();
        }
    }

    // #endregion

    // #region - private constructor

    private AssertTools() {
        throw new IllegalStateException("Utility class");
    }

    // #endregion
}
