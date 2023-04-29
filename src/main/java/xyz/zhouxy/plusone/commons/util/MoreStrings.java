package xyz.zhouxy.plusone.commons.util;

import javax.annotation.Nullable;

public class MoreStrings {
    public static boolean hasText(@Nullable String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private MoreStrings() {
        throw new IllegalStateException("Utility class");
    }
}
