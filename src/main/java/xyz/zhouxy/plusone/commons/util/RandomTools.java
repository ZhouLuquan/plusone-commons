package xyz.zhouxy.plusone.commons.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    public static String randomStr(Random random, char[] sourceCharacters, int length) {
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters[random.nextInt(sourceCharacters.length)];
        }
        return String.valueOf(result);
    }

    public static String randomStr(char[] sourceCharacters, int length) {
        return randomStr(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    public static String secureRandomStr(char[] sourceCharacters, int length) {
        return randomStr(DEFAULT_SECURE_RANDOM, sourceCharacters, length);
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
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters.charAt(random.nextInt(sourceCharacters.length()));
        }
        return String.valueOf(result);
    }

    public static String randomStr(String sourceCharacters, int length) {
        return randomStr(ThreadLocalRandom.current(), sourceCharacters, length);
    }

    public static String secureRandomStr(String sourceCharacters, int length) {
        return randomStr(DEFAULT_SECURE_RANDOM, sourceCharacters, length);
    }

    private RandomTools() {
        throw new IllegalStateException("Utility class");
    }
}
