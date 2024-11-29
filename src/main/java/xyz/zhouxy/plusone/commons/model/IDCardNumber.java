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

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

import xyz.zhouxy.plusone.commons.util.AssertTools;

/**
 * 身份证号
 */
public abstract class IDCardNumber {

    @Nonnull
    private final String value;

    private static final char DEFAULT_REPLACED_CHAR = '*';
    private static final int DEFAULT_DISPLAY_FRONT = 1;
    private static final int DEFAULT_DISPLAY_END = 2;

    protected IDCardNumber(String value) {
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    /**
     * 根据身份证号判断性别
     */
    public abstract Gender getGender();

    /**
     * 获取出生日期
     */
    public abstract LocalDate getBirthDate();

    /** 计算年龄 */
    public final int calculateAge() {
        LocalDate now = LocalDate.now();
        return Period.between(getBirthDate(), now).getYears();
    }

    // ================================
    // #region - toString
    // ================================

    @Override
    public String toString() {
        return toDesensitizedString();
    }

    public String toDesensitizedString() {
        return toDesensitizedString(DEFAULT_REPLACED_CHAR, DEFAULT_DISPLAY_FRONT, DEFAULT_DISPLAY_END);
    }

    public String toDesensitizedString(int front, int end) {
        return toDesensitizedString(DEFAULT_REPLACED_CHAR, front, end);
    }

    public String toDesensitizedString(char replacedChar, int front, int end) {
        AssertTools.checkArgument(front >= 0 && end >= 0);
        AssertTools.checkArgument((front + end) <= this.value.length(), "需要截取的长度不能大于身份证号长度");
        final char[] charArray = getValue().toCharArray();
        for (int i = front; i < charArray.length - end; i++) {
            charArray[i] = replacedChar;
        }
        return String.valueOf(charArray);
    }

    // ================================
    // #endregion - toString
    // ================================
}
