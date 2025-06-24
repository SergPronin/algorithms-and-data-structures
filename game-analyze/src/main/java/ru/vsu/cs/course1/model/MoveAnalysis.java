package ru.vsu.cs.course1.model;

public class MoveAnalysis {
    private int row;
    private int col;
    private double winProbability;

    public MoveAnalysis(int row, int col, double winProbability) {
        this.row = row;
        this.col = col;
        this.winProbability = winProbability;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public double getWinProbability() {
        return winProbability;
    }

    @Override
    public String toString() {
        return String.format("Ход (%d, %d): %.2f%% шанса на победу", row + 1, col + 1, winProbability * 100);
    }
}