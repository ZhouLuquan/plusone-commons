/*
 * Copyright 2022-2023 the original author or authors.
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

    public static final String DATE = "^\\d{4}-\\d{2}-\\d{2}";

    public static final String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[\\w\\\\!#$%&'*\\+\\-/=?^`{|}~@\\(\\)\\[\\]\",\\.;':><]{8,32}$";

    public static final String CAPTCHA = "^[0-9A-Za-z]{4,6}$";

    public static final String EMAIL = "^\\w+([-+.]\\w+)*@[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})*(\\.(?![0-9]+$)[a-zA-Z0-9][-0-9A-Za-z]{0,62})$";

    public static final String MOBILE_PHONE = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static final String USERNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    public static final String NICKNAME = "^[\\da-zA-Z_.@\\\\]{4,36}$";

    private RegexConsts() {
        throw new IllegalStateException("Utility class");
    }
}
