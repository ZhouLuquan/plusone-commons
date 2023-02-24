package xyz.zhouxy.plusone.commons.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式常量
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public final class PatternConsts {

    public static final Pattern DATE = Pattern.compile(RegexConsts.DATE);

    public static final Pattern PASSWORD = Pattern.compile(RegexConsts.PASSWORD);

    public static final Pattern CAPTCHA = Pattern.compile(RegexConsts.CAPTCHA);

    public static final Pattern EMAIL = Pattern.compile(RegexConsts.EMAIL);

    public static final Pattern MOBILE_PHONE = Pattern.compile(RegexConsts.MOBILE_PHONE);

    public static final Pattern USERNAME = Pattern.compile(RegexConsts.USERNAME);

    public static final Pattern NICKNAME = Pattern.compile(RegexConsts.NICKNAME);

    private PatternConsts() {
        throw new IllegalStateException("Utility class");
    }
}
