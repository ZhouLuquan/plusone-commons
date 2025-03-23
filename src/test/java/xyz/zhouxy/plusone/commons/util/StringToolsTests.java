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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

@SuppressWarnings("null")
public
class StringToolsTests {

    // ================================
    // #region - isNotBlank
    // ================================

    @Test
    void isNotBlank_NullString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank(null));
    }

    @Test
    void isNotBlank_EmptyString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank(""));
    }

    @Test
    void isNotBlank_WhitespaceString_ReturnsFalse() {
        assertFalse(StringTools.isNotBlank("   "));
    }

    @Test
    void isNotBlank_NonWhitespaceString_ReturnsTrue() {
        assertTrue(StringTools.isNotBlank("Hello"));
    }

    // ================================
    // #endregion - isNotBlank
    // ================================

    // ================================
    // #region - isBlank
    // ================================

    @Test
    void isBlank_NullString_ReturnsTrue() {
        assertTrue(StringTools.isBlank(null));
    }

    @Test
    void isBlank_EmptyString_ReturnsTrue() {
        assertTrue(StringTools.isBlank(""));
    }

    @Test
    void isBlank_WhitespaceString_ReturnsTrue() {
        assertTrue(StringTools.isBlank("   "));
    }

    @Test
    void isBlank_NonWhitespaceString_ReturnsFalse() {
        assertFalse(StringTools.isBlank("Hello"));
    }

    // ================================
    // #endregion - isBlank
    // ================================

    // ================================
    // #region - isNotEmpty
    // ================================

    @Test
    void isNotEmpty_NullString_ReturnsFalse() {
        assertFalse(StringTools.isNotEmpty(null));
    }

    @Test
    void isNotEmpty_EmptyString_ReturnsFalse() {
        assertFalse(StringTools.isNotEmpty(""));
    }

    @Test
    void isNotEmpty_WhitespaceString_ReturnsTrue() {
        assertTrue(StringTools.isNotEmpty("   "));
    }

    @Test
    void isNotEmpty_NonWhitespaceString_ReturnsTrue() {
        assertTrue(StringTools.isNotEmpty("Hello"));
    }

    // ================================
    // #endregion - isNotEmpty
    // ================================

    // ================================
    // #region - isEmpty
    // ================================

    @Test
    void isEmpty_NullString_ReturnsTrue() {
        assertTrue(StringTools.isEmpty(null));
    }

    @Test
    void isEmpty_EmptyString_ReturnsTrue() {
        assertTrue(StringTools.isEmpty(""));
    }

    @Test
    void isEmpty_WhitespaceString_ReturnsFalse() {
        assertFalse(StringTools.isEmpty("   "));
    }

    @Test
    void isEmpty_NonWhitespaceString_ReturnsFalse() {
        assertFalse(StringTools.isEmpty("Hello"));
    }

    // ================================
    // #endregion - isEmpty
    // ================================

    // ================================
    // #region - EMAIL
    // ================================

    @Test
    public void testValidEmails() {
        assertTrue(StringTools.isEmail("test@example.com"));
        assertTrue(StringTools.isEmail("user.name+tag+sorting@example.com"));
        assertTrue(StringTools.isEmail("user@sub.example.com"));
        assertTrue(StringTools.isEmail("user@123.123.123.123"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(StringTools.isEmail(".username@example.com"));
        assertFalse(StringTools.isEmail("@missingusername.com"));
        assertFalse(StringTools.isEmail("plainaddress"));
        assertFalse(StringTools.isEmail("username..username@example.com"));
        assertFalse(StringTools.isEmail("username.@example.com"));
        assertFalse(StringTools.isEmail("username@-example.com"));
        assertFalse(StringTools.isEmail("username@-example.com"));
        assertFalse(StringTools.isEmail("username@.com.com"));
        assertFalse(StringTools.isEmail("username@.com.my"));
        assertFalse(StringTools.isEmail("username@.com"));
        assertFalse(StringTools.isEmail("username@com."));
        assertFalse(StringTools.isEmail("username@com"));
        assertFalse(StringTools.isEmail("username@example..com"));
        assertFalse(StringTools.isEmail("username@example.com-"));
        assertFalse(StringTools.isEmail("username@example.com."));
        assertFalse(StringTools.isEmail("username@example"));
    }

    // ================================
    // #endregion - EMAIL
    // ================================

    // ================================
    // #region - isURL
    // ================================

        /**
     * TC1: 验证标准HTTP协议URL
     */
    @Test
    void isURL_ValidHttpURL_ReturnsTrue() {
        assertTrue(StringTools.isURL("http://example.com"));
    }

    /**
     * TC2: 验证带路径参数的HTTPS复杂URL
     */
    @Test
    void isURL_ValidHttpsComplexURL_ReturnsTrue() {
        assertTrue(StringTools.isURL("https://test.com:8080/api/v1?param=value#anchor"));
    }

    /**
     * TC3: 验证FTP协议URL
     */
    @Test
    void isURL_ValidFtpURL_ReturnsTrue() {
        assertTrue(StringTools.isURL("ftp://files.example.com/directory/"));
    }

    /**
     * TC4: 验证非法协议处理
     */
    @Test
    void isURL_InvalidProtocol_ReturnsFalse() {
        assertFalse(StringTools.isURL("httpx://invalid.com"));
    }

    /**
     * TC5: 验证null输入处理
     */
    @Test
    void isURL_NullInput_ReturnsFalse() {
        assertFalse(StringTools.isURL(null));
    }

    /**
     * TC6: 验证空字符串处理
     */
    @Test
    void isURL_EmptyString_ReturnsFalse() {
        assertFalse(StringTools.isURL(StringTools.EMPTY_STRING));
    }

    /**
     * TC7: 验证缺失协议头处理
     */
    @Test
    void isURL_MissingProtocol_ReturnsFalse() {
        assertFalse(StringTools.isURL("www.example.com/path"));
    }

    /**
     * TC8: 验证未编码特殊字符处理
     */
    @Test
    void isURL_UnencodedSpecialChars_ReturnsTrue() {
        assertTrue(StringTools.isURL("http://example.com/测试"));
    }

    // ================================
    // #endregion - isURL
    // ================================

    // ================================
    // #region - repeat
    // ================================

    @Test
    void repeat_NullString_ThrowsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            StringTools.repeat(null, 2);
        });
        assertNull(exception.getMessage());
    }

    @Test
    void repeat_EmptyString_ReturnsEmptyString() {
        assertEquals("", StringTools.repeat("", 2));
    }

    @Test
    void repeat_RepeatOnce_ReturnsOriginalString() {
        assertEquals("Hello", StringTools.repeat("Hello", 1));
    }

    @Test
    void repeat_RepeatMultipleTimes_ReturnsRepeatedString() {
        assertEquals("HelloHelloHello", StringTools.repeat("Hello", 3));
    }

    @Test
    void repeat_ExceedsMaxLength_ReturnsTruncatedString() {
        assertEquals("HelloHello", StringTools.repeat("Hello", 2, 14));
        assertEquals("HelloHel", StringTools.repeat("Hello", 2, 8));
        assertEquals("He", StringTools.repeat("Hello", 2, 2));
        assertEquals("", StringTools.repeat("Hello", 0, 2));
        assertThrows(IllegalArgumentException.class, () -> StringTools.repeat("Hello", -1, 2));
        assertThrows(IllegalArgumentException.class, () -> StringTools.repeat("Hello", 5, -1));
    }

    @Test
    void repeat_ZeroTimes_ReturnsEmptyString() {
        assertEquals("", StringTools.repeat("Hello", 0));
    }

    // ================================
    // #endregion - repeat
    // ================================

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = StringTools.class.getDeclaredConstructors();
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
}
