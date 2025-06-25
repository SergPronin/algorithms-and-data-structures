package ru.vsu.cs.course1;

public class Probabilities {
    long xWins, oWins, draws;

    public Probabilities(long xWins, long oWins, long draws) {
        this.xWins = xWins;
        this.oWins = oWins;
        this.draws = draws;
    }

    public Probabilities add(Probabilities other) {
        return new Probabilities(
                this.xWins + other.xWins,
                this.oWins + other.oWins,
                this.draws + other.draws
        );
    }

    @Override
    public String toString() {
        long total = xWins + oWins + draws;
        if (total == 0) return "Нет возможных исходов";
        return String.format("Победа X: %.2f%%, Победа O: %.2f%%, Ничья: %.2f%%",
                (xWins * 100.0) / total, (oWins * 100.0) / total, (draws * 100.0) / total);
    }
}