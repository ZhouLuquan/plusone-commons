/*
 * Copyright 2023-2024 the original author or authors.
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

package xyz.zhouxy.plusone.commons.sql;

import static xyz.zhouxy.plusone.commons.sql.MyBatisSql.IN;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
