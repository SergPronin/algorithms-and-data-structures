package org.example.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Credit;
import org.example.Credit.CreditType;

public class CreditCalculatorApp extends Application {
    private TextField principalField = new TextField();
    private TextField rateField = new TextField();
    private TextField termField = new TextField();
    private ChoiceBox<CreditType> typeChoiceBox = new ChoiceBox<>();
    private TextArea resultArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        // Инициализация интерфейса
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Label("Сумма кредита:"), 0, 0);
        grid.add(principalField, 1, 0);
        grid.add(new Label("Годовая ставка (%):"), 0, 1);
        grid.add(rateField, 1, 1);
        grid.add(new Label("Срок (месяцы):"), 0, 2);
        grid.add(termField, 1, 2);
        grid.add(new Label("Тип кредита:"), 0, 3);
        typeChoiceBox.getItems().addAll(CreditType.ANNUITY, CreditType.DIFFERENTIATED);
        typeChoiceBox.setValue(CreditType.ANNUITY);
        grid.add(typeChoiceBox, 1, 3);

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setOnAction(e -> calculate());
        grid.add(calculateButton, 1, 4);

        resultArea.setEditable(false);
        VBox vbox = new VBox(10, grid, resultArea);

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Калькулятор кредита");
        primaryStage.show();
    }

    private void calculate() {
        try {
            double principal = Double.parseDouble(principalField.getText());
            double rate = Double.parseDouble(rateField.getText());
            int term = Integer.parseInt(termField.getText());
            CreditType type = typeChoiceBox.getValue();

            Credit credit = new Credit(principal, rate, term, type);

            StringBuilder result = new StringBuilder();
            result.append("Ежемесячный платёж: ").append(
                            type == CreditType.ANNUITY ? credit.calculateAnnuityPayment() : credit.calculateDifferentiatedPayment())
                    .append("\n");
            result.append("Общая сумма: ").append(credit.calculateTotalCreditSum()).append("\n");
            result.append("Переплата: ").append(credit.calculateOverpayment()).append("\n");

            for (int month = 1; month <= term; month++) {
                double payment = credit.calculateMonthSum(month);
                result.append("Платёж за месяц ").append(month).append(": ").append(payment).append("\n");
            }

            resultArea.setText(result.toString());
        } catch (NumberFormatException e) {
            resultArea.setText("Ошибка: введите корректные числа!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}