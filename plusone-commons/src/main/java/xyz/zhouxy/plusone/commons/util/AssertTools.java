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

package xyz.zhouxy.plusone.commons.util;

import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import xyz.zhouxy.plusone.commons.exception.DataNotExistsException;

/**
 * 断言工具
 *
 * <p>
 * 本工具类不封装过多判断逻辑，鼓励充分使用项目中的工具类进行逻辑判断。
 *
 * <pre>
 * checkArgument(StringUtils.hasText(str), "The argument cannot be blank.");
 * checkState(ArrayUtils.isNotEmpty(result), "The result cannot be empty.");
 * checkCondition(!CollectionUtils.isEmpty(roles),
 *     () -> new InvalidInputException("The roles cannot be empty."));
 * checkCondition(RegexTools.matches(email, PatternConsts.EMAIL),
 *     "must be a well-formed email address");
 * </pre>
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 */
public class AssertTools {

    // ================================
    // #region - Argument
    // ================================

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param errorMessage 异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, @Nullable String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param errorMessageSupplier 异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, Supplier<String> errorMessageSupplier) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessageSupplier.get());
        }
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (!condition) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    // ================================
    // #endregion - Argument
    // ================================

    // ================================
    // #region - ArgumentNotNull
    // ================================

    /**
     * 判断入参不为 {@code null}
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @throws IllegalArgumentException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> T checkArgumentNotNull(@Nullable T obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        return obj;
    }

    /**
     * 判断入参不为 {@code null}
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessage 异常信息
     * @throws IllegalArgumentException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> T checkArgumentNotNull(@Nullable T obj, String errorMessage) {
        if (obj == null) {
            throw new IllegalArgumentException(errorMessage);
        }
        return obj;
    }

    /**
     * 判断入参不为 {@code null}
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageSupplier 异常信息
     * @throws IllegalArgumentException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> T checkArgumentNotNull(@Nullable T obj, Supplier<String> errorMessageSupplier) {
        if (obj == null) {
            throw new IllegalArgumentException(errorMessageSupplier.get());
        }
        return obj;
    }

    /**
     * 判断入参不为 {@code null}
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @throws IllegalArgumentException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> T checkArgumentNotNull(@Nullable T obj,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
        return obj;
    }

    // ================================
    // #endregion - ArgumentNotNull
    // ================================

    // ================================
    // #region - State
    // ================================

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition) {
        if (!condition) {
            throw new IllegalStateException();
        }
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param errorMessage 异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, @Nullable String errorMessage) {
        if (!condition) {
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param errorMessageSupplier 异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, Supplier<String> errorMessageSupplier) {
        if (!condition) {
            throw new IllegalStateException(errorMessageSupplier.get());
        }
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (!condition) {
            throw new IllegalStateException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    // ================================
    // #endregion - State
    // ================================

    // ================================
    // #region - NotNull
    // ================================

    /**
     * 判空
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    /**
     * 判空
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessage 异常信息
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj, String errorMessage) {
        if (obj == null) {
            throw new NullPointerException(errorMessage);
        }
    }

    /**
     * 判空
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageSupplier 异常信息
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj, Supplier<String> errorMessageSupplier) {
        if (obj == null) {
            throw new NullPointerException(errorMessageSupplier.get());
        }
    }

    /**
     * 判空
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (obj == null) {
            throw new NullPointerException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    // ================================
    // #endregion - NotNull
    // ================================

    // ================================
    // #region - Exists
    // ================================

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj)
            throws DataNotExistsException {
        if (obj == null) {
            throw new DataNotExistsException();
        }
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessage 异常信息
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj, String errorMessage)
            throws DataNotExistsException {
        if (obj == null) {
            throw new DataNotExistsException(errorMessage);
        }
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageSupplier 异常信息
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj, Supplier<String> errorMessageSupplier)
            throws DataNotExistsException {
        if (obj == null) {
            throw new DataNotExistsException(errorMessageSupplier.get());
        }
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj,
            String errorMessageTemplate, Object... errorMessageArgs)
            throws DataNotExistsException {
        if (obj == null) {
            throw new DataNotExistsException(String.format(errorMessageTemplate, errorMessageArgs));
        }
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param optional 入参
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional)
            throws DataNotExistsException {
        if (!optional.isPresent()) {
            throw new DataNotExistsException();
        }
        return optional.get();
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param optional 入参
     * @param errorMessage 异常信息
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional, String errorMessage)
            throws DataNotExistsException {
        if (!optional.isPresent()) {
            throw new DataNotExistsException(errorMessage);
        }
        return optional.get();
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param optional 入参
     * @param errorMessageSupplier 异常信息
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional, Supplier<String> errorMessageSupplier)
            throws DataNotExistsException {
        if (!optional.isPresent()) {
            throw new DataNotExistsException(errorMessageSupplier.get());
        }
        return optional.get();
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param optional 入参
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional,
            String errorMessageTemplate, Object... errorMessageArgs)
            throws DataNotExistsException {
        if (!optional.isPresent()) {
            throw new DataNotExistsException(String.format(errorMessageTemplate, errorMessageArgs));
        }
        return optional.get();
    }

    // ================================
    // #endregion - Exists
    // ================================

    // ================================
    // #region - Condition
    // ================================

    /**
     * 当条件不满足时抛出异常。
     *
     * @param <T> 异常类型
     * @param condition 条件
     * @param e 异常
     * @throws T 当条件不满足时抛出异常
     */
    public static <T extends Exception> void checkCondition(boolean condition, Supplier<T> e)
            throws T {
        if (!condition) {
            throw e.get();
        }
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - constructor
    // ================================

    private AssertTools() {
        throw new IllegalStateException("Utility class");
    }

    // ================================
    // #endregion
    // ================================
}
