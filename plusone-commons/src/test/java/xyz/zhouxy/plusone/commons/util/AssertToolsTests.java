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

import static org.junit.jupiter.api.Assertions.*;
import static xyz.zhouxy.plusone.commons.util.AssertTools.*;
import static xyz.zhouxy.plusone.commons.exception.system.JdbcUpdateAffectedIncorrectNumberOfRowsException.*;

import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import xyz.zhouxy.plusone.commons.exception.DataNotExistsException;
import xyz.zhouxy.plusone.commons.exception.system.JdbcUpdateAffectedIncorrectNumberOfRowsException;

@SuppressWarnings("null")
public class AssertToolsTests {

    // #region - Argument

    @Test
    void testCheckArgument_true() {
        checkArgument(true);
    }

    @Test
    void testCheckArgument_true_withMessage() {
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        checkArgument(true, IGNORE_ME);
    }

    @Test
    void testCheckArgument_true_withNullMessage() {
        final String IGNORE_ME = null; // NOSONAR
        checkArgument(true, IGNORE_ME);
    }

    @Test
    void testCheckArgument_true_withMessageSupplier() {
        checkArgument(true, () -> "Error message: " + LocalDate.now());
    }

    @Test
    void testCheckArgument_true_withNullMessageSupplier() {
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        checkArgument(true, IGNORE_ME);
    }

    @Test
    void testCheckArgument_true_withMessageFormat() {
        LocalDate today = LocalDate.now();
        checkArgument(true, "String format: %s", today);
    }

    @Test
    void testCheckArgument_true_withNullMessageFormat() {
        checkArgument(true, null, LocalDate.now());
    }

