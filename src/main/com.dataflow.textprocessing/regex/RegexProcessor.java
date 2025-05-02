package com.dataflow.textprocessing.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {

    // Write content to a file (overwrites existing content)
    public static void writeToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    // Append content to an existing file without overwriting
    public static void appendToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine(); // Adds a new line for readability
        }
    }
}
