package xyz.zhouxy.plusone.commons.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.model.dto.UnifiedResponse.SuccessResult;

@Slf4j
public
class UnifiedResponseTests {

    static final ObjectMapper jackson = new ObjectMapper();

    static final Gson gson = new Gson();

    static final PageResult<User> pageResult = PageResult.of(Lists.newArrayList(
        new User("zhouxy1", "zhouxy1@gmail.com"),
        new User("zhouxy2", "zhouxy2@gmail.com")
    ), 108);

    static {
        jackson.setSerializationInclusion(Include.NON_NULL);
    }

    @Test
    void testSuccess_WithOutArgument() throws Exception {
        // 1. success without argument
        UnifiedResponse success = UnifiedResponse.success();
        assertEquals(SuccessResult.SUCCESS_STATUS, success.getStatus());
        assertEquals("SUCCESS", success.getMessage());
        assertNull(success.getData());
        String jacksonSuccess = jackson.writeValueAsString(success);
        log.info("jacksonSuccess: {}", jacksonSuccess);
        assertEquals("{\"status\":\"2000000\",\"message\":\"SUCCESS\"}", jacksonSuccess);
    }

    @Test
    void testSuccess_WithMessage() throws Exception {
        // 2. success with message
        UnifiedResponse successWithMessage = UnifiedResponse.success("成功");
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithMessage.getStatus());
        assertEquals("成功", successWithMessage.getMessage());
        assertNull(successWithMessage.getData());
        String jacksonSuccessWithMessage = jackson.writeValueAsString(successWithMessage);
        log.info("jacksonSuccessWithMessage: {}", jacksonSuccessWithMessage);
        assertEquals("{\"status\":\"2000000\",\"message\":\"成功\"}", jacksonSuccessWithMessage);
    }

    @Test
    void testSuccess_WithNullMessage() throws Exception {
        // 3. success with null message
        UnifiedResponse successWithNullMessage = UnifiedResponse.success(null);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithNullMessage.getStatus());
        assertEquals("", successWithNullMessage.getMessage());
        assertNull(successWithNullMessage.getData());
        String jacksonSuccessWithNullMessage = jackson.writeValueAsString(successWithNullMessage);
        log.info("jacksonSuccessWithNullMessage: {}", jacksonSuccessWithNullMessage);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\"}", jacksonSuccessWithNullMessage);
    }

    @Test
    void testSuccess_WithEmptyMessage() throws Exception {
        // 4. success with empty message
        UnifiedResponse successWithEmptyMessage = UnifiedResponse.success("");
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithEmptyMessage.getStatus());
        assertEquals("", successWithEmptyMessage.getMessage());
        assertNull(successWithEmptyMessage.getData());
        String jacksonSuccessWithEmptyMessage = jackson.writeValueAsString(successWithEmptyMessage);
        log.info("jacksonSuccessWithEmptyMessage: {}", jacksonSuccessWithEmptyMessage);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\"}", jacksonSuccessWithEmptyMessage);
    }

    @Test
    void testSuccess_WithData_String() throws Exception {
        UnifiedResponse successWithStringData = UnifiedResponse.success("查询成功", "zhouxy");
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithStringData.getStatus());
        assertEquals("查询成功", successWithStringData.getMessage());
        assertEquals("zhouxy", successWithStringData.getData());
        String jacksonSuccessWithStringData = jackson.writeValueAsString(successWithStringData);
        log.info("jacksonSuccessWithStringData: {}", jacksonSuccessWithStringData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"查询成功\",\"data\":\"zhouxy\"}", jacksonSuccessWithStringData);

        assertEquals("{status: \"2000000\", message: \"查询成功\", data: \"zhouxy\"}", successWithStringData.toString());
    }

    @Test
    void testSuccess_WithData_Integer() throws Exception {
        final UnifiedResponse successWithIntegerData = UnifiedResponse.success("查询成功", 1);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithIntegerData.getStatus());
        assertEquals("查询成功", successWithIntegerData.getMessage());
        assertEquals(1, successWithIntegerData.getData());
        final String jacksonSuccessWithIntegerData = jackson.writeValueAsString(successWithIntegerData);
        log.info("jacksonSuccessWithIntegerData: {}", jacksonSuccessWithIntegerData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"查询成功\",\"data\":1}", jacksonSuccessWithIntegerData);

        assertEquals("{status: \"2000000\", message: \"查询成功\", data: 1}", successWithIntegerData.toString());
    }

    @Test
    void testSuccess_WithData_PageResult() throws Exception {
        UnifiedResponse successWithData = UnifiedResponse.success("查询成功", pageResult);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithData.getStatus());
        assertEquals("查询成功", successWithData.getMessage());
        assertNotNull(successWithData.getData());
        assertEquals(pageResult, successWithData.getData());
        String jacksonSuccessWithData = jackson.writeValueAsString(successWithData);
        log.info("jacksonSuccessWithData: {}", jacksonSuccessWithData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"查询成功\",\"data\":{\"total\":108,\"content\":[{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"},{\"username\":\"zhouxy2\",\"email\":\"zhouxy2@gmail.com\"}]}}", jacksonSuccessWithData);
    }

    @Test
    void testSuccess_WithMessageAndNullData() throws Exception {
        // success with message and null data
        final UnifiedResponse successWithMessageAndNullData = UnifiedResponse.success("查询成功", null);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithMessageAndNullData.getStatus());
        assertEquals("查询成功", successWithMessageAndNullData.getMessage());
        assertNull(successWithMessageAndNullData.getData());
        final String jacksonSuccessWithMessageAndNullData = jackson.writeValueAsString(successWithMessageAndNullData);
        log.info("jacksonSuccessWithMessageAndNullData: {}", jacksonSuccessWithMessageAndNullData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"查询成功\"}", jacksonSuccessWithMessageAndNullData);

        assertEquals("{status: \"2000000\", message: \"查询成功\", data: null}", successWithMessageAndNullData.toString());
    }

    @Test
    void testSuccess_WithNullMessageAndData() throws Exception {
        // success with null message and data
        final User user = new User("zhouxy", "zhouxy@code108.cn");
        final UnifiedResponse successWithNullMessageAndData = UnifiedResponse.success(null, user);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithNullMessageAndData.getStatus());
        assertEquals("", successWithNullMessageAndData.getMessage());
        assertEquals(user, successWithNullMessageAndData.getData());
        final String jacksonSuccessWithNullMessageAndData = jackson.writeValueAsString(successWithNullMessageAndData);
        log.info("jacksonSuccessWithNullMessageAndData: {}", jacksonSuccessWithNullMessageAndData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@code108.cn\"}}",
                jacksonSuccessWithNullMessageAndData);
    }

    // success with null message and null data
    @Test
    void testSuccess_WithNullMessageAndNullData() throws Exception {
        final UnifiedResponse successWithNullMessageAndNullData = UnifiedResponse.success(null, null);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithNullMessageAndNullData.getStatus());
        assertEquals("", successWithNullMessageAndNullData.getMessage());
        assertNull(successWithNullMessageAndNullData.getData());

        final String jacksonSuccessWithNullMessageAndNullData = jackson.writeValueAsString(successWithNullMessageAndNullData);
        log.info("jacksonSuccessWithNullMessageAndNullData: {}", jacksonSuccessWithNullMessageAndNullData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\"}", jacksonSuccessWithNullMessageAndNullData);

        assertEquals("{status: \"2000000\", message: \"\", data: null}", successWithNullMessageAndNullData.toString());
    }

    // success with empty message and data
    @Test
    void testSuccess_WithEmptyMessageAndData() throws Exception {
        final User user = new User("zhouxy", "zhouxy@gmail.com");
        final UnifiedResponse successWithEmptyMessageAndData = UnifiedResponse.success("", user);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithEmptyMessageAndData.getStatus());
        assertEquals("", successWithEmptyMessageAndData.getMessage());
        assertEquals(user, successWithEmptyMessageAndData.getData());

        final String jacksonSuccessWithEmptyMessageAndData = jackson.writeValueAsString(successWithEmptyMessageAndData);
        log.info("jacksonSuccessWithEmptyMessageAndData: {}", jacksonSuccessWithEmptyMessageAndData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@gmail.com\"}}", jacksonSuccessWithEmptyMessageAndData);
    }

    // success with empty message and null data
    @Test
    void testSuccess_WithEmptyMessageAndNullData() throws Exception {
        final UnifiedResponse successWithEmptyMessageAndNullData = UnifiedResponse.success("", null);
        assertEquals(SuccessResult.SUCCESS_STATUS, successWithEmptyMessageAndNullData.getStatus());
        assertEquals("", successWithEmptyMessageAndNullData.getMessage());
        assertNull(successWithEmptyMessageAndNullData.getData());

        final String jacksonSuccessWithEmptyMessageAndNullData = jackson.writeValueAsString(successWithEmptyMessageAndNullData);
        log.info("jacksonSuccessWithEmptyMessageAndNullData: {}", jacksonSuccessWithEmptyMessageAndNullData);
        assertEquals("{\"status\":\"2000000\",\"message\":\"\"}", jacksonSuccessWithEmptyMessageAndNullData);

        assertEquals("{status: \"2000000\", message: \"\", data: null}", successWithEmptyMessageAndNullData.toString());
    }

    @Test
    void testError_Status_And_Message() throws Exception {
        final UnifiedResponse errorWithStatusAndMessage = UnifiedResponse.error(108, "查询失败");
        assertEquals(108, errorWithStatusAndMessage.getStatus());
        assertEquals("查询失败", errorWithStatusAndMessage.getMessage());
        assertNull(errorWithStatusAndMessage.getData());

        final String jacksonErrorWithStatusAndMessage = jackson.writeValueAsString(errorWithStatusAndMessage);
        log.info("jacksonErrorWithStatusAndMessage: {}", jacksonErrorWithStatusAndMessage);
        assertEquals("{\"status\":108,\"message\":\"查询失败\"}", jacksonErrorWithStatusAndMessage);

        assertEquals("{status: 108, message: \"查询失败\", data: null}", errorWithStatusAndMessage.toString());
    }

    @Test
    void testError_NullStatus() {
        final Object nullStatus = null;
        final String nullMessage = null;
        final User user = new User("zhouxy", "zhouxy@gmail.com");

        // message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "查询失败"));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "查询失败", user));

        // empty message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, ""));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "", user));

        // null message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, nullMessage));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "nullMessage", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "nullMessage", user));

        // Throwable
        // TODO
    }

    @Test
    void testError_Status_And_NullMessage() throws Exception {
        // TODO
    }

    @Test
    void testError_Message() throws Exception {
        // TODO
    }

    @Test
    void testError_NullMessage() throws Exception {
        // TODO
    }

    @Test
    void testError_EmptyMessage() throws Exception {
        // TODO
    }

    @Test
    void testOf() {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String username;
        private String email;
    }

}
