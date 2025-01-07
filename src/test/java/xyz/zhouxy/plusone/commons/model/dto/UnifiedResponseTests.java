/*
 * Copyright 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import xyz.zhouxy.plusone.commons.exception.business.BizException;
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
    void testSuccess_WithoutArgument() throws Exception {
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
    void testSuccess_WithMessageAndStringData() throws Exception {
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
    void testSuccess_WithMessageAndIntegerData() throws Exception {
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
    void testSuccess_WithMessageAndData() throws Exception {
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

    @Test
    void testError_WithMessage() throws Exception {
        UnifiedResponse errorWithMessage = UnifiedResponse.error("查询失败");
        assertEquals("9999999", errorWithMessage.getStatus());
        assertEquals("查询失败", errorWithMessage.getMessage());
        assertNull(errorWithMessage.getData());

        final String jacksonErrorWithMessage = jackson.writeValueAsString(errorWithMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\"}", jacksonErrorWithMessage);
        final String gsonErrorWithMessage = gson.toJson(errorWithMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\"}", gsonErrorWithMessage);
    }

    @Test
    void testError_WithMessage_AndNullData() throws Exception {
        UnifiedResponse errorWithMessageAndNullData = UnifiedResponse.error("查询失败", (Object) null);
        assertEquals("9999999", errorWithMessageAndNullData.getStatus());
        assertEquals("查询失败", errorWithMessageAndNullData.getMessage());
        assertNull(errorWithMessageAndNullData.getData());

        final String jacksonErrorWithMessageAndNullData = jackson.writeValueAsString(errorWithMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\"}", jacksonErrorWithMessageAndNullData);
        final String gsonErrorWithMessageAndNullData = gson.toJson(errorWithMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\"}", gsonErrorWithMessageAndNullData);
    }

    @Test
    void testError_WithMessage_AndData() throws Exception {
        final User user = new User("zhouxy", "zhouxy@gmail.com");
        UnifiedResponse errorWithMessageAndData = UnifiedResponse.error("查询失败", user);
        assertEquals("9999999", errorWithMessageAndData.getStatus());
        assertEquals("查询失败", errorWithMessageAndData.getMessage());
        assertEquals(user, errorWithMessageAndData.getData());

        final String jacksonErrorWithMessageAndData = jackson.writeValueAsString(errorWithMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@gmail.com\"}}",
                jacksonErrorWithMessageAndData);
        final String gsonErrorWithMessageAndData = gson.toJson(errorWithMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"查询失败\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@gmail.com\"}}",
                gsonErrorWithMessageAndData);
    }

    @Test
    void testError_WithNullMessage() throws Exception {
        UnifiedResponse errorWithNullMessage = UnifiedResponse.error((String) null);
        assertEquals("9999999", errorWithNullMessage.getStatus());
        assertEquals("", errorWithNullMessage.getMessage());
        assertNull(errorWithNullMessage.getData());

        final String jacksonErrorWithNullMessage = jackson.writeValueAsString(errorWithNullMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", jacksonErrorWithNullMessage);
        final String gsonErrorWithNullMessage = gson.toJson(errorWithNullMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", gsonErrorWithNullMessage);
    }

    @Test
    void testError_WithNullMessage_AndNullData() throws Exception {
        UnifiedResponse errorWithNullMessageAndNullData = UnifiedResponse.error((String) null, (User) null);
        assertEquals("9999999", errorWithNullMessageAndNullData.getStatus());
        assertEquals("", errorWithNullMessageAndNullData.getMessage());
        assertNull(errorWithNullMessageAndNullData.getData());

        final String jacksonErrorWithNullMessageAndNullData = jackson.writeValueAsString(errorWithNullMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", jacksonErrorWithNullMessageAndNullData);
        final String gsonErrorWithNullMessageAndNullData = gson.toJson(errorWithNullMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", gsonErrorWithNullMessageAndNullData);
    }

    @Test
    void testError_WithNullMessage_AndData() throws Exception {
        final User user = new User("zhouxy1", "zhouxy1@gmail.com");
        UnifiedResponse errorWithNullMessageAndData = UnifiedResponse.error((String) null, user);
        assertEquals("9999999", errorWithNullMessageAndData.getStatus());
        assertEquals("", errorWithNullMessageAndData.getMessage());
        assertEquals(user, errorWithNullMessageAndData.getData());

        final String jacksonErrorWithNullMessageAndData = jackson.writeValueAsString(errorWithNullMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\",\"data\":{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"}}",
                jacksonErrorWithNullMessageAndData);
        final String gsonErrorWithNullMessageAndData = gson.toJson(errorWithNullMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\",\"data\":{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"}}",
                gsonErrorWithNullMessageAndData);
    }

    @Test
    void testError_WithEmptyMessage() throws Exception {
        UnifiedResponse errorWithEmptyMessage = UnifiedResponse.error("");
        assertEquals("9999999", errorWithEmptyMessage.getStatus());
        assertEquals("", errorWithEmptyMessage.getMessage());
        assertNull(errorWithEmptyMessage.getData());
        final String jacksonErrorWithEmptyMessage = jackson.writeValueAsString(errorWithEmptyMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", jacksonErrorWithEmptyMessage);
        final String gsonErrorWithEmptyMessage = gson.toJson(errorWithEmptyMessage);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", gsonErrorWithEmptyMessage);
    }

    @Test
    void testError_WithEmptyMessage_AndNullData() throws Exception {
        UnifiedResponse errorWithEmptyMessageAndNullData = UnifiedResponse.error("", (User) null);
        assertEquals("9999999", errorWithEmptyMessageAndNullData.getStatus());
        assertEquals("", errorWithEmptyMessageAndNullData.getMessage());
        assertNull(errorWithEmptyMessageAndNullData.getData());

        final String jacksonErrorEmptyNullMessageAndNullData = jackson.writeValueAsString(errorWithEmptyMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", jacksonErrorEmptyNullMessageAndNullData);
        final String gsonErrorWithEmptyMessageAndNullData = gson.toJson(errorWithEmptyMessageAndNullData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\"}", gsonErrorWithEmptyMessageAndNullData);
    }

    @Test
    void testError_WithEmptyMessage_AndData() throws Exception {
        final User user = new User("zhouxy1", "zhouxy1@gmail.com");
        UnifiedResponse errorWithEmptyMessageAndData = UnifiedResponse.error("", user);
        assertEquals("9999999", errorWithEmptyMessageAndData.getStatus());
        assertEquals("", errorWithEmptyMessageAndData.getMessage());
        assertEquals(user, errorWithEmptyMessageAndData.getData());

        final String jacksonErrorWithEmptyMessageAndData = jackson.writeValueAsString(errorWithEmptyMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\",\"data\":{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"}}",
                jacksonErrorWithEmptyMessageAndData);
        final String gsonErrorWithEmptyMessageAndData = gson.toJson(errorWithEmptyMessageAndData);
        assertEquals("{\"status\":\"9999999\",\"message\":\"\",\"data\":{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"}}",
                gsonErrorWithEmptyMessageAndData);
    }

    @Test
    void testError_WithStatusAndMessage() throws Exception {
        final UnifiedResponse errorWithStatusAndMessage = UnifiedResponse.error(108, "查询失败");
        assertEquals(108, errorWithStatusAndMessage.getStatus());
        assertEquals("查询失败", errorWithStatusAndMessage.getMessage());
        assertNull(errorWithStatusAndMessage.getData());
        assertEquals("{status: 108, message: \"查询失败\", data: null}", errorWithStatusAndMessage.toString());

        final String jacksonErrorWithStatusAndMessage = jackson.writeValueAsString(errorWithStatusAndMessage);
        log.info("jacksonErrorWithStatusAndMessage: {}", jacksonErrorWithStatusAndMessage);
        assertEquals("{\"status\":108,\"message\":\"查询失败\"}", jacksonErrorWithStatusAndMessage);

        final String gsonErrorWithStatusAndMessage = gson.toJson(errorWithStatusAndMessage);
        assertEquals("{\"status\":108,\"message\":\"查询失败\"}", gsonErrorWithStatusAndMessage);
    }

    @Test
    void testError_WithStatusAndMessage_AndNullData() throws Exception {
        final UnifiedResponse errorWithStatusAndMessageAndNullData = UnifiedResponse.error(108, "查询失败", null);
        assertEquals(108, errorWithStatusAndMessageAndNullData.getStatus());
        assertEquals("查询失败", errorWithStatusAndMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndMessageAndNullData.getData());
        assertEquals("{status: 108, message: \"查询失败\", data: null}", errorWithStatusAndMessageAndNullData.toString());

        final String jacksonErrorWithStatusAndMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndMessageAndNullData);
        log.info("jacksonErrorWithStatusAndMessage: {}", jacksonErrorWithStatusAndMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"查询失败\"}", jacksonErrorWithStatusAndMessageAndNullData);

        final String gsonErrorWithStatusAndMessageAndNullData = gson.toJson(errorWithStatusAndMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"查询失败\"}", gsonErrorWithStatusAndMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndMessage_AndData() throws Exception {
        final PageResult<User> emptyPageResult = PageResult.empty();
        final UnifiedResponse errorWithStatusAndMessageAndData = UnifiedResponse.error(108, "查询失败", emptyPageResult);
        assertEquals(108, errorWithStatusAndMessageAndData.getStatus());
        assertEquals("查询失败", errorWithStatusAndMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndMessageAndData.getData());
        assertEquals("{status: 108, message: \"查询失败\", data: PageResult [total=0, content=[]]}",  errorWithStatusAndMessageAndData.toString());

        final String jacksonErrorWithStatusAndMessageAndData = jackson.writeValueAsString(errorWithStatusAndMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"查询失败\",\"data\":{\"total\":0,\"content\":[]}}",
                jacksonErrorWithStatusAndMessageAndData);

                final String gsonErrorWithStatusAndMessageAndData = gson.toJson(errorWithStatusAndMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"查询失败\",\"data\":{\"total\":0,\"content\":[]}}",
                gsonErrorWithStatusAndMessageAndData);
    }

    @Test
    void testError_WithStatusAndNullMessage() throws Exception {
        UnifiedResponse errorWithStatusAndNullMessage = UnifiedResponse.error(500, (String) null);
        assertEquals(500, errorWithStatusAndNullMessage.getStatus());
        assertEquals("", errorWithStatusAndNullMessage.getMessage());
        assertNull(errorWithStatusAndNullMessage.getData());

        final String jacksonErrorWithStatusAndNullMessage = jackson.writeValueAsString(errorWithStatusAndNullMessage);
        assertEquals("{\"status\":500,\"message\":\"\"}", jacksonErrorWithStatusAndNullMessage);

        final String gsonErrorWithStatusAndNullMessage = gson.toJson(errorWithStatusAndNullMessage);
        assertEquals("{\"status\":500,\"message\":\"\"}", gsonErrorWithStatusAndNullMessage);
    }

    @Test
    void testError_WithStatusAndNullMessage_AndNullData() throws Exception {
        UnifiedResponse errorWithStatusAndNullMessageAndNullData = UnifiedResponse.error(500, (String) null, null);

        assertEquals(500, errorWithStatusAndNullMessageAndNullData.getStatus());
        assertEquals("", errorWithStatusAndNullMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndNullMessageAndNullData.getData());

        final String jacksonErrorWithStatusAndNullMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndNullMessageAndNullData);
        assertEquals("{\"status\":500,\"message\":\"\"}", jacksonErrorWithStatusAndNullMessageAndNullData);

        final String gsonErrorWithStatusAndNullMessageAndNullData = gson.toJson(errorWithStatusAndNullMessageAndNullData);
        assertEquals("{\"status\":500,\"message\":\"\"}", gsonErrorWithStatusAndNullMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndNullMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse errorWithStatusAndNullMessageAndData = UnifiedResponse.error(500, (String) null, emptyPageResult);
        assertEquals(500, errorWithStatusAndNullMessageAndData.getStatus());
        assertEquals("", errorWithStatusAndNullMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndNullMessageAndData.getData());
        final String jacksonErrorWithStatusAndNullMessageAndData = jackson.writeValueAsString(errorWithStatusAndNullMessageAndData);
        assertEquals("{\"status\":500,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonErrorWithStatusAndNullMessageAndData);
        final String gsonErrorWithStatusAndNullMessageAndData = gson.toJson(errorWithStatusAndNullMessageAndData);
        assertEquals("{\"status\":500,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonErrorWithStatusAndNullMessageAndData);
    }

    @Test
    void testError_WithStatusAndEmptyMessage() throws Exception {
        UnifiedResponse errorWithStatusAndEmptyMessage = UnifiedResponse.error(500, "");
        assertEquals(500, errorWithStatusAndEmptyMessage.getStatus());
        assertEquals("", errorWithStatusAndEmptyMessage.getMessage());
        assertNull(errorWithStatusAndEmptyMessage.getData());

        final String jacksonErrorWithStatusAndEmptyMessage = jackson.writeValueAsString(errorWithStatusAndEmptyMessage);
        assertEquals("{\"status\":500,\"message\":\"\"}", jacksonErrorWithStatusAndEmptyMessage);

        final String gsonErrorWithStatusAndEmptyMessage = gson.toJson(errorWithStatusAndEmptyMessage);
        assertEquals("{\"status\":500,\"message\":\"\"}", gsonErrorWithStatusAndEmptyMessage);
    }

    @Test
    void testError_WithStatusAndEmptyMessage_AndNullData() throws Exception {
        UnifiedResponse errorWithStatusAndEmptyMessageAndNullData = UnifiedResponse.error(500, "", null);

        assertEquals(500, errorWithStatusAndEmptyMessageAndNullData.getStatus());
        assertEquals("", errorWithStatusAndEmptyMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndEmptyMessageAndNullData.getData());

        final String jacksonErrorWithStatusAndEmptyMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"status\":500,\"message\":\"\"}", jacksonErrorWithStatusAndEmptyMessageAndNullData);

        final String gsonErrorWithStatusAndEmptyMessageAndNullData = gson.toJson(errorWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"status\":500,\"message\":\"\"}", gsonErrorWithStatusAndEmptyMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndEmptyMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse errorWithStatusAndEmptyMessageAndData = UnifiedResponse.error(500, "", emptyPageResult);
        assertEquals(500, errorWithStatusAndEmptyMessageAndData.getStatus());
        assertEquals("", errorWithStatusAndEmptyMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndEmptyMessageAndData.getData());
        final String jacksonErrorWithStatusAndEmptyMessageAndData = jackson.writeValueAsString(errorWithStatusAndEmptyMessageAndData);
        assertEquals("{\"status\":500,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonErrorWithStatusAndEmptyMessageAndData);
        final String gsonErrorWithStatusAndEmptyMessageAndData = gson.toJson(errorWithStatusAndEmptyMessageAndData);
        assertEquals("{\"status\":500,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonErrorWithStatusAndEmptyMessageAndData);
    }

    @Test
    void testError_WithStatusAndThrowable() throws Exception {
        final IllegalArgumentException e = new IllegalArgumentException("ID cannot be null");
        final UnifiedResponse errorWithStatusThrowable = UnifiedResponse.error(500, e);
        assertEquals(500, errorWithStatusThrowable.getStatus());
        assertEquals("ID cannot be null", errorWithStatusThrowable.getMessage());
        assertNull(errorWithStatusThrowable.getData());
        assertEquals("{\"status\":500,\"message\":\"ID cannot be null\"}", jackson.writeValueAsString(errorWithStatusThrowable));
        assertEquals("{\"status\":500,\"message\":\"ID cannot be null\"}", gson.toJson(errorWithStatusThrowable));
    }

    @Test
    void testError_WithStatusAndNullThrowable() {
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(500, (Throwable) null));
    }

    @Test
    void testError_WithNullStatus() {
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
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, "查询失败", user));

        // Throwable
        BizException bizException = new BizException("业务异常");
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, bizException));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.error(nullStatus, (Throwable) null));
    }


    @Test
    void testOf_WithStatusAndMessage() throws Exception {
        final UnifiedResponse ofWithStatusAndMessage = UnifiedResponse.of(108, "This is a message.");
        assertEquals(108, ofWithStatusAndMessage.getStatus());
        assertEquals("This is a message.", ofWithStatusAndMessage.getMessage());
        assertNull(ofWithStatusAndMessage.getData());

        final String jacksonOfWithStatusAndMessage = jackson.writeValueAsString(ofWithStatusAndMessage);
        log.info("jacksonOfWithStatusAndMessage: {}", jacksonOfWithStatusAndMessage);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\"}", jacksonOfWithStatusAndMessage);

        assertEquals("{status: 108, message: \"This is a message.\", data: null}", ofWithStatusAndMessage.toString());

        final String gsonOfWithStatusAndMessage = gson.toJson(ofWithStatusAndMessage);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\"}", gsonOfWithStatusAndMessage);
    }

    @Test
    void testOf_WithStatusAndMessage_AndNullData() throws Exception {
        final UnifiedResponse ofWithStatusAndMessageAndNullData = UnifiedResponse.of(108, "This is a message.", null);
        assertEquals(108, ofWithStatusAndMessageAndNullData.getStatus());
        assertEquals("This is a message.", ofWithStatusAndMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndMessageAndNullData.getData());

        final String jacksonOfWithStatusAndMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndMessageAndNullData);
        log.info("jacksonOfWithStatusAndMessage: {}", jacksonOfWithStatusAndMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\"}", jacksonOfWithStatusAndMessageAndNullData);

        assertEquals("{status: 108, message: \"This is a message.\", data: null}", ofWithStatusAndMessageAndNullData.toString());

        final String gsonOfWithStatusAndMessageAndNullData = gson.toJson(ofWithStatusAndMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\"}", gsonOfWithStatusAndMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndMessage_AndData() throws Exception {
        final PageResult<User> emptyPageResult = PageResult.empty();
        final UnifiedResponse ofWithStatusAndMessageAndData
                = UnifiedResponse.of(108, "This is a message.", emptyPageResult);
        assertEquals("{status: 108, message: \"This is a message.\", data: PageResult [total=0, content=[]]}",
                ofWithStatusAndMessageAndData.toString());
        assertEquals(108, ofWithStatusAndMessageAndData.getStatus());
        assertEquals("This is a message.", ofWithStatusAndMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndMessageAndData.getData());
        final String jacksonOfWithStatusAndMessageAndData = jackson.writeValueAsString(ofWithStatusAndMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\",\"data\":{\"total\":0,\"content\":[]}}",
                jacksonOfWithStatusAndMessageAndData);
        final String gsonOfWithStatusAndMessageAndData = gson.toJson(ofWithStatusAndMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"This is a message.\",\"data\":{\"total\":0,\"content\":[]}}",
                gsonOfWithStatusAndMessageAndData);
    }

    @Test
    void testOf_WithStatusAndNullMessage() throws Exception {
        UnifiedResponse ofWithStatusAndNullMessage = UnifiedResponse.of(108, (String) null);
        assertEquals(108, ofWithStatusAndNullMessage.getStatus());
        assertEquals("", ofWithStatusAndNullMessage.getMessage());
        assertNull(ofWithStatusAndNullMessage.getData());

        final String jacksonOfWithStatusAndNullMessage = jackson.writeValueAsString(ofWithStatusAndNullMessage);
        assertEquals("{\"status\":108,\"message\":\"\"}", jacksonOfWithStatusAndNullMessage);

        final String gsonOfWithStatusAndNullMessage = gson.toJson(ofWithStatusAndNullMessage);
        assertEquals("{\"status\":108,\"message\":\"\"}", gsonOfWithStatusAndNullMessage);
    }

    @Test
    void testOf_WithStatusAndNullMessage_AndNullData() throws Exception {
        UnifiedResponse ofWithStatusAndNullMessageAndNullData = UnifiedResponse.of(108, (String) null, null);

        assertEquals(108, ofWithStatusAndNullMessageAndNullData.getStatus());
        assertEquals("", ofWithStatusAndNullMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndNullMessageAndNullData.getData());

        final String jacksonOfWithStatusAndNullMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndNullMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"\"}", jacksonOfWithStatusAndNullMessageAndNullData);

        final String gsonOfWithStatusAndNullMessageAndNullData = gson.toJson(ofWithStatusAndNullMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"\"}", gsonOfWithStatusAndNullMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndNullMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse ofWithStatusAndNullMessageAndData = UnifiedResponse.of(108, (String) null, emptyPageResult);
        assertEquals(108, ofWithStatusAndNullMessageAndData.getStatus());
        assertEquals("", ofWithStatusAndNullMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndNullMessageAndData.getData());
        final String jacksonOfWithStatusAndNullMessageAndData = jackson.writeValueAsString(ofWithStatusAndNullMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonOfWithStatusAndNullMessageAndData);
        final String gsonOfWithStatusAndNullMessageAndData = gson.toJson(ofWithStatusAndNullMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonOfWithStatusAndNullMessageAndData);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage() throws Exception {
        UnifiedResponse ofWithStatusAndEmptyMessage = UnifiedResponse.of(108, "");
        assertEquals(108, ofWithStatusAndEmptyMessage.getStatus());
        assertEquals("", ofWithStatusAndEmptyMessage.getMessage());
        assertNull(ofWithStatusAndEmptyMessage.getData());

        final String jacksonOfWithStatusAndEmptyMessage = jackson.writeValueAsString(ofWithStatusAndEmptyMessage);
        assertEquals("{\"status\":108,\"message\":\"\"}", jacksonOfWithStatusAndEmptyMessage);

        final String gsonOfWithStatusAndEmptyMessage = gson.toJson(ofWithStatusAndEmptyMessage);
        assertEquals("{\"status\":108,\"message\":\"\"}", gsonOfWithStatusAndEmptyMessage);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage_AndNullData() throws Exception {
        UnifiedResponse ofWithStatusAndEmptyMessageAndNullData = UnifiedResponse.of(108, "", null);

        assertEquals(108, ofWithStatusAndEmptyMessageAndNullData.getStatus());
        assertEquals("", ofWithStatusAndEmptyMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndEmptyMessageAndNullData.getData());

        final String jacksonOfWithStatusAndEmptyMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"\"}", jacksonOfWithStatusAndEmptyMessageAndNullData);

        final String gsonOfWithStatusAndEmptyMessageAndNullData = gson.toJson(ofWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"status\":108,\"message\":\"\"}", gsonOfWithStatusAndEmptyMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse ofWithStatusAndEmptyMessageAndData = UnifiedResponse.of(108, "", emptyPageResult);
        assertEquals(108, ofWithStatusAndEmptyMessageAndData.getStatus());
        assertEquals("", ofWithStatusAndEmptyMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndEmptyMessageAndData.getData());
        final String jacksonOfWithStatusAndEmptyMessageAndData = jackson.writeValueAsString(ofWithStatusAndEmptyMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonOfWithStatusAndEmptyMessageAndData);
        final String gsonOfWithStatusAndEmptyMessageAndData = gson.toJson(ofWithStatusAndEmptyMessageAndData);
        assertEquals("{\"status\":108,\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonOfWithStatusAndEmptyMessageAndData);
    }

    @Test
    void testOf_WithNullStatus() {
        final Object nullStatus = null;
        final String nullMessage = null;
        final User user = new User("zhouxy", "zhouxy@gmail.com");

        // message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "查询失败"));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "查询失败", user));

        // empty message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, ""));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "", user));

        // null message
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, nullMessage));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> UnifiedResponse.of(nullStatus, "查询失败", user));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String username;
        private String email;
    }

}
