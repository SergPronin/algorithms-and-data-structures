package ru.vsu.cs.course1.GUI;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortApp extends Application {

    private List<RowData> rowDataList = new ArrayList<>();
    private VBox vbox = new VBox(15);
    private HBox controlPanel = new HBox(20);
    private VBox listContainer = new VBox(10);
    private Label statusLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Сортировка чисел с фиксированными элементами");

        vbox.setPadding(new Insets(20));

        controlPanel.setAlignment(Pos.CENTER);
        vbox.getChildren().add(controlPanel);

        Button addButton = createActionButton("Добавить элемент", event -> addNewElement());
        Button removeAllButton = createActionButton("Удалить все элементы", event -> removeAllElements());
        Button saveButton = createActionButton("Сохранить список", event -> saveList());

        controlPanel.getChildren().addAll(addButton, removeAllButton, saveButton);

        Button quickSortButton = createSortButton("Обычная быстрая сортировка", event -> sort(true));
        Button modifiedSortButton = createSortButton("Модифицированная быстрая сортировка", event -> sort(false));

        HBox sortButtonPanel = new HBox(20, quickSortButton, modifiedSortButton);
        sortButtonPanel.setAlignment(Pos.CENTER);
        vbox.getChildren().add(sortButtonPanel);

        Button resetFixesButton = createActionButton("Сбросить фиксации", event -> resetFixations());
        controlPanel.getChildren().add(resetFixesButton);

        statusLabel.setStyle("-fx-text-fill: red;");
        vbox.getChildren().add(statusLabel);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        vbox.getChildren().add(scrollPane);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createActionButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        button.setOnAction(action);
        addButtonHoverEffect(button);
        return button;
    }

    private Button createSortButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-font-size: 14px; -fx-padding: 10px 20px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        button.setOnAction(action);
        addButtonHoverEffect(button);
        return button;
    }

    private void addButtonHoverEffect(Button button) {
        button.setOnMouseEntered(event -> button.setOpacity(0.8));
        button.setOnMouseExited(event -> button.setOpacity(1.0));
    }

    private void addNewElement() {
        RowData newRow = new RowData(0, false);
        rowDataList.add(newRow);

        TextField numberField = new TextField();
        numberField.setPromptText("Число");
        numberField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                if (newText.isEmpty()) {
                    newRow.setNumber(0);
                    statusLabel.setText("");
                } else {
                    newRow.setNumber(Integer.parseInt(newText));
                    statusLabel.setText("");
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Ошибка: введите целое число!");
            }
        });

        CheckBox fixedCheckBox = new CheckBox();
        fixedCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            newRow.setFixed(isNowSelected);
            if (isNowSelected) {
                numberField.setStyle("-fx-background-color: #FFCDD2;");
            } else {
                numberField.setStyle("");
            }
        });

        Button removeButton = new Button("Удалить");
        removeButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px;");
        removeButton.setOnAction(event -> removeElement(newRow));

        HBox rowPanel = new HBox(10, numberField, fixedCheckBox, removeButton);
        rowPanel.setAlignment(Pos.CENTER_LEFT);
        rowPanel.setStyle("-fx-padding: 10px;");
        listContainer.getChildren().add(rowPanel);
    }

    private void removeAllElements() {
        rowDataList.clear();
        listContainer.getChildren().clear();
        statusLabel.setText("Все элементы удалены.");
    }

    private void removeElement(RowData rowData) {
        rowDataList.remove(rowData);
        listContainer.getChildren().removeIf(node -> {
            HBox hbox = (HBox) node;
            TextField textField = (TextField) hbox.getChildren().get(0);
            return textField.getText().isEmpty() || rowData.getNumber() == Integer.parseInt(textField.getText());
        });

        removeEmptyRows();
        statusLabel.setText("Элемент удалён.");
    }

    private void removeEmptyRows() {
        listContainer.getChildren().removeIf(node -> {
            HBox hbox = (HBox) node;
            TextField textField = (TextField) hbox.getChildren().get(0);
            return textField.getText().isEmpty();
        });
    }

    private void updateTable(Integer[] numbers) {
        listContainer.getChildren().clear();
        for (int i = 0; i < numbers.length; i++) {
            RowData row = rowDataList.get(i);
            row.setNumber(numbers[i]);

            TextField numberField = new TextField(String.valueOf(row.getNumber()));
            CheckBox fixedCheckBox = new CheckBox();
            fixedCheckBox.setSelected(row.isFixed());
            if (row.isFixed()) {
                numberField.setStyle("-fx-background-color: #FFCDD2;");
            }

            Button removeButton = new Button("Удалить");
            removeButton.setStyle("-fx-font-size: 12px; -fx-padding: 5px;");
            removeButton.setOnAction(event -> removeElement(row));

            fixedCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                row.setFixed(isNowSelected);
                if (isNowSelected) {
                    numberField.setStyle("-fx-background-color: #FFCDD2;");
                } else {
                    numberField.setStyle("");
                }
            });

            HBox rowPanel = new HBox(10, numberField, fixedCheckBox, removeButton);
            rowPanel.setStyle("-fx-padding: 10px;");
            listContainer.getChildren().add(rowPanel);
        }

        removeEmptyRows();
        statusLabel.setText("Список обновлён.");
    }

    private void sort(boolean regularSort) {
        try {
            Integer[] numbers = rowDataList.stream().map(RowData::getNumber).toArray(Integer[]::new);
            boolean[] fixed = getFixedArray();

            if (regularSort) {
                QuickSort.sort(numbers);
            } else {
                QuickSortModificated.sort(numbers, fixed);
            }

            updateTable(numbers);
            statusLabel.setText("Сортировка выполнена.");
        } catch (Exception e) {
            statusLabel.setText("Ошибка при сортировке: " + e.getMessage());
        }
    }

    private boolean[] getFixedArray() {
        boolean[] fixed = new boolean[rowDataList.size()];
        for (int i = 0; i < rowDataList.size(); i++) {
            fixed[i] = rowDataList.get(i).isFixed();
        }
        return fixed;
    }

    private void resetFixations() {
        for (RowData rowData : rowDataList) {
            rowData.setFixed(false);
        }
        Integer[] numbers = rowDataList.stream().map(RowData::getNumber).toArray(Integer[]::new);
        updateTable(numbers);
        statusLabel.setText("Фиксации сброшены.");
    }

    private void saveList() {
        String listContent = rowDataList.stream()
                .map(row -> row.getNumber() + (row.isFixed() ? " (fixed)" : ""))
                .collect(Collectors.joining("\n"));
        System.out.println("Сохранённый список:\n" + listContent);
        statusLabel.setText("Список сохранён в консоль.");
    }

    public static class RowData {
        private final IntegerProperty number = new SimpleIntegerProperty();
        private final BooleanProperty fixed = new SimpleBooleanProperty();

        public RowData(int number, boolean fixed) {
            this.number.set(number);
            this.fixed.set(fixed);
        }

        public Integer getNumber() {
            return number.get();
        }

        public void setNumber(int number) {
            this.number.set(number);
        }

        public IntegerProperty numberProperty() {
            return number;
        }

        public boolean isFixed() {
            return fixed.get();
        }

        public void setFixed(boolean fixed) {
            this.fixed.set(fixed);
        }

        public BooleanProperty fixedProperty() {
            return fixed;
        }
    }
}