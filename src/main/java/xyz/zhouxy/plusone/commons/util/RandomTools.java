package xyz.zhouxy.plusone.commons.util;

import java.security.SecureRandom;

public final class RandomTools {
    private RandomTools() {
        throw new IllegalStateException("Utility class");
    }

    public static String secureRandomStr(char[] sourceCharacters, int length) {
        SecureRandom random = new SecureRandom();
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = sourceCharacters[random.nextInt(sourceCharacters.length)];
        }
        return String.valueOf(result);
    }
}
