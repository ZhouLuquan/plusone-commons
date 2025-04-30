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
import xyz.zhouxy.plusone.commons.exception.system.DataOperationResultException;

/**
 * 断言工具
 *
 * <p>
 * 本工具类不封装过多判断逻辑，鼓励充分使用项目中的工具类进行逻辑判断。
 * </p>
 *
 * <pre>
 * AssertTools.checkArgument(StringUtils.hasText(str), "The argument cannot be blank.");
 * AssertTools.checkState(ArrayUtils.isNotEmpty(result), "The result cannot be empty.");
 * AssertTools.checkCondition(!CollectionUtils.isEmpty(roles),
 *     () -> new InvalidInputException("The roles cannot be empty."));
 * AssertTools.checkCondition(RegexTools.matches(email, PatternConsts.EMAIL),
 *     "must be a well-formed email address");
 * </pre>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
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
        checkCondition(condition, IllegalArgumentException::new);
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param errorMessage 异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, @Nullable String errorMessage) {
        checkCondition(condition, () -> new IllegalArgumentException(errorMessage));
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param errorMessageSupplier 异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, Supplier<String> errorMessageSupplier) {
        checkCondition(condition, () -> new IllegalArgumentException(errorMessageSupplier.get()));
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
        checkCondition(condition,
                () -> new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs)));
    }

    // ================================
    // #endregion - Argument
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
        checkCondition(condition, IllegalStateException::new);
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param errorMessage 异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, @Nullable String errorMessage) {
        checkCondition(condition, () -> new IllegalStateException(errorMessage));
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param errorMessageSupplier 异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, Supplier<String> errorMessageSupplier) {
        checkCondition(condition, () -> new IllegalStateException(errorMessageSupplier.get()));
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
        checkCondition(condition,
                () -> new IllegalStateException(String.format(errorMessageTemplate, errorMessageArgs)));
    }

    // ================================
    // #endregion
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
        checkCondition(obj != null, NullPointerException::new);
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
        checkCondition(obj != null, () -> new NullPointerException(errorMessage));
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
        checkCondition(obj != null, () -> new NullPointerException(errorMessageSupplier.get()));
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
        checkCondition(obj != null,
                () -> new NullPointerException(String.format(errorMessageTemplate, errorMessageArgs)));
    }

    // ================================
    // #endregion
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
        checkCondition(obj != null, DataNotExistsException::new);
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
        checkCondition(obj != null, () -> new DataNotExistsException(errorMessage));
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
        checkCondition(obj != null, () -> new DataNotExistsException(errorMessageSupplier.get()));
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
        checkCondition(obj != null,
        () -> new DataNotExistsException(String.format(errorMessageTemplate, errorMessageArgs)));
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
        checkCondition(optional.isPresent(), DataNotExistsException::new);
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
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(errorMessage));
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
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(errorMessageSupplier.get()));
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
        checkCondition(optional.isPresent(),
                () -> new DataNotExistsException(String.format(errorMessageTemplate, errorMessageArgs)));
        return optional.get();
    }

    // ================================
    // #endregion - Exists
    // ================================

    // ================================
    // #region - AffectedRows
    // ================================

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     */
    public static void checkAffectedRows(int expectedValue, int result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessage 异常信息
     */
    public static void checkAffectedRows(int expectedValue, int result, @Nullable String errorMessage) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(errorMessage));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedRows(int expectedValue, int result,
            Supplier<String> errorMessageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(errorMessageSupplier.get()));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedRows(int expectedValue, int result,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(errorMessageTemplate, errorMessageArgs)));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     */
    public static void checkAffectedRows(long expectedValue, long result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessage 异常信息
     */
    public static void checkAffectedRows(long expectedValue, long result, @Nullable String errorMessage) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(errorMessage));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedRows(long expectedValue, long result,
            Supplier<String> errorMessageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(errorMessageSupplier.get()));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result 实际影响的数据量
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedRows(long expectedValue, long result,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(errorMessageTemplate, errorMessageArgs)));
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     */
    public static void checkAffectedOneRow(int result) {
        checkAffectedRows(1, result,
                () -> "The number of rows affected is expected to be 1, but is: " + result);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessage 异常信息
     */
    public static void checkAffectedOneRow(int result, String errorMessage) {
        checkAffectedRows(1, result, errorMessage);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedOneRow(int result, Supplier<String> errorMessageSupplier) {
        checkAffectedRows(1, result, errorMessageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedOneRow(int result,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkAffectedRows(1, result, errorMessageTemplate, errorMessageArgs);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     */
    public static void checkAffectedOneRow(long result) {
        checkAffectedRows(1L, result,
                () -> "The number of rows affected is expected to be 1, but is: " + result);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessage 异常信息
     */
    public static void checkAffectedOneRow(long result, String errorMessage) {
        checkAffectedRows(1L, result, errorMessage);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedOneRow(long result, Supplier<String> errorMessageSupplier) {
        checkAffectedRows(1L, result, errorMessageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedOneRow(long result,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkAffectedRows(1L, result, errorMessageTemplate, errorMessageArgs);
    }

    // ================================
    // #endregion - AffectedRows
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
