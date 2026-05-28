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

package xyz.zhouxy.plusone.commons.util;

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;
import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgumentNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.constant.PatternConsts;

/**
 * StringTools
 *
 * <p>
 * 字符串工具类。
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public class StringTools {

    public static final String EMPTY_STRING = "";

    /**
     * 判断字符串是否非空白
     *
     * <pre>
     * StringTools.isNotBlank(null);    // false
     * StringTools.isNotBlank("");      // false
     * StringTools.isNotBlank("   ");   // false
     * StringTools.isNotBlank("Hello"); // true
     * </pre>
     *
     * @param cs 检查的字符串
     * @return 是否非空白
     */
    public static boolean isNotBlank(@Nullable final String cs) {
        if (cs == null || cs.isEmpty()) {
            return false;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否空白字符串
     *
     * <pre>
     * StringTools.isBlank(null);    // true
     * StringTools.isBlank("");      // true
     * StringTools.isBlank("   ");   // true
     * StringTools.isBlank("Hello"); // false
     * </pre>
     *
     * @param cs 检查的字符串
     * @return 是否空白
     * @since 1.1.0
     */
    public static boolean isBlank(@Nullable String cs) {
        if (cs == null || cs.isEmpty()) {
            return true;
        }
        for (int i = 0; i < cs.length(); i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 重复字符串
     *
     * @param str   要重复的字符串
     * @param times 重复次数
     * @return 结果
     */
    public static String repeat(String str, int times) {
        return repeat(str, times, Integer.MAX_VALUE);
    }

    /**
     * 重复字符串
     *
     * @param str       要重复的字符串
     * @param times     重复次数
     * @param maxLength 最大长度
     * @return 结果
     */
    public static String repeat(final String str, int times, int maxLength) {
        checkArgumentNotNull(str);
        return String.valueOf(ArrayTools.repeat(str.toCharArray(), times, maxLength));
    }

    /**
     * 判断字符串是否非空
     *
     * <pre>
     * StringTools.isNotEmpty(null);    // false
     * StringTools.isNotEmpty("");      // false
     * StringTools.isNotEmpty("   ");   // true
     * StringTools.isNotEmpty("Hello"); // true
     * </pre>
     *
     * @param cs 检查的字符串
     * @return 是否非空
     * @since 1.1.0
     */
    public static boolean isNotEmpty(@Nullable final String cs) {
        return cs != null && !cs.isEmpty();
    }

    /**
     * 判断字符串是否为空字符串
     *
     * <pre>
     * StringTools.isEmpty(null);    // true
     * StringTools.isEmpty("");      // true
     * StringTools.isEmpty("   ");   // false
     * StringTools.isEmpty("Hello"); // false
     * </pre>
     *
     * @param cs 检查的字符串
     * @return 是否空字符串
     * @since 1.1.0
     */
    public static boolean isEmpty(@Nullable final String cs) {
        return cs == null || cs.isEmpty();
    }

    /**
     * 判断字符串是否为邮箱地址
     *
     * @param cs 检查的字符串
     * @return 是否是邮箱地址
     * @since 1.1.0
     *
     * @see PatternConsts#EMAIL
     */
    @Beta
    public static boolean isEmail(@Nullable final String cs) {
        return RegexTools.matches(cs, PatternConsts.EMAIL);
    }

    /**
     * 判断字符串是否为 URL 地址
     *
     * @param cs 检查的字符串
     * @return 是否是 URL
     * @since 1.1.0
     *
     * @see URL
     */
    @Beta
    public static boolean isURL(@Nullable final String cs) {
        if (cs == null) {
            return false;
        }
        try {
            new URL(cs);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    /**
     * 脱敏
     *
     * @param src          原字符串
     * @param front        前面保留的字符数
     * @param end          后面保留的字符数
     * @return 脱敏结果
     * @since 1.1.0
     */
    public static String desensitize(@Nullable final String src, int front, int end) {
        return desensitize(src, '*', front, end);
    }

    /**
     * 脱敏
     *
     * @param src          原字符串
     * @param replacedChar 用于替换的字符
     * @param front        前面保留的字符数
     * @param end          后面保留的字符数
     * @return 脱敏结果
     * @since 1.1.0
     */
    public static String desensitize(@Nullable final String src, char replacedChar, int front, int end) {
        if (src == null || src.isEmpty()) {
            return EMPTY_STRING;
        }
        checkArgument(front >= 0 && end >= 0);
        checkArgument((front + end) <= src.length(), "需要截取的长度不能大于原字符串长度");
        final char[] charArray = src.toCharArray();
        for (int i = front; i < charArray.length - end; i++) {
            charArray[i] = replacedChar;
        }
        return String.valueOf(charArray);
    }

    /**
     * 转换为带引号的字符串
     *
     * @param value 值
     * @return 带引号的字符串
     * @since 1.1.0
     */
    public static String toQuotedString(@Nullable String value) {
        if (value == null) {
            return "null";
        }
        if (value.isEmpty()) {
            return "\"\"";
        }
        return "\"" + value + "\"";
    }

    private StringTools() {
        throw new IllegalStateException("Utility class");
    }
}
