package xyz.zhouxy.plusone.commons.util;

import static xyz.zhouxy.plusone.commons.sql.MyBatisSql.IN;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.zhouxy.plusone.commons.sql.MyBatisSql;

class MyBatisSqlBuilderTests {
    private static final Logger log = LoggerFactory.getLogger(MyBatisSqlBuilderTests.class);

    @Test
    void test() {
        // List<String> ids = Arrays.asList("2333", "4501477");
        MyBatisSql sql = MyBatisSql.newScriptSql()
                .SELECT("*")
                .FROM("test_table")
                .WHERE(IN("id", "ids"));
        log.info("sql: {}", sql);
    }
}
