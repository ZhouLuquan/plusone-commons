package xyz.zhouxy.plusone.commons.collection;

import java.util.List;
import java.util.function.Function;

public class ListTools {

    public static <T> void transformValue(List<T> list, int index, Function<T, ? extends T> func) {
        list.set(index, func.apply(list.get(index)));
    }

    private ListTools() {
        throw new IllegalStateException("Utility class");
    }
}
