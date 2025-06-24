//package ru.vsu.cs.course1.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Board {
//    public enum Cell { EMPTY, X, O }
//    private Cell[][] board;
//    private int xCount;
//    private int oCount;
//    private int[] winningLine; // [row1, col1, row2, col2, row3, col3] or null
//
//    public Board() {
//        board = new Cell[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                board[i][j] = Cell.EMPTY;
//            }
//        }
//        xCount = 0;
//        oCount = 0;
//        winningLine = null;
//    }
//
//    public Board(Board other) {
//        board = new Cell[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                board[i][j] = other.board[i][j];
//            }
//        }
//        xCount = other.xCount;
//        oCount = other.oCount;
//        winningLine = other.winningLine == null ? null : other.winningLine.clone();
//    }
//
//    public Cell getCell(int row, int col) {
//        return board[row][col];
//    }
//
//    public boolean setCell(int row, int col, Cell cell) {
//        if (board[row][col] != Cell.EMPTY) {
//            return false;
//        }
//        board[row][col] = cell;
//        if (cell == Cell.X) xCount++;
//        else if (cell == Cell.O) oCount++;
//        winningLine = null; // Reset winning line on new move
//        return true;
//    }
//
//    public boolean isValid() {
//        return Math.abs(xCount - oCount) <= 1;
//    }
//
//    public Cell getNextPlayer() {
//        return xCount <= oCount ? Cell.X : Cell.O;
//    }
//
//    public boolean isWin(Cell player) {
//        // Check rows
//        for (int i = 0; i < 3; i++) {
//            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
//                winningLine = new int[]{i, 0, i, 1, i, 2};
//                return true;
//            }
//        }
//        // Check columns
//        for (int j = 0; j < 3; j++) {
//            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
//                winningLine = new int[]{0, j, 1, j, 2, j};
//                return true;
//            }
//        }
//        // Check diagonals
//        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
//            winningLine = new int[]{0, 0, 1, 1, 2, 2};
//            return true;
//        }
//        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
//            winningLine = new int[]{0, 2, 1, 1, 2, 0};
//            return true;
//        }
//        return false;
//    }
//
//    public int[] getWinningLine() {
//        return winningLine;
//    }
//
//    public boolean isDraw() {
//        return xCount + oCount == 9 && !isWin(Cell.X) && !isWin(Cell.O);
//    }
//
//    public boolean isGameOver() {
//        return isWin(Cell.X) || isWin(Cell.O) || isDraw();
//    }
//
//    public List<int[]> getPossibleMoves() {
//        List<int[]> moves = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == Cell.EMPTY) {
//                    moves.add(new int[]{i, j});
//                }
//            }
//        }
//        return moves;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Board other = (Board) o;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] != other.board[i][j]) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 1;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                result = 31 * result + (board[i][j] == null ? 0 : board[i][j].hashCode());
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                sb.append(board[i][j] == Cell.EMPTY ? "-" : board[i][j]);
//                if (j < 2) sb.append(" ");
//            }
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//}

package ru.vsu.cs.course1.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public enum Cell { EMPTY, X, O }

    private Cell[][] cells;

    public Board() {
        cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = Cell.EMPTY;
            }
        }
    }

    public Board(Board other) {
        this.cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = other.cells[i][j];
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return cells[row][col];
        }
        return null;
    }

    public boolean setCell(int row, int col, Cell value) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && cells[row][col] == Cell.EMPTY) {
            cells[row][col] = value;
            return true;
        }
        return false;
    }

    public Cell getNextPlayer() {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == Cell.X) xCount++;
                if (cells[i][j] == Cell.O) oCount++;
            }
        }
        return xCount <= oCount ? Cell.X : Cell.O;
    }

    public boolean isWin(Cell player) {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] == player && cells[i][1] == player && cells[i][2] == player) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (cells[0][j] == player && cells[1][j] == player && cells[2][j] == player) return true;
        }
        if (cells[0][0] == player && cells[1][1] == player && cells[2][2] == player) return true;
        if (cells[0][2] == player && cells[1][1] == player && cells[2][0] == player) return true;
        return false;
    }

    public int[] getWinningLine() {
        for (int i = 0; i < 3; i++) {
            if (cells[i][0] != Cell.EMPTY && cells[i][0] == cells[i][1] && cells[i][1] == cells[i][2]) {
                return new int[]{i, 0, i, 1, i, 2};
            }
        }
        for (int j = 0; j < 3; j++) {
            if (cells[0][j] != Cell.EMPTY && cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j]) {
                return new int[]{0, j, 1, j, 2, j};
            }
        }
        if (cells[0][0] != Cell.EMPTY && cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2]) {
            return new int[]{0, 0, 1, 1, 2, 2};
        }
        if (cells[0][2] != Cell.EMPTY && cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            return new int[]{0, 2, 1, 1, 2, 0};
        }
        return null;
    }

    public boolean isValid() {
        int xCount = 0, oCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == Cell.X) xCount++;
                if (cells[i][j] == Cell.O) oCount++;
            }
        }
        return Math.abs(xCount - oCount) <= 1;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == Cell.EMPTY) return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return isWin(Cell.X) || isWin(Cell.O) || isFull();
    }

    public boolean isDraw() {
        return isFull() && !isWin(Cell.X) && !isWin(Cell.O);
    }

    public List<int[]> getPossibleMoves() {
        List<int[]> moves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cells[i][j] == Cell.EMPTY) {
                    moves.add(new int[]{i, j});
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cells[i][j] == Cell.EMPTY ? "-" : cells[i][j].toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Board other = (Board) obj;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.cells[i][j] != other.cells[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result = 31 * result + (cells[i][j] == null ? 0 : cells[i][j].hashCode());
            }
        }
        return result;
    }
}