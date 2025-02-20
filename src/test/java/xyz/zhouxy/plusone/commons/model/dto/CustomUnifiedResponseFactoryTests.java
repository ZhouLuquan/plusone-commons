/*
 * Copyright 2025 the original author or authors.
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

import javax.annotation.Nullable;

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

@Slf4j
public
class CustomUnifiedResponseFactoryTests {

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
        UnifiedResponse<Void> success = CustomUnifiedResponses.success();
        assertEquals("0000000", success.getCode());
        assertEquals("成功", success.getMessage());
        assertNull(success.getData());
        String jacksonSuccess = jackson.writeValueAsString(success);
        log.info("jacksonSuccess: {}", jacksonSuccess);
        assertEquals("{\"code\":\"0000000\",\"message\":\"成功\"}", jacksonSuccess);
    }

    @Test
    void testSuccess_WithMessage() throws Exception {
        // 2. success with message
        UnifiedResponse<Void> successWithMessage = CustomUnifiedResponses.success("成功");
        assertEquals("0000000", successWithMessage.getCode());
        assertEquals("成功", successWithMessage.getMessage());
        assertNull(successWithMessage.getData());
        String jacksonSuccessWithMessage = jackson.writeValueAsString(successWithMessage);
        log.info("jacksonSuccessWithMessage: {}", jacksonSuccessWithMessage);
        assertEquals("{\"code\":\"0000000\",\"message\":\"成功\"}", jacksonSuccessWithMessage);
    }

    @Test
    void testSuccess_WithMessageAndNullData() throws Exception {
        // success with message and null data
        final UnifiedResponse<Void> successWithMessageAndNullData = CustomUnifiedResponses.success("查询成功", null);
        assertEquals("0000000", successWithMessageAndNullData.getCode());
        assertEquals("查询成功", successWithMessageAndNullData.getMessage());
        assertNull(successWithMessageAndNullData.getData());
        final String jacksonSuccessWithMessageAndNullData = jackson.writeValueAsString(successWithMessageAndNullData);
        log.info("jacksonSuccessWithMessageAndNullData: {}", jacksonSuccessWithMessageAndNullData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"查询成功\"}", jacksonSuccessWithMessageAndNullData);

        assertEquals("{code: \"0000000\", message: \"查询成功\", data: null}", successWithMessageAndNullData.toString());
    }

    @Test
    void testSuccess_WithMessageAndStringData() throws Exception {
        UnifiedResponse<String> successWithStringData = CustomUnifiedResponses.success("查询成功", "zhouxy");
        assertEquals("0000000", successWithStringData.getCode());
        assertEquals("查询成功", successWithStringData.getMessage());
        assertEquals("zhouxy", successWithStringData.getData());
        String jacksonSuccessWithStringData = jackson.writeValueAsString(successWithStringData);
        log.info("jacksonSuccessWithStringData: {}", jacksonSuccessWithStringData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"查询成功\",\"data\":\"zhouxy\"}", jacksonSuccessWithStringData);

        assertEquals("{code: \"0000000\", message: \"查询成功\", data: \"zhouxy\"}", successWithStringData.toString());
    }

    @Test
    void testSuccess_WithMessageAndIntegerData() throws Exception {
        final UnifiedResponse<Integer> successWithIntegerData = CustomUnifiedResponses.success("查询成功", 1);
        assertEquals("0000000", successWithIntegerData.getCode());
        assertEquals("查询成功", successWithIntegerData.getMessage());
        assertEquals(1, successWithIntegerData.getData());
        final String jacksonSuccessWithIntegerData = jackson.writeValueAsString(successWithIntegerData);
        log.info("jacksonSuccessWithIntegerData: {}", jacksonSuccessWithIntegerData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"查询成功\",\"data\":1}", jacksonSuccessWithIntegerData);

        assertEquals("{code: \"0000000\", message: \"查询成功\", data: 1}", successWithIntegerData.toString());
    }

    @Test
    void testSuccess_WithMessageAndData() throws Exception {
        UnifiedResponse<PageResult<User>> successWithData = CustomUnifiedResponses.success("查询成功", pageResult);
        assertEquals("0000000", successWithData.getCode());
        assertEquals("查询成功", successWithData.getMessage());
        assertNotNull(successWithData.getData());
        assertEquals(pageResult, successWithData.getData());
        String jacksonSuccessWithData = jackson.writeValueAsString(successWithData);
        log.info("jacksonSuccessWithData: {}", jacksonSuccessWithData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"查询成功\",\"data\":{\"total\":108,\"content\":[{\"username\":\"zhouxy1\",\"email\":\"zhouxy1@gmail.com\"},{\"username\":\"zhouxy2\",\"email\":\"zhouxy2@gmail.com\"}]}}", jacksonSuccessWithData);
    }

    @Test
    void testSuccess_WithNullMessage() throws Exception {
        // 3. success with null message
        UnifiedResponse<Void> successWithNullMessage = CustomUnifiedResponses.success(null);
        assertEquals("0000000", successWithNullMessage.getCode());
        assertEquals("", successWithNullMessage.getMessage());
        assertNull(successWithNullMessage.getData());
        String jacksonSuccessWithNullMessage = jackson.writeValueAsString(successWithNullMessage);
        log.info("jacksonSuccessWithNullMessage: {}", jacksonSuccessWithNullMessage);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\"}", jacksonSuccessWithNullMessage);
    }

    // success with null message and null data
    @Test
    void testSuccess_WithNullMessageAndNullData() throws Exception {
        final UnifiedResponse<Void> successWithNullMessageAndNullData = CustomUnifiedResponses.success(null, null);
        assertEquals("0000000", successWithNullMessageAndNullData.getCode());
        assertEquals("", successWithNullMessageAndNullData.getMessage());
        assertNull(successWithNullMessageAndNullData.getData());

        final String jacksonSuccessWithNullMessageAndNullData = jackson.writeValueAsString(successWithNullMessageAndNullData);
        log.info("jacksonSuccessWithNullMessageAndNullData: {}", jacksonSuccessWithNullMessageAndNullData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\"}", jacksonSuccessWithNullMessageAndNullData);

        assertEquals("{code: \"0000000\", message: \"\", data: null}", successWithNullMessageAndNullData.toString());
    }

    @Test
    void testSuccess_WithNullMessageAndData() throws Exception {
        // success with null message and data
        final User user = new User("zhouxy", "zhouxy@code108.cn");
        final UnifiedResponse<User> successWithNullMessageAndData = CustomUnifiedResponses.success(null, user);
        assertEquals("0000000", successWithNullMessageAndData.getCode());
        assertEquals("", successWithNullMessageAndData.getMessage());
        assertEquals(user, successWithNullMessageAndData.getData());
        final String jacksonSuccessWithNullMessageAndData = jackson.writeValueAsString(successWithNullMessageAndData);
        log.info("jacksonSuccessWithNullMessageAndData: {}", jacksonSuccessWithNullMessageAndData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@code108.cn\"}}",
                jacksonSuccessWithNullMessageAndData);
    }

    @Test
    void testSuccess_WithEmptyMessage() throws Exception {
        // 4. success with empty message
        UnifiedResponse<Void> successWithEmptyMessage = CustomUnifiedResponses.success("");
        assertEquals("0000000", successWithEmptyMessage.getCode());
        assertEquals("", successWithEmptyMessage.getMessage());
        assertNull(successWithEmptyMessage.getData());
        String jacksonSuccessWithEmptyMessage = jackson.writeValueAsString(successWithEmptyMessage);
        log.info("jacksonSuccessWithEmptyMessage: {}", jacksonSuccessWithEmptyMessage);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\"}", jacksonSuccessWithEmptyMessage);
    }

    // success with empty message and null data
    @Test
    void testSuccess_WithEmptyMessageAndNullData() throws Exception {
        final UnifiedResponse<Void> successWithEmptyMessageAndNullData = CustomUnifiedResponses.success("", null);
        assertEquals("0000000", successWithEmptyMessageAndNullData.getCode());
        assertEquals("", successWithEmptyMessageAndNullData.getMessage());
        assertNull(successWithEmptyMessageAndNullData.getData());

        final String jacksonSuccessWithEmptyMessageAndNullData = jackson.writeValueAsString(successWithEmptyMessageAndNullData);
        log.info("jacksonSuccessWithEmptyMessageAndNullData: {}", jacksonSuccessWithEmptyMessageAndNullData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\"}", jacksonSuccessWithEmptyMessageAndNullData);

        assertEquals("{code: \"0000000\", message: \"\", data: null}", successWithEmptyMessageAndNullData.toString());
    }

    // success with empty message and data
    @Test
    void testSuccess_WithEmptyMessageAndData() throws Exception {
        final User user = new User("zhouxy", "zhouxy@gmail.com");
        final UnifiedResponse<User> successWithEmptyMessageAndData = CustomUnifiedResponses.success("", user);
        assertEquals("0000000", successWithEmptyMessageAndData.getCode());
        assertEquals("", successWithEmptyMessageAndData.getMessage());
        assertEquals(user, successWithEmptyMessageAndData.getData());

        final String jacksonSuccessWithEmptyMessageAndData = jackson.writeValueAsString(successWithEmptyMessageAndData);
        log.info("jacksonSuccessWithEmptyMessageAndData: {}", jacksonSuccessWithEmptyMessageAndData);
        assertEquals("{\"code\":\"0000000\",\"message\":\"\",\"data\":{\"username\":\"zhouxy\",\"email\":\"zhouxy@gmail.com\"}}", jacksonSuccessWithEmptyMessageAndData);
    }

    @Test
    void testError_WithStatusAndMessage() throws Exception {
        final UnifiedResponse<Void> errorWithStatusAndMessage = CustomUnifiedResponses.error("108", "查询失败");
        assertEquals("108", errorWithStatusAndMessage.getCode());
        assertEquals("查询失败", errorWithStatusAndMessage.getMessage());
        assertNull(errorWithStatusAndMessage.getData());
        assertEquals("{code: \"108\", message: \"查询失败\", data: null}", errorWithStatusAndMessage.toString());

        final String jacksonErrorWithStatusAndMessage = jackson.writeValueAsString(errorWithStatusAndMessage);
        log.info("jacksonErrorWithStatusAndMessage: {}", jacksonErrorWithStatusAndMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\"}", jacksonErrorWithStatusAndMessage);

        final String gsonErrorWithStatusAndMessage = gson.toJson(errorWithStatusAndMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\"}", gsonErrorWithStatusAndMessage);
    }

    @Test
    void testError_WithStatusAndMessage_AndNullData() throws Exception {
        final UnifiedResponse<Void> errorWithStatusAndMessageAndNullData = CustomUnifiedResponses.error("108", "查询失败", null);
        assertEquals("108", errorWithStatusAndMessageAndNullData.getCode());
        assertEquals("查询失败", errorWithStatusAndMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndMessageAndNullData.getData());
        assertEquals("{code: \"108\", message: \"查询失败\", data: null}", errorWithStatusAndMessageAndNullData.toString());

        final String jacksonErrorWithStatusAndMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndMessageAndNullData);
        log.info("jacksonErrorWithStatusAndMessage: {}", jacksonErrorWithStatusAndMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\"}", jacksonErrorWithStatusAndMessageAndNullData);

        final String gsonErrorWithStatusAndMessageAndNullData = gson.toJson(errorWithStatusAndMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\"}", gsonErrorWithStatusAndMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndMessage_AndData() throws Exception {
        final PageResult<User> emptyPageResult = PageResult.empty();
        final UnifiedResponse<PageResult<User>> errorWithStatusAndMessageAndData = CustomUnifiedResponses.error("108", "查询失败", emptyPageResult);
        assertEquals("108", errorWithStatusAndMessageAndData.getCode());
        assertEquals("查询失败", errorWithStatusAndMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndMessageAndData.getData());
        assertEquals("{code: \"108\", message: \"查询失败\", data: PageResult [total=0, content=[]]}",  errorWithStatusAndMessageAndData.toString());

        final String jacksonErrorWithStatusAndMessageAndData = jackson.writeValueAsString(errorWithStatusAndMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\",\"data\":{\"total\":0,\"content\":[]}}",
                jacksonErrorWithStatusAndMessageAndData);

                final String gsonErrorWithStatusAndMessageAndData = gson.toJson(errorWithStatusAndMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"查询失败\",\"data\":{\"total\":0,\"content\":[]}}",
                gsonErrorWithStatusAndMessageAndData);
    }

    @Test
    void testError_WithStatusAndNullMessage() throws Exception {
        UnifiedResponse<Void> errorWithStatusAndNullMessage = CustomUnifiedResponses.error("500", (String) null);
        assertEquals("500", errorWithStatusAndNullMessage.getCode());
        assertEquals("", errorWithStatusAndNullMessage.getMessage());
        assertNull(errorWithStatusAndNullMessage.getData());

        final String jacksonErrorWithStatusAndNullMessage = jackson.writeValueAsString(errorWithStatusAndNullMessage);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", jacksonErrorWithStatusAndNullMessage);

        final String gsonErrorWithStatusAndNullMessage = gson.toJson(errorWithStatusAndNullMessage);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", gsonErrorWithStatusAndNullMessage);
    }

    @Test
    void testError_WithStatusAndNullMessage_AndNullData() throws Exception {
        UnifiedResponse<Void> errorWithStatusAndNullMessageAndNullData = CustomUnifiedResponses.error("500", (String) null, null);

        assertEquals("500", errorWithStatusAndNullMessageAndNullData.getCode());
        assertEquals("", errorWithStatusAndNullMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndNullMessageAndNullData.getData());

        final String jacksonErrorWithStatusAndNullMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndNullMessageAndNullData);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", jacksonErrorWithStatusAndNullMessageAndNullData);

        final String gsonErrorWithStatusAndNullMessageAndNullData = gson.toJson(errorWithStatusAndNullMessageAndNullData);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", gsonErrorWithStatusAndNullMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndNullMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse<PageResult<User>> errorWithStatusAndNullMessageAndData = CustomUnifiedResponses.error("500", (String) null, emptyPageResult);
        assertEquals("500", errorWithStatusAndNullMessageAndData.getCode());
        assertEquals("", errorWithStatusAndNullMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndNullMessageAndData.getData());
        final String jacksonErrorWithStatusAndNullMessageAndData = jackson.writeValueAsString(errorWithStatusAndNullMessageAndData);
        assertEquals("{\"code\":\"500\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonErrorWithStatusAndNullMessageAndData);
        final String gsonErrorWithStatusAndNullMessageAndData = gson.toJson(errorWithStatusAndNullMessageAndData);
        assertEquals("{\"code\":\"500\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonErrorWithStatusAndNullMessageAndData);
    }

    @Test
    void testError_WithStatusAndEmptyMessage() throws Exception {
        UnifiedResponse<Void> errorWithStatusAndEmptyMessage = CustomUnifiedResponses.error("500", "");
        assertEquals("500", errorWithStatusAndEmptyMessage.getCode());
        assertEquals("", errorWithStatusAndEmptyMessage.getMessage());
        assertNull(errorWithStatusAndEmptyMessage.getData());

        final String jacksonErrorWithStatusAndEmptyMessage = jackson.writeValueAsString(errorWithStatusAndEmptyMessage);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", jacksonErrorWithStatusAndEmptyMessage);

        final String gsonErrorWithStatusAndEmptyMessage = gson.toJson(errorWithStatusAndEmptyMessage);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", gsonErrorWithStatusAndEmptyMessage);
    }

    @Test
    void testError_WithStatusAndEmptyMessage_AndNullData() throws Exception {
        UnifiedResponse<Void> errorWithStatusAndEmptyMessageAndNullData = CustomUnifiedResponses.error("500", "", null);

        assertEquals("500", errorWithStatusAndEmptyMessageAndNullData.getCode());
        assertEquals("", errorWithStatusAndEmptyMessageAndNullData.getMessage());
        assertNull(errorWithStatusAndEmptyMessageAndNullData.getData());

        final String jacksonErrorWithStatusAndEmptyMessageAndNullData = jackson.writeValueAsString(errorWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", jacksonErrorWithStatusAndEmptyMessageAndNullData);

        final String gsonErrorWithStatusAndEmptyMessageAndNullData = gson.toJson(errorWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"code\":\"500\",\"message\":\"\"}", gsonErrorWithStatusAndEmptyMessageAndNullData);
    }

    @Test
    void testError_WithStatusAndEmptyMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse<PageResult<User>> errorWithStatusAndEmptyMessageAndData = CustomUnifiedResponses.error("500", "", emptyPageResult);
        assertEquals("500", errorWithStatusAndEmptyMessageAndData.getCode());
        assertEquals("", errorWithStatusAndEmptyMessageAndData.getMessage());
        assertEquals(emptyPageResult, errorWithStatusAndEmptyMessageAndData.getData());
        final String jacksonErrorWithStatusAndEmptyMessageAndData = jackson.writeValueAsString(errorWithStatusAndEmptyMessageAndData);
        assertEquals("{\"code\":\"500\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonErrorWithStatusAndEmptyMessageAndData);
        final String gsonErrorWithStatusAndEmptyMessageAndData = gson.toJson(errorWithStatusAndEmptyMessageAndData);
        assertEquals("{\"code\":\"500\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonErrorWithStatusAndEmptyMessageAndData);
    }

    @Test
    void testError_WithStatusAndThrowable() throws Exception {
        final IllegalArgumentException e = new IllegalArgumentException("ID cannot be null");
        final UnifiedResponse<Void> errorWithStatusThrowable = CustomUnifiedResponses.error("500", e);
        assertEquals("500", errorWithStatusThrowable.getCode());
        assertEquals("ID cannot be null", errorWithStatusThrowable.getMessage());
        assertNull(errorWithStatusThrowable.getData());
        assertEquals("{\"code\":\"500\",\"message\":\"ID cannot be null\"}", jackson.writeValueAsString(errorWithStatusThrowable));
        assertEquals("{\"code\":\"500\",\"message\":\"ID cannot be null\"}", gson.toJson(errorWithStatusThrowable));
    }

    @Test
    void testError_WithStatusAndNullThrowable() {
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error("500", (Throwable) null));
    }

    @Test
    void testError_WithNullStatus() {
        final String nullStatus = null;
        final String nullMessage = null;
        final User user = new User("zhouxy", "zhouxy@gmail.com");

        // message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "查询失败"));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "查询失败", user));

        // empty message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, ""));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "", user));

        // null message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, nullMessage));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, "查询失败", user));

        // Throwable
        BizException bizException = new BizException("业务异常");
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, bizException));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.error(nullStatus, (Throwable) null));
    }


    @Test
    void testOf_WithStatusAndMessage() throws Exception {
        final UnifiedResponse<Void> ofWithStatusAndMessage = CustomUnifiedResponses.of("108", "This is a message.");
        assertEquals("108", ofWithStatusAndMessage.getCode());
        assertEquals("This is a message.", ofWithStatusAndMessage.getMessage());
        assertNull(ofWithStatusAndMessage.getData());

        final String jacksonOfWithStatusAndMessage = jackson.writeValueAsString(ofWithStatusAndMessage);
        log.info("jacksonOfWithStatusAndMessage: {}", jacksonOfWithStatusAndMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\"}", jacksonOfWithStatusAndMessage);

        assertEquals("{code: \"108\", message: \"This is a message.\", data: null}", ofWithStatusAndMessage.toString());

        final String gsonOfWithStatusAndMessage = gson.toJson(ofWithStatusAndMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\"}", gsonOfWithStatusAndMessage);
    }

    @Test
    void testOf_WithStatusAndMessage_AndNullData() throws Exception {
        final UnifiedResponse<Void> ofWithStatusAndMessageAndNullData = CustomUnifiedResponses.of("108", "This is a message.", null);
        assertEquals("108", ofWithStatusAndMessageAndNullData.getCode());
        assertEquals("This is a message.", ofWithStatusAndMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndMessageAndNullData.getData());

        final String jacksonOfWithStatusAndMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndMessageAndNullData);
        log.info("jacksonOfWithStatusAndMessage: {}", jacksonOfWithStatusAndMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\"}", jacksonOfWithStatusAndMessageAndNullData);

        assertEquals("{code: \"108\", message: \"This is a message.\", data: null}", ofWithStatusAndMessageAndNullData.toString());

        final String gsonOfWithStatusAndMessageAndNullData = gson.toJson(ofWithStatusAndMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\"}", gsonOfWithStatusAndMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndMessage_AndData() throws Exception {
        final PageResult<User> emptyPageResult = PageResult.empty();
        final UnifiedResponse<PageResult<User>> ofWithStatusAndMessageAndData
                = CustomUnifiedResponses.of("108", "This is a message.", emptyPageResult);
        assertEquals("{code: \"108\", message: \"This is a message.\", data: PageResult [total=0, content=[]]}",
                ofWithStatusAndMessageAndData.toString());
        assertEquals("108", ofWithStatusAndMessageAndData.getCode());
        assertEquals("This is a message.", ofWithStatusAndMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndMessageAndData.getData());
        final String jacksonOfWithStatusAndMessageAndData = jackson.writeValueAsString(ofWithStatusAndMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\",\"data\":{\"total\":0,\"content\":[]}}",
                jacksonOfWithStatusAndMessageAndData);
        final String gsonOfWithStatusAndMessageAndData = gson.toJson(ofWithStatusAndMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"This is a message.\",\"data\":{\"total\":0,\"content\":[]}}",
                gsonOfWithStatusAndMessageAndData);
    }

    @Test
    void testOf_WithStatusAndNullMessage() throws Exception {
        UnifiedResponse<Void> ofWithStatusAndNullMessage = CustomUnifiedResponses.of("108", (String) null);
        assertEquals("108", ofWithStatusAndNullMessage.getCode());
        assertEquals("", ofWithStatusAndNullMessage.getMessage());
        assertNull(ofWithStatusAndNullMessage.getData());

        final String jacksonOfWithStatusAndNullMessage = jackson.writeValueAsString(ofWithStatusAndNullMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", jacksonOfWithStatusAndNullMessage);

        final String gsonOfWithStatusAndNullMessage = gson.toJson(ofWithStatusAndNullMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", gsonOfWithStatusAndNullMessage);
    }

    @Test
    void testOf_WithStatusAndNullMessage_AndNullData() throws Exception {
        UnifiedResponse<Void> ofWithStatusAndNullMessageAndNullData = CustomUnifiedResponses.of("108", (String) null, null);

        assertEquals("108", ofWithStatusAndNullMessageAndNullData.getCode());
        assertEquals("", ofWithStatusAndNullMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndNullMessageAndNullData.getData());

        final String jacksonOfWithStatusAndNullMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndNullMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", jacksonOfWithStatusAndNullMessageAndNullData);

        final String gsonOfWithStatusAndNullMessageAndNullData = gson.toJson(ofWithStatusAndNullMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", gsonOfWithStatusAndNullMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndNullMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse<PageResult<User>> ofWithStatusAndNullMessageAndData = CustomUnifiedResponses.of("108", (String) null, emptyPageResult);
        assertEquals("108", ofWithStatusAndNullMessageAndData.getCode());
        assertEquals("", ofWithStatusAndNullMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndNullMessageAndData.getData());
        final String jacksonOfWithStatusAndNullMessageAndData = jackson.writeValueAsString(ofWithStatusAndNullMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonOfWithStatusAndNullMessageAndData);
        final String gsonOfWithStatusAndNullMessageAndData = gson.toJson(ofWithStatusAndNullMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonOfWithStatusAndNullMessageAndData);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage() throws Exception {
        UnifiedResponse<Void> ofWithStatusAndEmptyMessage = CustomUnifiedResponses.of("108", "");
        assertEquals("108", ofWithStatusAndEmptyMessage.getCode());
        assertEquals("", ofWithStatusAndEmptyMessage.getMessage());
        assertNull(ofWithStatusAndEmptyMessage.getData());

        final String jacksonOfWithStatusAndEmptyMessage = jackson.writeValueAsString(ofWithStatusAndEmptyMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", jacksonOfWithStatusAndEmptyMessage);

        final String gsonOfWithStatusAndEmptyMessage = gson.toJson(ofWithStatusAndEmptyMessage);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", gsonOfWithStatusAndEmptyMessage);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage_AndNullData() throws Exception {
        UnifiedResponse<Void> ofWithStatusAndEmptyMessageAndNullData = CustomUnifiedResponses.of("108", "", null);

        assertEquals("108", ofWithStatusAndEmptyMessageAndNullData.getCode());
        assertEquals("", ofWithStatusAndEmptyMessageAndNullData.getMessage());
        assertNull(ofWithStatusAndEmptyMessageAndNullData.getData());

        final String jacksonOfWithStatusAndEmptyMessageAndNullData = jackson.writeValueAsString(ofWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", jacksonOfWithStatusAndEmptyMessageAndNullData);

        final String gsonOfWithStatusAndEmptyMessageAndNullData = gson.toJson(ofWithStatusAndEmptyMessageAndNullData);
        assertEquals("{\"code\":\"108\",\"message\":\"\"}", gsonOfWithStatusAndEmptyMessageAndNullData);
    }

    @Test
    void testOf_WithStatusAndEmptyMessage_AndData() throws Exception {
        PageResult<User> emptyPageResult = PageResult.empty();
        UnifiedResponse<PageResult<User>> ofWithStatusAndEmptyMessageAndData = CustomUnifiedResponses.of("108", "", emptyPageResult);
        assertEquals("108", ofWithStatusAndEmptyMessageAndData.getCode());
        assertEquals("", ofWithStatusAndEmptyMessageAndData.getMessage());
        assertEquals(emptyPageResult, ofWithStatusAndEmptyMessageAndData.getData());
        final String jacksonOfWithStatusAndEmptyMessageAndData = jackson.writeValueAsString(ofWithStatusAndEmptyMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", jacksonOfWithStatusAndEmptyMessageAndData);
        final String gsonOfWithStatusAndEmptyMessageAndData = gson.toJson(ofWithStatusAndEmptyMessageAndData);
        assertEquals("{\"code\":\"108\",\"message\":\"\",\"data\":{\"total\":0,\"content\":[]}}", gsonOfWithStatusAndEmptyMessageAndData);
    }

    @Test
    void testOf_WithNullStatus() {
        final String nullStatus = null;
        final String nullMessage = null;
        final User user = new User("zhouxy", "zhouxy@gmail.com");

        // message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "查询失败"));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "查询失败", user));

        // empty message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, ""));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "", user));

        // null message
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, nullMessage));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "查询失败", null));
        assertThrows(NullPointerException.class, () -> CustomUnifiedResponses.of(nullStatus, "查询失败", user));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String username;
        private String email;
    }

    public static class CustomUnifiedResponses extends UnifiedResponses {

        public static final String SUCCESS_CODE = "0000000";
        public static final String DEFAULT_SUCCESS_MSG = "成功";

        public static UnifiedResponse<Void> success() {
            return of(SUCCESS_CODE, DEFAULT_SUCCESS_MSG);
        }

        public static UnifiedResponse<Void> success(@Nullable String message) {
            return of(SUCCESS_CODE, message);
        }

        public static <T> UnifiedResponse<T> success(@Nullable String message, @Nullable T data) {
            return of(SUCCESS_CODE, message, data);
        }

        private CustomUnifiedResponses() {
            super();
        }
    }

}
