package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Visualizes a random binary tree with fixed parameters using JavaFX.
 */
public class TreeVisualizer extends Application {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100;
    private static final double LEFT_P = 0.5;
    private static final double RIGHT_P = 0.5;
    private static final int MAX_HEIGHT = 5;

    @Override
    public void start(Stage primaryStage) {
        TreeNode root = RandomBinaryTree.generateTree(MIN_VALUE, MAX_VALUE, LEFT_P, RIGHT_P, MAX_HEIGHT, 0);

        Pane rootPane = new Pane();
        drawTree(root, rootPane, 400, 50, 150, 4);

        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setTitle("Визуализация случайного бинарного дерева");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Draws the binary tree on the specified pane.
     * @param node The root node of the tree or subtree.
     * @param pane The pane to draw on.
     * @param x The x-coordinate of the current node.
     * @param y The y-coordinate of the current node.
     * @param offset The horizontal offset for child nodes.
     * @param level The remaining levels to draw.
     */
    private void drawTree(TreeNode node, Pane pane, double x, double y, double offset, int level) {
        if (node == null || level == 0) return;

        Text valueText = new Text(x, y, Integer.toString(node.getValue()));
        pane.getChildren().add(valueText);

        if (node.getLeft() != null) {
            Line leftLine = new Line(x, y, x - offset, y + 50);
            pane.getChildren().add(leftLine);
            drawTree(node.getLeft(), pane, x - offset, y + 50, offset / 2, level - 1);
        }

        if (node.getRight() != null) {
            Line rightLine = new Line(x, y, x + offset, y + 50);
            pane.getChildren().add(rightLine);
            drawTree(node.getRight(), pane, x + offset, y + 50, offset / 2, level - 1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}