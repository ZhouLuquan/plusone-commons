package xyz.zhouxy.plusone.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

@SuppressWarnings("all")
public class ArrayToolsTests {
    @Test
    void testIsAllNotNull() {
        assertTrue(ArrayTools.isAllElementsNotNull(new Object[] { 1L, 2, 3.0, "Java" }));
        assertFalse(ArrayTools.isAllElementsNotNull(new Object[] { 1L, 2, 3.0, "Java", null }));
        assertFalse(ArrayTools.isAllElementsNotNull(new Object[] { null, 1L, 2, 3.0, "Java" }));
        assertTrue(ArrayTools.isAllElementsNotNull(new Object[] {}));
        assertThrows(IllegalArgumentException.class,
                () -> ArrayTools.isAllElementsNotNull(null));
    }
}
