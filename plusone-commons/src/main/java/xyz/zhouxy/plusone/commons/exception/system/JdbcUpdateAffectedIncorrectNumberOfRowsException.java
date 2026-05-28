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

package xyz.zhouxy.plusone.commons.exception.system;

import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * JdbcUpdateAffectedIncorrectNumberOfRowsException
 *
 * <p>
 * 当数据操作的结果不符合预期时抛出。
 *
 * <p>
 * 比如当一个 insert 或 update 操作时，预计影响数据库中的一行数据，但结果却影响了零条数据或多条数据，
 * 当出现这种始料未及的诡异情况时，抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException} 并回滚事务。
 * 后续需要排查原因。
 *
 * @author ZhouXY
 * @since 1.0.0
 */
public final class JdbcUpdateAffectedIncorrectNumberOfRowsException extends SysException {
    private static final long serialVersionUID = 992754090625352516L;

    private final long expected;
    private final long actual;

    /**
     * 创建一个 {@code JdbcUpdateAffectedIncorrectNumberOfRowsException} 对象
     *
     * @param expected 预期影响的行数
     * @param actual 实际影响的行数
     */
    public JdbcUpdateAffectedIncorrectNumberOfRowsException(long expected, long actual) {
        super(String.format("The number of rows affected is expected to be %d, but is: %d", expected, actual));
        this.expected = expected;
        this.actual = actual;
    }

    /**
     * 创建一个 {@code JdbcUpdateAffectedIncorrectNumberOfRowsException} 对象
     *
     * @param expected 预期影响的行数
     * @param actual 实际影响的行数
     * @param message 错误信息
     */
    public JdbcUpdateAffectedIncorrectNumberOfRowsException(long expected, long actual, String message) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }

    /**
     * 预期影响的行数
     *
     * @return the expected
     */
    public long getExpected() {
        return expected;
    }

    /**
     * 实际影响的行数
     *
     * @return the actual
     */
    public long getActual() {
        return actual;
    }

    // ================================
    // #region - AffectedRows
    // ================================

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     */
    public static void checkAffectedRows(int expected, int actualRowCount) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount);
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessage 异常信息
     */
    public static void checkAffectedRows(int expected, int actualRowCount,
            @Nullable String errorMessage) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount, errorMessage);
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedRows(int expected, int actualRowCount,
            Supplier<String> errorMessageSupplier) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount, errorMessageSupplier.get());
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedRows(int expected, int actualRowCount,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount,
                    String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     */
    public static void checkAffectedRows(long expected, long actualRowCount) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount);
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessage 异常信息
     */
    public static void checkAffectedRows(long expected, long actualRowCount,
            @Nullable String errorMessage) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount, errorMessage);
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedRows(long expected, long actualRowCount,
            Supplier<String> errorMessageSupplier) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount, errorMessageSupplier.get());
        }
    }

    /**
     * 当影响的数据量与预计不同时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param expected 预期影响的行数
     * @param actualRowCount 实际影响的行数
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedRows(long expected, long actualRowCount,
            String errorMessageTemplate, Object... errorMessageArgs) {
        if (expected != actualRowCount) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(expected, actualRowCount,
                    String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     */
    public static void checkAffectedOneRow(int actualRowCount) {
        checkAffectedRows(1, actualRowCount);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessage 异常信息
     */
    public static void checkAffectedOneRow(int actualRowCount, String errorMessage) {
        checkAffectedRows(1, actualRowCount, errorMessage);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedOneRow(int actualRowCount, Supplier<String> errorMessageSupplier) {
        checkAffectedRows(1, actualRowCount, errorMessageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedOneRow(int actualRowCount,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkAffectedRows(1, actualRowCount, errorMessageTemplate, errorMessageArgs);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param result 实际影响的数据量
     */
    public static void checkAffectedOneRow(long result) {
        checkAffectedRows(1L, result);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessage 异常信息
     */
    public static void checkAffectedOneRow(long actualRowCount, String errorMessage) {
        checkAffectedRows(1L, actualRowCount, errorMessage);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessageSupplier 异常信息
     */
    public static void checkAffectedOneRow(long actualRowCount, Supplier<String> errorMessageSupplier) {
        checkAffectedRows(1L, actualRowCount, errorMessageSupplier);
    }

    /**
     * 当影响的数据量不为 1 时抛出 {@link JdbcUpdateAffectedIncorrectNumberOfRowsException}。
     *
     * @param actualRowCount 实际影响的行数
     * @param errorMessageTemplate 异常信息模板
     * @param errorMessageArgs 异常信息参数
     */
    public static void checkAffectedOneRow(long actualRowCount,
            String errorMessageTemplate, Object... errorMessageArgs) {
        checkAffectedRows(1L, actualRowCount, errorMessageTemplate, errorMessageArgs);
    }

    // ================================
    // #endregion - AffectedRows
    // ================================

}
