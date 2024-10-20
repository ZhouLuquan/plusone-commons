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

package xyz.zhouxy.plusone.commons.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.annotation.ValueObject;
import xyz.zhouxy.plusone.commons.constant.PatternConsts;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

class ValidatableStringRecordTests {

    private static final Logger log = LoggerFactory.getLogger(ValidatableStringRecordTests.class);

    @Test
    void test() {
        Username username = Username.of("ZhouXY");
        assertNotNull(username);
        String usernameStr = username.value();
        assertNotNull(usernameStr);
        log.info("usernameStr: {}", usernameStr);

        List<Username> usernames = Arrays.asList(
                Username.of("ZhouXY108"),
                Username.of("code_108"),
                Username.of("Luquan"),
                Username.of("Code108"));
        log.info("{}", Collections.max(usernames));
        Function<Username, String> compare = o -> o.value().toLowerCase();
        log.info("{}", Collections.max(usernames, Comparator.comparing(compare)));
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class User {
    Username username;
    Email email;

    static class Diff {
        public static DiffResult<User> diff(User left, User right) {
            return DiffBuilder.<User>builder()
                    .setLeft(left)
                    .setRight(right)
                    .setStyle(ToStringStyle.JSON_STYLE)
                    .build()
                    .append("username", left.username, right.username)
                    .append("email", left.email, right.email)
                    .build();
        }
    }
}

@ValueObject
class Email extends ValidatableStringRecord {
    private Email(String value) {
        super(value, PatternConsts.EMAIL);
    }

    @StaticFactoryMethod(Email.class)
    public static Email of(String value) {
        return new Email(value);
    }
}

@ValueObject
class Username extends ValidatableStringRecord {
    private Username(String username) {
        super(username, PatternConsts.USERNAME);
    }

    @StaticFactoryMethod(Username.class)
    public static Username of(String username) {
        return new Username(username);
    }
}
