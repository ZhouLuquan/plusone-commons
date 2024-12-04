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
