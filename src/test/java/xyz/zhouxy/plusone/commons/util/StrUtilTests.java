package xyz.zhouxy.plusone.commons.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class StrUtilTests {

    private static final Logger log = LoggerFactory.getLogger(StrUtilTests.class);

    @Test
    void testFillZero() {
        char c = '=';
        log.info(StrUtil.fillBefore("1", 6, c));
        log.info(StrUtil.fillBefore("12", 6, c));
        log.info(StrUtil.fillBefore("123", 6, c));
        log.info(StrUtil.fillBefore("1234", 6, c));
        log.info(StrUtil.fillBefore("12345", 6, c));
        log.info(StrUtil.fillBefore("123456", 6, c));
        log.info(StrUtil.fillBefore("1234567", 6, c));
        log.info(StrUtil.fillBefore("12345678", 6, c));
        log.info(StrUtil.fillAfter("1", 6, c));
        log.info(StrUtil.fillAfter("12", 6, c));
        log.info(StrUtil.fillAfter("123", 6, c));
        log.info(StrUtil.fillAfter("1234", 6, c));
        log.info(StrUtil.fillAfter("12345", 6, c));
        log.info(StrUtil.fillAfter("123456", 6, c));
        log.info(StrUtil.fillAfter("1234567", 6, c));
        log.info(StrUtil.fillAfter("12345678", 6, c));
    }
}
