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

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import xyz.zhouxy.plusone.commons.base.JRE;

public class ConcurrentHashMapUtil { // TODO 添加文档注释

    public static <K, V> V computIfAbsent(ConcurrentHashMap<K, V> map, final K key, final Function<? super K, ? extends V> mappingFunction) {
        if (JRE.isJava8()) {
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
        } else {
            return map.computeIfAbsent(key, mappingFunction);
        }
    }

    private ConcurrentHashMapUtil() {
        throw new IllegalStateException("Utility class");
    }
}
