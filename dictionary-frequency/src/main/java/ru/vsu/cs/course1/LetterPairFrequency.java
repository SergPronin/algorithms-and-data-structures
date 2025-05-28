package ru.vsu.cs.course1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterPairFrequency {

    public record PairResult(String pair, int count, double frequency) {
        public SimpleStringProperty pairProperty() {
            return new SimpleStringProperty(pair);
        }

        public SimpleIntegerProperty countProperty() {
            return new SimpleIntegerProperty(count);
        }

        public SimpleDoubleProperty frequencyProperty() {
            return new SimpleDoubleProperty(frequency);
        }
    }

    public record Result(List<PairResult> pairs, int totalPairs) {}

    public static Result analyzeText(String filePath, Map<String, Integer> map) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(" ");
            }
        }

        String content = text.toString().toLowerCase();
        int totalPairs = 0;

        for (int i = 0; i < content.length() - 1; i++) {
            char c1 = content.charAt(i);
            char c2 = content.charAt(i + 1);
            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String pair = "" + c1 + c2;
                map.put(pair, map.getOrDefault(pair, 0) + 1);
                totalPairs++;
            }
        }

        List<Map.Entry<String, Integer>> sortedPairs = new ArrayList<>(map.entrySet());
        sortedPairs.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        List<PairResult> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedPairs) {
            double relativeFrequency = (double) entry.getValue() / totalPairs;
            results.add(new PairResult(entry.getKey(), entry.getValue(), relativeFrequency));
        }

        return new Result(results, totalPairs);
    }
}