package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MainApp extends Application {

    private File selectedFile;
    private final Label statusLabel = new Label();
    private final ComboBox<String> mapTypeCombo = new ComboBox<>();
    private final TableView<LetterPairFrequency.PairResult> resultTable = new TableView<>();

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #f4f4f4;");

        // Заголовок
        Label titleLabel = new Label("Анализ частоты пар букв");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Поле и кнопка для выбора файла
        TextField filePathField = new TextField();
        filePathField.setPrefWidth(300);
        filePathField.setEditable(false);
        filePathField.setStyle("-fx-font-size: 14px;");
        Button chooseFileButton = new Button("Выбрать файл");
        chooseFileButton.setStyle("-fx-font-size: 14px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-cursor: hand;");
        chooseFileButton.setOnAction(e -> chooseFile(primaryStage));
        HBox fileBox = new HBox(10, new Label("Файл: "), filePathField, chooseFileButton);
        fileBox.setAlignment(Pos.CENTER);

        // Выбор типа Map
        mapTypeCombo.setItems(FXCollections.observableArrayList("HashMap", "TreeMap", "LinkedHashMap", "CustomHashMap"));
        mapTypeCombo.getSelectionModel().select("HashMap");
        mapTypeCombo.setStyle("-fx-font-size: 14px;");
        HBox mapBox = new HBox(10, new Label("Тип Map: "), mapTypeCombo);
        mapBox.setAlignment(Pos.CENTER);

        // Кнопка анализа
        Button analyzeButton = new Button("Анализировать");
        analyzeButton.setStyle("-fx-font-size: 14px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand;");
        analyzeButton.setOnAction(e -> analyzeText());

        // Таблица результатов
        TableColumn<LetterPairFrequency.PairResult, String> pairColumn = new TableColumn<>("Пара букв");
        pairColumn.setPrefWidth(120);
        pairColumn.setCellValueFactory(cellData -> cellData.getValue().pairProperty());

        TableColumn<LetterPairFrequency.PairResult, Number> countColumn = new TableColumn<>("Количество");
        countColumn.setPrefWidth(120);
        countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty());

        TableColumn<LetterPairFrequency.PairResult, Number> frequencyColumn = new TableColumn<>("Частота");
        frequencyColumn.setPrefWidth(120);
        frequencyColumn.setCellValueFactory(cellData -> cellData.getValue().frequencyProperty());
        frequencyColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(String.format("%.4f", item.doubleValue()));
                }
            }
        });

        resultTable.getColumns().addAll(pairColumn, countColumn, frequencyColumn);
        resultTable.setPrefHeight(400);
        resultTable.setStyle("-fx-font-size: 14px;");

        statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");

        // Сборка интерфейса
        root.getChildren().addAll(titleLabel, fileBox, mapBox, analyzeButton, statusLabel, resultTable);

        // Настройка сцены
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Анализ частоты пар букв");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void chooseFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите текстовый файл");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            selectedFile = file;
            statusLabel.setText("Выбран файл: " + file.getName());
        }
    }

    private void analyzeText() {
        if (selectedFile == null) {
            statusLabel.setText("Ошибка: выберите файл!");
            statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");
            return;
        }

        try {
            Map<String, Integer> map = switch (mapTypeCombo.getValue()) {
                case "HashMap" -> new HashMap<>();
                case "TreeMap" -> new TreeMap<>();
                case "LinkedHashMap" -> new LinkedHashMap<>();
                case "CustomHashMap" -> new CustomHashMap();
                default -> new HashMap<>();
            };
            LetterPairFrequency.Result result = LetterPairFrequency.analyzeText(selectedFile.getAbsolutePath(), map);
            ObservableList<LetterPairFrequency.PairResult> data = FXCollections.observableArrayList(result.pairs());
            resultTable.setItems(data);
            statusLabel.setText("Общее количество пар букв: " + result.totalPairs());
            statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2e8b57;");
        } catch (IOException e) {
            statusLabel.setText("Ошибка при чтении файла: " + e.getMessage());
            statusLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff0000;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}