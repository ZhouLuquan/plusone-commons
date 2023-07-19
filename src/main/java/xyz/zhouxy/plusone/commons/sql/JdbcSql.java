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