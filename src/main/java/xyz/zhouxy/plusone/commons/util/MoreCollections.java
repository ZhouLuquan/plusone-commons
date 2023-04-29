package xyz.zhouxy.plusone.commons.util;

import java.util.Collection;

import javax.annotation.Nullable;

public class MoreCollections {
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    private MoreCollections() {
        throw new IllegalStateException("Utility class");
    }
}
