package xyz.zhouxy.plusone.util;

import java.util.regex.Pattern;

import cn.hutool.core.util.ReUtil;

public class RegexUtil {

    public static boolean matches(CharSequence input, String regex) {
        return ReUtil.isMatch(regex, input);
    }

    public static boolean matches(CharSequence input, Pattern regex) {
        return ReUtil.isMatch(regex, input);
    }

    public static boolean matchesOr(CharSequence input, String... regexs) {
        boolean isMatched;
        for (String regex : regexs) {
            isMatched = matches(input, regex);
            if (isMatched) {
                return true;
            }
        }
        return false;
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

    public static boolean matchesAnd(CharSequence input, String... regexs) {
        boolean isMatched;
        for (String regex : regexs) {
            isMatched = matches(input, regex);
            if (!isMatched) {
                return false;
            }
        }
        return true;
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
