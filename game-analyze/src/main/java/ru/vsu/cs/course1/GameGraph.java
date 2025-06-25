package ru.vsu.cs.course1;

import java.util.*;

public class GameGraph {
    Map<Board, Probabilities> memo = new HashMap<>();

    public Probabilities calculateProbabilities(Board board, Board.Cell nextPlayer) {
        if (memo.containsKey(board)) {
            return memo.get(board);
        }

        Board.GameResult result = board.checkResult();
        if (result != Board.GameResult.ONGOING) {
            Probabilities probs = new Probabilities(
                    result == Board.GameResult.X_WINS ? 1 : 0,
                    result == Board.GameResult.O_WINS ? 1 : 0,
                    result == Board.GameResult.DRAW ? 1 : 0
            );
            memo.put(board, probs);
            return probs;
        }

        Probabilities total = new Probabilities(0, 0, 0);
        List<Board> moves = board.getPossibleMoves(nextPlayer);

        for (Board nextBoard : moves) {
            Board.Cell next = (nextPlayer == Board.Cell.X) ? Board.Cell.O : Board.Cell.X;
            total = total.add(calculateProbabilities(nextBoard, next));
        }

        memo.put(board, total);
        return total;
    }

    public Map<Board, List<Board>> getSubGraph(Board startBoard, Board.Cell nextPlayer, int depth) {
        Map<Board, List<Board>> subGraph = new HashMap<>();
        buildSubGraph(startBoard, nextPlayer, depth, subGraph, new HashSet<>());
        return subGraph;
    }

    public Map<Board, List<Board>> getFilteredSubGraph(Board startBoard, Board.Cell nextPlayer, int depth, int maxChildrenPerLevel) {
        Map<Board, List<Board>> subGraph = new HashMap<>();
        buildFilteredSubGraph(startBoard, nextPlayer, depth, subGraph, new HashSet<>(), maxChildrenPerLevel);
        return subGraph;
    }

    private void buildSubGraph(Board board, Board.Cell player, int depth, Map<Board, List<Board>> subGraph, Set<Board> visited) {
        if (depth == 0 || board.checkResult() != Board.GameResult.ONGOING || visited.contains(board)) {
            subGraph.computeIfAbsent(board, k -> new ArrayList<>());
            return;
        }

        visited.add(board);
        List<Board> moves = board.getPossibleMoves(player);
        subGraph.computeIfAbsent(board, k -> new ArrayList<>()).addAll(moves);

        Board.Cell next = (player == Board.Cell.X) ? Board.Cell.O : Board.Cell.X;
        for (Board nextBoard : moves) {
            buildSubGraph(nextBoard, next, depth - 1, subGraph, visited);
        }
        visited.remove(board);
    }

    private void buildFilteredSubGraph(Board board, Board.Cell player, int depth, Map<Board, List<Board>> subGraph, Set<Board> visited, int maxChildrenPerLevel) {
        if (depth == 0 || board.checkResult() != Board.GameResult.ONGOING || visited.contains(board)) {
            subGraph.computeIfAbsent(board, k -> new ArrayList<>());
            return;
        }

        visited.add(board);
        List<Board> moves = board.getPossibleMoves(player);

        List<Board> sortedMoves = new ArrayList<>(moves);
        sortedMoves.sort((b1, b2) -> {
            Probabilities p1 = calculateProbabilities(b1, player == Board.Cell.X ? Board.Cell.O : Board.Cell.X);
            Probabilities p2 = calculateProbabilities(b2, player == Board.Cell.X ? Board.Cell.O : Board.Cell.X);
            double score1 = p1.xWins + p1.oWins + p1.draws;
            double score2 = p2.xWins + p2.oWins + p2.draws;
            return Double.compare(score2, score1); // Сортировка по убыванию
        });
        List<Board> filteredMoves = sortedMoves.size() > maxChildrenPerLevel ? sortedMoves.subList(0, maxChildrenPerLevel) : sortedMoves;
        subGraph.computeIfAbsent(board, k -> new ArrayList<>()).addAll(filteredMoves);

        Board.Cell next = (player == Board.Cell.X) ? Board.Cell.O : Board.Cell.X;
        for (Board nextBoard : filteredMoves) {
            buildFilteredSubGraph(nextBoard, next, depth - 1, subGraph, visited, maxChildrenPerLevel);
        }
        visited.remove(board);
    }
}