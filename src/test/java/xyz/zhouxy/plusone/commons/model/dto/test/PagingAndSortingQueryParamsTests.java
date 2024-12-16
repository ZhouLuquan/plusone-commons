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

package xyz.zhouxy.plusone.commons.model.dto.test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import xyz.zhouxy.plusone.commons.model.dto.PagingAndSortingQueryParams;
import xyz.zhouxy.plusone.commons.model.dto.PagingParams;

@Slf4j
public //
class PagingAndSortingQueryParamsTests {
    static final String JSON_STR = "" +
            "{\n" +
            "    \"size\": 15,\n" +
            "    \"orderBy\": [\"username-asc\"],\n" +
            "    \"createTimeStart\": \"2024-05-06\",\n" +
            "    \"createTimeEnd\": \"2024-07-06\",\n" +
            "    \"updateTimeStart\": \"2024-08-06\",\n" +
            "    \"updateTimeEnd\": \"2024-10-06\",\n" +
            "    \"mobilePhone\": \"13169053215\"\n" +
            "}";

    @Test
    void testJackson() throws Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            om.registerModule(new JavaTimeModule());
            AccountQueryParams params = om.readValue(JSON_STR, AccountQueryParams.class);
            log.info(params.toString());
            PagingParams pagingParams = params.buildPagingParams();
            log.info("pagingParams: {}", pagingParams);
        } catch (Exception e) {
            log.error("测试不通过", e);
            throw e;
        }
    }

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static final TypeAdapter<LocalDate> dateAdapter = new TypeAdapter<LocalDate>() {

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            out.value(dateFormatter.format(value));
        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            return LocalDate.parse(in.nextString(), dateFormatter);
        }

    };

    @Test
    void testGson() throws Exception {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, dateAdapter)
                    .create();
            AccountQueryParams params = gson.fromJson(JSON_STR, AccountQueryParams.class);
            log.info(params.toString());
            PagingParams pagingParams = params.buildPagingParams();
            log.info("pagingParams: {}", pagingParams);
        } catch (Exception e) {
            log.error("测试不通过", e);
            throw e;
        }
    }
}


/**
 * 账号信息查询参数
 *
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
@ToString(callSuper = true)
class AccountQueryParams extends PagingAndSortingQueryParams {

    private static final Map<String, String> PROPERTY_COLUMN_MAP = ImmutableMap.<String, String>builder()
            .put("id", "id")
            .put("username", "username")
            .put("email", "email")
            .put("mobilePhone", "mobile_phone")
            .put("status", "status")
            .put("nickname", "nickname")
            .put("sex", "sex")
            .put("createdBy", "created_by")
            .put("createTime", "create_time")
            .put("updatedBy", "updated_by")
            .put("updateTime", "update_time")
            .build();

    public AccountQueryParams() {
        super(PROPERTY_COLUMN_MAP);
    }

    private @Getter @Setter Long id;
    private @Getter @Setter String username;
    private @Getter @Setter String email;
    private @Getter @Setter String mobilePhone;
    private @Getter @Setter Integer status;
    private @Getter @Setter String nickname;
    private @Getter @Setter Integer sex;
    private @Getter @Setter Long createdBy;
    private @Getter @Setter LocalDate createTimeStart;
    private @Setter LocalDate createTimeEnd;
    private @Getter @Setter Long updatedBy;
    private @Getter @Setter LocalDate updateTimeStart;
    private @Setter LocalDate updateTimeEnd;
    private @Getter @Setter Long roleId;

    public LocalDate getCreateTimeEnd() {
        if (this.createTimeEnd == null) {
            return null;
        }
        return this.createTimeEnd.plusDays(1);
    }

    public LocalDate getUpdateTimeEnd() {
        if (this.updateTimeEnd == null) {
            return null;
        }
        return this.updateTimeEnd.plusDays(1);
    }
}
