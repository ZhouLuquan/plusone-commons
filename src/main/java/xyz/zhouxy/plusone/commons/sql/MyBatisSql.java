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

    public static String IN(String col, String paramName) {
        return " " + col + " IN" + buildForeach(col, paramName);
    }

    public static String NOT_IN(String col, String paramName) {
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