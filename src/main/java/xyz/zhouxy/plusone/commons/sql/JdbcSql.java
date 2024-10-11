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

import java.util.Collection;

public class JdbcSql extends SQL<JdbcSql> {

    JdbcSql() {
        super();
    }

    public static JdbcSql newSql() {
        return new JdbcSql();
    }

    @Override
    public JdbcSql getSelf() {
        return this;
    }

    public static String IN(String col, Collection<?> c) {
        return IN(col, c.size());
    }

    public static <T> String IN(String col, T[] c) {
        return IN(col, c.length);
    }

    private static String IN(String col, int length) {
        return col + " IN (" + String.valueOf(buildQuestionsList(length)) + ')';
    }

    public static String NOT_IN(String col, Collection<?> c) {
        return NOT_IN(col, c.size());
    }

    public static <T> String NOT_IN(String col, T[] c) {
        return NOT_IN(col, c.length);
    }

    private static String NOT_IN(String col, int length) {
        return col + " NOT IN (" + String.valueOf(buildQuestionsList(length)) + ')';
    }

    private static char[] buildQuestionsList(int times) {
        char[] arr = new char[times * 3 - 2];
        int i = 0;
        for (int t = 1; t <= times; t++) {
            arr[i++] = '?';
            if (t < times) {
                arr[i++] = ',';
                arr[i++] = ' ';
            }
        }
        return arr;
    }
}
