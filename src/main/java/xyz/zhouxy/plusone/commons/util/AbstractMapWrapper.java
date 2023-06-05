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

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.google.common.annotations.Beta;

@Beta
public abstract class AbstractMapWrapper<K, V, T extends AbstractMapWrapper<K, V, T>> {

    private final Map<K, V> map;
    private final Consumer<K> keyChecker;
    private final Consumer<V> valueChecker;

    protected AbstractMapWrapper(Map<K, V> map, @Nullable Consumer<K> keyChecker, @Nullable Consumer<V> valueChecker) {
        this.map = map;
        this.keyChecker = keyChecker;
        this.valueChecker = valueChecker;
    }

    public final T put(K key, V value) {
        if (this.keyChecker != null) {
            this.keyChecker.accept(key);
        }
        if (this.valueChecker != null) {
            this.valueChecker.accept(value);
        }
        this.map.put(key, value);
        return getSelf();
    }

    public final T putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
        return getSelf();
    }

    /**
     * 获取 {@code map} 中的值。如果 {@code key} 不存在，则抛出异常。
     * 将 {@code value}（可为 {@code null}）装进 {@link Optional} 中后返回。
     * <i>为了这碟醋包的这盘饺子。</i>
     *
     * @param key 键
     * @return 可缺失的值
     * @throws IllegalArgumentException key 不存在时抛出。
     */
    public Optional<V> get(K key) {
        if (this.map.containsKey(key)) {
            return Optional.ofNullable(this.map.get(key));
        }
        throw new IllegalArgumentException("Key does not exist");
    }

    @SuppressWarnings("unchecked")
    public final <R> Optional<R> getAndConvert(K key) {
        return get(key).map(v -> (R) v);
    }

    public final <R> Optional<R> getAndConvert(K key, Function<V, R> mapper) {
        return get(key).map(mapper);
    }

    public final boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    public final int size() {
        return this.map.size();
    }

    public final Set<K> keySet() {
        return this.map.keySet();
    }

    public final Collection<V> values() {
        return this.map.values();
    }

    public final Set<Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }

    public final void clear() {
        this.map.clear();
    }

    public final boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final V remove(Object key) {
        return this.map.remove(key);
    }

    public final V putIfAbsent(K key, V value) {
        return this.map.putIfAbsent(key, value);
    }

    public final V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        V v = this.map.get(key);
        if (null == v) {
            this.map.putIfAbsent(key, mappingFunction.apply(key));
            v = this.map.get(key);
        }
        return v;
    }

    public final Map<K, V> exportMap() {
        return this.map;
    }

    public final Map<K, V> exportUnmodifiableMapMap() {
        return Collections.unmodifiableMap(this.map);
    }

    protected abstract T getSelf();

    @Override
    public String toString() {
        return this.map.toString();
    }

    protected abstract static class Builder<K, V> {
        protected final Map<K, V> map;
        protected Consumer<K> keyChecker;
        protected Consumer<V> valueChecker;

        protected Builder(Map<K, V> map) {
            this.map = map;
        }

        public Builder<K, V> keyChecker(@Nullable Consumer<K> keyChecker) {
            this.keyChecker = keyChecker;
            return this;
        }

        public Builder<K, V> valueChecker(@Nullable Consumer<V> valueChecker) {
            this.valueChecker = valueChecker;
            return this;
        }

        public Builder<K, V> put(K key, V value) {
            if (this.keyChecker != null) {
                this.keyChecker.accept(key);
            }
            if (this.valueChecker != null) {
                this.valueChecker.accept(value);
            }
            this.map.put(key, value);
            return this;
        }

        public Builder<K, V> putAll(Map<? extends K, ? extends V> m) {
            for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public abstract MapWrapper<K, V> build();

        public abstract MapWrapper<K, V> buildUnmodifiableMap();
    }
}
