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
    void getPattern_SameRegexAndFlag_CachePatternIsTrue_ReturnsCachedPattern() {
        String pattern = "abc";
        Pattern cachedPattern = RegexTools.getPattern(pattern, true);
        Pattern patternFromCache = RegexTools.getPattern(pattern);
        assertSame(cachedPattern, patternFromCache, "Pattern should be cached");

        Pattern cachedPatternWithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE, true);
        Pattern patternFromCacheWithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE);
        assertSame(cachedPatternWithFlag, patternFromCacheWithFlag, "Pattern should be cached");
    }

    @Test
    void getPattern_SameRegexAndFlag_CachePatternFalse_ReturnsNewPattern() {
        String pattern = "getPattern_SameRegexAndFlag_CachePatternFalse_ReturnsNewPattern";
        Pattern pattern1 = RegexTools.getPattern(pattern, false);
        Pattern pattern2 = RegexTools.getPattern(pattern, false);
        Pattern pattern3 = RegexTools.getPattern(pattern);
        assertNotSame(pattern1, pattern2, "Pattern should not be cached");
        assertNotSame(pattern1, pattern3, "Pattern should not be cached");
        assertNotSame(pattern2, pattern3, "Pattern should not be cached");

        Pattern pattern1WithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE, false);
        Pattern pattern2WithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE, false);
        Pattern pattern3WithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE);
        assertNotSame(pattern1WithFlag, pattern2WithFlag, "Pattern should not be cached");
        assertNotSame(pattern1WithFlag, pattern3WithFlag, "Pattern should not be cached");
        assertNotSame(pattern2WithFlag, pattern3WithFlag, "Pattern should not be cached");
    }

    @Test
    void getPattern_SameRegexAndDifferentFlag_ReturnsNewPattern() {
        String pattern = "getPattern_SameRegexAndDifferentFlag_CachePatternFalse_ReturnsNewPattern";

        Pattern pattern1WithFlag = RegexTools.getPattern(pattern, Pattern.CASE_INSENSITIVE, true);
        Pattern pattern2WithFlag = RegexTools.getPattern(pattern, 0, true);
        assertNotSame(pattern1WithFlag, pattern2WithFlag, "Patterns should not be the same");
    }

    @Test
    void getPattern_NullPattern_ThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getPattern(null, true);
        });
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getPattern(null, Pattern.CASE_INSENSITIVE, true);
        });
    }

    @Test
    void matches_InputMatchesPattern_ReturnsTrue() {
        String pattern = "abc";
        assertTrue(RegexTools.matches("abc", pattern), "Input should match pattern");
        assertFalse(RegexTools.matches("ABC", pattern), "Input should match pattern");
        assertTrue(RegexTools.matches("ABC", pattern, Pattern.CASE_INSENSITIVE), "Input should match pattern");

        Pattern compiledPattern = Pattern.compile(pattern);
        assertTrue(RegexTools.matches("abc", compiledPattern), "Input should match pattern");
        assertFalse(RegexTools.matches("ABC", compiledPattern), "Input should match pattern");

        assertTrue(RegexTools.matches("abc", pattern, true), "Input should match pattern");
        Pattern cachedPattern1 = RegexTools.getPattern(pattern);
        Pattern cachedPattern2 = RegexTools.getPattern(pattern);
        assertSame(cachedPattern1, cachedPattern2, "Cached pattern should be the same");
    }

    @Test
    void matches_InputDoesNotMatchPattern_ReturnsFalse() {
        String pattern = "abc";
        assertFalse(RegexTools.matches("abcd", pattern), "Input should not match pattern");
    }

    @Test
    void matches_NullInput_ReturnsFalse() {
        String pattern = "abc";
        assertFalse(RegexTools.matches(null, pattern), "Null input should return false");
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
        assertFalse(RegexTools.matchesOne(null, compiledPatterns), "Input should not match any pattern");
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
        assertFalse(RegexTools.matchesAll(null, compiledPatterns), "Input should not match all patterns");
    }

    @Test
    void getMatcher_ValidInputAndPattern_ReturnsMatcher() {
        String pattern = "abc";
        Matcher matcher1 = RegexTools.getMatcher("abc", pattern);
        assertNotNull(matcher1, "Matcher should not be null");
        assertTrue(matcher1.matches(), "Should be matches");

        Matcher matcher2 = RegexTools.getMatcher("ABC", pattern, true);
        assertNotNull(matcher2, "Matcher should not be null");
        assertFalse(matcher2.matches(), "Should be matches");

        Pattern cachedPattern = RegexTools.getPattern(pattern);
        Pattern patternFromCache = RegexTools.getPattern(pattern);
        assertSame(cachedPattern, patternFromCache);
    }

    @Test
    void getMatcher_NullInput_ThrowsException() {
        String pattern = "abc";
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getMatcher(null, pattern);
        });
    }

    @Test
    void getMatcher_NullPattern_ThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getMatcher("abc", (String) null);
        });
        assertThrows(NullPointerException.class, () -> {
            RegexTools.getMatcher("abc", (Pattern) null);
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
