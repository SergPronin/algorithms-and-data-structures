# Dictionary Frequency Analysis

This project implements a letter pair frequency analyzer using various `Map` implementations (`HashMap`, `TreeMap`, `LinkedHashMap`, and a custom `CustomHashMap`). It includes a JavaFX GUI for selecting a text file, choosing a `Map` type, and displaying the frequency of letter pairs in a table.
## Features
- **Custom HashMap**: A custom implementation of the `Map` interface with chaining for collision resolution.
- **Standard Map Implementations**: Uses `HashMap`, `TreeMap`, and `LinkedHashMap` for comparison.
- **Letter Pair Analysis**: Analyzes consecutive letter pairs in a text file, calculating their frequency.
- **Interactive GUI**:
    - Load a text file for analysis.
    - Select the type of `Map` to use.
    - Display results in a table with pair, count, and relative frequency.
- **Unit Tests**: Includes JUnit 5 tests for `CustomHashMap` and `LetterPairFrequency`.

## Technologies
- Java 23
- JavaFX 24.0.1
- Maven
- JUnit 5

## How It Works
The application reads a text file, converts it to lowercase, and counts consecutive letter pairs (e.g., "ab", "bc"). It stores the counts in a selected `Map` implementation and calculates the relative frequency of each pair.

Example:
- Input: "Hello World"
- Pairs: "he", "ll", "lo", "ow", "wo", "or", "rl"
- Output: A table showing each pair, its count, and frequency.

## How to Run
1. **Prerequisites**:
    - Install Java 23 (e.g., Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - Download JavaFX 24.0.1 SDK for macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Install Maven.
2. Navigate to the project directory:
   ```bash
   cd dictionary-frequency
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
├── main/java/ru/vsu/cs/course1/
│   ├── CustomHashMap.java         # Custom Map implementation
│   ├── LetterPairFrequency.java   # Logic for analyzing letter pairs
│   └── MainApp.java              # JavaFX GUI application
├── test/java/ru/vsu/cs/course1/
│   ├── CustomHashMapTest.java     # Tests for CustomHashMap
│   └── LetterPairFrequencyTest.java  # Tests for LetterPairFrequency
```

## License
MIT License (see [LICENSE](LICENSE))

---

## Русский

Этот проект реализует анализ частоты пар букв с использованием различных реализаций `Map` (`HashMap`, `TreeMap`, `LinkedHashMap` и кастомный `CustomHashMap`). Включает графический интерфейс на JavaFX для выбора текстового файла, выбора типа `Map` и отображения результатов в таблице.

### Возможности
- **Кастомный HashMap**: Собственная реализация интерфейса `Map` с цепочками для разрешения коллизий.
- **Стандартные реализации Map**: Использует `HashMap`, `TreeMap` и `LinkedHashMap` для сравнения.
- **Анализ пар букв**: Подсчитывает частоту пар подряд идущих букв в тексте.
- **Интерактивный GUI**:
    - Загрузка текстового файла.
    - Выбор типа `Map`.
    - Отображение результатов в таблице (пара, количество, частота).
- **Юнит-тесты**: Тесты с JUnit 5 для `CustomHashMap` и `LetterPairFrequency`.

### Как запустить
1. Установите:
    - Java 23 (например, Amazon Corretto: [Adoptium](https://adoptium.net/)).
    - JavaFX 24.0.1 SDK для macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/).
    - Maven.
2. Перейдите в папку проекта:
   ```bash
   cd dictionary-frequency
   ```
3. Соберите проект:
   ```bash
   mvn clean install
   ```
4. Запустите приложение:
   ```bash
   mvn javafx:run
   ```