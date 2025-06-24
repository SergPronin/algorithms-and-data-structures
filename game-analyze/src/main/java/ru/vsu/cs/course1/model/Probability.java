package ru.vsu.cs.course1.model;

public class Probability {
    private double xWin;
    private double oWin;
    private double draw;
    private long xWinCount;
    private long oWinCount;
    private long drawCount;

    public Probability(double xWin, double oWin, double draw, long xWinCount, long oWinCount, long drawCount) {
        this.xWin = xWin;
        this.oWin = oWin;
        this.draw = draw;
        this.xWinCount = xWinCount;
        this.oWinCount = oWinCount;
        this.drawCount = drawCount;
    }

    public double getXWin() {
        return xWin;
    }

    public double getOWin() {
        return oWin;
    }

    public double getDraw() {
        return draw;
    }

    public long getXWinCount() {
        return xWinCount;
    }

    public long getOWinCount() {
        return oWinCount;
    }

    public long getDrawCount() {
        return drawCount;
    }

    @Override
    public String toString() {
        return String.format("Победа X: %.2f%% (%d путей)\nПобеда O: %.2f%% (%d путей)\nНичья: %.2f%% (%d путей)",
                xWin * 100, xWinCount, oWin * 100, oWinCount, draw * 100, drawCount);
    }
}