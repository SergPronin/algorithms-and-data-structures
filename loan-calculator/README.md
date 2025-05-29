# Loan Calculator

## Overview
The **Loan Calculator** is a Java-based application designed to calculate loan payments, total credit sums, and overpayments for both **annuity** and **differentiated** loan types. It features a user-friendly JavaFX interface for inputting loan details and viewing results, along with unit tests to ensure calculation accuracy.

## Features
- Supports two loan types: **Annuity** (fixed monthly payments) and **Differentiated** (decreasing payments).
- Calculates monthly payments, total credit sum, and overpayment.
- Displays a detailed breakdown of payments for each month (especially for differentiated loans).
- Interactive JavaFX interface with custom CSS styling.
- Unit tests using JUnit 5 for validation of calculations.

## Technologies
- **Java**: 23
- **JavaFX**: 24.0.1
- **Maven**: For dependency management and building
- **JUnit**: 5.11.0 (for unit testing)

## How It Works
The application takes user input for:
- Loan amount (principal)
- Annual interest rate (%)
- Loan term (in months)
- Loan type (annuity or differentiated)

It then computes:
- Monthly payment (constant for annuity, varies for differentiated)
- Total credit sum over the term
- Overpayment (total paid minus principal)
- Monthly payment breakdown for differentiated loans

### Example
- **Input**:
    - Loan Amount: 100,000
    - Annual Interest Rate: 12%
    - Term: 12 months
    - Loan Type: Annuity
- **Output**:
    - Monthly Payment: 8,884.88
    - Total Credit Sum: 106,618.56
    - Overpayment: 6,618.56

