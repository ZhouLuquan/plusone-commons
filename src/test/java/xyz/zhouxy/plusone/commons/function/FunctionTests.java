package xyz.zhouxy.plusone.commons.function;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

class FunctionTests {

    @Test
    void test() {
        String str = "";
        Predicate<String> predicate = Predicates.<String>of(Objects::nonNull)
                .and(StringUtils::isNotBlank);
        assertFalse(predicate.test(str), "校验应是不通过");
    }
}
