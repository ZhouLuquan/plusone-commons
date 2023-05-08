package xyz.zhouxy.plusone.commons.jdbc;

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

    public SQL WHERE_IF(boolean condition, String sqlConditions) {
        if (condition) {
            return WHERE(sqlConditions);
        }
        return getSelf();
    }

}
