package ru.vsu.cs.course1.model;

import java.util.*;

public class Graph {
    private Map<Board, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public Node getOrCreateNode(Board board) {
        return nodes.computeIfAbsent(board, Node::new);
    }

    public void buildGraph(Board initialBoard, int depthLimit) {
        nodes.clear();
        Node root = getOrCreateNode(new Board(initialBoard));
        buildGraphRecursive(root, depthLimit);
        calculateAllProbabilities();
    }

    private void buildGraphRecursive(Node node, int depth) {
        if (depth <= 0) return;
        Board board = node.getBoard();
        if (board.isGameOver()) {
            if (board.isWin(Board.Cell.X)) {
                node.setProbability(new Probability(1.0, 0.0, 0.0, 1, 0, 0));
            } else if (board.isWin(Board.Cell.O)) {
                node.setProbability(new Probability(0.0, 1.0, 0.0, 0, 1, 0));
            } else if (board.isDraw()) {
                node.setProbability(new Probability(0.0, 0.0, 1.0, 0, 0, 1));
            }
            return;
        }
        Board.Cell nextPlayer = board.getNextPlayer();
        for (int[] move : board.getPossibleMoves()) {
            Board newBoard = new Board(board);
            newBoard.setCell(move[0], move[1], nextPlayer);
            Node nextNode = getOrCreateNode(newBoard);
            node.addEdge(nextNode);
            if (!nodes.containsKey(newBoard)) {
                buildGraphRecursive(nextNode, depth - 1);
            }
        }
    }

    public Probability calculateProbabilities(Board initialBoard) {
        Node root = nodes.get(initialBoard);
        if (root == null) {
            return new Probability(0, 0, 0, 0, 0, 0);
        }
        return root.getProbability();
    }

    private void calculateAllProbabilities() {
        // Используем пост-обход для вычисления вероятностей снизу вверх
        Set<Node> visited = new HashSet<>();
        for (Node node : nodes.values()) {
            if (!visited.contains(node)) {
                calculateNodeProbabilities(node, visited);
            }
        }
    }

    private Probability calculateNodeProbabilities(Node node, Set<Node> visited) {
        if (visited.contains(node)) {
            return node.getProbability();
        }
        visited.add(node);

        Board board = node.getBoard();
        if (board.isGameOver()) {
            if (board.isWin(Board.Cell.X)) {
                node.setProbability(new Probability(1.0, 0.0, 0.0, 1, 0, 0));
            } else if (board.isWin(Board.Cell.O)) {
                node.setProbability(new Probability(0.0, 1.0, 0.0, 0, 1, 0));
            } else if (board.isDraw()) {
                node.setProbability(new Probability(0.0, 0.0, 1.0, 0, 0, 1));
            }
            return node.getProbability();
        }

        long xWins = 0, oWins = 0, draws = 0, totalPaths = 0;
        for (Node next : node.getEdges()) {
            Probability childProb = calculateNodeProbabilities(next, visited);
            xWins += childProb.getXWinCount();
            oWins += childProb.getOWinCount();
            draws += childProb.getDrawCount();
            totalPaths += childProb.getXWinCount() + childProb.getOWinCount() + childProb.getDrawCount();
        }

        if (totalPaths > 0) {
            node.setProbability(new Probability(
                    (double) xWins / totalPaths,
                    (double) oWins / totalPaths,
                    (double) draws / totalPaths,
                    xWins, oWins, draws
            ));
        } else {
            node.setProbability(new Probability(0, 0, 0, 0, 0, 0));
        }
        return node.getProbability();
    }

    public List<MoveAnalysis> getBestMoves(Board initialBoard) {
        Node root = nodes.get(initialBoard);
        if (root == null || root.getEdges().isEmpty()) {
            return new ArrayList<>();
        }
        List<MoveAnalysis> analyses = new ArrayList<>();
        Board.Cell nextPlayer = initialBoard.getNextPlayer();
        for (Node nextNode : root.getEdges()) {
            Probability prob = nextNode.getProbability();
            if (prob != null) {
                Board board = nextNode.getBoard();
                int[] move = findMove(initialBoard, board);
                double winProb = nextPlayer == Board.Cell.X ? prob.getXWin() : prob.getOWin();
                analyses.add(new MoveAnalysis(move[0], move[1], winProb));
            }
        }
        analyses.sort((a, b) -> Double.compare(b.getWinProbability(), a.getWinProbability()));
        return analyses;
    }

    public int[] findMove(Board from, Board to) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (from.getCell(i, j) == Board.Cell.EMPTY && to.getCell(i, j) != Board.Cell.EMPTY) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public Map<Board, Node> getNodes() {
        return nodes;
    }
}