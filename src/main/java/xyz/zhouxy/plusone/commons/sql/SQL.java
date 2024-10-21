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

import org.apache.ibatis.jdbc.AbstractSQL;

import com.google.common.annotations.Beta;

/**
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 */
@Beta
public abstract class SQL<T> extends AbstractSQL<T> {

    public static JdbcSql newJdbcSql() {
        return new JdbcSql();
    }

    public static MyBatisSql newMyBatisSql(boolean withScript) {
        return new MyBatisSql(withScript);
    }

    public T WHERE(boolean condition, String sqlCondition) {
        if (condition) {
            return WHERE(sqlCondition);
        }
        return getSelf();
    }

    public T WHERE(boolean condition, String ifSqlCondition, String elseSqlCondition) {
        return WHERE(condition ? ifSqlCondition : elseSqlCondition);
    }
}