    @Test
    void testCheckArgument_false() {
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgument(false));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgument_false_withMessage() {
        final String message = "testCheckArgument_false_withMessage";

        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgument(false, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgument_false_withNullMessage() {
        final String message = null;

        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgument(false, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgument_false_withMessageSupplier() {
        final LocalDate today = LocalDate.now();
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgument(false, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgument_false_withNullMessageSupplier() {
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkArgument(false, messageSupplier));
    }

    @Test
    void testCheckArgument_false_withMessageFormat() {
        LocalDate today = LocalDate.now();
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgument(false, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckArgument_false_withNullMessageFormat() {
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkArgument(false, null, today));
    }

    // #endregion - Argument

    // #region - ArgumentNotNull

    @Test
    void testCheckArgumentNotNull_notNull() {
        final Object object = new Object();
        assertEquals(object, checkArgumentNotNull(object));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withMessage() {
        final Object object = new Object();
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        assertEquals(object, checkArgumentNotNull(object, IGNORE_ME));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withNullMessage() {
        final Object object = new Object();
        final String IGNORE_ME = null; // NOSONAR
        assertEquals(object, checkArgumentNotNull(object, IGNORE_ME));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withMessageSupplier() {
        final Object object = new Object();
        assertEquals(object, checkArgumentNotNull(object, () -> "Error message: " + LocalDate.now()));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withNullMessageSupplier() {
        final Object object = new Object();
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        assertEquals(object, checkArgumentNotNull(object, IGNORE_ME));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withMessageFormat() {
        final Object object = new Object();
        LocalDate today = LocalDate.now();
        assertEquals(object, checkArgumentNotNull(object, "String format: %s", today));
    }

    @Test
    void testCheckArgumentNotNull_notNull_withNullMessageFormat() {
        final Object object = new Object();
        assertEquals(object, checkArgumentNotNull(object, null, LocalDate.now()));
    }

    @Test
    void testCheckArgumentNotNull_null() {
        final Object object = null;
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgumentNotNull(object));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgumentNotNull_null_withMessage() {
        final Object object = null;
        final String message = "testCheckArgumentNotNull_null_withMessage";

        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgumentNotNull(object, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgumentNotNull_null_withNullMessage() {
        final Object object = null;
        final String message = null;

        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgumentNotNull(object, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgumentNotNull_null_withMessageSupplier() {
        final Object object = null;
        final LocalDate today = LocalDate.now();
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgumentNotNull(object, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckArgumentNotNull_null_withNullMessageSupplier() {
        final Object object = null;
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkArgumentNotNull(object, messageSupplier));
    }

    @Test
    void testCheckArgumentNotNull_null_withMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        final IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> checkArgumentNotNull(object, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckArgumentNotNull_null_withNullMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkArgumentNotNull(object, null, today));
    }

    // #endregion - ArgumentNotNull

    // #region - State

    @Test
    void testCheckState_true() {
        checkState(true);
    }

    @Test
    void testCheckState_true_withMessage() {
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        checkState(true, IGNORE_ME);
    }

    @Test
    void testCheckState_true_withNullMessage() {
        final String IGNORE_ME = null; // NOSONAR
        checkState(true, IGNORE_ME);
    }

    @Test
    void testCheckState_true_withMessageSupplier() {
        checkState(true, () -> "Error message: " + LocalDate.now());
    }

    @Test
    void testCheckState_true_withNullMessageSupplier() {
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        checkState(true, IGNORE_ME);
    }

    @Test
    void testCheckState_true_withMessageFormat() {
        LocalDate today = LocalDate.now();
        checkState(true, "String format: %s", today);
    }

    @Test
    void testCheckState_true_withNullMessageFormat() {
        checkState(true, null, LocalDate.now());
    }

    @Test
    void testCheckState_false() {
        final IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> checkState(false));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckState_false_withMessage() {
        final String message = "testCheckState_false_withMessage";

        final IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> checkState(false, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckState_false_withNullMessage() {
        final String message = null;

        final IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> checkState(false, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckState_false_withMessageSupplier() {
        final LocalDate today = LocalDate.now();
        final IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> checkState(false, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckState_false_withNullMessageSupplier() {
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkState(false, messageSupplier));
    }

    @Test
    void testCheckState_false_withMessageFormat() {
        LocalDate today = LocalDate.now();
        final IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> checkState(false, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckState_false_withNullMessageFormat() {
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkState(false, null, today));
    }

    // #endregion - State

    // #region - NotNull

    @Test
    void testCheckNotNull_notNull() {
        final Object object = new Object();
        checkNotNull(object);
    }

    @Test
    void testCheckNotNull_notNull_withMessage() {
        final Object object = new Object();
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        checkNotNull(object, IGNORE_ME);
    }

    @Test
    void testCheckNotNull_notNull_withNullMessage() {
        final Object object = new Object();
        final String IGNORE_ME = null; // NOSONAR
        checkNotNull(object, IGNORE_ME);
    }

    @Test
    void testCheckNotNull_notNull_withMessageSupplier() {
        final Object object = new Object();
        checkNotNull(object, () -> "Error message: " + LocalDate.now());
    }

    @Test
    void testCheckNotNull_notNull_withNullMessageSupplier() {
        final Object object = new Object();
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        checkNotNull(object, IGNORE_ME);
    }

    @Test
    void testCheckNotNull_notNull_withMessageFormat() {
        final Object object = new Object();
        LocalDate today = LocalDate.now();
        checkNotNull(object, "String format: %s", today);
    }

    @Test
    void testCheckNotNull_notNull_withNullMessageFormat() {
        final Object object = new Object();
        checkNotNull(object, null, LocalDate.now());
    }

    @Test
    void testCheckNotNull_null() {
        final Object object = null;
        final NullPointerException e = assertThrows(NullPointerException.class,
                () -> checkNotNull(object));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckNotNull_null_withMessage() {
        final Object object = null;
        final String message = "testCheckNotNull_null_withMessage";

        final NullPointerException e = assertThrows(NullPointerException.class,
                () -> checkNotNull(object, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckNotNull_null_withNullMessage() {
        final Object object = null;
        final String message = null;

        final NullPointerException e = assertThrows(NullPointerException.class,
                () -> checkNotNull(object, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckNotNull_null_withMessageSupplier() {
        final Object object = null;
        final LocalDate today = LocalDate.now();
        final NullPointerException e = assertThrows(NullPointerException.class,
                () -> checkNotNull(object, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckNotNull_null_withNullMessageSupplier() {
        final Object object = null;
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkNotNull(object, messageSupplier));
    }

    @Test
    void testCheckNotNull_null_withMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        final NullPointerException e = assertThrows(NullPointerException.class,
                () -> checkNotNull(object, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckNotNull_null_withNullMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkNotNull(object, null, today));
    }

    // #endregion - NotNull

    // #region - Exists

    @Test
    void testCheckExists_notNull() throws DataNotExistsException {
        final Object object = new Object();
        checkExists(object);
    }

    @Test
    void testCheckExists_notNull_withMessage() throws DataNotExistsException {
        final Object object = new Object();
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_notNull_withNullMessage() throws DataNotExistsException {
        final Object object = new Object();
        final String IGNORE_ME = null; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_notNull_withMessageSupplier() throws DataNotExistsException {
        final Object object = new Object();
        checkExists(object, () -> "Error message: " + LocalDate.now());
    }

    @Test
    void testCheckExists_notNull_withNullMessageSupplier() throws DataNotExistsException {
        final Object object = new Object();
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_notNull_withMessageFormat() throws DataNotExistsException {
        final Object object = new Object();
        LocalDate today = LocalDate.now();
        checkExists(object, "String format: %s", today);
    }

    @Test
    void testCheckExists_notNull_withNullMessageFormat() throws DataNotExistsException {
        final Object object = new Object();
        checkExists(object, null, LocalDate.now());
    }

    @Test
    void testCheckExists_null() {
        final Object object = null;
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_null_withMessage() {
        final Object object = null;
        final String message = "testCheckExists_null_withMessage";

        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_null_withNullMessage() {
        final Object object = null;
        final String message = null;

        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_null_withMessageSupplier() {
        final Object object = null;
        final LocalDate today = LocalDate.now();
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_null_withNullMessageSupplier() {
        final Object object = null;
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkExists(object, messageSupplier));
    }

    @Test
    void testCheckExists_null_withMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckExists_null_withNullMessageFormat() {
        final Object object = null;
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkExists(object, null, today));
    }

    @Test
    void testCheckExists_optional() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        checkExists(object);
    }

    @Test
    void testCheckExists_optional_withMessage() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        final String IGNORE_ME = "IGNORE_ME"; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_optional_withNullMessage() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        final String IGNORE_ME = null; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_optional_withMessageSupplier() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        checkExists(object, () -> "Error message: " + LocalDate.now());
    }

    @Test
    void testCheckExists_optional_withNullMessageSupplier() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        final Supplier<String> IGNORE_ME = null; // NOSONAR
        checkExists(object, IGNORE_ME);
    }

    @Test
    void testCheckExists_optional_withMessageFormat() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        LocalDate today = LocalDate.now();
        checkExists(object, "String format: %s", today);
    }

    @Test
    void testCheckExists_optional_withNullMessageFormat() throws DataNotExistsException {
        final Optional<Object> object = Optional.of(new Object());
        checkExists(object, null, LocalDate.now());
    }

    @Test
    void testCheckExists_emptyOptional() {
        final Optional<Object> object = Optional.empty();
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_emptyOptional_withMessage() {
        final Optional<Object> object = Optional.empty();
        final String message = "testCheckExists_emptyOptional_withMessage";

        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, message));

        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_emptyOptional_withNullMessage() {
        final Optional<Object> object = Optional.empty();
        final String message = null;

        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, message));

        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_emptyOptional_withMessageSupplier() {
        final Optional<Object> object = Optional.empty();
        final LocalDate today = LocalDate.now();
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, () -> "Error message: " + today));

        assertEquals("Error message: " + today, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testCheckExists_emptyOptional_withNullMessageSupplier() {
        final Optional<Object> object = Optional.empty();
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkExists(object, messageSupplier));
    }

    @Test
    void testCheckExists_emptyOptional_withMessageFormat() {
        final Optional<Object> object = Optional.empty();
        LocalDate today = LocalDate.now();
        final DataNotExistsException e = assertThrows(DataNotExistsException.class,
                () -> checkExists(object, "String format: %s", today));
        assertEquals(String.format("String format: %s", today), e.getMessage());
    }

    @Test
    void testCheckExists_emptyOptional_withNullMessageFormat() {
        final Optional<Object> object = Optional.empty();
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkExists(object, null, today));
    }

    @Test
    void testCheckExists_nullOptional() {
        final Optional<Object> object = null;
        assertThrows(NullPointerException.class,
                () -> checkExists(object));
    }

    @Test
    void testCheckExists_nullOptional_withMessage() {
        final Optional<Object> object = null;
        final String message = "testCheckExists_nullOptional_withMessage";

        assertThrows(NullPointerException.class,
                () -> checkExists(object, message));
    }

    @Test
    void testCheckExists_nullOptional_withNullMessage() {
        final Optional<Object> object = null;
        final String message = null;

        assertThrows(NullPointerException.class,
                () -> checkExists(object, message));
    }

    @Test
    void testCheckExists_nullOptional_withMessageSupplier() {
        final Optional<Object> object = null;
        final LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkExists(object, () -> "Error message: " + today));
    }

    @Test
    void testCheckExists_nullOptional_withNullMessageSupplier() {
        final Optional<Object> object = null;
        Supplier<String> messageSupplier = null;
        assertThrows(NullPointerException.class,
                () -> checkExists(object, messageSupplier));
    }

    @Test
    void testCheckExists_nullOptional_withMessageFormat() {
        final Optional<Object> object = null;
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkExists(object, "String format: %s", today));
    }

    @Test
    void testCheckExists_nullOptional_withNullMessageFormat() {
        final Optional<Object> object = null;
        LocalDate today = LocalDate.now();
        assertThrows(NullPointerException.class,
                () -> checkExists(object, null, today));
    }

    // #endregion - Exists

    // #region - AffectedRows

    @Test
    void testCheckAffectedRows_int() {
        final int expectedValue = 25;

        checkAffectedRows(expectedValue, 25);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e0 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108));
        assertEquals(String.format("The number of rows affected is expected to be %d, but is: %d", expectedValue, 108),
                e0.getMessage());

    }

    @Test
    void testCheckAffectedRows_int_message() {
        final int expectedValue = 25;

        final String message = "Static message";

        checkAffectedRows(expectedValue, 25, message);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108, message));
        assertEquals(message, e1.getMessage());

        final String nullMessage = null;

        checkAffectedRows(expectedValue, 25, nullMessage);

        e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108, nullMessage));
        assertNull(e1.getMessage());
    }

    @Test
    void testCheckAffectedRows_int_messageSupplier() {
        final int expectedValue = 25;

        final Supplier<String> messageSupplier = () -> "Supplier message";

        checkAffectedRows(expectedValue, 25, messageSupplier);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e2 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108, messageSupplier));
        assertEquals(messageSupplier.get(), e2.getMessage());

        final Supplier<String> nullSupplier = null;

        checkAffectedRows(expectedValue, 25, nullSupplier);

        assertThrows(NullPointerException.class,
                () -> checkAffectedRows(expectedValue, 108, nullSupplier));
    }

    @Test
    void testCheckAffectedRows_int_messageFormat() {
        final int expectedValue = 25;

        checkAffectedRows(expectedValue, 25, "预计是 %d，结果是 %d。", expectedValue, 25);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e3 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108, "预计是 %d，结果是 %d。", expectedValue, 108));
        assertEquals("预计是 25，结果是 108。", e3.getMessage());

        checkAffectedRows(expectedValue, 25, null, expectedValue, 25);

        assertThrows(NullPointerException.class,
                () -> checkAffectedRows(expectedValue, 108, null, expectedValue, 108));
    }

    @Test
    void testCheckAffectedRows_long() {
        final long expectedValue = 25L;

        checkAffectedRows(expectedValue, 25L);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e0 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108L));
        assertEquals(String.format("The number of rows affected is expected to be %d, but is: %d", expectedValue, 108L),
                e0.getMessage());

    }

    @Test
    void testCheckAffectedRows_long_message() {
        final long expectedValue = 25L;

        final String message = "Static message";

        checkAffectedRows(expectedValue, 25L, message);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108L, message));
        assertEquals(message, e1.getMessage());

        final String nullMessage = null;

        checkAffectedRows(expectedValue, 25L, nullMessage);

        e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108L, nullMessage));
        assertNull(e1.getMessage());
    }

    @Test
    void testCheckAffectedRows_long_messageSupplier() {
        final long expectedValue = 25L;

        final Supplier<String> messageSupplier = () -> "Supplier message";

        checkAffectedRows(expectedValue, 25L, messageSupplier);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e2 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108L, messageSupplier));
        assertEquals(messageSupplier.get(), e2.getMessage());

        final Supplier<String> nullSupplier = null;

        checkAffectedRows(expectedValue, 25L, nullSupplier);

        assertThrows(NullPointerException.class,
                () -> checkAffectedRows(expectedValue, 108L, nullSupplier));
    }

    @Test
    void testCheckAffectedRows_long_messageFormat() {
        final long expectedValue = 25L;

        checkAffectedRows(expectedValue, 25L, "预计是 %d，结果是 %d。", expectedValue, 25L);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e3 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedRows(expectedValue, 108L, "预计是 %d，结果是 %d。", expectedValue, 108L));
        assertEquals("预计是 25，结果是 108。", e3.getMessage());

        checkAffectedRows(expectedValue, 25L, null, expectedValue, 25L);

        assertThrows(NullPointerException.class,
                () -> checkAffectedRows(expectedValue, 108L, null, expectedValue, 108L));
    }

    @Test
    void testCheckAffectedOneRow_int() {
        checkAffectedOneRow(1);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e0 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108));
        assertEquals(String.format("The number of rows affected is expected to be 1, but is: %d", 108),
                e0.getMessage());

    }

    @Test
    void testCheckAffectedOneRow_int_message() {
        final String message = "Static message";

        checkAffectedOneRow(1, message);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108, message));
        assertEquals(message, e1.getMessage());

        final String nullMessage = null;

        checkAffectedOneRow(1, nullMessage);

        e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108, nullMessage));
        assertNull(e1.getMessage());
    }

    @Test
    void testCheckAffectedOneRow_int_messageSupplier() {
        final Supplier<String> messageSupplier = () -> "Supplier message";

        checkAffectedOneRow(1, messageSupplier);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e2 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108, messageSupplier));
        assertEquals(messageSupplier.get(), e2.getMessage());