## Prerequisites
- **Java 23** (e.g., Amazon Corretto: [Adoptium](https://adoptium.net/))
- **JavaFX 24.0.1 SDK** for macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/)
- **Maven**: For dependency management and building

## Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/algorithms-and-data-structures.git
   cd algorithms-and-data-structures/loan-calculator
   ```

2. **Set up JavaFX SDK**:
    - Download JavaFX SDK 24.0.1 from [GluonHQ](https://gluonhq.com/products/javafx/).
    - Extract it to a directory (e.g., `/Users/sergejpronin/javafx-sdk-24.0.1`).
    - Update the `<javafx.sdk.path>` in `pom.xml` to match your path:
      ```xml
      <javafx.sdk.path>/path/to/javafx-sdk-24.0.1</javafx.sdk.path>
      ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

## Running the Application
1. **Launch the JavaFX application**:
   ```bash
   mvn javafx:run
   ```

2. **Using the UI**:
    - Enter the loan amount (e.g., 100,000).
    - Enter the annual interest rate (e.g., 12.0).
    - Enter the loan term in months (e.g., 12).
    - Select the loan type (Annuity or Differentiated).
    - Click "Calculate" to see the results.

## Running Tests
The project includes unit tests in `CreditTest.java`. To run them:
```bash
mvn test
```

## Project Structure
```
loan-calculator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org/example/
│   │   │   │   └── Credit.java           # Core loan calculation logic
│   │   │   ├── org/example/ui/
│   │   │   │   └── CreditCalculatorApp.java  # JavaFX UI
│   │   │   └── module-info.java          # Module configuration for JavaFX
│   │   └── resources/
│   │       └── style.css                 # CSS styling for the UI
│   └── test/
│       └── java/
│           └── org/example/
│               └── CreditTest.java       # Unit tests for Credit class
├── pom.xml                                   # Maven configuration
└── README.md                                 # Project documentation
```

## Dependencies
- **JavaFX**: `org.openjfx:javafx-controls:24.0.1`, `org.openjfx:javafx-fxml:24.0.1`
- **JUnit**: `org.junit.jupiter:junit-jupiter-engine:5.11.0` (for testing)
- **Maven Plugins**:
    - `maven-compiler-plugin:3.13.0`
    - `javafx-maven-plugin:0.0.8`
    - `maven-surefire-plugin:3.2.5`

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Create a Pull Request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For questions or feedback, please contact [your-email@example.com](mailto:your-email@example.com).

---

## Русская версия

# Калькулятор кредитов

## Обзор
**Калькулятор кредитов** — это приложение на Java, предназначенное для расчёта платежей по кредиту, общей суммы кредита и переплаты для двух типов кредитов: **аннуитетного** и **дифференцированного**. Оно включает удобный интерфейс на JavaFX для ввода данных и просмотра результатов, а также юнит-тесты для проверки точности расчётов.

## Возможности
- Поддерживает два типа кредитов: **аннуитетный** (фиксированные ежемесячные платежи) и **дифференцированный** (убывающие платежи).
- Рассчитывает ежемесячные платежи, общую сумму кредита и переплату.
- Показывает детализированную разбивку платежей по месяцам (особенно для дифференцированных кредитов).
- Интерактивный интерфейс на JavaFX с пользовательскими стилями CSS.
- Юнит-тесты с использованием JUnit 5 для проверки расчётов.

## Технологии
- **Java**: 23
- **JavaFX**: 24.0.1
- **Maven**: Для управления зависимостями и сборки
- **JUnit**: 5.11.0 (для тестирования)

## Как это работает
Приложение принимает от пользователя следующие данные:
- Сумма кредита (основной долг)
- Годовая процентная ставка (%)
- Срок кредита (в месяцах)
- Тип кредита (аннуитетный или дифференцированный)

Затем вычисляет:
- Ежемесячный платёж (постоянный для аннуитета, переменный для дифференцированного)
- Общую сумму кредита за весь срок
- Переплату (общая сумма минус основной долг)
- Разбивку ежемесячных платежей для дифференцированных кредитов

### Пример
- **Входные данные**:
    - Сумма кредита: 100,000
    - Годовая процентная ставка: 12%
    - Срок: 12 месяцев
    - Тип кредита: Аннуитетный
- **Результат**:
    - Ежемесячный платёж: 8,884.88
    - Общая сумма кредита: 106,618.56
    - Переплата: 6,618.56

## Требования
- **Java 23** (например, Amazon Corretto: [Adoptium](https://adoptium.net/))
- **JavaFX 24.0.1 SDK** для macOS aarch64: [GluonHQ](https://gluonhq.com/products/javafx/)
- **Maven**: Для управления зависимостями и сборки

## Установка
1. **Клонируйте репозиторий**:
   ```bash
   git clone https://github.com/your-username/algorithms-and-data-structures.git
   cd algorithms-and-data-structures/loan-calculator
   ```

2. **Настройте JavaFX SDK**:
    - Скачайте JavaFX SDK 24.0.1 с [GluonHQ](https://gluonhq.com/products/javafx/).
    - Распакуйте его в директорию (например, `/Users/sergejpronin/javafx-sdk-24.0.1`).
    - Обновите `<javafx.sdk.path>` в `pom.xml` в соответствии с вашим путём:
      ```xml
      <javafx.sdk.path>/path/to/javafx-sdk-24.0.1</javafx.sdk.path>
      ```

3. **Установите зависимости**:
   ```bash
   mvn clean install
   ```

## Запуск приложения
1. **Запустите JavaFX-приложение**:
   ```bash
   mvn javafx:run
   ```

2. **Использование интерфейса**:
    - Введите сумму кредита (например, 100,000).
    - Введите годовую процентную ставку (например, 12.0).
    - Введите срок кредита в месяцах (например, 12).
    - Выберите тип кредита (аннуитетный или дифференцированный).
    - Нажмите "Рассчитать" для просмотра результатов.

## Запуск тестов
Проект включает юнит-тесты в `CreditTest.java`. Для их запуска:
```bash
mvn test
```

## Структура проекта
```
loan-calculator/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── org/example/
│   │   │   │   └── Credit.java           # Основная логика расчёта кредита
│   │   │   ├── org/example/ui/
│   │   │   │   └── CreditCalculatorApp.java  # Интерфейс на JavaFX
│   │   │   └── module-info.java          # Конфигурация модуля для JavaFX
│   │   └── resources/
│   │       └── style.css                 # Стили CSS для интерфейса
│   └── test/
│       └── java/
│           └── org/example/
│               └── CreditTest.java       # Юнит-тесты для класса Credit
├── pom.xml                                   # Конфигурация Maven
└── README.md                                 # Документация проекта
```

## Зависимости
- **JavaFX**: `org.openjfx:javafx-controls:24.0.1`, `org.openjfx:javafx-fxml:24.0.1`
- **JUnit**: `org.junit.jupiter:junit-jupiter-engine:5.11.0` (для тестирования)
- **Maven-плагины**:
    - `maven-compiler-plugin:3.13.0`
    - `javafx-maven-plugin:0.0.8`
    - `maven-surefire-plugin:3.2.5`

## Внесение изменений
1. Сделайте форк репозитория.
2. Создайте новую ветку (`git checkout -b feature-branch`).
3. Внесите изменения.
4. Зафиксируйте изменения (`git commit -am 'Добавлена новая функция'`).
5. Отправьте ветку (`git push origin feature-branch`).
6. Создайте Pull Request.

## Лицензия
Проект распространяется под лицензией MIT — см. файл [LICENSE](LICENSE) для подробностей.

## Контакты
По вопросам и обратной связи пишите на [your-email@example.com](mailto:your-email@example.com).