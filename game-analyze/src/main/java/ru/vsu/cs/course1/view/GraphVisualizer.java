package ru.vsu.cs.course1.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ru.vsu.cs.course1.model.Board;
import ru.vsu.cs.course1.model.Graph;
import ru.vsu.cs.course1.model.MoveAnalysis;
import ru.vsu.cs.course1.model.Node;

import java.util.*;
import java.util.function.Consumer;

public class GraphVisualizer {
    private Canvas canvas;
    private GraphicsContext gc;
    private Map<Node, Double[]> positions;
    private Consumer<Node> onNodeClick;
    private double scale = 1.0;

    public GraphVisualizer(Canvas canvas, Consumer<Node> onNodeClick) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.positions = new HashMap<>();
        this.onNodeClick = onNodeClick;
        canvas.setOnMouseClicked(this::handleMouseClick);
        canvas.setOnMouseMoved(this::handleMouseMove);
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    private void handleMouseClick(MouseEvent event) {
        double x = event.getX() / scale;
        double y = event.getY() / scale;
        for (Map.Entry<Node, Double[]> entry : positions.entrySet()) {
            Double[] pos = entry.getValue();
            double dx = x - pos[0];
            double dy = y - pos[1];
            if (dx * dx + dy * dy <= 576) { // Радиус узла 24, квадрат радиуса 576
                onNodeClick.accept(entry.getKey());
                break;
            }
        }
    }

    private void handleMouseMove(MouseEvent event) {
        double x = event.getX() / scale;
        double y = event.getY() / scale;
        for (Map.Entry<Node, Double[]> entry : positions.entrySet()) {
            Double[] pos = entry.getValue();
            double dx = x - pos[0];
            double dy = y - pos[1];
            if (dx * dx + dy * dy <= 576) {
                String probText = String.format("X: %.0f%% O: %.0f%% D: %.0f%%",
                        entry.getKey().getProbability().getXWin() * 100,
                        entry.getKey().getProbability().getOWin() * 100,
                        entry.getKey().getProbability().getDraw() * 100);
                canvas.setUserData(probText);
                return;
            }
        }
        canvas.setUserData(null);
    }

    public void drawGraph(Graph graph, Board initialBoard) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.save();
        gc.scale(scale, scale);
        positions.clear();
        double centerX = canvas.getWidth() / scale / 2;
        double radius = 150;

        // Root node
        Node root = graph.getNodes().get(initialBoard);
        if (root == null) return;
        positions.put(root, new Double[]{centerX, 50.0});

        // First level nodes
        List<Node> firstLevel = root.getEdges();
        List<MoveAnalysis> bestMoves = graph.getBestMoves(initialBoard);
        Map<Node, Board.Cell> nodePlayer = new HashMap<>();
        for (Node node : firstLevel) {
            Board board = node.getBoard();
            int[] move = graph.findMove(initialBoard, board);
            for (MoveAnalysis m : bestMoves) {
                if (m.getRow() == move[0] && m.getCol() == move[1]) {
                    nodePlayer.put(node, initialBoard.getNextPlayer());
                    break;
                }
            }
        }

        for (int i = 0; i < firstLevel.size(); i++) {
            double angle = 2 * Math.PI * i / Math.max(1, firstLevel.size());
            double x = centerX + radius * Math.cos(angle);
            double y = 150 + radius * Math.sin(angle);
            positions.put(firstLevel.get(i), new Double[]{x, y});
        }

        // Draw edges
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.5);
        for (Node node : positions.keySet()) {
            Double[] pos = positions.get(node);
            for (Node next : node.getEdges()) {
                if (positions.containsKey(next)) {
                    Double[] nextPos = positions.get(next);
                    gc.strokeLine(pos[0], pos[1], nextPos[0], nextPos[1]);
                }
            }
        }

        // Draw nodes
        gc.setFont(new javafx.scene.text.Font("Arial", 12));
        for (Map.Entry<Node, Double[]> entry : positions.entrySet()) {
            Node node = entry.getKey();
            Double[] pos = entry.getValue();
            Board.Cell player = nodePlayer.getOrDefault(node, null);
            gc.setFill(node == root ? Color.RED : player == Board.Cell.X ? Color.BLUE : player == Board.Cell.O ? Color.ORANGE : Color.LIGHTGRAY);
            gc.fillOval(pos[0] - 12, pos[1] - 12, 24, 24);
            gc.setFill(Color.BLACK);
            int[] move = graph.findMove(initialBoard, node.getBoard());
            String moveText = node == root ? "Текущая" : String.format("Ход (%d,%d)", move[0], move[1]);
            String probText = String.format("X:%.0f%% O:%.0f%% D:%.0f%%",
                    node.getProbability().getXWin() * 100,
                    node.getProbability().getOWin() * 100,
                    node.getProbability().getDraw() * 100);
            gc.fillText(moveText, pos[0] - 30, pos[1] + 20);
            gc.fillText(probText, pos[0] - 30, pos[1] + 40);
        }
        gc.restore();
    }
}