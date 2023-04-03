package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RestfulResultTest {

    @Test
    void testSuccessIf() {
        String str = null;
        RestfulResult result = RestfulResult.successIf(str != null, "成功")
                .orError();
        System.out.println(result);
        assertEquals(RestfulResult.DEFAULT_ERROR_STATUS, result.getStatus());

        result = RestfulResult.successIf(str != null, "成功")
                .orError(2333, "失败");
        System.out.println(result);
        assertEquals(2333, result.getStatus());
        assertEquals("失败", result.getMessage());

        str = "";
        result = RestfulResult.successIf(str != null, "成功")
                .orError();
        System.out.println(result);
        assertEquals(RestfulResult.SUCCESS_STATUS, result.getStatus());

        result = RestfulResult.successIf(str != null, "成功", str)
                .orError(2333, "失败");
        System.out.println(result);
        assertEquals("成功", result.getMessage());
    }
}
