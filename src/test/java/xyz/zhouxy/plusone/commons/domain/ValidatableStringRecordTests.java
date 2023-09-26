package xyz.zhouxy.plusone.commons.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        log.info("{}", Collections.max(usernames,
                Comparator.<Username, String>comparing(o -> o.value().toLowerCase())));
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
