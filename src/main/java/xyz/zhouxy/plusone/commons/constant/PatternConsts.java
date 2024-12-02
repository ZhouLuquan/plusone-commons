/*
 * Copyright 2023-2024 the original author or authors.
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
 * @see RegexConsts
 * @see xyz.zhouxy.plusone.commons.util.RegexTools
 */
public final class PatternConsts {

    /** yyyyMMdd */
    public static final Pattern BASIC_ISO_DATE = Pattern.compile(RegexConsts.BASIC_ISO_DATE);

    /** yyyy-MM-dd */
    public static final Pattern ISO_LOCAL_DATE = Pattern.compile(RegexConsts.ISO_LOCAL_DATE);

    /** 密码 */
    public static final Pattern PASSWORD = Pattern.compile(RegexConsts.PASSWORD);

    /** 验证码 */
    public static final Pattern CAPTCHA = Pattern.compile(RegexConsts.CAPTCHA);

    /** 邮箱地址 */
    public static final Pattern EMAIL = Pattern.compile(RegexConsts.EMAIL);

    /** 中国大陆手机号 */
    public static final Pattern MOBILE_PHONE = Pattern.compile(RegexConsts.MOBILE_PHONE);

    /** 用户名 */
    public static final Pattern USERNAME = Pattern.compile(RegexConsts.USERNAME);

    /** 昵称 */
    public static final Pattern NICKNAME = Pattern.compile(RegexConsts.NICKNAME);

    /** 中国第二代居民身份证 */
    public static final Pattern CHINESE_2ND_ID_CARD_NUMBER
            = Pattern.compile(RegexConsts.CHINESE_2ND_ID_CARD_NUMBER);

    private PatternConsts() {
        throw new IllegalStateException("Utility class");
    }
}
