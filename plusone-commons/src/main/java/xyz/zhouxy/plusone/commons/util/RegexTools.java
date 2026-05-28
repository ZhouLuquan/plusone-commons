/*
 * Copyright 2023-present ZhouXY
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

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkNotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 封装一些常用的正则操作，并可以缓存 {@link Pattern} 实例以复用。
 *
 * @author ZhouXY
 */
public final class RegexTools {

    private static final int MAX_CACHE_SIZE = 256;
    private static final int DEFAULT_FLAG = 0;
    private static final LoadingCache<RegexAndFlags, Pattern> PATTERN_CACHE = CacheBuilder
            .newBuilder()
            .maximumSize(MAX_CACHE_SIZE)
            .build(new CacheLoader<RegexAndFlags, Pattern>() {
                @SuppressWarnings("null")
                public Pattern load(RegexAndFlags regexAndFlags) {
                    return regexAndFlags.compilePattern();
                }
            });

    // ================================
    // #region - getPattern
    // ================================

    /**
     * 获取 {@link Pattern} 实例。
     *
     * @param pattern      正则表达式
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern, final boolean cachePattern) {
        return getPattern(pattern, DEFAULT_FLAG, cachePattern);
    }

    /**
     * 获取 {@link Pattern} 实例。
     *
     * @param pattern      正则表达式
     * @param flags        正则表达式匹配标识
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern, final int flags, final boolean cachePattern) {
        checkNotNull(pattern);
        return cachePattern ? cacheAndGetPatternInternal(pattern, flags) : getPatternInternal(pattern, flags);
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     *
     * @param pattern 正则表达式
     * @return {@link Pattern} 实例
     */
    public static Pattern getPattern(final String pattern) {
        return getPattern(pattern, DEFAULT_FLAG);
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     *
     * @param pattern 正则表达式
     * @param flags   正则表达式匹配标识
     * @return {@link Pattern} 实例
     */
    @Nonnull
    public static Pattern getPattern(final String pattern, final int flags) {
        checkNotNull(pattern);
        return getPatternInternal(pattern, flags);
    }

    // ================================
    // #endregion - getPattern
    // ================================

    // ================================
    // #region - matches
    // ================================

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     *
     * @param input   输入
     * @param pattern 正则
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final Pattern pattern) {
        checkNotNull(pattern);
        return matchesInternal(input, pattern);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code patterns} 中的一个。
     *
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchesAny(@Nullable final CharSequence input, final Pattern[] patterns) {
        checkArgument(ArrayTools.isAllElementsNotNull(patterns));
        return matchesAnyInternal(input, patterns);
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     *
     * @param input    输入
     * @param patterns 正则
     * @return 判断结果
     */
    public static boolean matchesAll(@Nullable final CharSequence input, final Pattern[] patterns) {
        checkArgument(ArrayTools.isAllElementsNotNull(patterns));
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
        return matches(input, pattern, DEFAULT_FLAG, cachePattern);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。
     *
     * @param input        输入
     * @param pattern      正则表达式
     * @param flags        正则表达式匹配标识
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final String pattern, final int flags,
            final boolean cachePattern) {
        return matchesInternal(input, getPattern(pattern, flags, cachePattern));
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input, final String pattern) {
        return matches(input, pattern, DEFAULT_FLAG);
    }

    /**
     * 判断 {@code input} 是否匹配 {@code pattern}。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @param flags   正则表达式匹配标识
     * @return 判断结果
     */
    public static boolean matches(@Nullable final CharSequence input,
            final String pattern, final int flags) {
        return matchesInternal(input, getPattern(pattern, flags));
    }

    // ================================
    // #endregion - matches
    // ================================

    // ================================
    // #region - getMatcher
    // ================================

    /**
     * 生成 Matcher。
     *
     * @param input   输入
     * @param pattern 正则
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final Pattern pattern) {
        checkNotNull(input);
        checkNotNull(pattern);
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
        return getMatcher(input, pattern, DEFAULT_FLAG, cachePattern);
    }

    /**
     * 生成 Matcher。
     *
     * @param input        输入
     * @param pattern      正则表达式
     * @param flags        正则表达式匹配标识
     * @param cachePattern 是否缓存 {@link Pattern} 实例
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input,
            final String pattern, final int flags, boolean cachePattern) {
        return getMatcher(input, getPattern(pattern, flags, cachePattern));
    }

    /**
     * 生成 Matcher。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern) {
        return getMatcher(input, pattern, DEFAULT_FLAG);
    }

    /**
     * 生成 Matcher。不缓存 {@link Pattern} 实例。
     *
     * @param input   输入
     * @param pattern 正则表达式
     * @param flags   正则表达式匹配标识
     * @return 结果
     */
    public static Matcher getMatcher(final CharSequence input, final String pattern, final int flags) {
        checkNotNull(input);
        checkNotNull(pattern);
        return getPatternInternal(pattern, flags).matcher(input);
    }

    // ================================
    // #endregion - getMatcher
    // ================================

    // ================================
    // #region - internal methods
    // ================================

    /**
     * 获取 {@link Pattern} 实例。
     *
     * @param pattern      正则表达式
     * @param flags        正则表达式匹配标识
     * @return {@link Pattern} 实例
     */
    @Nonnull
    private static Pattern cacheAndGetPatternInternal(final String pattern, final int flags) {
        final RegexAndFlags regexAndFlags = new RegexAndFlags(pattern, flags);
        return PATTERN_CACHE.getUnchecked(regexAndFlags);
    }

    /**
     * 获取 {@link Pattern} 实例，不缓存。
     *
     * @param pattern 正则表达式
     * @param flags   正则表达式匹配标识
     * @return {@link Pattern} 实例
     */
    @Nonnull
    private static Pattern getPatternInternal(final String pattern, final int flags) {
        final RegexAndFlags regexAndFlags = new RegexAndFlags(pattern, flags);
        return Optional.ofNullable(PATTERN_CACHE.getIfPresent(regexAndFlags))
                .orElseGet(regexAndFlags::compilePattern);
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

    /**
     * 判断 {@code input} 是否匹配至少一个正则。
     *
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    private static boolean matchesAnyInternal(@Nullable final CharSequence input, final Pattern[] patterns) {
        return input != null
                && Arrays.stream(patterns)
                        .anyMatch(pattern -> pattern.matcher(input).matches());
    }

    /**
     * 判断 {@code input} 是否匹配全部正则。
     *
     * @param input    输入
     * @param patterns 正则表达式
     * @return 判断结果
     */
    private static boolean matchesAllInternal(@Nullable final CharSequence input, final Pattern[] patterns) {
        return input != null
                && Arrays.stream(patterns)
                        .allMatch(pattern -> pattern.matcher(input).matches());
    }

    // ================================
    // #endregion - internal methods
    // ================================

    private RegexTools() {
        // 不允许实例化
        throw new IllegalStateException("Utility class");
    }

    // ================================
    // #region - RegexAndFlags
    // ================================

    private static final class RegexAndFlags {
        private final String regex;
        private final int flags;

        private RegexAndFlags(String regex, int flags) {
            this.regex = regex;
            this.flags = flags;
        }

        private Pattern compilePattern() {
            return Pattern.compile(regex, flags);
        }

        @Override
        public int hashCode() {
            return Objects.hash(regex, flags);
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (this == obj)
                return true;
            if (!(obj instanceof RegexAndFlags))
                return false;
            RegexAndFlags other = (RegexAndFlags) obj;
            return Objects.equals(regex, other.regex) && flags == other.flags;
        }
    }

    // ================================
    // #endregion - RegexAndFlags
    // ================================
}
