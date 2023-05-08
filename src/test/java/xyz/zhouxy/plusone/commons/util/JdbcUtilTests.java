package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import xyz.zhouxy.plusone.commons.jdbc.JdbcUtil;

class JdbcUtilTests {

    private static final Logger log = LoggerFactory.getLogger(JdbcUtilTests.class);

    static final String url = "jdbc:postgresql://localhost:5432/plusone";
    static final String username = "postgres";
    static final String password = "zhouxy108";

    DataSource dataSource;

    String[] cStruct = {
            "id",
            "created_by",
            "create_time",
            "updated_by",
            "update_time",
            "status"
    };

    JdbcUtilTests() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        this.dataSource = new HikariDataSource(config);
    }

    @Test
    void testQuery() throws SQLException {
        Connection conn = this.dataSource.getConnection();
        List<Map<String, Object>> ms = JdbcUtil.connect(conn).query(
                "SELECT * FROM public.base_table WHERE id IN (?, ?, ?)", 501533, 501554, 544599);
        assertNotNull(ms);
        List<DbRecord> es = ms.stream()
                .map(input -> new DbRecord().putAll(input))
                .collect(Collectors.toList());
        assertEquals(3, es.size());
        for (DbRecord baseEntity : es) {
            log.info("id: {}", baseEntity.getValueAsLong("id"));
            assertNull(baseEntity.getValueAsString("updated_by"));
        }
    }
}
