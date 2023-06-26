/*
 * Copyright 2022-2023 the original author or authors.
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

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

/**
 * 封装一些常用的正则操作，并可以缓存 {@link Pattern} 实例以复用（最多缓存 256 个）。
 * 
 * @author ZhouXY
 *
 */
public final class RegexUtil {

    private static final int DEFAULT_CACHE_INITIAL_CAPACITY = 64;
    private static final int MAX_CACHE_SIZE = 256;
    private static final ConcurrentHashMap<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>(
            DEFAULT_CACHE_INITIAL_CAPACITY);

    /**
     * 获取 {@link Pattern} 实例。
     * 
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern, final boolean cachePattern) {
        Preconditions.checkNotNull(pattern, "The pattern can not be null.");
        Pattern result = PATTERN_CACHE.get(pattern);
        if (result == null) {
            result = Pattern.compile(pattern);
            if (cachePattern && PATTERN_CACHE.size() < MAX_CACHE_SIZE) {
                PATTERN_CACHE.putIfAbsent(pattern, result);
                result = PATTERN_CACHE.get(pattern);
            }
        }
        return result;
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     * 
     * @param pattern 正则表达式
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern) {
        Preconditions.checkNotNull(pattern, "The pattern can not be null.");
        Pattern result = PATTERN_CACHE.get(pattern);
        if (result == null) {
            result = Pattern.compile(pattern);
        }
        return result;
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例。
     * 
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return Pattern 实例数组
     */
    public static Pattern[] getPatterns(final String[] patterns, final boolean cachePattern) {
        Preconditions.checkNotNull(patterns, "The patterns can not be null.");
        final Pattern[] result = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            result[i] = getPattern(patterns[i], cachePattern);
        }
        return result;
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例，不缓存。
     * 
     * @param patterns 正则表达式
     * @return {@link Pattern} 实例数组
     */
    public static Pattern[] getPatterns(final String[] patterns) {
        Preconditions.checkNotNull(patterns, "The patterns can not be null.");
        final Pattern[] result = new Pattern[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            result[i] = getPattern(patterns[i]);
        }
        return result;
    }

    /**
     * 手动缓存 Pattern 实例。
     * 
     * @param pattern 要缓存的 {@link Pattern} 实例
     * @return 缓存的 Pattern 实例。如果缓存已满，则返回 {@code null}。
     */
    public static Pattern cachePattern(final Pattern pattern) {
        if (PATTERN_CACHE.size() >= MAX_CACHE_SIZE) {
            return null;
        }
        final String patternStr = pattern.pattern();
        PATTERN_CACHE.putIfAbsent(patternStr, pattern);
        return PATTERN_CACHE.get(patternStr);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     * 
     * @param input   输入
     * @param pattern 正则
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final Pattern pattern) {
        Preconditions.checkNotNull(pattern, "The pattern can not be null.");
        return input != null && pattern.matcher(input).matches();
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。
     * 
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchOne(@Nullable final CharSequence input, final Pattern[] patterns) {
        Preconditions.checkNotNull(patterns, "The patterns can not be null.");
        if (input == null) {
            return false;
        }
        for (Pattern pattern : patterns) {
            if (matches(input, pattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     * 
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchAll(@Nullable final CharSequence input, final Pattern[] patterns) {
        Preconditions.checkNotNull(patterns, "The patterns can not be null.");
        if (input == null) {
            return false;
        }
        for (Pattern pattern : patterns) {
            if (!matches(input, pattern)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     * 
     * @param input        输入
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final String pattern,
            final boolean cachePattern) {
        return matches(input, getPattern(pattern, cachePattern));
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。不缓存 {@link Pattern} 实例。
     * 
     * @param input   输入
     * @param pattern 正则表达式
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final String pattern) {
        return matches(input, getPattern(pattern));
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。
     * 
     * @param input        输入
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matchOne(@Nullable final CharSequence input, final String[] patterns,
            final boolean cachePattern) {
        final Pattern[] patternSet = getPatterns(patterns, cachePattern);
        return matchOne(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。不缓存 {@link Pattern} 实例。
     * 
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    public static boolean matchOne(@Nullable final CharSequence input, final String[] patterns) {
        final Pattern[] patternSet = getPatterns(patterns);
        return matchOne(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     * 
     * @param input        输入
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matchAll(@Nullable final CharSequence input, final String[] patterns,
            final boolean cachePattern) {
        final Pattern[] patternSet = getPatterns(patterns, cachePattern);
        return matchAll(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。不缓存 {@link Pattern} 实例。
     * 
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    public static boolean matchAll(@Nullable final CharSequence input, final String[] patterns) {
        final Pattern[] patternSet = getPatterns(patterns);
        return matchAll(input, patternSet);
    }

    /**
     * 生成匹配器。
     * 
     * @param input   输入
     * @param pattern 正则
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final Pattern pattern) {
        Preconditions.checkNotNull(input, "The input can not be null");
        Preconditions.checkNotNull(pattern, "The pattern can not be null");
        return pattern.matcher(input);
    }

    /**
     * 生成匹配器。
     * 
     * @param input        输入
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern, boolean cachePattern) {
        Preconditions.checkNotNull(input, "The input can not be null");
        return getPattern(pattern, cachePattern).matcher(input);
    }

    /**
     * 生成匹配器。不缓存 {@link Pattern} 实例。
     * 
     * @param input   输入
     * @param pattern 正则表达式
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern) {
        Preconditions.checkNotNull(input, "The input can not be null");
        return getPattern(pattern).matcher(input);
    }

    private RegexUtil() {
        // 不允许实例化
        throw new IllegalStateException("Utility class");
    }
}
