package ru.vsu.cs.course1.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultExporter {
    public static void export(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            writer.write(content);
        }
    }
}