package com.dataflow.textprocessing.files;

import java.io.*;

public class FileReaderUtil {

    // Reads a file line by line and returns its content
    public static void readFileByLine(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    // Reads the entire content of a file as a single string
    public static String readFileAsString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

}
