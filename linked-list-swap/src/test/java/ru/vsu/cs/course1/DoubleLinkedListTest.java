package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTest {

    @Test
    public void testAddFirstAndGet() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("A");
        list.addFirst("B");
        assertEquals("B", list.get(0));
        assertEquals("A", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddLastAndGet() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void testRemoveFirst() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.removeFirst();
        assertEquals("B", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveLast() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.removeLast();
        assertEquals("A", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testSwapPairs() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.addLast("D");
        list.swapPairs();
        assertEquals("[B, A, D, C]", list.toString());
    }

    @Test
    public void testSwapPairsOddSize() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");
        list.swapPairs();
        assertEquals("[B, A, C]", list.toString());
    }

    @Test
    public void testEmptyList() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertEquals("[]", list.toString());
    }

    @Test
    public void testExceptions() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        assertThrows(IllegalStateException.class, list::removeFirst);
        assertThrows(IllegalStateException.class, list::removeLast);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IllegalArgumentException.class, () -> list.addFirst(null));
    }
}