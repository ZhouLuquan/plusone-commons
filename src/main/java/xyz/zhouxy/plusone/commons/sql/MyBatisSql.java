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
        return new StringBuilder(" ")
                .append(col)
                .append(" IN")
                .append(buildQuestionsList(col, paramName))
                .toString();
    }

    public static String NOT_IN(String col, String paramName) {
        return new StringBuilder()
                .append(col)
                .append(" NOT IN")
                .append(buildQuestionsList(col, paramName))
                .toString();
    }

    private static String buildQuestionsList(String col, String paramName) {
        return new StringBuilder()
                .append("<foreach item=\"")
                .append(col)
                .append("\" index=\"index\" collection=\"")
                .append(paramName)
                .append("\" open=\"(\" separator=\",\" close=\")\">")
                .append("#{")
                .append(col)
                .append("}</foreach>")
                .toString();
    }

    @Override
    public String toString() {
        String str = super.toString();
        if (withScript) {
            str = "<script>\n" + str + "\n</script>";
        }
        return str;
    }
}