package xyz.zhouxy.plusone.commons.function;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import xyz.zhouxy.plusone.commons.util.Assert;

class FunctionTests {

    @Test
    void test() {
        String str = "";
        Predicate<String> predicate = new Predicates<String>().of(this::nonNull)
                .or(StringUtils::isNotBlank);
        Assert.isTrue(predicate.test(str), "未通过");
    }

    boolean nonNull(Object obj) {
        return obj != null;
    }
}
