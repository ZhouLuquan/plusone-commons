package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static xyz.zhouxy.plusone.commons.jdbc.JdbcSql.IN;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import xyz.zhouxy.plusone.commons.jdbc.DbRecord;
import xyz.zhouxy.plusone.commons.jdbc.SQL;
import xyz.zhouxy.plusone.commons.jdbc.SimpleJdbcTemplate;

class SimpleJdbcTemplateTests {

    private static final Logger log = LoggerFactory.getLogger(SimpleJdbcTemplateTests.class);

    DataSource dataSource;

    String[] cStruct = {
            "id",
            "created_by",
            "create_time",
            "updated_by",
            "update_time",
            "status"
    };

    SimpleJdbcTemplateTests() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/plusone");
        config.setUsername("postgres");
        config.setPassword("zhouxy108");
        this.dataSource = new HikariDataSource(config);
    }

    @Test
    void testQuery() throws SQLException {
        try (Connection conn = this.dataSource.getConnection()) {
            Object[] params = SimpleJdbcTemplate.buildParams("501533", "501554", "544599");
            String sql = SQL.newJdbcSql()
                    .SELECT("*")
                    .FROM("test_table")
                    .WHERE(IN("id", params))
                    .toString();
            log.info(sql);
            List<DbRecord> rs = SimpleJdbcTemplate.connect(conn)
                    .queryToRecordList(sql, params);
            assertNotNull(rs);
            for (DbRecord baseEntity : rs) {
                // log.info("id: {}", baseEntity.getValueAsString("id"));
                log.info(baseEntity.toString());
                assertEquals(Optional.empty(), baseEntity.getValueAsString("updated_by"));
            }
        }
    }

    @Test
    void testTransaction() {
        try (Connection conn = dataSource.getConnection()) {
            SimpleJdbcTemplate.connect(conn)
                .tx(() -> {
                    SimpleJdbcTemplate.connect(conn)
                        .update("INSERT INTO base_table (created_by, create_time, status) VALUES (?, now(), 0)", 585757);
                    Optional<Map<String,Object>> first = SimpleJdbcTemplate.connect(conn)
                        .queryFirst("SELECT * FROM base_table WHERE created_by = ?", 585757);
                    log.info("first: {}", first);
                    throw new NullPointerException();
                });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
