/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.collection;

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgumentNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

/**
 * Map 修改器
 *
 * <p>
 * 封装一系列对 Map 数据的修改操作，修改 Map 的数据。可以用于 Map 的数据初始化等操作。
 *
 * <pre>
 * // MapModifier
 * MapModifier&lt;String, Object&gt; modifier = new MapModifier&lt;String, Object&gt;()
 *     .putAll(commonProperties)
 *     .put("username", "Ben")
 *     .put("accountStatus", LOCKED);
 *
 * // 从 Supplier 中获取 Map，并修改数据
 * Map&lt;String, Object&gt; map = modifier.getAndModify(HashMap::new);
 *
 * // 可以灵活使用不同 Map 类型的不同构造器
 * Map&lt;String, Object&gt; map = modifier.getAndModify(() -&gt; new HashMap&lt;&gt;(8));
 * Map&lt;String, Object&gt; map = modifier.getAndModify(() -&gt; new HashMap&lt;&gt;(anotherMap));
 * Map&lt;String, Object&gt; map = modifier.getAndModify(TreeMap::new);
 * Map&lt;String, Object&gt; map = modifier.getAndModify(ConcurrentHashMap::new);
 *
 * // 修改已有的 Map
 * modifier.modify(map);
 *
 * // 创建一个有初始化数据的不可变的 Map
 * Map&lt;String, Object&gt; map = modifier.getUnmodifiableMap();
 *
 * // 链式调用创建并初始化数据
 * Map&lt;String, Object&gt; map = new MapModifier&lt;String, Object&gt;()
 *     .putAll(commonProperties)
 *     .put("username", "Ben")
 *     .put("accountStatus", LOCKED)
 *     .getAndModify(HashMap::new);
 * </pre>
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.1.0
 */
@Beta
public class MapModifier<K, V> {

    @Nonnull
    private Consumer<Map<K, V>> operators;

    /**
     * 创建一个空的 MapModifier
     */
    public MapModifier() {
        this.operators = m -> {
            // do nothing
        };
    }

    /**
     * 添加一个键值对。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param key 要添加的 {@code key}
     * @param value 要添加的 {@code value}
     * @return MapModifier
     */
    public MapModifier<K, V> put(@Nullable K key, @Nullable V value) {
        return addOperationInternal(map -> map.put(key, value));
    }

    /**
     * 添加一个键值对，如果 key 已经存在，则不添加。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param key 要添加的 {@code key}
     * @param value 要添加的 {@code value}
     * @return MapModifier
     */
    public MapModifier<K, V> putIfAbsent(@Nullable K key, @Nullable V value) {
        return addOperationInternal(map -> map.putIfAbsent(key, value));
    }

    /**
     * 添加多个键值对。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param otherMap 要添加的键值对集合。
     *                 如果为 {@code null}，则什么都不做。
     *
     * @return MapModifier
     */
    public MapModifier<K, V> putAll(@Nullable Map<? extends K, ? extends V> otherMap) {
        if (otherMap == null || otherMap.isEmpty()) {
            return this;
        }
        return addOperationInternal(map -> map.putAll(otherMap));
    }

    /**
     * 添加多个键值对。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param entries 要添加的键值对集合
     * @return MapModifier
     */
    @SafeVarargs
    public final MapModifier<K, V> putAll(Map.Entry<? extends K, ? extends V>... entries) {
        if (entries.length == 0) {
            return this;
        }
        return addOperationInternal(map -> {
            for (Map.Entry<? extends K, ? extends V> entry : entries) {
                map.put(entry.getKey(), entry.getValue());
            }
        });
    }

    /**
     * 当 {@code key} 不存在时，计算对应的值，并添加到 {@code map} 中。
     *
     * <p>
     * 调用 {@link Map#computeIfAbsent(Object, Function)}。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param key 要添加的 {@code key}
     * @param mappingFunction 计算 {@code key} 对应的值
     * @return MapModifier
     */
    public MapModifier<K, V> computeIfAbsent(K key,
            Function<? super K, ? extends V> mappingFunction) {
        return addOperationInternal(map -> map.computeIfAbsent(key, mappingFunction));
    }

    /**
     * 当 {@code key} 存在时，计算对应的值，并添加到 {@code map} 中。
     *
     * <p>
     * 调用 {@link Map#computeIfPresent(Object, BiFunction)}。
     *
     * <p>
     * <b>注意：键值对是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param key 要添加的 {@code key}
     * @param remappingFunction 计算 {@code key} 对应的值
     * @return MapModifier
     */
    public MapModifier<K, V> computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return addOperationInternal(map -> map.computeIfPresent(key, remappingFunction));
    }

    /**
     * 删除 {@code key}。
     *
     * <p>
     * <b>注意：key 是否允许为 {@code null}，由最终作用的 {@link Map} 类型决定。</b>
     *
     * @param key 要删除的 {@code key}
     * @return MapModifier
     */
    public MapModifier<K, V> remove(K key) {
        return addOperationInternal(map -> map.remove(key));
    }

    /**
     * 清空 {@code map}
     *
     * @return MapModifier
     */
    public MapModifier<K, V> clear() {
        return addOperationInternal(Map::clear);
    }

    /**
     * 修改 {@code map}
     *
     * @param map 要修改的 {@code map}
     */
    public <T extends Map<K, V>> void modify(@Nullable T map) {
        if (map != null) {
            this.operators.accept(map);
        }
    }

    /**
     * 修改 {@code map}
     *
     * @param mapSupplier {@code map} 的 {@link Supplier}
     * @return 修改后的 {@code map}。
     *         当从 {@code mapSupplier} 获取的 {@code map} 为 {@code null} 时，返回 {@code null}。
     */
    @CheckForNull
    public <T extends Map<K, V>> T getAndModify(Supplier<T> mapSupplier) {
        checkArgumentNotNull(mapSupplier, "The map supplier cannot be null.");
        T map = mapSupplier.get();
        modify(map);
        return map;
    }

    /**
     * 创建一个有初始化数据的不可变的 {@code Map}
     *
     * @return 不可变的 {@code Map}
     */
    public Map<K, V> getUnmodifiableMap() {
        return Collections.unmodifiableMap(getAndModify(HashMap::new));
    }

    private MapModifier<K, V> addOperationInternal(Consumer<Map<K, V>> operator) {
        this.operators = this.operators.andThen(operator);
        return this;
    }
}
