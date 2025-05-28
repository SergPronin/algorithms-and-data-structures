package ru.vsu.cs.course1;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Visualizes a random binary tree with user-defined parameters using JavaFX.
 */
public class TreeVisualizerWithInput extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField minValueField = new TextField("1");
        minValueField.setPromptText("minValue");

        TextField maxValueField = new TextField("100");
        maxValueField.setPromptText("maxValue");

        TextField leftPField = new TextField("0.5");
        leftPField.setPromptText("leftP (0 - 1)");

        TextField rightPField = new TextField("0.5");
        rightPField.setPromptText("rightP (0 - 1)");

        TextField maxHeightField = new TextField("5");
        maxHeightField.setPromptText("maxHeight");

        Button generateButton = new Button("Generate Tree");

        Pane treePane = new Pane();
        treePane.setPrefSize(800, 600);

        generateButton.setOnAction(e -> {
            try {
                int minValue = Integer.parseInt(minValueField.getText());
                int maxValue = Integer.parseInt(maxValueField.getText());
                double leftP = Double.parseDouble(leftPField.getText());
                double rightP = Double.parseDouble(rightPField.getText());
                int maxHeight = Integer.parseInt(maxHeightField.getText());

                if (minValue > maxValue || leftP < 0 || leftP > 1 || rightP < 0 || rightP > 1 || maxHeight <= 0) {
                    showError("Invalid input values!");
                    return;
                }

                FadeTransition clearFade = new FadeTransition(Duration.seconds(0.3), treePane);
                clearFade.setFromValue(1);
                clearFade.setToValue(0);
                clearFade.setOnFinished(event -> {
                    treePane.getChildren().clear();
                    TreeNode root = RandomBinaryTree.generateTree(minValue, maxValue, leftP, rightP, maxHeight, 0);
                    drawTree(root, treePane, 400, 50, 150, 5);
                    treePane.setOpacity(1);
                });
                clearFade.play();
            } catch (NumberFormatException ex) {
                showError("Please enter valid numbers!");
            }
        });

        VBox inputBox = new VBox(10,
                new Label("Минимальное значение узла"), minValueField,
                new Label("Максимальное значение узла"), maxValueField,
                new Label("Вероятность левого потомка (0–1)"), leftPField,
                new Label("Вероятность правого потомка (0–1)"), rightPField,
                new Label("Максимальная высота дерева"), maxHeightField,
                generateButton);
        inputBox.setPadding(new Insets(20));

        HBox rootLayout = new HBox(20, inputBox, treePane);
        rootLayout.setPadding(new Insets(20));

        Scene scene = new Scene(rootLayout, 1000, 600);
        primaryStage.setTitle("Random Binary Tree Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(TreeNode node, Pane pane, double x, double y, double offset, int level) {
        if (node == null || level == 0) return;

        Circle circle = new Circle(x, y, 15);
        circle.setFill(Color.CORAL);
        pane.getChildren().add(circle);

        Text valueText = new Text(x - 7, y + 5, Integer.toString(node.getValue()));
        valueText.setFill(Color.WHITE);
        pane.getChildren().add(valueText);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), circle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        if (node.getLeft() != null) {
            Line leftLine = new Line(x, y, x - offset, y + 50);
            leftLine.setStroke(Color.BLUE);
            leftLine.setStrokeWidth(2);
            pane.getChildren().add(leftLine);
            drawTree(node.getLeft(), pane, x - offset, y + 50, offset / 2, level - 1);
        }

        if (node.getRight() != null) {
            Line rightLine = new Line(x, y, x + offset, y + 50);
            rightLine.setStroke(Color.GREEN);
            rightLine.setStrokeWidth(2);
            pane.getChildren().add(rightLine);
            drawTree(node.getRight(), pane, x + offset, y + 50, offset / 2, level - 1);
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}