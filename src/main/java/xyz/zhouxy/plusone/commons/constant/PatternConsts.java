/*
 * Copyright 2022-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.constant;

import java.util.regex.Pattern;

/**
 * 正则表达式常量
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
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
