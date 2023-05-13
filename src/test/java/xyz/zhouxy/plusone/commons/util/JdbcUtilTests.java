package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import xyz.zhouxy.plusone.commons.jdbc.DbRecord;
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
        List<DbRecord> rs = JdbcUtil.connect(conn).queryToRecordList(
                "SELECT * FROM public.base_table WHERE id IN (?, ?, ?)", 501533, 501554, 544599);
        assertTrue(3 > rs.size());
        for (DbRecord baseEntity : rs) {
            log.info("id: {}", baseEntity.getValueAsLong("id"));
            assertEquals(Optional.empty(), baseEntity.getValueAsString("updated_by"));
        }
    }
}
