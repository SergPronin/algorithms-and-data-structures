# 📚 Algorithms and Data Structures (University Java Projects)

---


### ✨ Overview
This repository is a curated collection of Java projects developed by **Sergey Pronin**, a first-year student at **Voronezh State University (VSU)**, Faculty of Computer Science, majoring in Software Engineering (group 7.1). These projects were created as part of the university coursework for the subject **"Algorithms and Data Structures"** in Spring 2025. The repository showcases practical programming skills, problem-solving abilities, and software design principles through implementations of algorithms, data structures, and graphical user interface (GUI) applications using JavaFX.

---

### 🚀 Projects
Below is a list of projects included in this repository, each with a brief description and a clickable link to its directory:

| **Project** | **Description** | **Technologies** | **Link** |
|-------------|-----------------|------------------|----------|
| **Loan Calculator** | A JavaFX-based application for calculating loan payments, total credit sums, and overpayments for annuity and differentiated loans. Features an interactive UI and unit tests. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Go to Project](loan-calculator/) |
| **QuickSort with Fixed Elements** | A modified QuickSort algorithm that sorts an array while preserving fixed elements, with a JavaFX GUI for visualization. Includes unit tests. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Go to Project](quicksort-fixed/) |
| **Random Binary Tree** | An implementation of a random binary tree with a JavaFX visualizer to generate and explore tree structures. Includes unit tests. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Go to Project](random-binary-tree/) |
| **Stack-Queue Brackets** | A project that checks bracket balance in mathematical expressions using stack-based approaches (recursive and standard). Includes unit tests. | Java 23, Maven, JUnit 5 | [📂 Go to Project](stack-queue-brackets/) |
| **Linked List Swap** | A double-linked list implementation with a JavaFX GUI for swapping nodes and visualizing the list structure. Includes unit tests. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Go to Project](linked-list-swap/) |
| **Dictionary Frequency** | An application that analyzes letter pair frequencies in text using a custom hash map implementation. Includes unit tests. | Java 23, Maven, JUnit 5 | [📂 Go to Project](dictionary-frequency/) |

---

### 🛠️ Technologies
- **Programming Language**: Java 23 ☕
- **GUI Framework**: JavaFX 24.0.1 🖼️
- **Build Tool**: Maven 🏗️
- **Testing Framework**: JUnit 5 🧪

---

