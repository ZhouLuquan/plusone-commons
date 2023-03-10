package xyz.zhouxy.plusone.commons.util;

import java.util.regex.Pattern;

public class RegexUtil {

    public static boolean matches(CharSequence input, Pattern regex) {
        return regex.matcher(input).matches();
    }

    public static boolean matchesOr(CharSequence input, Pattern... regexs) {
        boolean isMatched;
        for (Pattern regex : regexs) {
            isMatched = matches(input, regex);
            if (isMatched) {
                return true;
            }
        }
        return false;
    }

    public static boolean matchesAnd(CharSequence input, Pattern... regexs) {
        boolean isMatched;
        for (Pattern regex : regexs) {
            isMatched = matches(input, regex);
            if (!isMatched) {
                return false;
            }
        }
        return true;
    }

    private RegexUtil() {
        throw new IllegalStateException("Utility class");
    }
}
