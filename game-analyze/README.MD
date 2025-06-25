# Крестики-Нолики с Анализом Вероятностей / Tic-Tac-Toe with Probability Analysis

## Описание / Description

**Русский:**  
Это Java-приложение на JavaFX реализует игру в крестики-нолики (3x3) с графическим интерфейсом. Программа вычисляет вероятности исходов (победа X, победа O, ничья) с помощью рекурсии и мемоизации, а также визуализирует дерево возможных ходов с ограничением до 4 узлов на уровень и глубины 3. Поддерживает интерактивное масштабирование и сворачивание узлов графа.

**English:**  
This Java application, built with JavaFX, implements a 3x3 Tic-Tac-Toe game with a graphical interface. It calculates outcome probabilities (X wins, O wins, draw) using recursion and memoization, and visualizes the game tree with a limit of 4 nodes per level and depth 3. It supports interactive graph zooming and node collapsing.

## Возможности / Features

- **Игра:** Поочередные ходы X и O на поле 3x3.
- **Вероятности:** Рекурсивный подсчёт исходов с мемоизацией для оптимизации.
- **Граф ходов:** Визуализация дерева ходов с ограничением для читаемости.
- **Интерактивность:** Масштабирование графа и сворачивание узлов.

- **Gameplay:** Alternating X and O moves on a 3x3 board.
- **Probabilities:** Recursive outcome calculation with memoization for optimization.
- **Game Tree:** Visualization of the move tree with limits for readability.
- **Interactivity:** Graph zooming and node collapsing.

## Установка и запуск / Installation and Running

**Русский:**
1. Убедитесь, что установлен JDK 17 или выше.
2. Установите JavaFX SDK и настройте его в вашем IDE (например, IntelliJ IDEA).
3. Склонируйте репозиторий:
   ```bash
   git clone <ваш-репозиторий>
   ```
4. Откройте проект в IDE, настройте JavaFX (добавьте `--module-path <путь-к-javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml`).
5. Запустите `TicTacToeGUI.java`.

**English:**
1. Ensure JDK 17 or higher is installed.
2. Install JavaFX SDK and configure it in your IDE (e.g., IntelliJ IDEA).
3. Clone the repository:
   ```bash
   git clone <your-repository>
   ```
4. Open the project in your IDE, configure JavaFX (add `--module-path <path-to-javafx-sdk>/lib --add-modules javafx.controls,javafx.fxml`).
5. Run `TicTacToeGUI.java`.

## Структура проекта / Project Structure

- **`Board.java`**: Логика игры (проверка результата, генерация ходов).
- **`GameGraph.java`**: Подсчёт вероятностей и построение графа ходов.
- **`Probabilities.java`**: Хранение и обработка вероятностей.
- **`TicTacToeGUI.java`**: Графический интерфейс (JavaFX) и визуализация графа.

- **`Board.java`**: Game logic (result checking, move generation).
- **`GameGraph.java`**: Probability calculation and game tree construction.
- **`Probabilities.java`**: Storage and processing of probabilities.
- **`TicTacToeGUI.java`**: Graphical interface (JavaFX) and tree visualization.

## Ключевые моменты / Key Points

**Русский:**
- **Мемоизация** (`GameGraph.java`, строка 4): Сохраняет результаты для ускорения рекурсии.
- **Рекурсия** (`GameGraph.java`, строка 21): Перебирает все ходы до конца игры.
- **Фильтрация графа** (`GameGraph.java`, строка 49): Ограничивает граф до 4 узлов на уровень для читаемости.
- **Интерфейс** (`TicTacToeGUI.java`, строка 199): Использует JavaFX для игры и визуализации.

**English:**
- **Memoization** (`GameGraph.java`, line 4): Caches results to speed up recursion.
- **Recursion** (`GameGraph.java`, line 21): Iterates through all moves until game end.
- **Graph Filtering** (`GameGraph.java`, line 49): Limits graph to 4 nodes per level for readability.
- **Interface** (`TicTacToeGUI.java`, line 199): Uses JavaFX for gameplay and visualization.

## Зависимости / Dependencies

- Java 17+
- JavaFX SDK (версия 17 или выше / version 17 or higher)
- IDE с поддержкой JavaFX (например, IntelliJ IDEA / e.g., IntelliJ IDEA)


## Автор / Author

[Sergey Pronin]