package xyz.zhouxy.plusone.commons.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

import org.apache.commons.lang3.ArrayUtils;

import com.google.common.annotations.Beta;

import xyz.zhouxy.plusone.commons.util.DbRecord;
import xyz.zhouxy.plusone.commons.util.MoreCollections;

@Beta
public class JdbcUtil {

    public static JdbcExecutor connect(Connection conn) {
        return new JdbcExecutor(conn);
    }

    private JdbcUtil() {
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

        public Optional<String> queryToString(String sql, Object... params) throws SQLException {
            List<String> result = query(sql, params, (ResultSet rs) -> rs.getString(1));
            return MoreCollections.isNotEmpty(result) ? Optional.ofNullable(result.get(0)) : Optional.empty();
        }

        public OptionalInt queryToInt(String sql, Object... params) throws SQLException {
            List<Integer> result = query(sql, params, (ResultSet rs) -> rs.getBigDecimal(1).intValue());
            Integer i = MoreCollections.isNotEmpty(result) ? result.get(0) : null;
            return i != null ? OptionalInt.of(i) : OptionalInt.empty();
        }

        public OptionalLong queryToLong(String sql, Object... params) throws SQLException {
            List<Long> result = query(sql, params, (ResultSet rs) -> rs.getBigDecimal(1).longValue());
            Long i = MoreCollections.isNotEmpty(result) ? result.get(0) : null;
            return i != null ? OptionalLong.of(i) : OptionalLong.empty();
        }

        public Optional<BigDecimal> queryToBigDecimal(String sql, Object... params) throws SQLException {
            List<BigDecimal> result = query(sql, params, (ResultSet rs) -> rs.getBigDecimal(1));
            return MoreCollections.isNotEmpty(result) ? Optional.ofNullable(result.get(0)) : Optional.empty();
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
            int[] result = {};
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
                        result = ArrayUtils.addAll(result, n);
                        stmt.clearBatch();
                    }
                }
                return result;
            }
        }
    }
}
