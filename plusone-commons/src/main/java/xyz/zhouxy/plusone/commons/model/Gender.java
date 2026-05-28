/*
 * Copyright 2024-present ZhouXY
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.model;

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkCondition;

import xyz.zhouxy.plusone.commons.base.IWithIntCode;

/**
 * 性别
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 */
public enum Gender implements IWithIntCode {
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

    /**
     * 根据码值获取对应枚举
     *
     * @param value 码值
     * @return 枚举值
     */
    public static Gender of(int value) {
        checkCondition(0 <= value && value < VALUES.length,
                () -> new EnumConstantNotPresentException(Gender.class, String.valueOf(value)));
        return VALUES[value];
    }

    /**
     * 获取枚举码值
     *
     * @return 码值
     */
    public int getValue() {
        return value;
    }

    /**
     * 枚举名称
     *
     * @return 枚举名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 枚举中文名称
     *
     * @return 枚举中文名称
     */
    public String getDisplayNameZh() {
        return displayNameZh;
    }

    @Override
    public int getCode() {
        return getValue();
    }

}
