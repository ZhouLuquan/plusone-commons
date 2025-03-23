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

package xyz.zhouxy.plusone.commons.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.constant.PatternConsts;

/**
 * StringTools
 *
 * <p>
 * 字符串工具类。
 * </p>
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public class StringTools {

    public static final String EMPTY_STRING = "";

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

    public static String repeat(String str, int times) {
        return repeat(str, times, Integer.MAX_VALUE);
    }

    public static String repeat(final String str, int times, int maxLength) {
        AssertTools.checkArgument(Objects.nonNull(str));
        return String.valueOf(ArrayTools.repeat(str.toCharArray(), times, maxLength));
    }

    public static boolean isNotEmpty(@Nullable final String cs) {
        return cs != null && !cs.isEmpty();
    }

    public static boolean isEmpty(@Nullable final String cs) {
        return cs == null || cs.isEmpty();
    }

    @Beta
    public static boolean isEmail(@Nullable final String cs) {
        return RegexTools.matches(cs, PatternConsts.EMAIL);
    }

    @Beta
    public static boolean isURL(@Nullable final String cs) {
        try {
            new URL(cs);
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }

    private StringTools() {
        throw new IllegalStateException("Utility class");
    }
}
