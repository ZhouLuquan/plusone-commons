package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class RestfulResultTest {

    @Test
    void testSuccessIf() {
        String str = null;
        RestfulResult result = RestfulResult.successIf(str != null).orError();
        log.info(result.toString());
        assertEquals(RestfulResult.DEFAULT_ERROR_STATUS, result.getStatus());

        result = RestfulResult.successIf(str != null, "成功")
                .orError(2333, "失败");
        log.info(result.toString());
        assertEquals(2333, result.getStatus());
        assertEquals("失败", result.getMessage());

        str = "";
        result = RestfulResult.successIf(str != null).orError();
        log.info(result.toString());
        assertEquals(RestfulResult.SUCCESS_STATUS, result.getStatus());

        result = RestfulResult.successIf(str != null, "成功", str)
                .orError(2333, "失败");
        log.info(result.toString());
        assertEquals("成功", result.getMessage());
    }
}
