package xyz.zhouxy.plusone.commons.collection;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import javax.annotation.concurrent.ThreadSafe;

import xyz.zhouxy.plusone.commons.base.JRE;

@ThreadSafe
public class SafeConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 4352954948768449595L;

    /**
     * Creates a new, empty map with the default initial table size (16).
     */
    public SafeConcurrentHashMap() {
    }

    /**
     * Creates a new, empty map with an initial table size
     * accommodating the specified number of elements without the need
     * to dynamically resize.
     *
     * @param initialCapacity The implementation performs internal
     *                        sizing to accommodate this many elements.
     * @throws IllegalArgumentException if the initial capacity of
     *                                  elements is negative
     */
    public SafeConcurrentHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new map with the same mappings as the given map.
     *
     * @param m the map
     */
    public SafeConcurrentHashMap(Map<? extends K, ? extends V> m) {
        super(m);
    }

    /**
     * Creates a new, empty map with an initial table size based on
     * the given number of elements ({@code initialCapacity}) and
     * initial table density ({@code loadFactor}).
     *
     * @param initialCapacity the initial capacity. The implementation
     *                        performs internal sizing to accommodate this many elements,
     *                        given the specified load factor.
     * @param loadFactor      the load factor (table density) for
     *                        establishing the initial table size
     * @throws IllegalArgumentException if the initial capacity of
     *                                  elements is negative or the load factor is nonpositive
     * @since 1.6
     */
    public SafeConcurrentHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    /**
     * Creates a new, empty map with an initial table size based on
     * the given number of elements ({@code initialCapacity}), table
     * density ({@code loadFactor}), and number of concurrently
     * updating threads ({@code concurrencyLevel}).
     *
     * @param initialCapacity  the initial capacity. The implementation
     *                         performs internal sizing to accommodate this many elements,
     *                         given the specified load factor.
     * @param loadFactor       the load factor (table density) for
     *                         establishing the initial table size
     * @param concurrencyLevel the estimated number of concurrently
     *                         updating threads. The implementation may use this value as
     *                         a sizing hint.
     * @throws IllegalArgumentException if the initial capacity is
     *                                  negative or the load factor or concurrencyLevel are
     *                                  nonpositive
     */
    public SafeConcurrentHashMap(int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(initialCapacity, loadFactor, concurrencyLevel);
    }

    /** {@inheritDoc} */
    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        if (JRE.isJava8()) {
            V v = get(key);
            if (null == v) {
                // this bug fix methods maybe cause `mappingFunction.apply` multiple calls.
                v = mappingFunction.apply(key);
                if (null == v) {
                    return null;
                }
                final V res = putIfAbsent(key, v);
                if (null != res) {
                    // if pre value present, means other thread put value already,
                    // and putIfAbsent not effect
                    // return exist value
                    return res;
                }
                // if pre value is null, means putIfAbsent effected, return current value
            }
            return v;
        } else {
            return computeIfAbsent(key, mappingFunction);
        }
    }
}
