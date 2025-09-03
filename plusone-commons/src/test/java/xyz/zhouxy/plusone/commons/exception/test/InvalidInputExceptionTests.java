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

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.exception.business.InvalidInputException;

@Slf4j
public class InvalidInputExceptionTests {

    // ================================
    // #region - createByType
    // ================================

    @Test
    void test_createByType() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS.create();
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS, e.getType());
        assertEquals(InvalidInputException.Type.CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS.getDefaultMessage(), e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withMessage() {
        final String message = "test_createByType_withMessage";
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS.create(message);
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS, e.getType());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withNullMessage() {
        final String message = null;
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.PICTURE_CONTAINS_ILLEGAL_INFORMATION.create(message);
        });
        assertSame(InvalidInputException.Type.PICTURE_CONTAINS_ILLEGAL_INFORMATION, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withCause() {
        Arrays.asList("test_createByType_withCause", null).forEach(message -> {

            NumberFormatException nfe = new NumberFormatException(message);
            InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
                throw InvalidInputException.Type.INFRINGE_COPYRIGHT.create(nfe);
            });

            assertSame(InvalidInputException.Type.INFRINGE_COPYRIGHT, e.getType());
            log.info("{}", e.getMessage());
            assertEquals(nfe.toString(), e.getMessage());
            assertSame(nfe, e.getCause());
        });
    }

    @Test
    void test_createByType_withNullCause() {
        NumberFormatException nfe = null;
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.DEFAULT.create(nfe);
        });

        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withMessageAndCause() {
        final String message = "test_createByType_withMessageAndCause";
        final NumberFormatException nfe = new NumberFormatException("NumberFormatExceptionMessage");

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS.create(message, nfe);
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_AND_MALICIOUS_LINKS, e.getType());
        assertEquals(message, e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void test_createByType_withNullMessageAndCause() {
        final String message = null;
        final NullPointerException nfe = new NullPointerException("Context is null.");

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS.create(message, nfe);
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS, e.getType());
        assertNull(e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void test_createByType_withMessageAndNullCause() {
        final String message = "test_createByType_withMessageAndNullCause";
        final NullPointerException npe = null;

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS.create(message, npe);
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS, e.getType());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void test_createByType_withNullMessageAndNullCause() {
        final String message = null;
        final NullPointerException nfe = null;

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS.create(message, nfe);
        });
        assertSame(InvalidInputException.Type.CONTAINS_ILLEGAL_WORDS, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    // ================================
    // #endregion - createByType
    // ================================

    // ================================
    // #region - constructor
    // ================================

    @Test
    void testConstructor() {
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException();
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertEquals(InvalidInputException.Type.DEFAULT.getDefaultMessage(), e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testConstructor_withMessage() {
        final String message = "testConstructor_withMessage";
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testConstructor_withNullMessage() {
        final String message = null;
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testConstructor_withCause() {
        Arrays.asList("testConstructor_withCause", null).forEach(message -> {

            NumberFormatException nfe = new NumberFormatException(message);
            InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
                throw new InvalidInputException(nfe);
            });

            assertSame(InvalidInputException.Type.DEFAULT, e.getType());
            log.info("{}", e.getMessage());
            assertEquals(nfe.toString(), e.getMessage());
            assertSame(nfe, e.getCause());
        });
    }

    @Test
    void testConstructor_withNullCause() {
        NumberFormatException nfe = null;
        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(nfe);
        });

        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testConstructor_withMessageAndCause() {
        final String message = "testConstructor_withMessageAndCause";
        final NumberFormatException nfe = new NumberFormatException("NumberFormatExceptionMessage");

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message, nfe);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertEquals(message, e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void testConstructor_withNullMessageAndCause() {
        final String message = null;
        final NullPointerException nfe = new NullPointerException("Context is null.");

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message, nfe);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertNull(e.getMessage());
        assertSame(nfe, e.getCause());
    }

    @Test
    void testConstructor_withMessageAndNullCause() {
        final String message = "testConstructor_withMessageAndNullCause";
        final NullPointerException npe = null;

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message, npe);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertEquals(message, e.getMessage());
        assertNull(e.getCause());
    }

    @Test
    void testConstructor_withNullMessageAndNullCause() {
        final String message = null;
        final NullPointerException nfe = null;

        InvalidInputException e = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(message, nfe);
        });
        assertSame(InvalidInputException.Type.DEFAULT, e.getType());
        assertNull(e.getMessage());
        assertNull(e.getCause());
    }

    // ================================
    // #endregion - constructor
    // ================================
}
