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

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import javax.annotation.Nonnull;

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

    /** Throw {@link IllegalArgumentException} if the {@code condition} is false. */
    public static void checkArgument(boolean condition) {
        checkCondition(condition, IllegalArgumentException::new);
    }

    /** Throw {@link IllegalArgumentException} if the {@code condition} is false. */
    public static void checkArgument(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalArgumentException(errMsg));
    }

    /** Throw {@link IllegalArgumentException} if the {@code condition} is false. */
    public static void checkArgument(boolean condition, @Nonnull Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalArgumentException(messageSupplier.get()));
    }

    /** Throw {@link IllegalArgumentException} if the {@code condition} is false. */
    public static void checkArgument(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalArgumentException(String.format(format, args)));
    }

    // ================================
    // #endregion - Argument
    // ================================

    // ================================
    // #region - State
    // ================================

    /** Throw {@link IllegalStateException} if the {@code condition} is false. */
    public static void checkState(boolean condition) {
        checkCondition(condition, IllegalStateException::new);
    }

    /** Throw {@link IllegalStateException} if the {@code condition} is false. */
    public static void checkState(boolean condition, String errMsg) {
        checkCondition(condition, () -> new IllegalStateException(errMsg));
    }

    /** Throw {@link IllegalStateException} if the {@code condition} is false. */
    public static void checkState(boolean condition, @Nonnull Supplier<String> messageSupplier) {
        checkCondition(condition, () -> new IllegalStateException(messageSupplier.get()));
    }

    /** Throw {@link IllegalStateException} if the {@code condition} is false. */
    public static void checkState(boolean condition, String format, Object... args) {
        checkCondition(condition, () -> new IllegalStateException(String.format(format, args)));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - NotNull
    // ================================

    /** Throw {@link NullPointerException} if the {@code obj} is null. */
    public static <T> void checkNotNull(T obj) {
        checkCondition(obj != null, NullPointerException::new);
    }

    /** Throw {@link NullPointerException} if the {@code obj} is null. */
    public static <T> void checkNotNull(T obj, String errMsg) {
        checkCondition(obj != null, () -> new NullPointerException(errMsg));
    }

    /** Throw {@link NullPointerException} if the {@code obj} is null. */
    public static <T> void checkNotNull(T obj, @Nonnull Supplier<String> messageSupplier) {
        checkCondition(obj != null, () -> new NullPointerException(messageSupplier.get()));
    }

    /** Throw {@link NullPointerException} if the {@code obj} is null. */
    public static <T> void checkNotNull(T obj, String format, Object... args) {
        checkCondition(obj != null, () -> new NullPointerException(String.format(format, args)));
    }

    // ================================
    // #endregion
    // ================================

    // ================================
    // #region - Exists
    // ================================

    /** Throw {@link DataNotExistsException} if the {@code obj} is null. */
    public static <T> T checkExists(T obj)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), DataNotExistsException::new);
        return obj;
    }

    /** Throw {@link DataNotExistsException} if the {@code obj} is null. */
    public static <T> T checkExists(T obj, String message)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(message));
        return obj;
    }

    /** Throw {@link DataNotExistsException} if the {@code obj} is null. */
    public static <T> T checkExists(T obj, @Nonnull Supplier<String> messageSupplier)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(messageSupplier.get()));
        return obj;
    }

    /** Throw {@link DataNotExistsException} if the {@code obj} is null. */
    public static <T> T checkExists(T obj, String format, Object... args)
            throws DataNotExistsException {
        checkCondition(Objects.nonNull(obj), () -> new DataNotExistsException(String.format(format, args)));
        return obj;
    }

    /** Throw {@link DataNotExistsException} if the {@code optional} is present. */
    public static <T> T checkExists(@Nonnull Optional<T> optional)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), DataNotExistsException::new);
        return optional.get();
    }

    /** Throw {@link DataNotExistsException} if the {@code optional} is present. */
    public static <T> T checkExists(@Nonnull Optional<T> optional, String message)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(message));
        return optional.get();
    }

    /** Throw {@link DataNotExistsException} if the {@code optional} is present. */
    public static <T> T checkExists(@Nonnull Optional<T> optional, @Nonnull Supplier<String> messageSupplier)
            throws DataNotExistsException {
        checkCondition(optional.isPresent(), () -> new DataNotExistsException(messageSupplier.get()));
        return optional.get();
    }

    /** Throw {@link DataNotExistsException} if the {@code optional} is present. */
    public static <T> T checkExists(@Nonnull Optional<T> optional, String format, Object... args)
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

    public static void checkAffectedRows(int expectedValue, int result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    public static void checkAffectedRows(int expectedValue, int result, String message) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(message));
    }

    public static void checkAffectedRows(int expectedValue, int result,
            @Nonnull Supplier<String> messageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(messageSupplier.get()));
    }

    public static void checkAffectedRows(int expectedValue, int result, String format, Object... args) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(format, args)));
    }

    public static void checkAffectedRows(long expectedValue, long result) {
        checkAffectedRows(expectedValue, result,
                "The number of rows affected is expected to be %d, but is: %d", expectedValue, result);
    }

    public static void checkAffectedRows(long expectedValue, long result, String message) {
        checkCondition(expectedValue == result, () -> new DataOperationResultException(message));
    }

    public static void checkAffectedRows(long expectedValue, long result,
            @Nonnull Supplier<String> messageSupplier) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(messageSupplier.get()));
    }

    public static void checkAffectedRows(long expectedValue, long result, String format, Object... args) {
        checkCondition(expectedValue == result,
                () -> new DataOperationResultException(String.format(format, args)));
    }

    public static void checkAffectedOneRow(int result) {
        checkAffectedRows(1, result,
                () -> "The number of rows affected is expected to be 1, but is: " + result);
    }

    public static void checkAffectedOneRow(int result, String message) {
        checkAffectedRows(1, result, message);
    }

    public static void checkAffectedOneRow(int result, @Nonnull Supplier<String> messageSupplier) {
        checkAffectedRows(1, result, messageSupplier);
    }

    public static void checkAffectedOneRow(int result, String format, Object... args) {
        checkAffectedRows(1, result, format, args);
    }

    public static void checkAffectedOneRow(long result) {
        checkAffectedRows(1L, result,
                () -> "The number of rows affected is expected to be 1, but is: " + result);
    }

    public static void checkAffectedOneRow(long result, String message) {
        checkAffectedRows(1L, result, message);
    }

    public static void checkAffectedOneRow(long result, @Nonnull Supplier<String> messageSupplier) {
        checkAffectedRows(1L, result, messageSupplier);
    }

    public static void checkAffectedOneRow(long result, String format, Object... args) {
        checkAffectedRows(1L, result, format, args);
    }

    // ================================
    // #endregion - AffectedRows
    // ================================

    // ================================
    // #region - Condition
    // ================================

    public static <T extends Exception> void checkCondition(boolean condition, @Nonnull Supplier<T> e)
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
