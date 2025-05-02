package com.dataflow.textprocessing.files;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class FileWriterUtilTest {

    private static final String TEST_DIR = "test-resources";
    private static final String WRITE_FILE = TEST_DIR + "/write_test.txt";
    private static final String APPEND_FILE = TEST_DIR + "/append_test.txt";
    private static final String INVALID_PATH = "/invalid_path/test.txt";

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.writeString(Paths.get(APPEND_FILE), "Original Line");
    }

    @AfterEach
    void cleanUpEach() throws IOException {
        Files.deleteIfExists(Paths.get(WRITE_FILE));
        Files.writeString(Paths.get(APPEND_FILE), "Original Line");
    }

    @AfterAll
    static void teardown() throws IOException {
        Files.deleteIfExists(Paths.get(APPEND_FILE));
        Files.deleteIfExists(Paths.get(TEST_DIR));
    }

    @Test
    void testWriteToFile_overwritesContent() throws IOException {
        String content = "New content";
        FileWriterUtil.writeToFile(WRITE_FILE, content);

        String result = Files.readString(Paths.get(WRITE_FILE));
        assertEquals(content, result.trim());
    }

    @Test
    void testAppendToFile_appendsContent() throws IOException {
        String appendContent = "Appended Line";
        FileWriterUtil.appendToFile(APPEND_FILE, appendContent);

        String result = Files.readString(Paths.get(APPEND_FILE));
        assertTrue(result.contains("Original Line"));
        assertTrue(result.contains("Appended Line"));
    }

    @Test
    void testWriteToFile_invalidPath_throwsIOException() {
        assertThrows(IOException.class, () -> {
            FileWriterUtil.writeToFile(INVALID_PATH, "Some content");
        });
    }

    @Test
    void testAppendToFile_invalidPath_throwsIOException() {
        assertThrows(IOException.class, () -> {
            FileWriterUtil.appendToFile(INVALID_PATH, "Append this");
        });
    }
}
