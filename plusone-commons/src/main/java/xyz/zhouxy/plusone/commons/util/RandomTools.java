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
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgumentNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/**
 * 随机工具类
 *
 * @author ZhouXY
 */
public final class RandomTools {

    private static final SecureRandom DEFAULT_SECURE_RANDOM;

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        }
        catch (NoSuchAlgorithmException e) {
            secureRandom = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        DEFAULT_SECURE_RANDOM = secureRandom;
    }

    /**
     * 大写字母
     */
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 小写字母
     */
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 数字
     */
    public static final String NUMBERS = "0123456789";

    /**
     * 默认的 {@code SecureRandom}
     *
     * @return 默认的 {@code SecureRandom}
     */
    public static SecureRandom defaultSecureRandom() {
        return DEFAULT_SECURE_RANDOM;
    }

    /**
     * 当前线程的 {@code ThreadLocalRandom}
     *
     * @return 当前线程的 {@code ThreadLocalRandom}
     */
    public static ThreadLocalRandom currentThreadLocalRandom() {
        return ThreadLocalRandom.current();
    }

    // ================================
    // #region - randomStr
    // ================================

    /**
     * 使用传入的随机数生成器，生成指定长度的字符串
     *
     * @param random           随机数生成器。根据需要可以传入
     *                         {@link java.util.concurrent.ThreadLocalRandom}、{@link java.security.SecureRandom}
     *                         等，不为空
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String randomStr(Random random, char[] sourceCharacters, int length) {
        checkArgumentNotNull(random, "Random cannot be null.");
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(random, sourceCharacters, length);
    }

    /**
     * 使用当前线程的 {@code ThreadLocalRandom}，生成指定长度的字符串
     *
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String randomStr(char[] sourceCharacters, int length) {
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    /**
     * 使用默认的 {@code SecureRandom}，生成指定长度的字符串
     *
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String secureRandomStr(char[] sourceCharacters, int length) {
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(DEFAULT_SECURE_RANDOM, sourceCharacters, length);
    }

    /**
     * 使用传入的随机数生成器，生成指定长度的字符串
     *
     * @param random           随机数生成器。根据需要可以传入
     *                         {@link java.util.concurrent.ThreadLocalRandom}、{@link java.security.SecureRandom}
     *                         等，不为空
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String randomStr(Random random, String sourceCharacters, int length) {
        checkArgumentNotNull(random, "Random cannot be null.");
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(random, sourceCharacters, length);
    }

    /**
     * 使用当前线程的 {@code ThreadLocalRandom}，生成指定长度的字符串
     *
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String randomStr(String sourceCharacters, int length) {
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    /**
     * 使用默认的 {@code SecureRandom}，生成指定长度的字符串
     *
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    public static String secureRandomStr(String sourceCharacters, int length) {
        checkArgumentNotNull(sourceCharacters, "Source characters cannot be null.");
        checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(DEFAULT_SECURE_RANDOM, sourceCharacters, length);
    }

    // ================================
    // #endregion - randomStr
    // ================================

    // ================================
    // #region - randomInt
    // ================================

    /**
     * 使用传入的随机数生成器，生成随机整数
     *
     * @param random 随机数生成器。根据需要传入
     * @param startInclusive 最小值（包含）
     * @param endExclusive 最大值（不包含）
     * @return 在区间 {@code [min, max)} 内的随机整数
     *
     * @since 1.1.0
     */
    public static int randomInt(Random random, int startInclusive, int endExclusive) {
        checkArgumentNotNull(random, "Random cannot be null.");
        checkArgument(startInclusive < endExclusive, "Start value must be less than end value.");
        return randomIntInternal(random, startInclusive, endExclusive);
    }

    /**
     * 使用当前线程的 {@code ThreadLocalRandom}，生成随机整数
     *
     * @param startInclusive 最小值（包含）
     * @param endExclusive 最大值（不包含）
     * @return 在区间 {@code [min, max)} 内的随机整数
     *
     * @since 1.1.0
     */
    public static int randomInt(int startInclusive, int endExclusive) {
        checkArgument(startInclusive < endExclusive, "Start value must be less than end value.");
        return randomIntInternal(ThreadLocalRandom.current(), startInclusive, endExclusive);
    }

    /**
     * 使用默认的 {@code SecureRandom}，生成随机整数
     *
     * @param startInclusive 最小值（包含）
     * @param endExclusive 最大值（不包含）
     * @return 在区间 {@code [min, max)} 内的随机整数
     *
     * @since 1.1.0
     */
    public static int secureRandomInt(int startInclusive, int endExclusive) {
        checkArgument(startInclusive < endExclusive, "Start value must be less than end value.");
        return randomIntInternal(DEFAULT_SECURE_RANDOM, startInclusive, endExclusive);
    }

    /**
     * 使用传入的随机数生成器，生成随机整数
     *
     * @param random 随机数生成器
     * @param range 整数区间
     * @return 在指定区间内的随机整数
     *
     * @since 1.1.0
     */
    public static int randomInt(Random random, Range<Integer> range) {
        checkArgumentNotNull(random, "Random cannot be null.");
        checkArgumentNotNull(range, "Range cannot be null.");
        return randomIntInternal(random, range);
    }

    /**
     * 使用当前线程的 {@code ThreadLocalRandom}，生成随机整数
     *
     * @param range 整数区间
     * @return 在指定区间内的随机整数
     *
     * @since 1.1.0
     */
    public static int randomInt(Range<Integer> range) {
        checkArgumentNotNull(range, "Range cannot be null.");
        return randomIntInternal(ThreadLocalRandom.current(), range);
    }

    /**
     * 使用默认的 {@code SecureRandom}，生成随机整数
     *
     * @param range 整数区间
     * @return 在指定区间内的随机整数
     *
     * @since 1.1.0
     */
    public static int secureRandomInt(Range<Integer> range) {
        checkArgumentNotNull(range, "Range cannot be null.");
        return randomIntInternal(DEFAULT_SECURE_RANDOM, range);
    }

    // ================================
    // #endregion - randomInt
    // ================================

    // ================================
    // #region - private methods
    // ================================

    /**
     * 使用传入的随机数生成器，生成指定长度的字符串
     *
     * @param random           随机数生成器。根据需要可以传入
     *                         {@link java.util.concurrent.ThreadLocalRandom}、{@link java.security.SecureRandom}
     *                         等，不为空
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    private static String randomStrInternal(Random random, char[] sourceCharacters, int length) {
        if (length == 0) {
            return StringTools.EMPTY_STRING;
        }
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters[random.nextInt(sourceCharacters.length)];
        }
        return String.valueOf(result);
    }

    /**
     * 使用传入的随机数生成器，生成指定长度的字符串
     *
     * @param random           随机数生成器。根据需要可以传入
     *                         {@link java.util.concurrent.ThreadLocalRandom}、{@link java.security.SecureRandom}
     *                         等，不为空
     * @param sourceCharacters 字符池。字符串的字符将在数组中选，不为空
     * @param length           字符串长度
     * @return 随机字符串
     */
    private static String randomStrInternal(Random random, String sourceCharacters, int length) {
        if (length == 0) {
            return StringTools.EMPTY_STRING;
        }
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters.charAt(random.nextInt(sourceCharacters.length()));
        }
        return String.valueOf(result);
    }

    /**
     * 使用传入的随机数生成器，生成随机整数
     *
     * @param startInclusive 最小值（包含）
     * @param endExclusive 最大值（不包含）
     * @return 在区间 {@code [min, max)} 内的随机整数
     */
    private static int randomIntInternal(Random random, int startInclusive, int endExclusive) {
        return random.nextInt(endExclusive - startInclusive) + startInclusive;
    }

    /**
     * 使用传入的随机数生成器，生成随机整数
     *
     * @param range 整数区间
     * @return 在指定区间内的随机整数
     */
    private static int randomIntInternal(Random random, Range<Integer> range) {
        Integer lowerEndpoint = range.lowerEndpoint();
        Integer upperEndpoint = range.upperEndpoint();
        int min = range.lowerBoundType() == BoundType.CLOSED ? lowerEndpoint : lowerEndpoint + 1;
        int max = range.upperBoundType() == BoundType.OPEN ? upperEndpoint : upperEndpoint + 1;
        return random.nextInt(max - min) + min;
    }

    // ================================
    // #endregion - private methods
    // ================================

    private RandomTools() {
        throw new IllegalStateException("Utility class");
    }
}
