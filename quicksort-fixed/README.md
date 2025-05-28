# QuickSort with Fixed Elements

This project implements a modified QuickSort algorithm that sorts an array while preserving the positions of fixed elements. It includes a JavaFX GUI for interactive sorting and visualization, unit tests, and a reliable sorting algorithm. Created as of 09:43 PM +04, May 28, 2025.

## Features
- Standard QuickSort implementation.
- Modified QuickSort that sorts only movable elements while keeping fixed elements in place (using an auxiliary array for reliability).
- Interactive GUI with:
    - Add, remove, and sort elements.
    - Visual feedback for fixed elements (highlighted in red).
    - Error handling for invalid input.
    - Option to save the list to console.
- Unit tests with JUnit 5 to verify sorting logic.

## Technologies
- Java 23
- JavaFX 24.0.1
- Maven
- JUnit 5

## How It Works
The modified QuickSort takes two arrays: one with numbers and another with boolean flags indicating fixed positions. For example:
- Input: {7, 10, 3, 8, 7, 2, 1, 9, 5, 7} with fixed positions [false, true, false, true, false, false, false, true, false, false]
- Output: {1, 10, 2, 8, 3, 5, 7, 9, 7, 7}

## How to Run
1. **Prerequisites**:
    - Install Java 23 (e.g., Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - Download JavaFX 24.0.1 SDK for macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Install Maven.
2. Clone the repository:
   ```bash
   git clone https://github.com/your-username/quicksort-fixed.git
   ```
3. Navigate to the project directory:
   ```bash
   cd quicksort-fixed
   ```
4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn javafx:run
   ```
6. Alternatively, run in IntelliJ IDEA with VM options:
   ```bash
   --module-path /Users/sergejpronin/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.fxml
   ```
7. Run unit tests:
   ```bash
   mvn test
   ```

## Project Structure
```
src/
├── main/java/ru/vsu/cs/course1/
│   ├── GUI/
│   │   ├── QuickSort.java      # Standard QuickSort implementation
│   │   ├── QuickSortModificated.java  # Modified QuickSort with fixed elements
│   │   └── SortApp.java        # JavaFX GUI application
│   └── console/
│       └── QuickSortModificated.java  # Console version (if needed)
├── test/java/ru/vsu/cs/course1/
│   └── QuickSortTest.java      # Unit tests
screenshots/
├── example1.png
task/
├── Task_4.pdf
├── task4.txt
```

## Screenshots
![Sorting GUI](screenshots/example1.png)

## License
MIT License (see [LICENSE](LICENSE))

---

## Русский

Этот проект реализует модифицированный алгоритм быстрой сортировки, который сортирует массив, сохраняя позиции фиксированных элементов. Включает графический интерфейс на JavaFX, юнит-тесты и надёжный алгоритм сортировки. Создан по состоянию на 09:43 PM +04, 28 мая 2025 года.

### Возможности
- Стандартная реализация QuickSort.
- Модифицированная QuickSort, сортирующая только перемещаемые элементы (с использованием дополнительного массива для надёжности).
- Интерактивный GUI с:
    - Добавлением, удалением и сортировкой элементов.
    - Визуальной индикацией фиксированных элементов (выделены красным).
    - Обработкой ошибок ввода.
    - Возможностью сохранить список в консоль.
- Юнит-тесты с JUnit 5.

### Как запустить
1. Установите:
    - Java 23 (например, Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - JavaFX 24.0.1 SDK для macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Maven.
2. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/your-username/quicksort-fixed.git
   ```
3. Перейдите в папку проекта:
   ```bash
   cd quicksort-fixed
   ```
4. Соберите проект:
   ```bash
   mvn clean install
   ```
5. Запустите приложение:
   ```bash
   mvn javafx:run
   ```