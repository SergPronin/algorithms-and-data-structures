package ru.vsu.cs.course1.GUI;

public class QuickSortModificated {

    public static void sort(Integer[] arr, boolean[] fixed) {
        Integer[] movableElements = new Integer[arr.length];
        int movableCount = 0;

        // Собираем подвижные элементы
        for (int i = 0; i < arr.length; i++) {
            if (!fixed[i]) {
                movableElements[movableCount++] = arr[i];
            }
        }

        // Сортируем подвижные элементы
        quickSort(movableElements, 0, movableCount - 1);

        // Вставляем отсортированные подвижные элементы обратно
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!fixed[i]) {
                arr[i] = movableElements[index++];
            }
        }
    }

    private static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Integer[] arr, int low, int high) {
        Integer pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] != null && arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}