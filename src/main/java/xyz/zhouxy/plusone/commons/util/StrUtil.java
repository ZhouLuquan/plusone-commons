package xyz.zhouxy.plusone.commons.util;

import java.util.Arrays;

public class StrUtil {

    public static String fillBefore(String src, int minLength, char c) {
        if (src.length() >= minLength) {
            return src;
        }
        char[] result = new char[minLength];
        Arrays.fill(result, c);
        for (int i = 1; i <= src.length(); i++) {
            result[minLength - i] = src.charAt(src.length() - i);
        }
        return String.valueOf(result);
    }

    public static String fillAfter(String src, int length, char c) {
        if (src.length() >= length) {
            return src;
        }
        char[] result = new char[length];
        Arrays.fill(result, c);
        for (int i = 0; i < src.length(); i++) {
            result[i] = src.charAt(i);
        }
        return String.valueOf(result);
    }

    private StrUtil() {
        throw new IllegalStateException("Utility class");
    }
}
