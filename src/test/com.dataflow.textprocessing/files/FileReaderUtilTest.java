package com.dataflow.textprocessing.files;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilTest {

    private static final String TEST_DIR = "test-resources";
    private static final String VALID_FILE = TEST_DIR + "/valid.txt";
    private static final String EMPTY_FILE = TEST_DIR + "/empty.txt";
    private static final String NON_EXISTENT_FILE = TEST_DIR + "/missing.txt";

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.writeString(Paths.get(VALID_FILE), "Line 1\nLine 2\nLine 3");
        Files.writeString(Paths.get(EMPTY_FILE), "");
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get(VALID_FILE));
        Files.deleteIfExists(Paths.get(EMPTY_FILE));
        Files.deleteIfExists(Paths.get(NON_EXISTENT_FILE));
        Files.deleteIfExists(Paths.get(TEST_DIR));
    }

    @Test
    void testReadFileAsString_validFile() throws IOException {
        String content = FileReaderUtil.readFileAsString(VALID_FILE);
        assertTrue(content.contains("Line 1"));
        assertTrue(content.contains("Line 3"));
    }

    @Test
    void testReadFileAsString_emptyFile() throws IOException {
        String content = FileReaderUtil.readFileAsString(EMPTY_FILE);
        assertEquals("", content.trim());
    }

    @Test
    void testReadFileAsString_fileNotFound() {
        assertThrows(IOException.class, () -> {
            FileReaderUtil.readFileAsString(NON_EXISTENT_FILE);
        });
    }

    @Test
    void testReadFileByLine_validFile() {
        assertDoesNotThrow(() -> FileReaderUtil.readFileByLine(VALID_FILE));
        // This method prints lines, not easily testable without redirecting output.
        // We just check that no exception is thrown.
    }
}
