package ru.vsu.cs.course1.console;

public class QuickSortModificated {

    public static <T extends Comparable<T>> void sort(T[] arr, boolean[] fixed) {
        T[] movableElements = (T[]) new Comparable[arr.length];
        int movableCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if (!fixed[i]) {
                movableElements[movableCount++] = arr[i];
            }
        }

        quickSort(movableElements, 0, movableCount - 1);

        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!fixed[i]) {
                arr[i] = movableElements[index++];
            }
        }
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] data = {7, 10, 3, 8, 7, 2, 1, 9, 5, 7};
        boolean[] fixed = {false, true, false, false, true, true, false, false, true, false};

        sort(data, fixed);

        System.out.print("Отсортированный массив: ");
        for (Integer num : data) {
            System.out.print(num + " ");
        }
    }
}

