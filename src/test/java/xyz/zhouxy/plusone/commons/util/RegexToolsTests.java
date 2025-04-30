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

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("null")
public
class RegexToolsTests {

    @Test
    void getPattern_CachePatternTrue_ReturnsCachedPattern() {
        String pattern = "abc";
        Pattern cachedPattern = RegexTools.getPattern(pattern, true);
        Pattern patternFromCache = RegexTools.getPattern(pattern, true);
        assertSame(cachedPattern, patternFromCache, "Pattern should be cached");
    }

    @Test
    void getPattern_CachePatternFalse_ReturnsNewPattern() {
        String pattern = "getPattern_CachePatternFalse_ReturnsNewPattern";
        Pattern pattern1 = RegexTools.getPattern(pattern, false);
        Pattern pattern2 = RegexTools.getPattern(pattern, false);
        assertNotSame(pattern1, pattern2, "Pattern should not be cached");
    }

    @Test
    void getPattern_NullPattern_ThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getPattern(null, true);
        });
    }

    @Test
    void matches_InputMatchesPattern_ReturnsTrue() {
        String pattern = "abc";
        Pattern compiledPattern = Pattern.compile(pattern);
        assertTrue(RegexTools.matches("abc", compiledPattern), "Input should match pattern");
    }

    @Test
    void matches_InputDoesNotMatchPattern_ReturnsFalse() {
        String pattern = "abc";
        Pattern compiledPattern = Pattern.compile(pattern);
        assertFalse(RegexTools.matches("abcd", compiledPattern), "Input should not match pattern");
    }

    @Test
    void matches_NullInput_ReturnsFalse() {
        String pattern = "abc";
        Pattern compiledPattern = Pattern.compile(pattern);
        assertFalse(RegexTools.matches(null, compiledPattern), "Null input should return false");
    }

    @Test
    void matchesOne_InputMatchesOnePattern_ReturnsTrue() {
        String[] patterns = {"abc", "def"};
        Pattern[] compiledPatterns = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            compiledPatterns[i] = Pattern.compile(patterns[i]);
        }
        assertTrue(RegexTools.matchesOne("abc", compiledPatterns), "Input should match one pattern");
    }

    @Test
    void matchesOne_InputDoesNotMatchAnyPattern_ReturnsFalse() {
        String[] patterns = {"abc", "def"};
        Pattern[] compiledPatterns = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            compiledPatterns[i] = Pattern.compile(patterns[i]);
        }
        assertFalse(RegexTools.matchesOne("xyz", compiledPatterns), "Input should not match any pattern");
    }

    @Test
    void matchesAll_InputMatchesAllPatterns_ReturnsTrue() {
        String[] patterns = {"abc", "abc"};
        Pattern[] compiledPatterns = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            compiledPatterns[i] = Pattern.compile(patterns[i]);
        }
        assertTrue(RegexTools.matchesAll("abc", compiledPatterns), "Input should match all patterns");
    }

    @Test
    void matchesAll_InputDoesNotMatchAllPatterns_ReturnsFalse() {
        String[] patterns = {"abc", "def"};
        Pattern[] compiledPatterns = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            compiledPatterns[i] = Pattern.compile(patterns[i]);
        }
        assertFalse(RegexTools.matchesAll("abc", compiledPatterns), "Input should not match all patterns");
    }

    @Test
    void getMatcher_ValidInputAndPattern_ReturnsMatcher() {
        String pattern = "abc";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = RegexTools.getMatcher("abc", compiledPattern);
        assertNotNull(matcher, "Matcher should not be null");
    }

    @Test
    void getMatcher_NullInput_ThrowsException() {
        String pattern = "abc";
        Pattern compiledPattern = Pattern.compile(pattern);
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getMatcher(null, compiledPattern);
        });
    }

    @Test
    void getMatcher_NullPattern_ThrowsException() {
        final Pattern pattern = null;
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getMatcher("abc", pattern);
        });
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = RegexTools.class.getDeclaredConstructors();
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
