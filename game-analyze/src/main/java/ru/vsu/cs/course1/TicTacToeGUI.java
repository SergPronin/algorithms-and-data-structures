package ru.vsu.cs.course1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.util.*;

public class TicTacToeGUI extends Application {
    private Board board = new Board();
    private Button[][] cellButtons = new Button[3][3];
    private Label statusLabel;
    private Canvas graphCanvas;
    private GraphicsContext graphGc;
    private Board.Cell currentPlayer = Board.Cell.X;
    private double scale = 0.5; // Начальный масштаб для видимости
    private static final double SCALE_FACTOR = 1.2;
    private double translateX = 0;
    private double translateY = 0;
    private Map<Board, Boolean> expandedNodes = new HashMap<>(); // Отслеживание раскрытых узлов

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        HBox mainContent = new HBox(20);
        mainContent.setAlignment(Pos.CENTER);

        // Левая часть: игра и вероятности
        VBox gameBox = new VBox(15);
        gameBox.setAlignment(Pos.CENTER);
        gameBox.setPadding(new Insets(10));
        gameBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #d0d0d0; -fx-border-width: 2;");

        Label titleLabel = new Label("Крестики-нолики");
        titleLabel.setFont(new Font("Arial", 28));
        titleLabel.setStyle("-fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                cellButtons[i][j] = new Button(".");
                cellButtons[i][j].setFont(new Font("Arial", 40));
                cellButtons[i][j].setMinSize(80, 80);
                cellButtons[i][j].setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 1;");
                cellButtons[i][j].setOnAction(e -> makeMove(row, col));
                gridPane.add(cellButtons[i][j], j, i);
            }
        }

        statusLabel = new Label("Ходит: X");
        statusLabel.setFont(new Font("Arial", 16));
        statusLabel.setTextFill(Color.DARKBLUE);
        statusLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10;");

        Button resetGameButton = new Button("Новая игра");
        resetGameButton.setFont(new Font("Arial", 16));
        resetGameButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        resetGameButton.setOnAction(e -> resetGame());

        gameBox.getChildren().addAll(titleLabel, gridPane, statusLabel, resetGameButton);

        // Правая часть: граф
        VBox graphBox = new VBox(10);
        graphBox.setAlignment(Pos.CENTER);
        graphBox.setPadding(new Insets(10));
        graphBox.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #d0d0d0; -fx-border-width: 2;");

        Label graphLabel = new Label("Дерево ходов (глубина 3, до 4 узлов, клик для сворачивания)");
        graphLabel.setFont(new Font("Arial", 20));
        graphLabel.setStyle("-fx-font-weight: bold;");

        graphCanvas = new Canvas(2000, 1600); // Большой размер канваса
        graphGc = graphCanvas.getGraphicsContext2D();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(graphCanvas);
        scrollPane.setPrefSize(600, 600); // Увеличенный размер окна графа
        scrollPane.setPannable(true);

        // Обработка кликов по узлам
        graphCanvas.setOnMouseClicked(event -> {
            double x = event.getX() / scale - translateX;
            double y = event.getY() / scale - translateY;
            handleNodeClick(x, y);
        });

        HBox zoomControls = new HBox(10);
        zoomControls.setAlignment(Pos.CENTER);
        Button zoomIn = new Button("Увеличить");
        Button zoomOut = new Button("Уменьшить");
        Button resetZoom = new Button("Сбросить вид");
        zoomIn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        zoomOut.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        resetZoom.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        zoomIn.setOnAction(e -> { scale *= SCALE_FACTOR; drawGraph(new GameGraph()); });
        zoomOut.setOnAction(e -> { scale /= SCALE_FACTOR; scale = Math.max(0.3, scale); drawGraph(new GameGraph()); });
        resetZoom.setOnAction(e -> { resetZoom(); scrollPane.setHvalue(0.5); scrollPane.setVvalue(0.5); drawGraph(new GameGraph()); });
        zoomControls.getChildren().addAll(zoomIn, zoomOut, resetZoom);

        graphBox.getChildren().addAll(graphLabel, scrollPane, zoomControls);

        mainContent.getChildren().addAll(gameBox, graphBox);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 1000, 700); // Увеличенный размер окна приложения
        primaryStage.setTitle("Крестики-нолики: Вероятности и Граф");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateGameState();
    }

    private void makeMove(int row, int col) {
        if (board.grid[row][col] == Board.Cell.EMPTY && board.checkResult() == Board.GameResult.ONGOING) {
            board.grid[row][col] = currentPlayer;
            cellButtons[row][col].setText(currentPlayer.toString());
            currentPlayer = (currentPlayer == Board.Cell.X) ? Board.Cell.O : Board.Cell.X;
            updateGameState();
        }
    }

    private void resetGame() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellButtons[i][j].setText(".");
                board.grid[i][j] = Board.Cell.EMPTY;
            }
        }
        currentPlayer = Board.Cell.X;
        expandedNodes.clear();
        updateGameState();
    }

    private void resetZoom() {
        scale = 0.5; // Сбрасываем к начальному масштабу
        translateX = 0;
        translateY = 0;
    }

    private void updateGameState() {
        // Обновляем вероятности
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.grid[i][j] == Board.Cell.X) xCount++;
                else if (board.grid[i][j] == Board.Cell.O) oCount++;
            }
        }
        Board.Cell nextPlayer = (xCount <= oCount) ? Board.Cell.X : Board.Cell.O;

        GameGraph graph = new GameGraph();
        Probabilities probs = graph.calculateProbabilities(board, nextPlayer);
        Board.GameResult result = board.checkResult();

        String status = result == Board.GameResult.ONGOING ? "Ходит: " + currentPlayer :
                result == Board.GameResult.X_WINS ? "Победа X!" :
                        result == Board.GameResult.O_WINS ? "Победа O!" : "Ничья!";
        statusLabel.setText(status + "\n" + probs.toString());

        // Автоматически раскрываем все узлы
        expandedNodes.clear();
        Map<Board, List<Board>> subGraph = graph.getFilteredSubGraph(board, currentPlayer, 3, 4); // До 4 узлов на уровень
        for (Board node : subGraph.keySet()) {
            expandedNodes.put(node, true);
        }

        // Обновляем граф
        drawGraph(graph);
    }

    private void handleNodeClick(double x, double y) {
        Map<Board, Point> positions = buildGraphPositions(new GameGraph());
        for (Map.Entry<Board, Point> entry : positions.entrySet()) {
            Point p = entry.getValue();
            double nodeWidth = 60;
            double nodeHeight = 60;
            if (x >= p.x - nodeWidth / 2 && x <= p.x + nodeWidth / 2 &&
                    y >= p.y - nodeHeight / 2 && y <= p.y + nodeHeight / 2) {
                Board clickedBoard = entry.getKey();
                expandedNodes.put(clickedBoard, !expandedNodes.getOrDefault(clickedBoard, true));
                drawGraph(new GameGraph());
                break;
            }
        }
    }

    private Map<Board, Point> buildGraphPositions(GameGraph graph) {
        Map<Board, List<Board>> subGraph = graph.getFilteredSubGraph(board, currentPlayer, 3, 4);
        Map<Board, Point> positions = new HashMap<>();
        double nodeWidth = 60;
        double nodeHeight = 60;
        int yOffset = 50;
        int levelHeight = 450; // Большое расстояние между уровнями

        // Размещаем текущую доску
        positions.put(board, new Point(1000, yOffset));

        // Размещаем следующие уровни
        List<Board> currentLevel = new ArrayList<>();
        currentLevel.add(board);
        for (int level = 1; level <= 3; level++) {
            List<Board> nextLevel = new ArrayList<>();
            int totalChildren = 0;
            for (Board current : currentLevel) {
                List<Board> children = subGraph.getOrDefault(current, new ArrayList<>());
                if (expandedNodes.getOrDefault(current, false)) {
                    totalChildren += children.size();
                    nextLevel.addAll(children);
                }
            }
            double xSpacing = totalChildren > 0 ? Math.max(250, 1600.0 / totalChildren) : 250; // Минимум 250 пикселей
            double xStart = 1000 - (totalChildren - 1) * xSpacing / 2.0;
            int childIndex = 0;
            for (Board current : currentLevel) {
                List<Board> children = subGraph.getOrDefault(current, new ArrayList<>());
                if (expandedNodes.getOrDefault(current, false)) {
                    for (Board child : children) {
                        double x = xStart + childIndex * xSpacing;
                        double y = yOffset + level * levelHeight;
                        positions.put(child, new Point(x, y));
                        childIndex++;
                    }
                }
            }
            if (nextLevel.isEmpty()) break;
            currentLevel = nextLevel;
        }
        return positions;
    }

    private void drawGraph(GameGraph graph) {
        graphGc.clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
        graphGc.save();
        graphGc.translate(translateX, translateY);
        graphGc.scale(scale, scale);

        Map<Board, List<Board>> subGraph = graph.getFilteredSubGraph(board, currentPlayer, 3, 4);
        Map<Board, Point> positions = buildGraphPositions(graph);
        Map<Board, Move> moveLabels = new HashMap<>();
        double nodeWidth = 60;
        double nodeHeight = 60;

        // Рисуем рёбра и метки
        for (Map.Entry<Board, List<Board>> entry : subGraph.entrySet()) {
            Board parent = entry.getKey();
            if (!positions.containsKey(parent) || !expandedNodes.getOrDefault(parent, false)) continue;
            for (Board child : entry.getValue()) {
                if (!positions.containsKey(child)) continue;
                Move move = findMove(parent, child);
                moveLabels.put(child, move);
                Point p1 = positions.get(parent);
                Point p2 = positions.get(child);
                graphGc.setStroke(Color.BLACK);
                graphGc.strokeLine(p1.x, p1.y + nodeHeight / 2, p2.x, p2.y - nodeHeight / 2);
                graphGc.setFont(new Font("Arial", 12));
                graphGc.setFill(Color.DARKRED);
                graphGc.fillText(move.toString(), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            }
        }

        // Рисуем узлы
        for (Map.Entry<Board, Point> entry : positions.entrySet()) {
            Board b = entry.getKey();
            Point p = entry.getValue();
            boolean isCurrent = b.equals(board);
            boolean isTerminal = b.checkResult() != Board.GameResult.ONGOING;
            drawBoard(b, p.x, p.y, nodeWidth, nodeHeight, isCurrent, isTerminal);
        }

        graphGc.restore();
    }

    private Move findMove(Board from, Board to) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (from.grid[i][j] == Board.Cell.EMPTY && to.grid[i][j] != Board.Cell.EMPTY) {
                    return new Move(i + 1, j + 1);
                }
            }
        }
        return new Move(0, 0);
    }

    private void drawBoard(Board board, double x, double y, double width, double height, boolean isCurrent, boolean isTerminal) {
        graphGc.setFill(Color.LIGHTGRAY);
        graphGc.fillRect(x - width / 2, y - height / 2, width, height);
        graphGc.setStroke(isCurrent ? Color.GREEN : isTerminal ? Color.RED : Color.BLACK);
        graphGc.setLineWidth(isCurrent || isTerminal ? 2 : 1);
        graphGc.strokeRect(x - width / 2, y - height / 2, width, height);

        graphGc.setFont(new Font("Arial", 12));
        graphGc.setFill(Color.BLACK);
        double cellWidth = width / 3;
        double cellHeight = height / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String text = board.grid[i][j] == Board.Cell.EMPTY ? "." : board.grid[i][j].toString();
                graphGc.fillText(text, x - width / 2 + j * cellWidth + cellWidth / 3, y - height / 2 + i * cellHeight + cellHeight / 2);
            }
        }
    }

    private static class Point {
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Move {
        int row, col;
        Move(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public String toString() {
            return row + "," + col;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}