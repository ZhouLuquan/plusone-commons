package xyz.zhouxy.plusone.commons.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.common.annotations.Beta;

@Beta
@FunctionalInterface
public interface ResultMap<T> {
    T map(ResultSet rs) throws SQLException;
}
