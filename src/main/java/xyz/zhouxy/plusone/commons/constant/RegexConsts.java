package xyz.zhouxy.plusone.commons.constant;

/**
 * 正则表达式常量
 *
 * @author <a href="https://gitee.com/zhouxy108">ZhouXY</a>
 */
public final class RegexConsts {

    public static final String DATE = "^\\d{4}-\\d{2}-\\d{2}";

    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w\\\\!#$%&'*\\+\\-/=?^`{|}~@\\(\\)\\[\\]\",\\.;':><]{8,32}$";

    public static final String CAPTCHA = "^[0-9A-Za-z]{4,6}$";

    public static final String EMAIL = "^\\w+([-+.]\\w+)*@[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})*(\\.(?![0-9]+$)[a-zA-Z0-9][-0-9A-Za-z]{0,62})$";

    public static final String MOBILE_PHONE = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static final String USERNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    public static final String NICKNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    private RegexConsts() {
        throw new IllegalStateException("Utility class");
    }
}
