package ru.vsu.cs.course1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import ru.vsu.cs.course1.controller.GameController;

public class MainView {
    private VBox root;
    private Button[][] buttons;
    private Label resultLabel;
    private TextArea analysisArea;
    private Canvas graphCanvas;
    private Button zoomInButton;
    private Button zoomOutButton;

    public MainView() {
        initializeUI();
    }

    private void initializeUI() {
        root = new VBox(15);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f5f5f5;");

        // Title
        Label titleLabel = new Label("Крестики-нолики: Анализ вероятностей");
        titleLabel.getStyleClass().add("title");

        // Main content
        HBox mainContent = new HBox(15);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.prefWidthProperty().bind(root.widthProperty());

        // Board
        VBox boardBox = new VBox(10);
        boardBox.setAlignment(Pos.CENTER);
        Label boardLabel = new Label("Игровая доска");
        boardLabel.getStyleClass().add("subtitle");
        GridPane boardGrid = new GridPane();
        boardGrid.setHgap(8);
        boardGrid.setVgap(8);
        boardGrid.setAlignment(Pos.CENTER);
        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setMinSize(100, 100);
                buttons[i][j].setPrefSize(100, 100);
                buttons[i][j].getStyleClass().add("cell-button");
                boardGrid.add(buttons[i][j], j, i);
            }
        }
        boardBox.getChildren().addAll(boardLabel, boardGrid);

        // Graph
        VBox graphBox = new VBox(10);
        graphBox.setAlignment(Pos.CENTER);
        Label graphLabel = new Label("Граф ходов");
        graphLabel.getStyleClass().add("subtitle");
        graphCanvas = new Canvas(600, 400);
        ScrollPane graphScroll = new ScrollPane(graphCanvas);
        graphScroll.setPrefSize(620, 420);
        graphScroll.setMaxWidth(Region.USE_PREF_SIZE);
        graphScroll.getStyleClass().add("graph-scroll");
        HBox zoomBox = new HBox(10);
        zoomInButton = new Button("+");
        zoomOutButton = new Button("−");
        zoomInButton.getStyleClass().add("action-button");
        zoomOutButton.getStyleClass().add("action-button");
        zoomBox.setAlignment(Pos.CENTER);
        zoomBox.getChildren().addAll(zoomInButton, zoomOutButton);
        graphBox.getChildren().addAll(graphLabel, graphScroll, zoomBox);

        mainContent.getChildren().addAll(boardBox, graphBox);

        // Result
        resultLabel = new Label();
        resultLabel.getStyleClass().add("result");

        // Analysis
        VBox analysisBox = new VBox(10);
        analysisBox.setAlignment(Pos.CENTER);
        Label analysisLabel = new Label("Анализ");
        analysisLabel.getStyleClass().add("subtitle");
        analysisArea = new TextArea();
        analysisArea.setEditable(false);
        analysisArea.setPrefSize(800, 200);
        analysisArea.setMaxWidth(Region.USE_PREF_SIZE);
        analysisArea.getStyleClass().add("analysis-area");
        analysisBox.getChildren().addAll(analysisLabel, analysisArea);

        // Buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setAlignment(Pos.CENTER);
        Button resetButton = new Button("Сброс");
        Button exportButton = new Button("Экспорт");
        resetButton.getStyleClass().add("action-button");
        exportButton.getStyleClass().add("action-button");
        buttonBox.getChildren().addAll(resetButton, exportButton);

        // Controller
        GameController controller = new GameController(buttons, resultLabel, analysisArea, graphCanvas, zoomInButton, zoomOutButton);
        resetButton.setOnAction(e -> controller.reset());
        exportButton.setOnAction(e -> controller.exportResults());

        // Assemble
        root.getChildren().addAll(titleLabel, mainContent, resultLabel, analysisBox, buttonBox);
    }

    public VBox getRoot() {
        return root;
    }
}