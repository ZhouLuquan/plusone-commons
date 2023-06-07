package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static xyz.zhouxy.plusone.commons.jdbc.SQL.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Splitter;
import com.google.common.io.Files;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import xyz.zhouxy.plusone.commons.jdbc.DbRecord;
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
            String sql = newSql()
                    .SELECT("*")
                    .FROM("test_table")
                    .WHERE(NOT_IN("id", params))
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
    void testSaveTxt() throws IOException, SQLException {
        File file = new File("C:\\Users\\zhouxy\\Desktop", "Untitled-1.txt");
        assertTrue(file.exists());
        List<String> recordStrList = Files.readLines(file, StandardCharsets.UTF_8);
        if (MoreCollections.isEmpty(recordStrList)) {
            log.info("file is empty.");
            return;
        }

        List<DbRecord> recordList = new ArrayList<>(recordStrList.size());
        for (String rStr : recordStrList) {
            List<String> props = Splitter.on("|").splitToList(rStr)
                    .stream()
                    .map(s -> {
                        if (s == null) {
                            return null;
                        }
                        s = s.trim();
                        return ("".equals(s) || "null".equals(s) || "NULL".equals(s)) ? null : s;
                    })
                    .collect(Collectors.toList());
            DbRecord r = new DbRecord();
            for (int i = 0; i < cStruct.length; i++) {
                r.put(cStruct[i], props.get(i));
            }
            recordList.add(r);
        }

        final String sql = "INSERT INTO test_table(id, created_by, create_time, updated_by, update_time, status) VALUES(?, ?, ?, ?, ?, ?)";
        final List<Object[]> params = SimpleJdbcTemplate.buildBatchParams(
                recordList,
                r -> SimpleJdbcTemplate.buildParams(
                        r.getValueAsString("id"),
                        r.getValueAsString("created_by"),
                        r.getValueAsString("create_time"),
                        r.getValueAsString("updated_by"),
                        r.getValueAsString("update_time"),
                        r.getValueAsString("status")));
        log.info("params: {}", SimpleJdbcTemplate.paramsToString(params));
        try (Connection conn = this.dataSource.getConnection()) {
            SimpleJdbcTemplate.connect(conn).tx(() -> {
                SimpleJdbcTemplate.connect(conn).batchUpdate(sql, params, 20);

                File targetFile = new File("C:\\Users\\zhouxy\\Desktop\\done", file.getName());
                boolean moveSucceeded = file.renameTo(targetFile);
                if (!moveSucceeded) {
                    throw new IOException("文件移动失败：" + file.getName());
                }
            });
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
