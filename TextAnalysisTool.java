import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAnalysisTool {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String text = readNonEmptyParagraph(scanner);

            int characterCount = text.length();
            String[] words = splitWords(text);
            int wordCount = words.length;

            Character mostCommonCharacter = findMostCommonNonWhitespaceCharacter(text);

            char queryCharacter = readSingleCharacter(scanner);
            int characterFrequency = countCharacterFrequency(text, queryCharacter);

            String queryWord = readNonEmptyWord(scanner);
            int wordFrequency = countWordFrequency(words, queryWord);

            int uniqueWordCount = countUniqueWords(words);

            System.out.println();
            System.out.println("--- Text Analysis Results ---");
            System.out.println("Total characters: " + characterCount);
            System.out.println("Total words: " + wordCount);
            System.out.println("Most common character (excluding whitespace): " + (mostCommonCharacter == null ? "N/A" : mostCommonCharacter));
            System.out.println("Frequency of character '" + queryCharacter + "': " + characterFrequency);
            System.out.println("Frequency of word \"" + queryWord + "\": " + wordFrequency);
            System.out.println("Unique words (case-insensitive): " + uniqueWordCount);
        }
    }

    private static String readNonEmptyParagraph(Scanner scanner) {
        System.out.println("Enter a paragraph or lengthy text.");
        System.out.println("Finish input by entering an empty line:");

        StringBuilder builder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                break;
            }
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(line);
        }

        String text = builder.toString().trim();
        while (text.isEmpty()) {
            System.out.println("Input cannot be empty. Please enter your text again.");
            builder.setLength(0);
            while (true) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                if (builder.length() > 0) {
                    builder.append(' ');
                }
                builder.append(line);
            }
            text = builder.toString().trim();
        }
        return text;
    }

    private static String[] splitWords(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("\\s+");
    }

    private static Character findMostCommonNonWhitespaceCharacter(String text) {
        Map<Character, Integer> counts = new HashMap<>();
        String lowered = text.toLowerCase(Locale.ROOT);

        for (int i = 0; i < lowered.length(); i++) {
            char c = lowered.charAt(i);
            if (Character.isWhitespace(c)) {
                continue;
            }
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        if (counts.isEmpty()) {
            return null;
        }

        Character mostCommon = null;
        int max = -1;
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostCommon = entry.getKey();
            }
        }
        return mostCommon;
    }

    private static char readSingleCharacter(Scanner scanner) {
        while (true) {
            System.out.print("Enter a character to count its frequency: ");
            String input = scanner.nextLine().trim();
            if (input.length() == 1) {
                return input.charAt(0);
            }
            System.out.println("Invalid input. Please enter exactly one character.");
        }
    }

    private static int countCharacterFrequency(String text, char queryCharacter) {
        char normalizedQuery = Character.toLowerCase(queryCharacter);
        int count = 0;
        String lowered = text.toLowerCase(Locale.ROOT);
        for (int i = 0; i < lowered.length(); i++) {
            if (lowered.charAt(i) == normalizedQuery) {
                count++;
            }
        }
        return count;
    }

    private static String readNonEmptyWord(Scanner scanner) {
        while (true) {
            System.out.print("Enter a word to count its frequency: ");
            String input = scanner.nextLine().trim();
            String normalized = normalizeWord(input);
            if (!normalized.isEmpty()) {
                return input;
            }
            System.out.println("Invalid input. Please enter a non-empty word.");
        }
    }

    private static int countWordFrequency(String[] words, String queryWord) {
        String normalizedQuery = normalizeWord(queryWord);
        if (normalizedQuery.isEmpty()) {
            return 0;
        }

        int count = 0;
        for (String w : words) {
            if (normalizeWord(w).equals(normalizedQuery)) {
                count++;
            }
        }
        return count;
    }

    private static int countUniqueWords(String[] words) {
        Set<String> unique = new HashSet<>();
        for (String w : words) {
            String normalized = normalizeWord(w);
            if (!normalized.isEmpty()) {
                unique.add(normalized);
            }
        }
        return unique.size();
    }

    private static String normalizeWord(String word) {
        if (word == null) {
            return "";
        }
        String lowered = word.toLowerCase(Locale.ROOT).trim();
        int start = 0;
        int end = lowered.length();
        while (start < end && !Character.isLetterOrDigit(lowered.charAt(start))) {
            start++;
        }
        while (end > start && !Character.isLetterOrDigit(lowered.charAt(end - 1))) {
            end--;
        }
        return lowered.substring(start, end);
    }
}
