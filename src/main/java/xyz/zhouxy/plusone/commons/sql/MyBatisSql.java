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

import com.google.common.annotations.Beta;

@Beta
public class MyBatisSql extends SQL<MyBatisSql> {

    private final boolean withScript;

    MyBatisSql(boolean withScript) {
        super();
        this.withScript = withScript;
    }

    public static MyBatisSql newSql() {
        return new MyBatisSql(false);
    }

    public static MyBatisSql newScriptSql() {
        return new MyBatisSql(true);
    }

    @Override
    public MyBatisSql getSelf() {
        return this;
    }

    public static String IN(String col, String paramName) { // NOSONAR
        return " " + col + " IN" + buildForeach(col, paramName);
    }

    public static String NOT_IN(String col, String paramName) { // NOSONAR
        return col + " NOT IN" + buildForeach(col, paramName);
    }

    private static String buildForeach(String col, String paramName) {
        final String format = "<foreach" +
                " item=\"%s\"" +
                " index=\"index\"" +
                " collection=\"%s\"" +
                " open=\"(\"" +
                " separator=\",\"" +
                " close=\")\"" +
                ">" +
                "#{%s}" +
                "</foreach>";
        return String.format(format, col, paramName, col);
    }

    @Override
    public String toString() {
        if (withScript) {
            return "<script>\n" + super.toString() + "\n</script>";
        }
        return super.toString();
    }
}
