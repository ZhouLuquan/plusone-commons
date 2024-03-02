package xyz.zhouxy.plusone.commons.collection;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nullable;

public class CollectionTools {

    // isEmpty

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    // isNotEmpty

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    private CollectionTools() {
        throw new IllegalStateException("Utility class");
    }
}
