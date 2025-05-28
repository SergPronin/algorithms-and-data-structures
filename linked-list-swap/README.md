# Linked List Swap

This project implements a doubly linked list with a pair-swapping feature, presented through a JavaFX GUI. The application allows users to add, remove, and swap pairs of elements in the list interactively. Created as of 12:05 AM +04, May 29, 2025.

## Features
- **Doubly Linked List**: Custom implementation of a doubly linked list with methods for adding and removing elements at both ends.
- **Pair Swapping**: Swaps adjacent pairs of elements in the list (e.g., [A, B, C, D] becomes [B, A, D, C]).
- **Interactive GUI**:
    - Add elements to the beginning or end of the list.
    - Remove elements from the beginning or end.
    - Swap pairs of elements.
    - Clear the list.
    - Visual representation of the list using a `ListView`.
- **Unit Tests**: Includes JUnit 5 tests for the `DoubleLinkedList` class.

## Technologies
- Java 23
- JavaFX 24.0.1
- Maven
- JUnit 5

## How It Works
The application provides a GUI to manage a doubly linked list. Users can:
- Add elements to the beginning or end of the list via a text field.
- Remove elements from either end.
- Swap pairs of adjacent elements (e.g., if the list is [1, 2, 3, 4], it becomes [2, 1, 4, 3]).
- Clear the entire list.
  The list is displayed in a `ListView` with indexed items for clarity.

## How to Run
1. **Prerequisites**:
    - Install Java 23 (e.g., Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - Download JavaFX 24.0.1 SDK for macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Install Maven.
2. Navigate to the project directory:
   ```bash
   cd linked-list-swap
   ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn javafx:run
   ```
5. Alternatively, run in IntelliJ IDEA with VM options:
   ```bash
   --module-path /Users/sergejpronin/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.fxml
   ```
6. Run unit tests:
   ```bash
   mvn test
   ```

## Project Structure
```
src/
├── main/
│   ├── java/ru/vsu/cs/course1/
│   │   ├── DoubleLinkedList.java  # Doubly linked list implementation
│   │   ├── LinkedListApp.java     # JavaFX GUI application
│   │   └── Node.java              # Node class for the linked list
│   ├── resources/
│   │   └── ru/vsu/cs/course1/
│   │       └── style.css          # CSS styles for the GUI
├── test/
│   └── java/ru/vsu/cs/course1/
│       └── DoubleLinkedListTest.java  # Tests for DoubleLinkedList
```

## License
MIT License (see [LICENSE](LICENSE))

---

## Русский

Этот проект реализует двусвязный список с функцией обмена парного расположения элементов и графическим интерфейсом на JavaFX. Приложение позволяет добавлять, удалять и обменивать пары элементов в списке интерактивно. Создан по состоянию на 12:05 AM +04, 29 мая 2025 года.

### Возможности
- **Двусвязный список**: Собственная реализация с методами добавления и удаления элементов с обоих концов.
- **Обмен пар**: Обмен соседних пар элементов (например, [A, B, C, D] становится [B, A, D, C]).
- **Интерактивный GUI**:
    - Добавление элементов в начало или конец списка.
    - Удаление элементов с начала или конца.
    - Обмен пар элементов.
    - Очистка списка.
    - Отображение списка в `ListView`.
- **Юнит-тесты**: Тесты с JUnit 5 для `DoubleLinkedList`.

### Как запустить
1. Установите:
    - Java 23 (например, Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - JavaFX 24.0.1 SDK для macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Maven.
2. Перейдите в папку проекта:
   ```bash
   cd linked-list-swap
   ```
3. Соберите проект:
   ```bash
   mvn clean install
   ```
4. Запустите приложение:
   ```bash
   mvn javafx:run
   ```