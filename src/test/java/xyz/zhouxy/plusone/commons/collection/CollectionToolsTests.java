/*
 * Copyright 2024 the original author or authors.
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CollectionToolsTests {
    @Test
    void testIsEmpty() {

        List<String> list = new ArrayList<>();
        assertTrue(CollectionTools.isEmpty(list));
        assertFalse(CollectionTools.isNotEmpty(list));

        list.add("Test");
        assertFalse(CollectionTools.isEmpty(list));
        assertTrue(CollectionTools.isNotEmpty(list));

        Map<String, Integer> map = new HashMap<>();
        assertTrue(CollectionTools.isEmpty(map));
        assertFalse(CollectionTools.isNotEmpty(map));

        map.put("2", 2);
        assertFalse(CollectionTools.isEmpty(map));
        assertTrue(CollectionTools.isNotEmpty(map));
    }
}
