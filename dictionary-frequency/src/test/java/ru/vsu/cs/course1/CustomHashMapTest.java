package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomHashMapTest {

    @Test
    public void testPutAndGet() {
        CustomHashMap map = new CustomHashMap();
        map.put("ab", 5);
        map.put("bc", 3);
        assertEquals(5, map.get("ab"));
        assertEquals(3, map.get("bc"));
        assertNull(map.get("cd"));
    }

    @Test
    public void testOverwriteValue() {
        CustomHashMap map = new CustomHashMap();
        map.put("ab", 5);
        assertEquals(5, map.put("ab", 10));
        assertEquals(10, map.get("ab"));
    }

    @Test
    public void testSizeAndEmpty() {
        CustomHashMap map = new CustomHashMap();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        map.put("ab", 5);
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
    }

    @Test
    public void testRemove() {
        CustomHashMap map = new CustomHashMap();
        map.put("ab", 5);
        assertEquals(5, map.remove("ab"));
        assertNull(map.get("ab"));
        assertEquals(0, map.size());
    }

    @Test
    public void testNullKey() {
        CustomHashMap map = new CustomHashMap();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, 5));
    }

    @Test
    public void testNullValue() {
        CustomHashMap map = new CustomHashMap();
        map.put("ab", null);
        assertNull(map.get("ab"));
        assertTrue(map.containsValue(null));
    }
}