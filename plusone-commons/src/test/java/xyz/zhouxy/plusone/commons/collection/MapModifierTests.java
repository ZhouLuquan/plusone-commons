/*
 * Copyright 2025-present ZhouXY
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nullable;

import org.junit.jupiter.api.Test;

import com.google.common.collect.ImmutableMap;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapModifierTests {

    private static final String APP_START_ID = UUID.randomUUID().toString();
    private static final String LOCKED = "LOCKED";

    private static final Map<String, String> commonProperties = ImmutableMap.<String, String>builder()
            .put("channel", "MOBILE")
            .put("appStartId", APP_START_ID)
            .build();

    @Test
    void demo() {
        Map<String, String> expected = new HashMap<String, String>() {
            {
                put("channel", "MOBILE");
                put("appStartId", APP_START_ID);
                put("username", "Ben");
                put("accountStatus", LOCKED);
            }
        };

        // MapModifier
        MapModifier<String, String> modifier = new MapModifier<String, String>()
                .putAll(commonProperties)
                .put("username", "Ben")
                .put("accountStatus", LOCKED);

        // 从 Supplier 中获取 Map，并修改数据
        HashMap<String, String> hashMap1 = modifier.getAndModify(HashMap::new);
        assertEquals(expected, hashMap1);

        // 可以灵活使用不同 Map 类型的不同构造器
        HashMap<String, String> hashMap2 = modifier.getAndModify(() -> new HashMap<>(8));
        assertEquals(expected, hashMap2);

        // HashMap<String, String> hashMap3 = modifier.getAndModify(() -> new HashMap<>(anotherMap));
        TreeMap<String, String> treeMap = modifier.getAndModify(TreeMap::new);
        assertEquals(expected, treeMap);
        ConcurrentHashMap<String, String> concurrentHashMap = modifier.getAndModify(ConcurrentHashMap::new);
        assertEquals(expected, concurrentHashMap);

        assertNull(modifier.getAndModify(() -> (Map<String, String>) null));

        // 修改已有的 Map
        Map<String, String> srcMap = new HashMap<>();
        srcMap.put("srcKey1", "srcValue1");
        srcMap.put("srcKey2", "srcValue2");
        modifier.modify(srcMap);
        assertEquals(new HashMap<String, String>() {
            {
                putAll(commonProperties);
                put("username", "Ben");
                put("accountStatus", LOCKED);
                put("srcKey1", "srcValue1");
                put("srcKey2", "srcValue2");
            }
        }, srcMap);

        assertDoesNotThrow(() -> modifier.modify((Map<String, String>) null));

        // 创建一个有初始化数据的不可变的 Map
        Map<String, String> unmodifiableMap = modifier.getUnmodifiableMap();
        assertEquals(expected, unmodifiableMap);
        assertThrows(UnsupportedOperationException.class,
                () -> unmodifiableMap.put("key", "value"));
    }

    @Test
    void createAndInitData() {
        // 链式调用创建并初始化数据
        HashMap<String, String> map = new MapModifier<String, String>()
                .putAll(commonProperties)
                .put("username", "Ben")
                .put("accountStatus", LOCKED)
                .getAndModify(HashMap::new);

        HashMap<String, String> expected = new HashMap<String, String>() {
            {
                put("channel", "MOBILE");
                put("appStartId", APP_START_ID);
                put("username", "Ben");
                put("accountStatus", LOCKED);
            }
        };
        assertEquals(expected, map);
    }

    @Test
    void put() {
        Map<String, String> map = new MapModifier<String, String>()
                .put("key1", "value0")
                .put("key1", "value1")
                .getAndModify(HashMap::new);

        assertEquals(new HashMap<String, String>() {
            {
                put("key1", "value0");
                put("key1", "value1");
            }
        }, map);

        new MapModifier<String, String>()
                .put("key1", "newValue1")
                .put("key2", null)
                .modify(map);

        assertEquals("newValue1", map.get("key1"));
        assertTrue(map.containsKey("key2"));
        assertNull(map.get("key2"));
    }

    @Test
    void putIfAbsent() {
        Map<String, String> map = new MapModifier<String, String>()
                .putIfAbsent("key1", null)
                .putIfAbsent("key1", "value1")
                .putIfAbsent("key1", "value2")
                .getAndModify(HashMap::new);

        assertEquals(new HashMap<String, String>() {
            {
                putIfAbsent("key1", null);
                putIfAbsent("key1", "value1");
                putIfAbsent("key1", "value2");
            }
        }, map);

        new MapModifier<String, String>()
                .putIfAbsent("key1", "newValue1")
                .modify(map);

        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.get("key1"));
    }

    @Test
    void putAll_map() {
        Map<String, String> entries = new HashMap<String, String>() {
            {
                put("key1", "value1");
                put("key2", "value2");
            }
        };
        Map<String, String> map = new MapModifier<String, String>()
                .putAll((Map<String, String>) null)
                .putAll(Collections.emptyMap())
                .putAll(entries)
                .getAndModify(HashMap::new);
        assertEquals(entries, map);
        new MapModifier<String, String>()
                .putAll(new HashMap<String, String>() {
                    {
                        put("key2", "newValue2");
                        put("key3", "value3");
                    }
                })
                .modify(map);
        assertEquals(new HashMap<String, String>() {
            {
                put("key1", "value1");
                put("key2", "value2");
                put("key2", "newValue2");
                put("key3", "value3");
            }
        }, map);
    }

    @Test
    void computeIfAbsent_keyAndFunction() {
        Map<String, String> map = new MapModifier<String, String>()
                .computeIfAbsent("key1", k -> null)
                .computeIfAbsent("key1", k -> "value1")
                .computeIfAbsent("key1", k -> "value2")
                .getAndModify(HashMap::new);

        assertEquals(new HashMap<String, String>() {
            {
                computeIfAbsent("key1", k -> null);
                computeIfAbsent("key1", k -> "value1");
                computeIfAbsent("key1", k -> "value2");
            }
        }, map);

        new MapModifier<String, String>()
                .computeIfAbsent("key1", k -> "newValue1")
                .modify(map);

        assertNotNull(map);
        assertTrue(map.containsKey("key1"));
        assertEquals("value1", map.get("key1"));
    }

    @Test
    void computeIfPresent_keyAndBiFunction() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("key1", "value1");
        }};
        new MapModifier<String, String>()
                .computeIfPresent("key1", (k, v) -> k + v)
                .computeIfPresent("key2", (k, v) -> k + v)
                .modify(map);
        assertEquals(new HashMap<String, String>() {{
            put("key1", "key1value1");
        }}, map);
    }

    @Test
    void remove() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
        new MapModifier<String, String>()
                .remove("key2")
                .modify(map);
        assertEquals(new HashMap<String, String>() {{
            put("key1", "value1");
        }}, map);
    }

    @Test
    void clear() {
        Map<String, String> map = new HashMap<String, String>() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
        new MapModifier<String, String>()
                .clear()
                .modify(map);
        assertTrue(map.isEmpty());
    }

    @Getter
    static class SimpleEntry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final V value;

        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public V setValue(@Nullable V value) {
            throw new UnsupportedOperationException("Unimplemented method 'setValue'");
        }
    }
}
