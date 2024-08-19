package xyz.zhouxy.plusone.commons.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.annotation.ValueObject;
import xyz.zhouxy.plusone.commons.constant.PatternConsts;

import java.io.Serializable;
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
                Username.of("Code108")
        );
        log.info("{}", Collections.max(usernames));
        Function<Username, String> compare = o -> o.value().toLowerCase();
        log.info("{}", Collections.max(usernames, Comparator.comparing(compare)));
    }

    @Test
    void testSerial() {
        User obj = new User(Username.of("zhouxy"), Email.of("zhouxy@outlook.com"));
        User snapshot = ObjectUtil.clone(obj);
        obj.setUsername(Username.of("ZhouXY108"));
        log.info("snapshot: {}", snapshot);
        log.info("obj: {}", obj);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class User implements Serializable {
    private static final long serialVersionUID = 3549288698636099823L;
    Username username;
    Email email;
}

@ValueObject
class Email extends ValidatableStringRecord {
    private static final long serialVersionUID = -2092385577843676401L;

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
    private static final long serialVersionUID = -7105647514140482394L;

    private Username(String username) {
        super(username, PatternConsts.USERNAME);
    }

    @StaticFactoryMethod(Username.class)
    public static Username of(String username) {
        return new Username(username);
    }
}
