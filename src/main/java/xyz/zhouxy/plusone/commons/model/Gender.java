package xyz.zhouxy.plusone.commons.model;

import xyz.zhouxy.plusone.commons.util.AssertTools;

/**
 * 性别
 */
public enum Gender {
    UNKNOWN(0, "Unknown", "未知"),
    MALE(1, "Male", "男"),
    FEMALE(2, "Female", "女"),

    ;

    private static final Gender[] VALUES = new Gender[] { UNKNOWN, MALE, FEMALE };

    private final int value;
    private final String displayName;
    private final String displayNameZh;

    Gender(int value, String displayName, String displayNameZh) {
        this.value = value;
        this.displayName = displayName;
        this.displayNameZh = displayNameZh;
    }

    public static Gender of(int value) {
        AssertTools.checkCondition(0 <= value && value < VALUES.length,
                () -> new EnumConstantNotPresentException(Gender.class, String.valueOf(value)));
        return VALUES[value];
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayNameZh() {
        return displayNameZh;
    }

}
