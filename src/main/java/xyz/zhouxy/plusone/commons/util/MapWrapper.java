/*
 * Copyright 2022-2023 the original author or authors.
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

package xyz.zhouxy.plusone.commons.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;

import com.google.common.annotations.Beta;

@Beta
public final class MapWrapper<K, V> extends AbstractMapWrapper<K, V, MapWrapper<K, V>> {

    private MapWrapper(Map<K, V> map, Consumer<K> keyChecker, Consumer<V> valueChecker) {
        super(map, keyChecker, valueChecker);
    }

    public static <K, V> Builder<K, V> wrap(Map<K, V> map) {
        return new Builder<>(map);
    }

    public static <K, V> Builder<K, V> wrapHashMap() {
        return new Builder<>(new HashMap<>());
    }

    public static <K, V> Builder<K, V> wrapHashMap(int initialCapacity) {
        return new Builder<>(new HashMap<>(initialCapacity));
    }

    public static <K, V> Builder<K, V> wrapHashMap(int initialCapacity, float loadFactor) {
        return new Builder<>(new HashMap<>(initialCapacity, loadFactor));
    }

    public static <K extends Comparable<? super K>, V> Builder<K, V> wrapTreeMap() {
        return new Builder<>(new TreeMap<>());
    }

    public static <K, V> Builder<K, V> wrapTreeMap(SortedMap<K, ? extends V> m) {
        return new Builder<>(new TreeMap<>(m));
    }

    public static <K, V> Builder<K, V> wrapTreeMap(Comparator<? super K> comparator) {
        return new Builder<>(new TreeMap<>(comparator));
    }

    public static final class Builder<K, V> extends AbstractMapWrapper.Builder<K, V, MapWrapper<K, V>> {

        private Builder(Map<K, V> map) {
            super(map);
        }

        @Override
        public MapWrapper<K, V> build() {
            return new MapWrapper<>(map, keyChecker, valueChecker);
        }

        @Override
        public MapWrapper<K, V> buildUnmodifiableMap() {
            return new MapWrapper<>(Collections.unmodifiableMap(map), keyChecker, valueChecker);
        }
    }

    @Override
    protected MapWrapper<K, V> getSelf() {
        return this;
    }
}
