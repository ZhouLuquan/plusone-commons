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

package xyz.zhouxy.plusone.commons.exception.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.exception.ParsingFailureException;

@Slf4j
public class ParsingFailureExceptionTests {

    // ================================
    // #region - createByType
    // ================================

    @Test
    void test_createByType() {
        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.DATE_TIME_PARSING_FAILURE.create();
        });
        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getDefaultMessage(), e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withMessage() {
        final String message = "test_createByType_withMessage";
        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.JSON_PARSING_FAILURE.create(message);
        });
        assertSame(ParsingFailureException.Type.JSON_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.Type.JSON_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withNullMessage() {
        final String message = null;
        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.XML_PARSING_FAILURE.create(message);
        });
        assertSame(ParsingFailureException.XML_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.XML_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withCause() {
        Arrays.asList("test_createByType_withCause", null).forEach(message -> {

            NumberFormatException nfe = new NumberFormatException(message);
            ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
                throw ParsingFailureException.NUMBER_PARSING_FAILURE.create(nfe);
            });

            assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
            assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
            log.info("{}", e.getMessage());
            assertEquals(nfe.toString(), e.getMessage());
            assertSame(nfe, e.getCause());
        });
    }

    @Test
    void test_createByType_withNullCause() {
        NumberFormatException nfe = null;
        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.NUMBER_PARSING_FAILURE.create(nfe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withMessageAndCause() {
        final String message = "test_createByType_withMessageAndCause";
        final NumberFormatException nfe = new NumberFormatException("NumberFormatExceptionMessage");

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.NUMBER_PARSING_FAILURE.create(message, nfe);
        });
        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void test_createByType_withNullMessageAndCause() {
        final String message = null;
        final NullPointerException nfe = new NullPointerException("Context is null.");

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.DATE_TIME_PARSING_FAILURE.create(message, nfe);
        });
        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void test_createByType_withMessageAndNullCause() {
        final String message = "test_createByType_withMessageAndNullCause";
        final NullPointerException npe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.DATE_TIME_PARSING_FAILURE.create(message, npe);
        });
        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withNullMessageAndNullCause() {
        final String message = null;
        final NullPointerException nfe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.DATE_TIME_PARSING_FAILURE.create(message, nfe);
        });
        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    // ================================
    // #endregion - createByType
    // ================================

    // ================================
    // #region - of DateTimeParseException
    // ================================

    @Test
    void test_createByOf_withDateTimeParseException() {
        DateTimeParseException dtpe = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse("abcd", DateTimeFormatter.ISO_DATE_TIME);
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(dtpe.getMessage(), e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withNullDateTimeParseException() {
        DateTimeParseException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getDefaultMessage(), e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByOf_withDateTimeParseExceptionAndMessage() {
        final String message = "test_createByOf_withDateTimeParseExceptionAndMessage";
        DateTimeParseException dtpe = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse("abcd", DateTimeFormatter.ISO_DATE_TIME);
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withDateTimeParseExceptionAndNullMessage() {
        final String message = null;
        DateTimeParseException dtpe = assertThrows(DateTimeParseException.class, () -> {
            LocalDateTime.parse("abcd", DateTimeFormatter.ISO_DATE_TIME);
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withNullDateTimeParseExceptionAndMessage() {
        final String message = "test_createByOf_withDateTimeParseExceptionAndMessage";
        DateTimeParseException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByOf_withNullDateTimeParseExceptionAndNullMessage() {
        final String message = null;
        DateTimeParseException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.DATE_TIME_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.DATE_TIME_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    // ================================
    // #endregion - of DateTimeParseException
    // ================================

    // ================================
    // #region - of NumberFormatException
    // ================================

    @Test
    void test_createByOf_withNumberFormatException() {
        NumberFormatException dtpe = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("abcd");
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(dtpe.getMessage(), e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withNullNumberFormatException() {
        NumberFormatException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getDefaultMessage(), e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByOf_withNumberFormatExceptionAndMessage() {
        final String message = "test_createByOf_withNumberFormatExceptionAndMessage";
        NumberFormatException dtpe = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("abcd");
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withNumberFormatExceptionAndNullMessage() {
        final String message = null;
        NumberFormatException dtpe = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("abcd");
        });

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertSame(dtpe, e.getCause());
    }

    @Test
    void test_createByOf_withNullNumberFormatExceptionAndMessage() {
        final String message = "test_createByOf_withNumberFormatExceptionAndMessage";
        NumberFormatException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByOf_withNullNumberFormatExceptionAndNullMessage() {
        final String message = null;
        NumberFormatException dtpe = null;

        ParsingFailureException e = assertThrows(ParsingFailureException.class, () -> {
            throw ParsingFailureException.of(message, dtpe);
        });

        assertSame(ParsingFailureException.NUMBER_PARSING_FAILURE, e.getType());
        assertEquals(ParsingFailureException.NUMBER_PARSING_FAILURE.getCode(), e.getTypeCode());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    // ================================
    // #endregion - of NumberFormatException
    // ================================

}
