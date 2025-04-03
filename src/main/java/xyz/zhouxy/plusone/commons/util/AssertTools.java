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

import java.util.Objects;
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
     * @param errMsg    异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, @Nullable String errMsg) {
        checkCondition(condition, () -> new IllegalArgumentException(errMsg));
    }

    /**
     * 检查实参
     *
     * @param condition       判断参数是否符合条件的结果
     * @param messageSupplier 异常信息
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalArgumentException(messageSupplier.get()));
    }

    /**
     * 检查实参
     *
     * @param condition 判断参数是否符合条件的结果
     * @param format    异常信息模板
     * @param args      异常信息参数
     * @throws IllegalArgumentException 当条件不满足时抛出
     */
    public static void checkArgument(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalArgumentException(String.format(format, args)));
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
     * @param errMsg    异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, @Nullable String errMsg) {
        checkCondition(condition, () -> new IllegalStateException(errMsg));
    }

    /**
     * 检查状态
     *
     * @param condition       判断状态是否符合条件的结果
     * @param messageSupplier 异常信息
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalStateException(messageSupplier.get()));
    }

    /**
     * 检查状态
     *
     * @param condition 判断状态是否符合条件的结果
     * @param format    异常信息模板
     * @param args      异常信息参数
     * @throws IllegalStateException 当条件不满足时抛出
     */
    public static void checkState(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalStateException(String.format(format, args)));
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
     * @param obj 入参     *
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj) {
        checkCondition(obj != null, NullPointerException::new);
    }

    /**
     * 判空
     *
     * @param <T>    入参类型
     * @param obj    入参
     * @param errMsg 异常信息     *
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj, String errMsg) {
        checkCondition(obj != null, () -> new NullPointerException(errMsg));
    }

    /**
     * 判空
     *
     * @param <T>             入参类型
     * @param obj             入参
     * @param messageSupplier 异常信息     *
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj, Supplier<String> messageSupplier) {
        checkCondition(obj != null, () -> new NullPointerException(messageSupplier.get()));
    }

    /**
     * 判空
     *
     * @param <T>    入参类型
     * @param obj    入参
     * @param format 异常信息模板
     * @param args   异常信息参数
     * @throws NullPointerException 当 {@code obj} 为 {@code null} 时抛出
     */
    public static <T> void checkNotNull(@Nullable T obj, String format, Object... args) {
        checkCondition(obj != null, () -> new NullPointerException(String.format(format, args)));
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
        checkCondition(Objects.nonNull(obj), DataNotExistsException::new);
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T> 入参类型
     * @param obj 入参
     * @param message 异常信息
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj, String message)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(message));
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T>             入参类型
     * @param obj             入参
     * @param messageSupplier 异常信息
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj, Supplier<String> messageSupplier)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(messageSupplier.get()));
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T>    入参类型
     * @param obj    入参
     * @param format 异常信息模板
     * @param args   异常信息参数
     * @return 如果 {@code obj} 存在，返回 {@code obj} 本身
     * @throws DataNotExistsException 当 {@code obj} 不存在时抛出
     */
    public static <T> T checkExists(@Nullable T obj, String format, Object... args)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(String.format(format, args)));
        return obj;
    }

    /**
     * 检查数据是否存在
     *
     * @param <T>      入参类型
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
     * @param <T>      入参类型
     * @param optional 入参
     * @param message  异常信息
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional, String message)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(message));
        return optional.get();
    }

    /**
     * 检查数据是否存在
     *
     * @param <T>             入参类型
     * @param optional        入参
     * @param messageSupplier 异常信息
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional, Supplier<String> messageSupplier)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(messageSupplier.get()));
        return optional.get();
    }

    /**
     * 检查数据是否存在
     *
     * @param <T>      入参类型
     * @param optional 入参
     * @param format   异常信息模板
     * @param args     异常信息参数
     * @return 如果 {@code optional} 存在，返回 {@code optional} 包含的值
     * @throws DataNotExistsException 当 {@code optional} 的值不存在时抛出
     */
    public static <T> T checkExists(Optional<T> optional, String format, Object... args)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(String.format(format, args)));
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
     * @param result        实际影响的数据量
     */
    public static void checkAffectedRows(int expectedValue, int result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result        实际影响的数据量
     * @param message       异常信息
     */
    public static void checkAffectedRows(int expectedValue, int result, @Nullable String message) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(message));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue   预计的数量
     * @param result          实际影响的数据量
     * @param messageSupplier 异常信息
     */
    public static void checkAffectedRows(int expectedValue, int result,
            Supplier<String> messageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(messageSupplier.get()));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result        实际影响的数据量
     * @param format        异常信息模板
     * @param args          异常信息参数
     */
    public static void checkAffectedRows(int expectedValue, int result,
            String format, Object... args) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(format, args)));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result        实际影响的数据量
     */
    public static void checkAffectedRows(long expectedValue, long result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result        实际影响的数据量
     * @param message       异常信息
     */
    public static void checkAffectedRows(long expectedValue, long result, @Nullable String message) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(message));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue   预计的数量
     * @param result          实际影响的数据量
     * @param messageSupplier 异常信息
     */
    public static void checkAffectedRows(long expectedValue, long result,
            Supplier<String> messageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(messageSupplier.get()));
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link DataOperationResultException}。
     *
     * @param expectedValue 预计的数量
     * @param result        实际影响的数据量
     * @param format        异常信息模板
     * @param args          异常信息参数
     */
    public static void checkAffectedRows(long expectedValue, long result,
            String format, Object... args) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(format, args)));
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
     * @param result  实际影响的数据量
     * @param message 异常信息
     */
    public static void checkAffectedOneRow(int result, String message) {
        checkAffectedRows(1, result, message);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result          实际影响的数据量
     * @param messageSupplier 异常信息
     */
    public static void checkAffectedOneRow(int result, Supplier<String> messageSupplier) {
        checkAffectedRows(1, result, messageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param format 异常信息模板
     * @param args   异常信息参数
     */
    public static void checkAffectedOneRow(int result, String format, Object... args) {
        checkAffectedRows(1, result, format, args);
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
     * @param result  实际影响的数据量
     * @param message 异常信息
     */
    public static void checkAffectedOneRow(long result, String message) {
        checkAffectedRows(1L, result, message);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result          实际影响的数据量
     * @param messageSupplier 异常信息
     */
    public static void checkAffectedOneRow(long result, Supplier<String> messageSupplier) {
        checkAffectedRows(1L, result, messageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link DataOperationResultException}。
     *
     * @param result 实际影响的数据量
     * @param format 异常信息模板
     * @param args   异常信息参数
     */
    public static void checkAffectedOneRow(long result,
            String format, Object... args) {
        checkAffectedRows(1L, result, format, args);
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
     * @param <T>       异常类型
     * @param condition 条件
     * @param e         异常
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
