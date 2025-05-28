package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainApp extends Application {

    private TextArea inputArea;
    private TextArea outputArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bracket Checker");

        // Создаем текстовые области для ввода и вывода
        inputArea = new TextArea();
        inputArea.setWrapText(true);
        inputArea.setPromptText("Введите скобочное выражение (например, ([]){})...");
        inputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New';");

        outputArea = new TextArea();
        outputArea.setWrapText(true);
        outputArea.setEditable(false);
        outputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-text-fill: #2e8b57;");

        // Кнопка проверки
        Button checkButton = new Button("Проверить скобки");
        checkButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");

        // Кнопка загрузки из файла
        Button loadButton = new Button("Загрузить из файла");
        loadButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-cursor: hand;");

        // Кнопка очистки
        Button clearButton = new Button("Очистить");
        clearButton.setStyle("-fx-font-size: 14px; -fx-background-color: #f44336; -fx-text-fill: white; -fx-cursor: hand;");

        // Обработчик проверки
        checkButton.setOnAction(event -> {
            String input = inputArea.getText().trim();
            if (input.isEmpty()) {
                outputArea.setText("Ошибка: Введите скобочное выражение!");
                outputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-text-fill: #ff0000;");
                return;
            }

            boolean resultCustom = new BracketChecker().check(input);
            boolean resultStandard = new BracketCheckerStandard().check(input);
            boolean resultRecursive = new BracketCheckerRecursive().check(input);

            outputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-text-fill: #2e8b57;");
            outputArea.setText(String.format(
                    "Результат проверки выражения: '%s'\n\n" +
                            "Кастомный стек: %s\n" +
                            "Стандартный стек: %s\n" +
                            "Рекурсивный метод: %s\n",
                    input,
                    resultCustom ? "Корректно" : "Ошибка",
                    resultStandard ? "Корректно" : "Ошибка",
                    resultRecursive ? "Корректно" : "Ошибка"
            ));
        });

        // Обработчик для загрузки из файла
        loadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Открыть текстовый файл");
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    inputArea.setText(content.toString().trim());
                    outputArea.setText("Файл успешно загружен!");
                } catch (Exception e) {
                    outputArea.setText("Ошибка при чтении файла: " + e.getMessage());
                    outputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-text-fill: #ff0000;");
                }
            }
        });

        // Обработчик для очистки
        clearButton.setOnAction(event -> {
            inputArea.clear();
            outputArea.clear();
            outputArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-text-fill: #2e8b57;");
        });

        Label titleLabel = new Label("Проверка скобочных выражений");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label instructionLabel = new Label("Поддерживаются круглые (), квадратные [] и фигурные {} скобки.");
        instructionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");

        VBox root = new VBox(10, titleLabel, instructionLabel, inputArea, checkButton, loadButton, clearButton, outputArea);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f4f4f4;");

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}