package com.dataflow.textprocessing.regex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class RegexUtilsTest {

    // Test for RegexUtils.search
    @Test
    public void testSearch() {
        String text = "Hello, hello, world!";
        String regex = "hello";
        List<String> matches = RegexUtils.search(text, regex, true);
        assertEquals(2, matches.size(), "Should match 'hello' twice");
        assertTrue(matches.contains("Hello"), "First match should be 'Hello'");
        assertTrue(matches.contains("hello"), "Second match should be 'hello'");
    }

    @Test
    public void testSearchWithCaseInsensitive() {
        String text = "Hello, hello, World!";
        String regex = "world";
        List<String> matches = RegexUtils.search(text, regex, true);
        assertEquals(1, matches.size(), "Should match 'World' once, case-insensitive");
    }

    // Test for RegexUtils.extractGroups
    public void testExtractGroups() {
        String text = "Email: example@example.com, Phone: 123-456-7890";
        String regex = "(\\S+@\\S+\\.\\S+)(?=\\s|,|$)";  // Capture email, stop at space, comma, or end
        List<String> groups = RegexUtils.extractGroups(text, regex, false);
        assertEquals(1, groups.size(), "Should extract one email address");
        assertEquals("example@example.com", groups.get(0), "Extracted email should match");
    }

    // Test for RegexUtils.replace
    @Test
    public void testReplace() {
        String text = "This is a test. Test this out.";
        String regex = "test";
        String replacement = "experiment";
        String result = RegexUtils.replace(text, regex, replacement, true);
        assertEquals("This is a experiment. experiment this out.", result, "All occurrences of 'test' should be replaced with 'experiment'");
    }

    // Test for RegexUtils.countMatches
    @Test
    public void testCountMatches() {
        String text = "apple apple banana apple";
        String regex = "apple";
        int count = RegexUtils.countMatches(text, regex, false);
        assertEquals(3, count, "There should be 3 matches for 'apple'");
    }

    // Test for RegexUtils.searchWithLimit
    @Test
    public void testSearchWithLimit() {
        String text = "apple banana apple orange apple";
        String regex = "apple";
        List<String> matches = RegexUtils.searchWithLimit(text, regex, 2, false);
        assertEquals(2, matches.size(), "Should return only 2 matches for 'apple'");
    }

    // Test for RegexUtils.replacePattern
    @Test
    public void testReplacePattern() {
        String input = "I like apples and apples are sweet.";
        String pattern = "apples";
        String replacement = "oranges";
        String result = RegexUtils.replacePattern(input, pattern, replacement);
        assertEquals("I like oranges and oranges are sweet.", result, "All occurrences of 'apples' should be replaced with 'oranges'");
    }

    // Edge case: Empty text input
    @Test
    public void testEmptyText() {
        String text = "";
        String regex = "apple";
        List<String> matches = RegexUtils.search(text, regex, false);
        assertEquals(0, matches.size(), "No matches should be found in an empty string");
    }

    // Edge case: Regex pattern doesn't match
    @Test
    public void testNoMatch() {
        String text = "hello world";
        String regex = "abc";
        List<String> matches = RegexUtils.search(text, regex, false);
        assertEquals(0, matches.size(), "No matches should be found for a non-existent pattern");
    }

    // Edge case: Invalid regex pattern
    @Test
    public void testInvalidRegex() {
        String text = "hello world";
        String invalidRegex = "[a-z";  // Unbalanced square brackets
        try {
            RegexUtils.search(text, invalidRegex, false);
            fail("Should throw PatternSyntaxException due to invalid regex");
        } catch (PatternSyntaxException e) {
            assertNotNull(e, "Exception should be thrown for invalid regex pattern");
        }
    }

    // Additional test case to cover special regex characters
    @Test
    public void testSpecialRegexCharacters() {
        String text = "This is a (test) example with special * characters.";
        String regex = "\\(test\\)";  // Escape parentheses
        List<String> matches = RegexUtils.search(text, regex, false);
        assertEquals(1, matches.size(), "Should match the exact '(test)' pattern");
        assertTrue(matches.contains("(test)"), "Match should contain '(test)'");
    }

}
