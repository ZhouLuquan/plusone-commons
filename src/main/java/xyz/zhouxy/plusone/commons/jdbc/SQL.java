/*
 * Copyright 2022-2023 the original author or authors.
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

package xyz.zhouxy.plusone.commons.jdbc;

import java.util.Collection;

import org.apache.ibatis.jdbc.AbstractSQL;

import com.google.common.annotations.Beta;

/**
 * @author ZhouXY
 */
@Beta
public class SQL extends AbstractSQL<SQL> {

    @Override
    public SQL getSelf() {
        return this;
    }

    public static SQL newSql() {
        return new SQL();
    }

    public SQL WHERE(boolean condition, String sqlConditions) {
        if (condition) {
            return WHERE(sqlConditions);
        }
        return getSelf();
    }

    public static String IN(String col, Collection<?> c) {
        return IN(col, c.size());
    }

    public static <T> String IN(String col, T[] c) {
        return IN(col, c.length);
    }

    private static String IN(String col, int length) {
        return new StringBuilder()
                .append(col)
                .append(" IN (")
                .append(buildQuestionsList(length))
                .append(')')
                .toString();
    }

    public static String NOT_IN(String col, Collection<?> c) {
        return NOT_IN(col, c.size());
    }

    public static <T> String NOT_IN(String col, T[] c) {
        return NOT_IN(col, c.length);
    }

    private static String NOT_IN(String col, int length) {
        return new StringBuilder()
                .append(col)
                .append(" NOT IN (")
                .append(buildQuestionsList(length))
                .append(')')
                .toString();
    }

    private static String buildQuestionsList(int times) {
        char[] arr = new char[times * 3 - 2];
        int i = 0;
        for (int t = 1; t <= times; t++) {
            arr[i++] = '?';
            if (t < times) {
                arr[i++] = ',';
                arr[i++] = ' ';
            }
        }
        return String.valueOf(arr);
    }
}
