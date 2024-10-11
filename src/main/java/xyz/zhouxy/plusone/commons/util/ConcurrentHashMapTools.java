/*
 * Copyright 2022-2024 the original author or authors.
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

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import xyz.zhouxy.plusone.commons.base.JRE;
import xyz.zhouxy.plusone.commons.collection.SafeConcurrentHashMap;

/**
 * ConcurrentHashMapTools
 *
 * <p>
 * Java 8 的 {@link ConcurrentHashMap#computeIfAbsent(Object, Function)} 方法有 bug，
 * 可使用这个工具类的 {@link computeIfAbsentForJava8} 进行替换。
 *
 * <p>
 * <b>NOTE: 方法来自Dubbo，见：issues#2349</b>
 * 
 * @author <a href="http://zhouxy.xyz:3000/ZhouXY108">ZhouXY</a>
 * @since 1.0
 * @see ConcurrentHashMap
 * @see SafeConcurrentHashMap
 */
public class ConcurrentHashMapTools {

    public static <K, V> V computeIfAbsent(
            ConcurrentHashMap<K, V> map, final K key, // NOSONAR
            final Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(map, "map");
        return JRE.isJava8()
                ? computeIfAbsentForJava8(map, key, mappingFunction)
                : map.computeIfAbsent(key, mappingFunction);
    }

    public static <K, V> V computeIfAbsentForJava8(
            ConcurrentHashMap<K, V> map, final K key, // NOSONAR
            final Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(mappingFunction);
        V v = map.get(key);
        if (null == v) {
            v = mappingFunction.apply(key);
            if (null == v) {
                return null;
            }
            final V res = map.putIfAbsent(key, v);
            if (null != res) {
                return res;
            }
        }
        return v;
    }

    private ConcurrentHashMapTools() {
        throw new IllegalStateException("Utility class");
    }
}
