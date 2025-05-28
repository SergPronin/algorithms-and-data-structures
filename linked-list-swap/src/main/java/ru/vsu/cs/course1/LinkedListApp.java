package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LinkedListApp extends Application {
    private final DoubleLinkedList<String> list = new DoubleLinkedList<>();
    private final ListView<String> listView = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Двусвязный список (JavaFX)");

        // Поле ввода
        TextField inputField = new TextField();
        inputField.setPromptText("Введите значение");
        inputField.setMaxWidth(250);
        inputField.getStyleClass().add("input-field");

        // Кнопки
        Button addFirstBtn = createStyledButton("➕ В начало");
        Button addLastBtn = createStyledButton("➕ В конец");
        Button removeFirstBtn = createStyledButton("❌ Из начала");
        Button removeLastBtn = createStyledButton("❌ Из конца");
        Button swapPairsBtn = createStyledButton("🔄 Поменять пары");
        Button clearBtn = createStyledButton("🗑️ Очистить");

        // Действия кнопок
        addFirstBtn.setOnAction(e -> {
            String value = inputField.getText();
            if (!value.trim().isEmpty()) {
                list.addFirst(value);
                updateDisplay();
                inputField.clear();
            } else {
                showAlert("Ошибка", "Введите непустое значение!");
            }
        });

        addLastBtn.setOnAction(e -> {
            String value = inputField.getText();
            if (!value.trim().isEmpty()) {
                list.addLast(value);
                updateDisplay();
                inputField.clear();
            } else {
                showAlert("Ошибка", "Введите непустое значение!");
            }
        });

        removeFirstBtn.setOnAction(e -> {
            try {
                list.removeFirst();
                updateDisplay();
            } catch (IllegalStateException ex) {
                showAlert("Ошибка", "Список пуст!");
            }
        });

        removeLastBtn.setOnAction(e -> {
            try {
                list.removeLast();
                updateDisplay();
            } catch (IllegalStateException ex) {
                showAlert("Ошибка", "Список пуст!");
            }
        });

        swapPairsBtn.setOnAction(e -> {
            if (list.size() < 2) {
                showAlert("Ошибка", "Список должен содержать хотя бы два элемента!");
                return;
            }
            list.swapPairs();
            updateDisplay();
        });

        clearBtn.setOnAction(e -> {
            while (!list.isEmpty()) {
                list.removeFirst();
            }
            updateDisplay();
        });

        // Настройка ListView
        listView.setPrefHeight(250);
        listView.getStyleClass().add("list-view");

        // Компоновка кнопок
        HBox buttonsRow1 = new HBox(10, addFirstBtn, addLastBtn);
        HBox buttonsRow2 = new HBox(10, removeFirstBtn, removeLastBtn);
        HBox buttonsRow3 = new HBox(10, swapPairsBtn, clearBtn);

        buttonsRow1.setAlignment(Pos.CENTER);
        buttonsRow2.setAlignment(Pos.CENTER);
        buttonsRow3.setAlignment(Pos.CENTER);

        // Основной layout
        VBox layout = new VBox(15, inputField, buttonsRow1, buttonsRow2, buttonsRow3, listView);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(15));
        layout.getStyleClass().add("main-pane");

        // Настройка сцены
        Scene scene = new Scene(layout, 400, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        updateDisplay();
    }

    private void updateDisplay() {
        listView.getItems().clear();
        for (int i = 0; i < list.size(); i++) {
            listView.getItems().add("📌 " + (i + 1) + ". " + list.get(i));
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.getStyleClass().add("custom-button");
        return button;
    }
}