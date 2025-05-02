package com.dataflow.textprocessing.streams;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class StreamProcessorTest {

    private final String sampleText = "Java streams are powerful powerful tools for text processing.";

    @Test
    void testFilterWords() {
        List<String> filtered = StreamProcessor.filterWords(sampleText, "\\bpowerful\\b");
        assertFalse(filtered.contains("powerful"), "Filtered text should exclude 'powerful'");
    }

    @Test
    void testToUpperCase() {
        String result = StreamProcessor.toUpperCase(sampleText);
        assertEquals("JAVA STREAMS ARE POWERFUL POWERFUL TOOLS FOR TEXT PROCESSING.", result, "Text should be converted to uppercase");
    }

    @Test
    void testToLowerCase() {
        String result = StreamProcessor.toLowerCase(sampleText);
        assertEquals("java streams are powerful powerful tools for text processing.", result, "Text should be converted to lowercase");
    }

    @Test
    void testCountOccurrences() {
        long count = StreamProcessor.countOccurrences(sampleText, "powerful");
        assertEquals(2, count, "Word 'powerful' should appear twice");
    }

    @Test
    void testSortWordsAlphabetically() {
        String result = StreamProcessor.sortWordsAlphabetically(sampleText);
        assertEquals("Java are for powerful powerful processing. streams text tools", result, "Words should be sorted alphabetically");
    }

    @Test
    void testWordFrequency() {
        Map<String, Long> frequencies = StreamProcessor.wordFrequency(sampleText);
        assertEquals(2, frequencies.get("powerful"), "Word 'powerful' should appear twice");
    }

    @Test
    void testReverseWords() {
        String result = StreamProcessor.reverseWords(sampleText);
        assertEquals("processing. text for tools powerful powerful are streams Java", result, "Words should be reversed");
    }

    @Test
    void testRemoveDuplicates() {
        String result = StreamProcessor.removeDuplicates(sampleText);
        assertEquals("Java streams are powerful tools for text processing.", result, "Text should remove duplicate words");
    }

    @Test
    void testReplaceWords() {
        String result = StreamProcessor.replaceWords(sampleText, List.of("powerful"), "XXX");
        assertEquals("Java streams are XXX XXX tools for text processing.", result, "Text should replace 'powerful' with 'XXX'");
    }

    @Test
    void testExtractWordsByLength() {
        List<String> result = StreamProcessor.extractWordsByLength(sampleText, 7);
        assertTrue(result.contains("streams"), "Should extract words of length 7");
    }

    @Test
    void testCapitalizeWords() {
        String result = StreamProcessor.capitalizeWords(sampleText);
        assertEquals("Java Streams Are Powerful Powerful Tools For Text Processing.", result, "Each word should be capitalized");
    }

    @Test
    void testToCamelCase() {
        String result = StreamProcessor.toCamelCase("java streams processing");
        assertEquals("javaStreamsProcessing", result, "Text should be converted to camel case");
    }

    @Test
    void testToTitleCase() {
        String result = StreamProcessor.toTitleCase(sampleText);
        assertEquals("Java Streams Are Powerful Powerful Tools For Text Processing.", result, "Text should be converted to title case");
    }

    @Test
    void testFindLongestWord() {
        String result = StreamProcessor.findLongestWord(sampleText);
        assertEquals("processing.", result, "Longest word should be 'processing.'");
    }

    @Test
    void testFindMostFrequentWord() {
        String result = StreamProcessor.findMostFrequentWord(sampleText);
        assertEquals("powerful", result, "Most frequently occurring word should be 'powerful'");
    }

    @Test
    void testShuffleWords() {
        String result = StreamProcessor.shuffleWords(sampleText);
        assertNotEquals(sampleText, result, "Words should be randomly shuffled");
    }

    @Test
    void testCountCharacters() {
        long count = StreamProcessor.countCharacters(sampleText);
        assertEquals(52, count, "Character count (excluding spaces) should match");
    }

    @Test
    void testExtractNumbers() {
        List<String> result = StreamProcessor.extractNumbers("The price is 100 dollars.");
        assertEquals(List.of("100"), result, "Should extract numeric values");
    }

    @Test
    void testRemovePunctuation() {
        String result = StreamProcessor.removePunctuation("Hello, world! Welcome.");
        assertEquals("Hello world Welcome", result, "Should remove punctuation marks");
    }

    @Test
    void testRemoveSpecialCharacters() {
        String result = StreamProcessor.removeSpecialCharacters("Hello @World$!");
        assertEquals("Hello World", result, "Should remove special characters");
    }

    @Test
    void testGenerateAcronym() {
        String result = StreamProcessor.generateAcronym("Artificial Intelligence");
        assertEquals("AI", result, "Acronym should be 'AI'");
    }

    @Test
    void testCountUniqueWords() {
        long uniqueWordCount = StreamProcessor.countUniqueWords(sampleText);
        assertEquals(7, uniqueWordCount, "Unique word count should match expected value");
    }
}