        final Supplier<String> nullSupplier = null;

        checkAffectedOneRow(1, nullSupplier);

        assertThrows(NullPointerException.class,
                () -> checkAffectedOneRow(108, nullSupplier));
    }

    @Test
    void testCheckAffectedOneRow_int_messageFormat() {
        checkAffectedOneRow(1, "预计是 %d，结果是 %d。", 1, 108);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e3 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108, "预计是 %d，结果是 %d。", 1, 108));
        assertEquals("预计是 1，结果是 108。", e3.getMessage());

        checkAffectedOneRow(1, null, 1);

        assertThrows(NullPointerException.class,
                () -> checkAffectedOneRow(108, null, 108));
    }

    @Test
    void testCheckAffectedOneRow_long() {
        checkAffectedOneRow(1L);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e0 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108L));
        assertEquals(String.format("The number of rows affected is expected to be 1, but is: %d", 108L),
                e0.getMessage());

    }

    @Test
    void testCheckAffectedOneRow_long_message() {
        final String message = "Static message";

        checkAffectedOneRow(1L, message);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108L, message));
        assertEquals(message, e1.getMessage());

        final String nullMessage = null;

        checkAffectedOneRow(1L, nullMessage);

        e1 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108L, nullMessage));
        assertNull(e1.getMessage());
    }

    @Test
    void testCheckAffectedOneRow_long_messageSupplier() {
        final Supplier<String> messageSupplier = () -> "Supplier message";

        checkAffectedOneRow(1L, messageSupplier);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e2 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108L, messageSupplier));
        assertEquals(messageSupplier.get(), e2.getMessage());

        final Supplier<String> nullSupplier = null;

        checkAffectedOneRow(1L, nullSupplier);

        assertThrows(NullPointerException.class,
                () -> checkAffectedOneRow(108L, nullSupplier));
    }

    @Test
    void testCheckAffectedOneRow_long_messageFormat() {
        checkAffectedOneRow(1L, "预计是 %d，结果是 %d。", 1L, 108L);

        JdbcUpdateAffectedIncorrectNumberOfRowsException e3 = assertThrows(JdbcUpdateAffectedIncorrectNumberOfRowsException.class,
                () -> checkAffectedOneRow(108L, "预计是 %d，结果是 %d。", 1L, 108L));
        assertEquals("预计是 1，结果是 108。", e3.getMessage());

        checkAffectedOneRow(1L, null, 1L);

        assertThrows(NullPointerException.class,
                () -> checkAffectedOneRow(108L, null, 108L));
    }

    // #endregion - AffectedRows

    // #region - Condition

    static final class MyException extends RuntimeException {}

    @Test
    void testCheckCondition() {

        checkCondition(true, MyException::new);

        final MyException me = new MyException();
        MyException e = assertThrows(MyException.class, () -> checkCondition(false, () -> me));
        assertEquals(me, e);
    }

    // #endregion - Condition

    // ================================
    // #region - invoke constructor
    // ================================

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = AssertTools.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }

    // ================================
    // #endregion - invoke constructor
    // ================================
}
