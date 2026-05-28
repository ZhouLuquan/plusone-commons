/*
 * Copyright 2022-present ZhouXY
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

/**
 * 正则表达式常量
 *
 * @author ZhouXY
 * @see PatternConsts
 */
public final class RegexConsts {

    public static final String BASIC_ISO_DATE = "^(?<yyyy>\\d{4,9})(?<MM>\\d{2})(?<dd>\\d{2})";

    public static final String ISO_LOCAL_DATE = "^(?<yyyy>\\d{4,9})-(?<MM>\\d{2})-(?<dd>\\d{2})";

    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w\\\\!#$%&'*\\+\\-/=?^`{|}~@\\(\\)\\[\\]\",\\.;':><]{8,32}$";

    public static final String CAPTCHA = "^\\w{4,6}$";

    /**
     * from https://emailregex.com/
     */
    public static final String EMAIL
            = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
            + "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static final String MOBILE_PHONE = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static final String USERNAME = "^[\\w-_.@]{4,36}$";

    public static final String NICKNAME = "^[\\w-_.@]{4,36}$";

    public static final String CHINESE_2ND_ID_CARD_NUMBER
            = "^(?<county>(?<city>(?<province>\\d{2})\\d{2})\\d{2})(?<birthDate>\\d{8})\\d{2}(?<gender>\\d)([\\dX])$";

    private RegexConsts() {
        throw new IllegalStateException("Utility class");
    }
}
