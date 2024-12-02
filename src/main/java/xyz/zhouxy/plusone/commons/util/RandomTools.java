/*
 * Copyright 2023-2024 the original author or authors.
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

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.Nonnull;

public final class RandomTools {

    public static final SecureRandom DEFAULT_SECURE_RANDOM = new SecureRandom();

    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";

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
    public static String randomStr(@Nonnull Random random, @Nonnull char[] sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(random), "Random cannot be null.");
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(random, sourceCharacters, length);
    }

    public static String randomStr(@Nonnull char[] sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    public static String secureRandomStr(@Nonnull char[] sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
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
    public static String randomStr(@Nonnull Random random, @Nonnull String sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(random), "Random cannot be null.");
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(random, sourceCharacters, length);
    }

    public static String randomStr(@Nonnull String sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
        return randomStrInternal(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    public static String secureRandomStr(@Nonnull String sourceCharacters, int length) {
        AssertTools.checkArgument(Objects.nonNull(sourceCharacters), "Source characters cannot be null.");
        AssertTools.checkArgument(length >= 0, "The length should be greater than or equal to zero.");
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
    private static String randomStrInternal(@Nonnull Random random, @Nonnull char[] sourceCharacters, int length) {
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
    private static String randomStrInternal(@Nonnull Random random, @Nonnull String sourceCharacters, int length) {
        if (length == 0) {
            return StringTools.EMPTY_STRING;
        }
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters.charAt(random.nextInt(sourceCharacters.length()));
        }
        return String.valueOf(result);
    }

    private RandomTools() {
        throw new IllegalStateException("Utility class");
    }
}
