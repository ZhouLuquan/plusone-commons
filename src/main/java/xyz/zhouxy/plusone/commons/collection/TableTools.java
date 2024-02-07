package xyz.zhouxy.plusone.commons.collection;

import com.google.common.collect.Table;

public class TableTools {

    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> t) {
        if (t instanceof SynchronizedTable) {
            return t;
        }
        return SynchronizedTable.of(t);
    }

    private TableTools() {
        throw new IllegalStateException("Utility class");
    }
}
