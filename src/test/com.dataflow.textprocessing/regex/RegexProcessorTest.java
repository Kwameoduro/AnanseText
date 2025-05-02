package com.dataflow.textprocessing.regex;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegexProcessorTest {

    @Test
    void testIsMatch_fullMatch() {
        String text = "abc123";
        String regex = "\\w+\\d+";
        assertTrue(RegexProcessor.isMatch(text, regex));
    }

    @Test
    void testIsMatch_partialMismatch() {
        String text = "abc123xyz";
        String regex = "\\w+\\d+";
        // full match expected, so should return false
        assertFalse(RegexProcessor.isMatch(text, regex));
    }

    @Test
    void testFindMatches_multipleMatches() {
        String text = "Email me at one@test.com and two@test.com";
        String regex = "\\b\\w+@\\w+\\.com\\b";
        List<String> matches = RegexProcessor.findMatches(text, regex);
        assertEquals(2, matches.size());
        assertTrue(matches.contains("one@test.com"));
        assertTrue(matches.contains("two@test.com"));
    }

    @Test
    void testFindMatches_noMatch() {
        String text = "No emails here";
        String regex = "\\b\\w+@\\w+\\.com\\b";
        List<String> matches = RegexProcessor.findMatches(text, regex);
        assertTrue(matches.isEmpty());
    }

    @Test
    void testReplaceMatches_validReplacement() {
        String text = "Call 123-456-7890 now!";
        String regex = "\\d{3}-\\d{3}-\\d{4}";
        String replacement = "[REDACTED]";
        String result = RegexProcessor.replaceMatches(text, regex, replacement);
        assertEquals("Call [REDACTED] now!", result);
    }

    @Test
    void testReplaceMatches_noMatch() {
        String text = "Hello world";
        String regex = "\\d+";
        String result = RegexProcessor.replaceMatches(text, regex, "X");
        assertEquals("Hello world", result);
    }

    @Test
    void testInvalidRegex_throwsException() {
        String text = "test";
        String invalidRegex = "[abc"; // unclosed character class
        assertThrows(Exception.class, () -> RegexProcessor.isMatch(text, invalidRegex));
    }
}
