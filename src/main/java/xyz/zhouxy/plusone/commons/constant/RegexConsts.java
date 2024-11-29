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

/**
 * 正则表达式常量
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
public final class RegexConsts {

    // TODO 【优化】 根据需要添加 group

    // TODO 【添加】 新增身份证等正则常量

    public static final String BASIC_ISO_DATE = "^(?<y>\\d{4})(?<M>\\d{2})(?<d>\\d{2})";

    public static final String ISO_LOCAL_DATE = "^(?<y>\\d{4})-(?<M>\\d{2})-(?<d>\\d{2})";

    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w\\\\!#$%&'*\\+\\-/=?^`{|}~@\\(\\)\\[\\]\",\\.;':><]{8,32}$";

    public static final String CAPTCHA = "^[0-9A-Za-z]{4,6}$";

    public static final String EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static final String MOBILE_PHONE = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static final String USERNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    public static final String NICKNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    private RegexConsts() {
        throw new IllegalStateException("Utility class");
    }
}
