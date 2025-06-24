//package ru.vsu.cs.course1.controller;
//
//import javafx.animation.FadeTransition;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.util.Duration;
//import ru.vsu.cs.course1.model.*;
//import ru.vsu.cs.course1.utils.ResultExporter;
//import ru.vsu.cs.course1.view.GraphVisualizer;
//
//import java.util.List;
//
//public class GameController {
//    private Board board;
//    private Graph graph;
//    private Button[][] buttons;
//    private Label resultLabel;
//    private TextArea analysisArea;
//    private Canvas graphCanvas;
//    private GraphVisualizer graphVisualizer;
//    private double graphScale = 1.0;
//
//    public GameController(Button[][] buttons, Label resultLabel, TextArea analysisArea, Canvas graphCanvas, Button zoomInButton, Button zoomOutButton) {
//        this.board = new Board();
//        this.graph = new Graph();
//        this.buttons = buttons;
//        this.resultLabel = resultLabel;
//        this.analysisArea = analysisArea;
//        this.graphCanvas = graphCanvas;
//        this.graphVisualizer = new GraphVisualizer(graphCanvas, this::updateBoardFromNode);
//        initializeButtons();
//        zoomInButton.setOnAction(e -> zoomGraph(1.2));
//        zoomOutButton.setOnAction(e -> zoomGraph(0.833));
//    }
//
//    private void initializeButtons() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                final int row = i;
//                final int col = j;
//                buttons[i][j].setOnAction(e -> handleCellClick(row, col));
//                buttons[i][j].setOnMouseEntered(e -> showMoveHint(row, col));
//                buttons[i][j].setOnMouseExited(e -> clearMoveHint());
//            }
//        }
//    }
//
//    private void handleCellClick(int row, int col) {
//        Board.Cell currentPlayer = board.getNextPlayer();
//        if (board.setCell(row, col, currentPlayer)) {
//            buttons[row][col].setText(currentPlayer.toString());
//            buttons[row][col].setDisable(true);
//            clearWinningLine();
//            clearBestMoveHighlight();
//            animateBoard();
//            analyze();
//        } else {
//            showAlert("Недопустимый ход", "Эта клетка уже занята.");
//        }
//    }
//
//    private void showMoveHint(int row, int col) {
//        if (!buttons[row][col].isDisabled()) {
//            List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
//            for (MoveAnalysis move : bestMoves) {
//                if (move.getRow() == row && move.getCol() == col) {
//                    String hint = String.format("Ход (%d,%d): %.0f%% шанса на победу", row, col, move.getWinProbability() * 100);
//                    analysisArea.setText(analysisArea.getText().split("\n\n")[0] + "\n\nПодсказка: " + hint);
//                    break;
//                }
//            }
//        }
//    }
//
//    private void clearMoveHint() {
//        updateAnalysisArea();
//    }
//
//    private void animateBoard() {
//        for (Button[] row : buttons) {
//            for (Button button : row) {
//                FadeTransition ft = new FadeTransition(Duration.millis(300), button);
//                ft.setFromValue(0.5);
//                ft.setToValue(1.0);
//                ft.play();
//            }
//        }
//    }
//
//    private void animateGraph() {
//        FadeTransition ft = new FadeTransition(Duration.millis(300), graphCanvas);
//        ft.setFromValue(0.5);
//        ft.setToValue(1.0);
//        ft.play();
//    }
//
//    private void zoomGraph(double factor) {
//        graphScale *= factor;
//        graphScale = Math.max(0.5, Math.min(2.5, graphScale));
//        graphVisualizer.setScale(graphScale);
//        analyze();
//    }
//
//    public void analyze() {
//        if (!board.isValid()) {
//            showAlert("Недопустимое состояние", "Состояние доски некорректно.");
//            return;
//        }
//        graph.buildGraph(board, 1);
//        Probability prob = graph.calculateProbabilities(board);
//        resultLabel.setText(prob.toString());
//        highlightWinningLine();
//        highlightBestMove();
//        updateAnalysisArea();
//        animateGraph();
//        graphVisualizer.drawGraph(graph, board);
//    }
//
//    private void highlightWinningLine() {
//        clearWinningLine();
//        if (board.isWin(Board.Cell.X) || board.isWin(Board.Cell.O)) {
//            int[] line = board.getWinningLine();
//            if (line != null) {
//                for (int i = 0; i < 6; i += 2) {
//                    buttons[line[i]][line[i + 1]].getStyleClass().add("winning-cell");
//                }
//            }
//        }
//    }
//
//    private void clearWinningLine() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                buttons[i][j].getStyleClass().remove("winning-cell");
//            }
//        }
//    }
//
//    private void highlightBestMove() {
//        clearBestMoveHighlight();
//        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
//        if (!bestMoves.isEmpty()) {
//            MoveAnalysis best = bestMoves.get(0);
//            int row = best.getRow();
//            int col = best.getCol();
//            if (row >= 0 && col >= 0 && !buttons[row][col].isDisabled()) {
//                buttons[row][col].getStyleClass().add(board.getNextPlayer() == Board.Cell.X ? "best-move-x" : "best-move-o");
//                buttons[row][col].setText(board.getNextPlayer().toString() + "*");
//            }
//        }
//    }
//
//    private void clearBestMoveHighlight() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                buttons[i][j].getStyleClass().removeAll("best-move-x", "best-move-o");
//                if (!buttons[i][j].isDisabled()) {
//                    buttons[i][j].setText("");
//                }
//            }
//        }
//    }
//
//    private void updateBoardFromNode(Node node) {
//        Board selectedBoard = node.getBoard();
//        board = new Board(selectedBoard);
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Board.Cell cell = selectedBoard.getCell(i, j);
//                buttons[i][j].setText(cell == Board.Cell.EMPTY ? "" : cell.toString());
//                buttons[i][j].setDisable(cell != Board.Cell.EMPTY);
//            }
//        }
//        Probability prob = node.getProbability();
//        resultLabel.setText(prob.toString());
//        clearWinningLine();
//        clearBestMoveHighlight();
//        if (selectedBoard.isWin(Board.Cell.X) || selectedBoard.isWin(Board.Cell.O)) {
//            int[] line = selectedBoard.getWinningLine();
//            if (line != null) {
//                for (int i = 0; i < 6; i += 2) {
//                    buttons[line[i]][line[i + 1]].getStyleClass().add("winning-cell");
//                }
//            }
//        } else {
//            highlightBestMove();
//        }
//        updateAnalysisArea();
//        animateBoard();
//    }
//
//    private void updateAnalysisArea() {
//        StringBuilder sb = new StringBuilder();
//        Probability prob = graph.calculateProbabilities(board);
//        sb.append("=== Игровая доска ===\n").append(board.toString()).append("\n")
//                .append("=== Вероятности ===\n")
//                .append(String.format("Победа X: %.0f%%\n", prob.getXWin() * 100))
//                .append(String.format("Победа O: %.0f%%\n", prob.getOWin() * 100))
//                .append(String.format("Ничья: %.0f%%\n", prob.getDraw() * 100))
//                .append("=== Лучший ход ===\n");
//        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
//        if (!bestMoves.isEmpty()) {
//            MoveAnalysis best = bestMoves.get(0);
//            sb.append(String.format("Ход (%d,%d): %.0f%% шанса на победу\n",
//                    best.getRow(), best.getCol(), best.getWinProbability() * 100));
//            sb.append("Почему: Этот ход оптимален для ").append(board.getNextPlayer())
//                    .append(", так как максимизирует вероятность победы.\n");
//        } else {
//            sb.append("Нет доступных ходов.\n");
//        }
//        analysisArea.setText(sb.toString());
//    }
//
//    public void reset() {
//        board = new Board();
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                buttons[i][j].setText("");
//                buttons[i][j].setDisable(false);
//                buttons[i][j].getStyleClass().removeAll("winning-cell", "best-move-x", "best-move-o");
//            }
//        }
//        resultLabel.setText("");
//        analysisArea.setText("");
//        graphCanvas.getGraphicsContext2D().clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
//        graphScale = 1.0;
//        graphVisualizer.setScale(graphScale);
//    }
//
//    public void exportResults() {
//        StringBuilder content = new StringBuilder("=== Игровая доска ===\n").append(board.toString()).append("\n")
//                .append("=== Вероятности ===\n")
//                .append(String.format("Победа X: %.0f%%\n", graph.calculateProbabilities(board).getXWin() * 100))
//                .append(String.format("Победа O: %.0f%%\n", graph.calculateProbabilities(board).getOWin() * 100))
//                .append(String.format("Ничья: %.0f%%\n", graph.calculateProbabilities(board).getDraw() * 100))
//                .append("=== Лучший ход ===\n");
//        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
//        if (!bestMoves.isEmpty()) {
//            content.append(String.format("Ход (%d,%d): %.0f%% шанса на победу\n",
//                    bestMoves.get(0).getRow(), bestMoves.get(0).getCol(), bestMoves.get(0).getWinProbability() * 100));
//        }
//        try {
//            ResultExporter.export(content.toString());
//            showAlert("Успех", "Результаты экспортированы в result.txt");
//        } catch (Exception e) {
//            showAlert("Ошибка", "Не удалось экспортировать результаты: " + e.getMessage());
//        }
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//}

