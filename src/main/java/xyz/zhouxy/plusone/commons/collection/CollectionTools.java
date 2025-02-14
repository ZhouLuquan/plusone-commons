/*
 * Copyright 2023-2025 the original author or authors.
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
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 0.1.0
 */
public class CollectionTools {

    // ================================
    // #region - isEmpty
    // ================================

    public static boolean isEmpty(@Nullable Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(@Nullable Table<?, ?, ?> table) {
        return table == null || table.isEmpty();
    }

    public static boolean isEmpty(@Nullable Multimap<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(@Nullable Multiset<?> set) {
        return set == null || set.isEmpty();
    }

    public static boolean isEmpty(@Nullable RangeSet<?> set) {
        return set == null || set.isEmpty();
    }

    // ================================
    // #endregion - isEmpty
    // ================================

    // ================================
    // #region - isNotEmpty
    // ================================

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Table<?, ?, ?> table) {
        return table != null && !table.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Multimap<?, ?> map) {
        return map != null && !map.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Multiset<?> set) {
        return set != null && !set.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable RangeSet<?> set) {
        return set != null && !set.isEmpty();
    }

    // ================================
    // #endregion - isNotEmpty
    // ================================

    // ================================
    // #region - nullToEmpty
    // ================================

    @Nonnull
    public static <T> List<T> nullToEmptyList(@Nullable List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    @Nonnull
    public static <T> Set<T> nullToEmptySet(@Nullable Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

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
