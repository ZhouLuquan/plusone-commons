/*
 * Copyright 2023-2025 the original author or authors.
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

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 封装一些常用的正则操作，并可以缓存 {@link Pattern} 实例以复用（最多缓存大概 256 个）。
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 *
 */
public final class RegexTools {

    private static final int DEFAULT_CACHE_INITIAL_CAPACITY = 64;
    private static final int MAX_CACHE_SIZE = 256;
    private static final Map<String, Pattern> PATTERN_CACHE
            = new ConcurrentHashMap<>(DEFAULT_CACHE_INITIAL_CAPACITY);

    /**
     * 获取 {@link Pattern} 实例。
     *
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern, final boolean cachePattern) {
        AssertTools.checkNotNull(pattern);
        return cachePattern ? cacheAndGetPatternInternal(pattern) : getPatternInternal(pattern);
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     *
     * @param pattern 正则表达式
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern) {
        AssertTools.checkNotNull(pattern);
        return getPatternInternal(pattern);
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例。
     *
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return {@link Pattern} 实例数组
     */
    public static Pattern[] getPatterns(final String[] patterns, final boolean cachePattern) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        return cachePattern
                ? cacheAndGetPatternsInternal(patterns)
                : getPatternsInternal(patterns);
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例，不缓存。
     *
     * @param patterns 正则表达式
     * @return {@link Pattern} 实例数组
     */
    public static Pattern[] getPatterns(final String[] patterns) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        return getPatternsInternal(patterns);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     *
     * @param input   输入
     * @param pattern 正则
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final Pattern pattern) {
        AssertTools.checkNotNull(pattern);
        return matchesInternal(input, pattern);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。
     *
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchesOne(@Nullable final CharSequence input, final Pattern[] patterns) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        return matchesOneInternal(input, patterns);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     *
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchesAll(@Nullable final CharSequence input, final Pattern[] patterns) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        return matchesAllInternal(input, patterns);
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
        AssertTools.checkNotNull(pattern);
        Pattern p = cachePattern
                ? cacheAndGetPatternInternal(pattern)
                : getPatternInternal(pattern);
        return matchesInternal(input, p);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final String pattern) {
        AssertTools.checkNotNull(pattern);
        return matchesInternal(input, getPatternInternal(pattern));
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。
     *
     * @param input        输入
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matchesOne(@Nullable final CharSequence input, final String[] patterns,
            final boolean cachePattern) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        final Pattern[] patternSet = cachePattern
                ? cacheAndGetPatternsInternal(patterns)
                : getPatternsInternal(patterns);
        return matchesOneInternal(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。不缓存 {@link Pattern} 实例。
     *
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    public static boolean matchesOne(@Nullable final CharSequence input, final String[] patterns) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        final Pattern[] patternSet = getPatternsInternal(patterns);
        return matchesOneInternal(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     *
     * @param input        输入
     * @param patterns     正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matchesAll(@Nullable final CharSequence input, final String[] patterns,
            final boolean cachePattern) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        final Pattern[] patternSet = cachePattern
                ? cacheAndGetPatternsInternal(patterns)
                : getPatternsInternal(patterns);
        return matchesAllInternal(input, patternSet);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。不缓存 {@link Pattern} 实例。
     *
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    public static boolean matchesAll(@Nullable final CharSequence input, final String[] patterns) {
        AssertTools.checkNotNull(patterns);
        AssertTools.checkArgument(allNotNull(patterns));
        final Pattern[] patternSet = getPatternsInternal(patterns);
        return matchesAllInternal(input, patternSet);
    }

    /**
     * 生成 Matcher。
     *
     * @param input   输入
     * @param pattern 正则
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final Pattern pattern) {
        AssertTools.checkNotNull(input);
        AssertTools.checkNotNull(pattern);
        return pattern.matcher(input);
    }

    /**
     * 生成 Matcher。
     *
     * @param input        输入
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern, boolean cachePattern) {
        AssertTools.checkNotNull(input);
        AssertTools.checkNotNull(pattern);
        final Pattern p = cachePattern
                ? cacheAndGetPatternInternal(pattern)
                : getPatternInternal(pattern);
        return p.matcher(input);
    }

    /**
     * 生成 Matcher。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern) {
        AssertTools.checkNotNull(input);
        AssertTools.checkNotNull(pattern);
        return getPatternInternal(pattern).matcher(input);
    }

    // ========== internal methods ==========

    /**
     * 获取 {@link Pattern} 实例。
     *
     * @param pattern      正则表达式
     * @return {@link Pattern} 实例
     */
    @Nonnull
    private static Pattern cacheAndGetPatternInternal(final String pattern) {
        if (PATTERN_CACHE.size() < MAX_CACHE_SIZE) {
            return PATTERN_CACHE.computeIfAbsent(pattern, Pattern::compile);
        }
        Pattern result = PATTERN_CACHE.get(pattern);
        if (result != null) {
            return result;
        }
        return Pattern.compile(pattern);
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     *
     * @param pattern 正则表达式
     * @return {@link Pattern} 实例
     */
    @Nonnull
    private static Pattern getPatternInternal(final String pattern) {
        Pattern result = PATTERN_CACHE.get(pattern);
        if (result == null) {
            result = Pattern.compile(pattern);
        }
        return result;
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例。
     *
     * @param patterns 正则表达式
     * @return {@link Pattern} 实例数组
     */
    @Nonnull
    private static Pattern[] cacheAndGetPatternsInternal(final String[] patterns) {
        return Arrays.stream(patterns)
                .map(RegexTools::cacheAndGetPatternInternal)
                .toArray(Pattern[]::new);
    }

    /**
     * 将各个正则表达式转为 {@link Pattern} 实例。
     *
     * @param patterns 正则表达式
     * @return {@link Pattern} 实例数组
     */
    @Nonnull
    private static Pattern[] getPatternsInternal(final String[] patterns) {
        return Arrays.stream(patterns)
                .map(RegexTools::getPatternInternal)
                .toArray(Pattern[]::new);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     *
     * @param input   输入
     * @param pattern 正则
     * @return 判断结果
     */
    private static boolean matchesInternal(@Nullable final CharSequence input, final Pattern pattern) {
        return input != null && pattern.matcher(input).matches();
    }

    private static boolean matchesOneInternal(@Nullable final CharSequence input, final Pattern[] patterns) {
        return input != null
                && Arrays.stream(patterns)
                        .anyMatch(pattern -> pattern.matcher(input).matches());
    }

    private static boolean matchesAllInternal(@Nullable final CharSequence input, final Pattern[] patterns) {
        return input != null
                && Arrays.stream(patterns)
                        .allMatch(pattern -> pattern.matcher(input).matches());
    }

    private static <T> boolean allNotNull(T[] array) {
        return Arrays.stream(array).allMatch(Objects::nonNull);
    }

    private RegexTools() {
        // 不允许实例化
        throw new IllegalStateException("Utility class");
    }
}