### 📋 Prerequisites
Before running the projects, ensure you have the following installed:
- **Java 23** (e.g., Amazon Corretto: [Adoptium](https://adoptium.net/))
- **JavaFX 24.0.1 SDK** for macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/) (required for GUI projects)
- **Maven**: For dependency management and building

---

### ⚙️ Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/sergejpronin/algorithms-and-data-structures.git
   cd algorithms-and-data-structures
   ```

2. **Set up JavaFX SDK** (for GUI projects: `loan-calculator`, `quicksort-fixed`, `random-binary-tree`, `linked-list-swap`):
  - Download JavaFX SDK 24.0.1 from [GluonHQ](https://gluonhq.com/products/javafx/).
  - Extract it to a directory (e.g., `/Users/sergejpronin/javafx-sdk-24.0.1`).
  - Update the `<javafx.sdk.path>` in each project's `pom.xml`:
    ```xml
    <javafx.sdk.path>/path/to/javafx-sdk-24.0.1</javafx.sdk.path>
    ```

3. **Navigate to a project directory** (e.g., `loan-calculator`):
   ```bash
   cd loan-calculator
   ```

4. **Install dependencies**:
   ```bash
   mvn clean install
   ```

---

### 🚀 Running a Project
Each project has its own README with detailed instructions. General steps:
1. Navigate to the project directory.
2. Build and run the application (for GUI projects):
   ```bash
   mvn javafx:run
   ```
   For projects without GUI (e.g., `stack-queue-brackets`, `dictionary-frequency`):
   ```bash
   mvn exec:java -Dexec.mainClass="ru.vsu.cs.course1.MainApp"
   ```
3. Run unit tests:
   ```bash
   mvn test
   ```

---

### 📂 Project Structure
```
algorithms-and-data-structures/
├── 📂 loan-calculator/            # Loan Calculator project
├── 📂 quicksort-fixed/            # QuickSort with Fixed Elements project
├── 📂 random-binary-tree/         # Random Binary Tree project
├── 📂 stack-queue-brackets/       # Stack-Queue Brackets project
├── 📂 linked-list-swap/           # Linked List Swap project
├── 📂 dictionary-frequency/       # Dictionary Frequency project
├── 📜 LICENSE                     # Repository license
└── 📖 README.md                   # Repository overview
```

---

### 🤝 Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a Pull Request.

---

### 📜 License
This repository is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### 📬 Contact
For questions or feedback, please contact [sergejpronin@example.com](mailto:sergejpronin@example.com).

---

## Алгоритмы и структуры данных (Университетские проекты на Java)

### ✨ Обзор
Этот репозиторий представляет собой коллекцию проектов на Java, разработанных **Сергеем Прониным**, студентом 1-го курса **Воронежского государственного университета (ВГУ)**, Факультета компьютерных наук, направления "Программная инженерия" (группа 7.1). Проекты созданы в рамках университетского курса **"Алгоритмы и структуры данных"** в весеннем семестре 2025 года. Репозиторий демонстрирует практические навыки программирования, способности к решению задач и принципы проектирования программного обеспечения через реализацию алгоритмов, структур данных и приложений с графическим интерфейсом (GUI) на JavaFX.

---

### 🚀 Проекты
Ниже представлен список проектов, включённых в репозиторий, с кратким описанием и кликабельными ссылками на их директории:

| **Проект** | **Описание** | **Технологии** | **Ссылка** |
|------------|--------------|----------------|------------|
| **Калькулятор кредитов** | Приложение на JavaFX для расчёта платежей по кредиту, общей суммы и переплаты для аннуитетных и дифференцированных кредитов. Интерактивный интерфейс и юнит-тесты. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Перейти к проекту](loan-calculator/) |
| **Быстрая сортировка с фиксированными элементами** | Модифицированный алгоритм QuickSort, сортирующий массив с сохранением фиксированных элементов, с визуализацией на JavaFX. Включает юнит-тесты. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Перейти к проекту](quicksort-fixed/) |
| **Случайное бинарное дерево** | Реализация случайного бинарного дерева с визуализатором на JavaFX для генерации и исследования структур дерева. Включает юнит-тесты. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Перейти к проекту](random-binary-tree/) |
| **Проверка скобочной структуры** | Проект для проверки сбалансированности скобок в математических выражениях с использованием подходов на основе стека (рекурсивный и стандартный). Включает юнит-тесты. | Java 23, Maven, JUnit 5 | [📂 Перейти к проекту](stack-queue-brackets/) |
| **Обмен элементов в связном списке** | Реализация двусвязного списка с интерфейсом на JavaFX для обмена узлов и визуализации структуры списка. Включает юнит-тесты. | Java 23, JavaFX 24.0.1, Maven, JUnit 5 | [📂 Перейти к проекту](linked-list-swap/) |
| **Анализ частоты пар букв** | Приложение для анализа частоты пар букв в тексте с использованием собственной хэш-таблицы. Включает юнит-тесты. | Java 23, Maven, JUnit 5 | [📂 Перейти к проекту](dictionary-frequency/) |

---

### 🛠️ Технологии
- **Язык программирования**: Java 23 ☕
- **Фреймворк для GUI**: JavaFX 24.0.1 🖼️
- **Инструмент сборки**: Maven 🏗️
- **Фреймворк тестирования**: JUnit 5 🧪

---

### 📋 Требования
Перед запуском проектов убедитесь, что у вас установлены:
- **Java 23** (например, Amazon Corretto: [Adoptium](https://adoptium.net/))
- **JavaFX 24.0.1 SDK** для macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/) (требуется для проектов с GUI)
- **Maven**: Для управления зависимостями и сборки

---

### ⚙️ Установка
1. **Клонируйте репозиторий**:
   ```bash
   git clone https://github.com/sergejpronin/algorithms-and-data-structures.git
   cd algorithms-and-data-structures
   ```

2. **Настройте JavaFX SDK** (для проектов с GUI: `loan-calculator`, `quicksort-fixed`, `random-binary-tree`, `linked-list-swap`):
  - Скачайте JavaFX SDK 24.0.1 с [GluonHQ](https://gluonhq.com/products/javafx/).
  - Распакуйте его в директорию (например, `/Users/sergejpronin/javafx-sdk-24.0.1`).
  - Обновите `<javafx.sdk.path>` в `pom.xml` каждого проекта:
    ```xml
    <javafx.sdk.path>/path/to/javafx-sdk-24.0.1</javafx.sdk.path>
    ```

3. **Перейдите в директорию проекта** (например, `loan-calculator`):
   ```bash
   cd loan-calculator
   ```

4. **Установите зависимости**:
   ```bash
   mvn clean install
   ```

---

### 🚀 Запуск проекта
Каждый проект имеет собственный README с подробными инструкциями. Общие шаги:
1. Перейдите в директорию проекта.
2. Соберите и запустите приложение (для проектов с GUI):
   ```bash
   mvn javafx:run
   ```
   Для проектов без GUI (например, `stack-queue-brackets`, `dictionary-frequency`):
   ```bash
   mvn exec:java -Dexec.mainClass="ru.vsu.cs.course1.MainApp"
   ```
3. Запустите юнит-тесты:
   ```bash
   mvn test
   ```

---

### 📂 Структура проекта
```
algorithms-and-data-structures/
├── 📂 loan-calculator/            # Проект Калькулятора кредитов
├── 📂 quicksort-fixed/            # Проект Быстрой сортировки с фиксированными элементами
├── 📂 random-binary-tree/         # Проект Случайного бинарного дерева
├── 📂 stack-queue-brackets/       # Проект Проверки скобочной структуры
├── 📂 linked-list-swap/           # Проект Обмена элементов в связном списке
├── 📂 dictionary-frequency/       # Проект Анализа частоты пар букв
├── 📜 LICENSE                     # Лицензия репозитория
└── 📖 README.md                   # Общий обзор репозитория
```

---

### 🤝 Внесение изменений
Приветствуются любые вклады! Чтобы внести изменения:
1. Сделайте форк репозитория.
2. Создайте новую ветку (`git checkout -b feature-branch`).
3. Внесите изменения.
4. Зафиксируйте изменения (`git commit -am 'Добавлена новая функция'`).
5. Отправьте ветку (`git push origin feature-branch`).
6. Создайте Pull Request.

---

### 📜 Лицензия
Репозиторий распространяется под лицензией MIT — см. файл [LICENSE](LICENSE) для подробностей.

---

### 📬 Контакты
По вопросам и обратной связи пишите на [sergejpronin@example.com](mailto:sergejpronin@example.com).