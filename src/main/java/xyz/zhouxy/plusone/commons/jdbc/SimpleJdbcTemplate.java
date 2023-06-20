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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.annotations.Beta;
import com.google.common.collect.Lists;

import xyz.zhouxy.plusone.commons.util.Assert;
import xyz.zhouxy.plusone.commons.util.MoreArrays;
import xyz.zhouxy.plusone.commons.util.OptionalUtil;

@Beta
public class SimpleJdbcTemplate {

    public static JdbcExecutor connect(final Connection conn) {
        return new JdbcExecutor(conn);
    }

    public static Object[] buildParams(final Object... params) {
        return Arrays.stream(params)
                .map(p -> {
                    if (p instanceof Optional) {
                        return OptionalUtil.orElseNull((Optional<?>) p);
                    }
                    if (p instanceof OptionalInt) {
                        OptionalInt _p = ((OptionalInt) p);
                        return _p.isPresent() ? _p.getAsInt() : null;
                    }
                    if (p instanceof OptionalLong) {
                        OptionalLong _p = ((OptionalLong) p);
                        return _p.isPresent() ? _p.getAsLong() : null;
                    }
                    if (p instanceof OptionalDouble) {
                        OptionalDouble _p = ((OptionalDouble) p);
                        return _p.isPresent() ? _p.getAsDouble() : null;
                    }
                    return p;
                })
                .toArray();
    }

    public static <T> List<Object[]> buildBatchParams(final Collection<T> c, Function<T, Object[]> function) {
        return c.stream().map(function).collect(Collectors.toList());
    }

    public static String paramsToString(Object[] params) {
        return Arrays.toString(params);
    }

    public static String paramsToString(final Collection<Object[]> params) {
        if (params == null) {
            return "null";
        }
        if (params.isEmpty()) {
            return "[]";
        }
        int iMax = params.size() - 1;
        StringBuilder b = new StringBuilder();
        b.append('[');
        int i = 0;
        for (Object[] p : params) {
            b.append(Arrays.toString(p));
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(',');
            i++;
        }
        return b.append(']').toString();
    }

    private SimpleJdbcTemplate() {
        throw new IllegalStateException("Utility class");
    }

    public static class JdbcExecutor {

        private final Connection conn;

        public JdbcExecutor(Connection conn) {
            this.conn = conn;
        }

        public <T> List<T> query(String sql, Object[] params, ResultMap<T> resultMap) throws SQLException {
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        stmt.setObject(i + 1, params[i]);
                    }
                }
                try (ResultSet rs = stmt.executeQuery()) {
                    List<T> result = new ArrayList<>();
                    while (rs.next()) {
                        T e = resultMap.map(rs);
                        result.add(e);
                    }
                    return result;
                }
            }
        }

        public <T> Optional<T> queryFirst(String sql, Object[] params, ResultMap<T> resultMap) throws SQLException {
            List<T> list = query(sql, params, resultMap);
            return (list.isEmpty()) ? Optional.empty() : Optional.ofNullable(list.get(0));
        }

        public static final ResultMap<Map<String, Object>> mapResultMap = rs -> {
            Map<String, Object> result = new HashMap<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String colName = metaData.getColumnName(i);
                result.put(colName, rs.getObject(colName));
            }
            return result;
        };

        public List<Map<String, Object>> query(String sql, Object... params) throws SQLException {
            return query(sql, params, mapResultMap);
        }

        public Optional<Map<String, Object>> queryFirst(String sql, Object... params) throws SQLException {
            return queryFirst(sql, params, mapResultMap);
        }

        public static final ResultMap<DbRecord> recordResultMap = rs -> {
            DbRecord result = new DbRecord();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String colName = metaData.getColumnName(i);
                result.put(colName, rs.getObject(colName));
            }
            return result;
        };

        public List<DbRecord> queryToRecordList(String sql, Object... params) throws SQLException {
            return query(sql, params, recordResultMap);
        }

        public Optional<DbRecord> queryFirstRecord(String sql, Object... params) throws SQLException {
            return queryFirst(sql, params, recordResultMap);
        }

        public Optional<String> queryToString(String sql, Object... params) throws SQLException {
            return queryFirst(sql, params, (ResultSet rs) -> rs.getString(1));
        }

        public OptionalInt queryToInt(String sql, Object... params) throws SQLException {
            Optional<Integer> result = queryFirst(sql, params, (ResultSet rs) -> rs.getInt(1));
            return OptionalUtil.toOptionalInt(result);
        }

        public OptionalLong queryToLong(String sql, Object... params) throws SQLException {
            Optional<Long> result = queryFirst(sql, params, (ResultSet rs) -> rs.getLong(1));
            return OptionalUtil.toOptionalLong(result);
        }

        public OptionalDouble queryToDouble(String sql, Object... params) throws SQLException {
            Optional<Double> result = queryFirst(sql, params, (ResultSet rs) -> rs.getDouble(1));
            return OptionalUtil.toOptionalDouble(result);
        }

        public Optional<BigDecimal> queryToBigDecimal(String sql, Object... params) throws SQLException {
            return queryFirst(sql, params, (ResultSet rs) -> rs.getBigDecimal(1));
        }

        public int update(String sql, Object... params) throws SQLException {
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                if (params != null && params.length > 0) {
                    for (int i = 0; i < params.length; i++) {
                        stmt.setObject(i + 1, params[i]);
                    }
                }
                return stmt.executeUpdate();
            }
        }

        public int[] batchUpdate(String sql, Collection<Object[]> params, int batchSize) throws SQLException {
            int executeCount = params.size() / batchSize;
            executeCount = (params.size() % batchSize == 0) ? executeCount : (executeCount + 1);
            List<int[]> result = Lists.newArrayListWithCapacity(executeCount);
            
            try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
                int i = 0;
                for (Object[] ps : params) {
                    i++;
                    for (int j = 0; j < ps.length; j++) {
                        stmt.setObject(j + 1, ps[j]);
                    }
                    stmt.addBatch();
                    if (i % batchSize == 0 || i >= params.size()) {
                        int[] n = stmt.executeBatch();
                        result.add(n);
                        stmt.clearBatch();
                    }
                }
                return MoreArrays.concatIntArray(result);
            }
        }

        public void tx(final IAtom tx) throws Exception {
            Assert.notNull(tx, "Tx can not be null.");
            try {
                this.conn.setAutoCommit(false);
                tx.execute();
                conn.commit();
                conn.setAutoCommit(true);
            } catch (Exception e) {
                conn.rollback();
                conn.setAutoCommit(true);
                throw e;
            }
        }

        @FunctionalInterface
        public static interface IAtom {
            @SuppressWarnings("all")
            void execute() throws Exception;
        }
    }
}
