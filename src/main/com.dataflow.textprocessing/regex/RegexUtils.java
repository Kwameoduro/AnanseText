package com.dataflow.textprocessing.regex;

import java.util.regex.*;
import java.util.*;

public class RegexUtils {

    // Searches for a regex pattern in the given text and returns a list of matched strings.
    public static List<String> search(String text, String regex, boolean caseInsensitive) {
        List<String> matches = new ArrayList<>();
        int flags = caseInsensitive ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    // Extracts matching groups from the text based on the regex pattern.
    public static List<String> extractGroups(String text, String regex, boolean caseInsensitive) {
        List<String> groups = new ArrayList<>();
        int flags = caseInsensitive ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            // Assuming we want to extract the first group for each match
            groups.add(matcher.group(1)); // Change the index for different groups
        }
        return groups;
    }

    // Replaces all occurrences of the regex pattern with the replacement string.
    public static String replace(String text, String regex, String replacement, boolean caseInsensitive) {
        int flags = caseInsensitive ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(replacement);
    }

    // Counts the number of matches for the given regex pattern in the text.
    public static int countMatches(String text, String regex, boolean caseInsensitive) {
        int count = 0;
        int flags = caseInsensitive ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    // Searches and returns a list of matches up to a limit.
    public static List<String> searchWithLimit(String text, String regex, int limit, boolean caseInsensitive) {
        List<String> matches = new ArrayList<>();
        int flags = caseInsensitive ? Pattern.CASE_INSENSITIVE : 0;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find() && matches.size() < limit) {
            matches.add(matcher.group());
        }
        return matches;
    }
    public static String replacePattern(String input, String pattern, String replacement) {
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.replaceAll(replacement);
    }
}