package ru.vsu.cs.course1.controller;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import ru.vsu.cs.course1.model.*;
import ru.vsu.cs.course1.utils.ResultExporter;
import ru.vsu.cs.course1.view.GraphVisualizer;

import java.util.List;

public class GameController {
    private Board board;
    private Graph graph;
    private Button[][] buttons;
    private Label resultLabel;
    private TextArea analysisArea;
    private Canvas graphCanvas;
    private GraphVisualizer graphVisualizer;
    private double graphScale = 1.0;

    public GameController(Button[][] buttons, Label resultLabel, TextArea analysisArea, Canvas graphCanvas, Button zoomInButton, Button zoomOutButton) {
        this.board = new Board();
        this.graph = new Graph();
        this.buttons = buttons;
        this.resultLabel = resultLabel;
        this.analysisArea = analysisArea;
        this.graphCanvas = graphCanvas;
        this.graphVisualizer = new GraphVisualizer(graphCanvas, this::updateBoardFromNode);
        initializeButtons();
        zoomInButton.setOnAction(e -> zoomGraph(1.2));
        zoomOutButton.setOnAction(e -> zoomGraph(0.833));
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j].setOnAction(e -> handleCellClick(row, col));
                buttons[i][j].setOnMouseEntered(e -> showMoveHint(row, col));
                buttons[i][j].setOnMouseExited(e -> clearMoveHint());
            }
        }
    }

    private void handleCellClick(int row, int col) {
        Board.Cell currentPlayer = board.getNextPlayer();
        if (board.setCell(row, col, currentPlayer)) {
            buttons[row][col].setText(currentPlayer.toString());
            buttons[row][col].setDisable(true);
            clearWinningLine();
            clearBestMoveHighlight();
            animateBoard();
            analyze();
        } else {
            showAlert("Недопустимый ход", "Эта клетка уже занята.");
        }
    }

    private void showMoveHint(int row, int col) {
        if (!buttons[row][col].isDisabled()) {
            List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
            for (MoveAnalysis move : bestMoves) {
                if (move.getRow() == row && move.getCol() == col) {
                    String hint = String.format("%s: Ход (%d,%d) — %.0f%% шанса на победу",
                            board.getNextPlayer(), row, col, move.getWinProbability() * 100);
                    analysisArea.setText(analysisArea.getText().split("\n\n")[0] + "\n\n" + hint);
                    break;
                }
            }
        }
    }

    private void clearMoveHint() {
        updateAnalysisArea();
    }

    private void animateBoard() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                FadeTransition ft = new FadeTransition(Duration.millis(300), button);
                ft.setFromValue(0.5);
                ft.setToValue(1.0);
                ft.play();
            }
        }
    }

    private void animateGraph() {
        FadeTransition ft = new FadeTransition(Duration.millis(300), graphCanvas);
        ft.setFromValue(0.5);
        ft.setToValue(1.0);
        ft.play();
    }

    private void zoomGraph(double factor) {
        graphScale *= factor;
        graphScale = Math.max(0.5, Math.min(2.5, graphScale));
        graphVisualizer.setScale(graphScale);
        analyze();
    }

    public void analyze() {
        if (!board.isValid()) {
            showAlert("Недопустимое состояние", "Состояние доски некорректно.");
            return;
        }
        graph.buildGraph(board, 1);
        Probability prob = graph.calculateProbabilities(board);
        resultLabel.setText(prob.toString());
        highlightWinningLine();
        highlightBestMove();
        updateAnalysisArea();
        animateGraph();
        graphVisualizer.drawGraph(graph, board);

        // Проверка завершения игры
        if (board.isWin(Board.Cell.X)) {
            showAlert("Победа!", "Игрок X выиграл!");
            disableAllButtons();
        } else if (board.isWin(Board.Cell.O)) {
            showAlert("Победа!", "Игрок O выиграл!");
            disableAllButtons();
        } else if (board.isFull()) {
            showAlert("Ничья!", "Игра завершена без победителя.");
            disableAllButtons();
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(true);
            }
        }
    }

    private void highlightWinningLine() {
        clearWinningLine();
        if (board.isWin(Board.Cell.X) || board.isWin(Board.Cell.O)) {
            int[] line = board.getWinningLine();
            if (line != null) {
                for (int i = 0; i < 6; i += 2) {
                    buttons[line[i]][line[i + 1]].getStyleClass().add("winning-cell");
                }
            }
        }
    }

    private void clearWinningLine() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].getStyleClass().remove("winning-cell");
            }
        }
    }

    private void highlightBestMove() {
        clearBestMoveHighlight();
        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
        if (!bestMoves.isEmpty()) {
            MoveAnalysis best = bestMoves.get(0);
            int row = best.getRow();
            int col = best.getCol();
            if (row >= 0 && col >= 0 && !buttons[row][col].isDisabled()) {
                buttons[row][col].getStyleClass().add(board.getNextPlayer() == Board.Cell.X ? "best-move-x" : "best-move-o");
                buttons[row][col].setText(board.getNextPlayer().toString() + "*");
            }
        }
    }

    private void clearBestMoveHighlight() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].getStyleClass().removeAll("best-move-x", "best-move-o");
                if (!buttons[i][j].isDisabled()) {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    private void updateBoardFromNode(Node node) {
        Board selectedBoard = node.getBoard();
        board = new Board(selectedBoard);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Board.Cell cell = selectedBoard.getCell(i, j);
                buttons[i][j].setText(cell == Board.Cell.EMPTY ? "" : cell.toString());
                buttons[i][j].setDisable(cell != Board.Cell.EMPTY);
            }
        }
        Probability prob = node.getProbability();
        resultLabel.setText(prob.toString());
        clearWinningLine();
        clearBestMoveHighlight();
        if (selectedBoard.isWin(Board.Cell.X) || selectedBoard.isWin(Board.Cell.O)) {
            int[] line = selectedBoard.getWinningLine();
            if (line != null) {
                for (int i = 0; i < 6; i += 2) {
                    buttons[line[i]][line[i + 1]].getStyleClass().add("winning-cell");
                }
            }
        } else {
            highlightBestMove();
        }
        updateAnalysisArea();
        animateBoard();
    }

    private void updateAnalysisArea() {
        StringBuilder sb = new StringBuilder();
        Probability prob = graph.calculateProbabilities(board);
        sb.append("=== Игровая доска ===\n").append(board.toString()).append("\n")
                .append("=== Текущий ход ===\n")
                .append("Игрок: ").append(board.getNextPlayer()).append("\n")
                .append("=== Вероятности ===\n")
                .append(String.format("Победа X: %.0f%%\n", prob.getXWin() * 100))
                .append(String.format("Победа O: %.0f%%\n", prob.getOWin() * 100))
                .append(String.format("Ничья: %.0f%%\n", prob.getDraw() * 100))
                .append("=== Лучший ход ===\n");
        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
        if (!bestMoves.isEmpty()) {
            MoveAnalysis best = bestMoves.get(0);
            sb.append(String.format("%s: Ход (%d,%d) — %.0f%% шанса на победу\n",
                    board.getNextPlayer(), best.getRow(), best.getCol(), best.getWinProbability() * 100));
            sb.append("Почему: Этот ход оптимален для ").append(board.getNextPlayer())
                    .append(", так как максимизирует вероятность победы.\n");
        } else {
            sb.append("Нет доступных ходов.\n");
        }
        analysisArea.setText(sb.toString());
    }

    public void reset() {
        board = new Board();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setDisable(false);
                buttons[i][j].getStyleClass().removeAll("winning-cell", "best-move-x", "best-move-o");
            }
        }
        resultLabel.setText("");
        analysisArea.setText("");
        graphCanvas.getGraphicsContext2D().clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());
        graphScale = 1.0;
        graphVisualizer.setScale(graphScale);
    }

    public void exportResults() {
        StringBuilder content = new StringBuilder("=== Игровая доска ===\n").append(board.toString()).append("\n")
                .append("=== Текущий ход ===\n")
                .append("Игрок: ").append(board.getNextPlayer()).append("\n")
                .append("=== Вероятности ===\n")
                .append(String.format("Победа X: %.0f%%\n", graph.calculateProbabilities(board).getXWin() * 100))
                .append(String.format("Победа O: %.0f%%\n", graph.calculateProbabilities(board).getOWin() * 100))
                .append(String.format("Ничья: %.0f%%\n", graph.calculateProbabilities(board).getDraw() * 100))
                .append("=== Лучший ход ===\n");
        List<MoveAnalysis> bestMoves = graph.getBestMoves(board);
        if (!bestMoves.isEmpty()) {
            content.append(String.format("%s: Ход (%d,%d) — %.0f%% шанса на победу\n",
                    board.getNextPlayer(), bestMoves.get(0).getRow(), bestMoves.get(0).getCol(), bestMoves.get(0).getWinProbability() * 100));
        }
        try {
            ResultExporter.export(content.toString());
            showAlert("Успех", "Результаты экспортированы в result.txt");
        } catch (Exception e) {
            showAlert("Ошибка", "Не удалось экспортировать результаты: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}