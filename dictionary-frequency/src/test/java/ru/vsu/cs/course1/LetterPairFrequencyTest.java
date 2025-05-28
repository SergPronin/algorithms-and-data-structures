package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class LetterPairFrequencyTest {

    @Test
    public void testAnalyzeText() throws IOException {
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.writeString(tempFile, "Hello World");
        Map<String, Integer> map = new HashMap<>();
        LetterPairFrequency.Result result = LetterPairFrequency.analyzeText(tempFile.toString(), map);

        assertEquals(7, result.totalPairs()); // "he", "ll", "lo", "ow", "wo", "or", "rl"
        assertEquals(2, map.get("ll")); // "ll" встречается дважды
        assertEquals(1, map.get("he"));
        Files.delete(tempFile);
    }
}