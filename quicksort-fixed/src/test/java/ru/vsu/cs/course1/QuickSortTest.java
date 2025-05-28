package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.course1.GUI.QuickSort;
import ru.vsu.cs.course1.GUI.QuickSortModificated;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class QuickSortTest {

    @Test
    void testStandardQuickSort() {
        Integer[] arr = {7, 10, 3, 8, 7, 2, 1, 9, 5, 7};
        Integer[] expected = {1, 2, 3, 5, 7, 7, 7, 8, 9, 10};
        QuickSort.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testModifiedQuickSort() {
        Integer[] arr = {7, 10, 3, 8, 7, 2, 1, 9, 5, 7};
        boolean[] fixed = {false, true, false, true, false, false, false, true, false, false};
        Integer[] expected = {1, 10, 2, 8, 3, 5, 7, 9, 7, 7};
        QuickSortModificated.sort(arr, fixed);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testModifiedQuickSortAllFixed() {
        Integer[] arr = {7, 10, 3, 8};
        boolean[] fixed = {true, true, true, true};
        Integer[] expected = {7, 10, 3, 8};
        QuickSortModificated.sort(arr, fixed);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testModifiedQuickSortNoneFixed() {
        Integer[] arr = {7, 10, 3, 8};
        boolean[] fixed = {false, false, false, false};
        Integer[] expected = {3, 7, 8, 10};
        QuickSortModificated.sort(arr, fixed);
        assertArrayEquals(expected, arr);
    }
}