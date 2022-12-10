package xyz.zhouxy.plusone.util;

import java.util.regex.Pattern;

public class RegexUtil {

    private RegexUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean matches(CharSequence input, String regex) {
        return Pattern.matches(regex, input);
    }

    public static boolean matchesOr(CharSequence input, String... regexs) {
        boolean isMatched;
        for (String regex : regexs) {
            isMatched = Pattern.matches(regex, input);
            if (isMatched) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchesAnd(CharSequence input, String... regexs) {
        boolean isMatched;
        for (String regex : regexs) {
            isMatched = Pattern.matches(regex, input);
            if (!isMatched) {
                return false;
            }
        }
        return true;
    }
}
