package xyz.zhouxy.plusone.commons.util;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class MoreArraysTests {

    private static final Logger log = LoggerFactory.getLogger(MoreArraysTests.class);

    @Test
    void testAsObjectArray() {
        Object[] arr = MoreArrays.asObjectArray("1", "2", 1, 2, new Date());
        log.info("arr: {}", arr.toString());
        log.info("arr: {}", Arrays.toString(arr));
    }
}
