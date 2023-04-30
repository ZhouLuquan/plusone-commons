package xyz.zhouxy.plusone.commons.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.zhouxy.plusone.commons.annotation.StaticFactoryMethod;
import xyz.zhouxy.plusone.commons.annotation.ValueObject;
import xyz.zhouxy.plusone.commons.constant.PatternConsts;

class ValidatableStringRecordTests {

    private static final Logger log = LoggerFactory.getLogger(ValidatableStringRecordTests.class);

    @Test
    void test() {
        Username username = Username.of("ZhouXY");
        assertNotNull(username);
        String usernameStr = username.value();
        assertNotNull(usernameStr);
        log.info("usernameStr: {}", usernameStr);
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
