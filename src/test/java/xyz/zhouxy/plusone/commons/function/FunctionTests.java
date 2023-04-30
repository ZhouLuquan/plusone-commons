package xyz.zhouxy.plusone.commons.function;

import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import xyz.zhouxy.plusone.commons.util.Assert;

class FunctionTests {

    @Test
    void test() {
        String str = "";
        Predicate<String> predicate = Predicates.<String>of(Objects::nonNull)
                .and(StringUtils::isNotEmpty);
        Assert.isFalse(predicate.test(str), "校验应是不通过");
    }
}
