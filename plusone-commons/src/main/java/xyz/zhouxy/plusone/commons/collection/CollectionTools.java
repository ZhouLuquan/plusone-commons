/*
 * Copyright 2023-present ZhouXY
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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Table;

/**
 * 集合工具类
 *
 * @author ZhouXY108 <luquanlion@outlook.com>
 * @since 1.0.0
 */
public class CollectionTools {

    // ================================
    // #region - isEmpty
    // ================================

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param table 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable Table<?, ?, ?> table) {
        return table == null || table.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable Multimap<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param set 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable Multiset<?> set) {
        return set == null || set.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param set 集合
     * @return 是否为空
     */
    public static boolean isEmpty(@Nullable RangeSet<?> set) {
        return set == null || set.isEmpty();
    }

    // ================================
    // #endregion - isEmpty
    // ================================

    // ================================
    // #region - isNotEmpty
    // ================================

    /**
     * 判断集合是否不为空
     *
     * @param collection 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param map 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param table 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Table<?, ?, ?> table) {
        return table != null && !table.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param map 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Multimap<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param set 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable Multiset<?> set) {
        return set != null && !set.isEmpty();
    }

    /**
     * 判断集合是否不为空
     *
     * @param set 集合
     * @return 是否不为空
     */
    public static boolean isNotEmpty(@Nullable RangeSet<?> set) {
        return set != null && !set.isEmpty();
    }

    // ================================
    // #endregion - isNotEmpty
    // ================================

    // ================================
    // #region - nullToEmpty
    // ================================

    /**
     * 将 {@code null} 转为空 {@code List}
     *
     * @param <T> List 元素的类型
     * @param list list
     * @return 如果 {@code list} 为 {@code null}，返回空列表；
     *         如果 {@code list} 不为 {@code null}，返回 {@code list} 本身
     */
    @Nonnull
    public static <T> List<T> nullToEmptyList(@Nullable List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * 将 {@code null} 转为空 {@code Set}
     *
     * @param <T> Set 元素的类型
     * @param set set
     * @return 如果 {@code set} 为 {@code null}，返回空集合；
     *         如果 {@code set} 不为 {@code null}，返回 {@code set} 本身
     */
    @Nonnull
    public static <T> Set<T> nullToEmptySet(@Nullable Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    /**
     * 将 {@code null} 转为空 {@code Map}
     *
     * @param <K> Map 的键的类型
     * @param <V> Map 的值的类型
     * @param map map
     * @return 如果 {@code map} 为 {@code null}，返回空集合；
     *         如果 {@code map} 不为 {@code null}，返回 {@code map} 本身
     */
    @Nonnull
    public static <K, V> Map<K, V> nullToEmptyMap(@Nullable Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    // ================================
    // #endregion - nullToEmpty
    // ================================

    private CollectionTools() {
        throw new IllegalStateException("Utility class");
    }
}
