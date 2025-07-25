/*
 * Copyright 2024-2025 the original author or authors.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import xyz.zhouxy.plusone.commons.gson.adapter.JSR310TypeAdapters.LocalDateTimeTypeAdapter;
import xyz.zhouxy.plusone.commons.gson.adapter.JSR310TypeAdapters.LocalDateTypeAdapter;
import xyz.zhouxy.plusone.commons.model.dto.PageResult;
import xyz.zhouxy.plusone.commons.model.dto.PagingAndSortingQueryParams;
import xyz.zhouxy.plusone.commons.model.dto.PagingParams;

@Slf4j
public class PagingAndSortingQueryParamsTests {

    static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp() throws Exception {
        initDatabase();

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    static void initDatabase() throws Exception {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=FALSE;MODE=MySQL");
        dataSource.setUser("sa");
        dataSource.setPassword("");

        List<AccountVO> data = Lists.newArrayList(
            new AccountVO(1L, "zhouxy01", "zhouxy01@qq.com", 0, 108L, LocalDateTime.of(2020, 1, 1, 13, 15), 0L),
            new AccountVO(2L, "zhouxy02", "zhouxy02@qq.com", 0, 108L, LocalDateTime.of(2020, 1, 2, 13, 15), 0L),
            new AccountVO(3L, "zhouxy03", "zhouxy03@qq.com", 0, 108L, LocalDateTime.of(2020, 1, 3, 13, 15), 0L),
            new AccountVO(4L, "zhouxy04", "zhouxy04@qq.com", 0, 108L, LocalDateTime.of(2020, 1, 4, 13, 15), 0L),
            new AccountVO(5L, "zhouxy05", "zhouxy05@qq.com", 0, 108L, LocalDateTime.of(2020, 1, 5, 13, 15), 0L),
            new AccountVO(6L, "zhouxy06", "zhouxy06@qq.com", 0, 108L, LocalDateTime.of(2024, 1, 6, 13, 15), 0L),
            new AccountVO(7L, "zhouxy07", "zhouxy07@qq.com", 0, 108L, LocalDateTime.of(2024, 1, 7, 13, 15), 0L),
            new AccountVO(8L, "zhouxy08", "zhouxy08@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 8, 13, 15), 0L),
            new AccountVO(9L, "zhouxy09", "zhouxy09@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 9, 13, 15), 0L),
            new AccountVO(10L, "zhouxy10", "zhouxy10@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 10, 13, 15), 0L),
            new AccountVO(11L, "zhouxy11", "zhouxy11@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 11, 13, 15), 0L),
            new AccountVO(12L, "zhouxy12", "zhouxy12@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 12, 13, 15), 0L),
            new AccountVO(13L, "zhouxy13", "zhouxy13@qq.com", 1, 108L, LocalDateTime.of(2024, 5, 13, 13, 15), 0L),
            new AccountVO(14L, "zhouxy14", "zhouxy14@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 14, 13, 15), 0L),
            new AccountVO(15L, "zhouxy15", "zhouxy15@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 15, 13, 15), 0L),
            new AccountVO(16L, "zhouxy16", "zhouxy16@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 16, 13, 15), 0L),
            new AccountVO(17L, "zhouxy17", "zhouxy17@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 17, 13, 15), 0L),
            new AccountVO(18L, "zhouxy18", "zhouxy18@qq.com", 1, 108L, LocalDateTime.of(2024, 10, 18, 13, 15), 0L),
            new AccountVO(19L, "zhouxy19", "zhouxy19@qq.com", 1, 108L, LocalDateTime.of(2024, 11, 19, 13, 15), 0L),
            new AccountVO(20L, "zhouxy20", "zhouxy20@qq.com", 1, 108L, LocalDateTime.of(2024, 12, 20, 13, 15), 0L)
        );

        try (Connection conn = dataSource.getConnection()) {
            try (Statement statement = conn.createStatement()) {
                String ddl = "CREATE TABLE sys_account ("
                    + "\n" + "  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY"
                    + "\n" + "  ,username VARCHAR(255) NOT NULL"
                    + "\n" + "  ,email VARCHAR(255) NOT NULL"
                    + "\n" + "  ,status VARCHAR(2) NOT NULL"
                    + "\n" + "  ,create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP"
                    + "\n" + "  ,created_by BIGINT NOT NULL"
                    + "\n" + "  ,version BIGINT NOT NULL DEFAULT 0"
                    + "\n" + ")";
                statement.execute(ddl);
            }

            String sql = "INSERT INTO sys_account(id, username, email, status, create_time, created_by, version) VALUES"
                + "\n" + "(?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                for (AccountVO a : data) {
                    statement.setObject(1, a.getId());
                    statement.setObject(2, a.getUsername());
                    statement.setObject(3, a.getEmail());
                    statement.setObject(4, a.getStatus());
                    statement.setObject(5, a.getCreateTime());
                    statement.setObject(6, a.getCreatedBy());
                    statement.setObject(7, a.getVersion());
                    statement.addBatch();
                }
                statement.executeBatch();
                statement.clearBatch();
            }
        }
    }

    static final String JSON_STR = "" +
            "{\n" +
            "    \"pageNum\": 3,\n" +
            "    \"size\": 3,\n" +
            "    \"orderBy\": [\"username-asc\"],\n" +
            "    \"createTimeStart\": \"2024-05-06\",\n" +
            "    \"createTimeEnd\": \"2030-07-06\"" +
            "}";

    static final String WRONG_JSON_STR = "" +
            "{\n" +
            "    \"pageNum\": 3,\n" +
            "    \"size\": 3,\n" +
            "    \"orderBy\": [\"status-asc\"],\n" +
            "    \"createTimeStart\": \"2024-05-06\",\n" +
            "    \"createTimeEnd\": \"2030-07-06\"" +
            "}";

    @Test
    void testJackson() throws Exception {
        ObjectMapper jackson = new ObjectMapper();
        jackson.registerModule(new JavaTimeModule());
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountQueryParams params = jackson.readValue(JSON_STR, AccountQueryParams.class);
            PagingParams pagingParams = params.buildPagingParams();

            AccountQueries accountQueries = session.getMapper(AccountQueries.class);
            List<AccountVO> list = accountQueries.queryAccountList(params, pagingParams);
            long count = accountQueries.countAccount(params);
            PageResult<AccountVO> accountPageResult = PageResult.of(list, count);
            log.info(jackson.writeValueAsString(accountPageResult));

            assertEquals(Lists.newArrayList(
                new AccountVO(14L, "zhouxy14", "zhouxy14@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 14, 13, 15), 0L),
                new AccountVO(15L, "zhouxy15", "zhouxy15@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 15, 13, 15), 0L),
                new AccountVO(16L, "zhouxy16", "zhouxy16@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 16, 13, 15), 0L)
            ), accountPageResult.getContent());
            assertEquals(13, accountPageResult.getTotal());
        } catch (Exception e) {
            log.error("测试不通过", e);
            throw e;
        }

        AccountQueryParams queryParams = jackson.readValue(WRONG_JSON_STR, AccountQueryParams.class);
        assertThrows(IllegalArgumentException.class, queryParams::buildPagingParams);
    }

    @Test
    void testGson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter().nullSafe())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter().nullSafe())
                .create();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountQueryParams params = gson.fromJson(JSON_STR, AccountQueryParams.class);
            log.info(params.toString());
            PagingParams pagingParams = params.buildPagingParams();
            log.info("pagingParams: {}", pagingParams);
            AccountQueries accountQueries = session.getMapper(AccountQueries.class);
            List<AccountVO> list = accountQueries.queryAccountList(params, pagingParams);
            long count = accountQueries.countAccount(params);
            PageResult<AccountVO> accountPageResult = PageResult.of(list, count);

            log.info(gson.toJson(accountPageResult));

            assertEquals(Lists.newArrayList(
                new AccountVO(14L, "zhouxy14", "zhouxy14@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 14, 13, 15), 0L),
                new AccountVO(15L, "zhouxy15", "zhouxy15@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 15, 13, 15), 0L),
                new AccountVO(16L, "zhouxy16", "zhouxy16@qq.com", 1, 108L, LocalDateTime.of(2024, 8, 16, 13, 15), 0L)
            ), accountPageResult.getContent());
            assertEquals(13, accountPageResult.getTotal());
        } catch (Exception e) {
            log.error("测试不通过", e);
            throw e;
        }

        AccountQueryParams queryParams = gson.fromJson(WRONG_JSON_STR, AccountQueryParams.class);
        assertThrows(IllegalArgumentException.class, queryParams::buildPagingParams);
    }
}


/**
 * 账号信息查询参数
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 */
@ToString(callSuper = true)
class AccountQueryParams extends PagingAndSortingQueryParams {

    private static final Map<String, String> PROPERTY_COLUMN_MAP = ImmutableMap.<String, String>builder()
            .put("id", "id")
            .put("username", "username")
            .put("createTime", "create_time")
            .build();

    public AccountQueryParams() {
        super(PROPERTY_COLUMN_MAP);
    }

    private @Getter @Setter Long id;
    private @Getter @Setter String username;
    private @Getter @Setter String email;
    private @Getter @Setter Integer status;
    private @Getter @Setter Long createdBy;
    private @Getter @Setter LocalDate createTimeStart;
    private @Setter LocalDate createTimeEnd;

    public LocalDate getCreateTimeEnd() {
        if (this.createTimeEnd == null) {
            return null;
        }
        return this.createTimeEnd.plusDays(1);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class AccountVO {
    private Long id;
    private String username;
    private String email;
    private Integer status;
    private Long createdBy;
    private LocalDateTime createTime;
    private Long version;
}

interface AccountQueries {

    List<AccountVO> queryAccountList(@Param("query") AccountQueryParams query,
                                     @Param("page") PagingParams page);

    long countAccount(@Param("query") AccountQueryParams query);
}
