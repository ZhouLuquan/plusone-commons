package xyz.zhouxy.plusone.commons.collection;

import java.util.Map;
import java.util.function.Function;

public class MapTools {

    public static <K, V> void transformValue(Map<K, V> map, K key, Function<V, ? extends V> func) {
        if (map.containsKey(key)) {
            map.put(key, func.apply(map.get(key)));
        }
    }

    private MapTools() {
        throw new IllegalStateException("Utility class");
    }
}
