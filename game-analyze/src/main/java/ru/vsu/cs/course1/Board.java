package ru.vsu.cs.course1;

import java.util.*;

public class Board {
    public enum Cell { EMPTY, X, O }
    public enum GameResult { X_WINS, O_WINS, DRAW, ONGOING }
    Cell[][] grid;
    public static final int SIZE = 3;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = Cell.EMPTY;
            }
        }
    }

    public Board(Cell[][] grid) {
        this.grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public GameResult checkResult() {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][0] != Cell.EMPTY && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0] == Cell.X ? GameResult.X_WINS : GameResult.O_WINS;
            }
            if (grid[0][i] != Cell.EMPTY && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return grid[0][i] == Cell.X ? GameResult.X_WINS : GameResult.O_WINS;
            }
        }
        if (grid[0][0] != Cell.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0] == Cell.X ? GameResult.X_WINS : GameResult.O_WINS;
        }
        if (grid[0][2] != Cell.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2] == Cell.X ? GameResult.X_WINS : GameResult.O_WINS;
        }

        boolean full = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    full = false;
                    break;
                }
            }
        }
        return full ? GameResult.DRAW : GameResult.ONGOING;
    }

    public List<Board> getPossibleMoves(Cell player) {
        List<Board> moves = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == Cell.EMPTY) {
                    Cell[][] newGrid = new Cell[SIZE][SIZE];
                    for (int k = 0; k < SIZE; k++) {
                        newGrid[k] = grid[k].clone();
                    }
                    newGrid[i][j] = player;
                    moves.add(new Board(newGrid));
                }
            }
        }
        return moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(grid, board.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(grid[i][j] == Cell.X ? "X" :
                        grid[i][j] == Cell.O ? "O" : ".");
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}