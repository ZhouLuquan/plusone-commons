/*
 * Copyright 2024-2025 the original author or authors.
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

import xyz.zhouxy.plusone.commons.util.StringTools;

/**
 * 身份证号
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public interface IDCardNumber {

    char DEFAULT_REPLACED_CHAR = '*';
    int DEFAULT_DISPLAY_FRONT = 1;
    int DEFAULT_DISPLAY_END = 2;

    /**
     * 身份证号
     *
     * @return 身份证号
     */
    String value();

    /**
     * 根据身份证号判断性别
     *
     * @return {@link Gender} 对象
     */
    Gender getGender();

    /**
     * 获取出生日期
     *
     * @return 出生日期
     */
    LocalDate getBirthDate();

    /**
     * 计算年龄
     *
     * @return 年龄
     */
    default int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(getBirthDate(), now).getYears();
    }

    // ================================
    // #region - toString
    // ================================

    /**
     * 脱敏字符串（前面留 1 位，后面留 2 位）
     *
     * @return 脱敏字符串
     */
    default String toDesensitizedString() {
        return StringTools.desensitize(value(), DEFAULT_REPLACED_CHAR, DEFAULT_DISPLAY_FRONT, DEFAULT_DISPLAY_END);
    }

    /**
     * 脱敏字符串
     *
     * @param front 前面保留的字符数
     * @param end   后面保留的字符数
     * @return 脱敏字符串
     */
    default String toDesensitizedString(int front, int end) {
        return StringTools.desensitize(value(), DEFAULT_REPLACED_CHAR, front, end);
    }

    /**
     * 脱敏字符串
     *
     * @param replacedChar 替换字符
     * @param front        前面保留的字符数
     * @param end          后面保留的字符数
     * @return 脱敏字符串
     */
    default String toDesensitizedString(char replacedChar, int front, int end) {
        return StringTools.desensitize(value(), replacedChar, front, end);
    }

    // ================================
    // #endregion - toString
    // ================================
}
