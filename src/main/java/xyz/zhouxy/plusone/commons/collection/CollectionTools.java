package xyz.zhouxy.plusone.commons.collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

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

    // Collection -> Map

    public static <K, V> HashMap<K, V> toHashMap(
            Iterable<V> c,
            Function<? super V, K> keyGenerator,
            int initialCapacity) {
        HashMap<K, V> map = new HashMap<>(initialCapacity);
        fillIntoEmptyMap(map, c, keyGenerator);
        return map;
    }

    public static <K, V> HashMap<K, V> toHashMap(
            Collection<V> c,
            Function<? super V, K> keyGenerator) {
        return toHashMap(c, keyGenerator, c.size());
    }

    public static <K, V> SafeConcurrentHashMap<K, V> toConcurrentHashMap(
            Iterable<V> c,
            Function<? super V, K> keyGenerator,
            int initialCapacity) {
        SafeConcurrentHashMap<K, V> map = new SafeConcurrentHashMap<>(initialCapacity);
        fillIntoEmptyMap(map, c, keyGenerator);
        return map;
    }

    public static <K, V> SafeConcurrentHashMap<K, V> toConcurrentHashMap(
            Collection<V> c,
            Function<? super V, K> keyGenerator) {
        return toConcurrentHashMap(c, keyGenerator, c.size());
    }

    public static <K extends Comparable<? super K>, V> TreeMap<K, V> toTreeMap(
            Iterable<V> c,
            Function<? super V, K> keyGenerator) {
        TreeMap<K, V> map = new TreeMap<>();
        fillIntoEmptyMap(map, c, keyGenerator);
        return map;
    }

    public static <K, V> TreeMap<K, V> toTreeMap(
            Iterable<V> c,
            Function<? super V, K> keyGenerator,
            Comparator<? super K> keycComparator) {
        TreeMap<K, V> map = new TreeMap<>(keycComparator);
        fillIntoEmptyMap(map, c, keyGenerator);
        return map;
    }

    public static <K, V> void fillIntoEmptyMap(
            Map<K, ? super V> map,
            Iterable<V> c,
            Function<? super V, K> keyGenerator) {
        Preconditions.checkNotNull(map);
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(keyGenerator);
        Preconditions.checkArgument(map.isEmpty(), "The map should be empty.");
        for (V v : c) {
            map.put(keyGenerator.apply(v), v);
        }
    }

    private CollectionTools() {
        throw new IllegalStateException("Utility class");
    }
}
