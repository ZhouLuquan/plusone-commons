/*
 * Copyright 2024 the original author or authors.
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
