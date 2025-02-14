/*
 * Copyright 2024-2025 the original author or authors.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.TreeRangeSet;

public class CollectionToolsTests {
    @Test
    void testIsEmpty() {

        // Collection
        List<String> list = new ArrayList<>();
        assertTrue(CollectionTools.isEmpty(list));
        assertFalse(CollectionTools.isNotEmpty(list));

        list.add("Test");
        assertFalse(CollectionTools.isEmpty(list));
        assertTrue(CollectionTools.isNotEmpty(list));

        // Map
        Map<String, Integer> map = new HashMap<>();
        assertTrue(CollectionTools.isEmpty(map));
        assertFalse(CollectionTools.isNotEmpty(map));

        map.put("2", 2);
        assertFalse(CollectionTools.isEmpty(map));
        assertTrue(CollectionTools.isNotEmpty(map));

        // Table
        Table<String, String, Integer> table = HashBasedTable.create();
        assertTrue(CollectionTools.isEmpty(table));
        assertFalse(CollectionTools.isNotEmpty(table));

        table.put("ABC", "d", 4);
        assertFalse(CollectionTools.isEmpty(table));
        assertTrue(CollectionTools.isNotEmpty(table));

        // Multimap
        Multimap<String, String> multimap = HashMultimap.create();
        assertTrue(CollectionTools.isEmpty(multimap));
        assertFalse(CollectionTools.isNotEmpty(multimap));

        multimap.put("ABC", "d");
        assertFalse(CollectionTools.isEmpty(multimap));
        assertTrue(CollectionTools.isNotEmpty(multimap));

        // Multiset
        Multiset<String> multiset = HashMultiset.create();
        assertTrue(CollectionTools.isEmpty(multiset));
        assertFalse(CollectionTools.isNotEmpty(multiset));

        multiset.add("ABC");
        assertFalse(CollectionTools.isEmpty(multiset));
        assertTrue(CollectionTools.isNotEmpty(multiset));

        // RangeSet
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        assertTrue(CollectionTools.isEmpty(rangeSet));
        assertFalse(CollectionTools.isNotEmpty(rangeSet));

        rangeSet.add(Range.closed(0, 100));
        rangeSet.add(Range.openClosed(100, 200));
        assertFalse(CollectionTools.isEmpty(rangeSet));
        assertTrue(CollectionTools.isNotEmpty(rangeSet));
    }

    @Test
    void testNullToEmpty() {
        List<String> list = Lists.newArrayList("Java", "C", "C++", "C#");
        assertSame(list, CollectionTools.nullToEmptyList(list));
        assertEquals(Collections.emptyList(), CollectionTools.nullToEmptyList(null));

        Set<String> set = Sets.newHashSet("Java", "C", "C++", "C#");
        assertSame(set, CollectionTools.nullToEmptySet(set));
        assertEquals(Collections.emptySet(), CollectionTools.nullToEmptySet(null));

        Map<String, Integer> map = ImmutableMap.of("K1", 1, "K2", 2, "K3", 3);
        assertSame(map, CollectionTools.nullToEmptyMap(map));
        assertEquals(Collections.emptyMap(), CollectionTools.nullToEmptyMap(null));
    }

    @Test
    void test_constructor_isNotAccessible_ThrowsIllegalStateException() {
        Constructor<?>[] constructors = CollectionTools.class.getDeclaredConstructors();
        Arrays.stream(constructors)
                .forEach(constructor -> {
                    assertFalse(constructor.isAccessible());
                    constructor.setAccessible(true);
                    Throwable cause = assertThrows(Exception.class, constructor::newInstance)
                            .getCause();
                    assertInstanceOf(IllegalStateException.class, cause);
                    assertEquals("Utility class", cause.getMessage());
                });
    }
}
