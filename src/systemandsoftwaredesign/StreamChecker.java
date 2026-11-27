package systemandsoftwaredesign;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Design an algorithm that accepts a stream of characters and checks whether
 * any non-empty suffix matches a word in a given array {@code words}.
 * For example, with {@code words = ["abc", "xyz"]}, the stream
 * {@code "axyz"} matches because its suffix {@code "xyz"} is in the array.
 *
 * <ul>
 *   <li>{@code StreamChecker(String[] words)} initializes the checker with
 *       the words to match.</li>
 *   <li>{@code query(char letter)} accepts the next character and returns
 *       {@code true} if any non-empty suffix matches a word.</li>
 * </ul>
 *
 * <p>With {@code words = ["cd", "f", "kl"]}, querying the letters of
 * {@code "abcdefghijkl"} in order returns:
 * <pre>{@code
 * [false, false, false, true, false, true, false, false, false, false, false, true]
 * }</pre>
 * The matches occur at {@code 'd'}, {@code 'f'}, and {@code 'l'}.
 */
public class StreamChecker {

    private final TrieNode root;
    private final StringBuilder stream;
    private int maxLength = 0;

    public StreamChecker(String[] words) {
        this.root = new TrieNode();
        this.stream = new StringBuilder();

        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());
            insert(word);
        }
    }

    public boolean query(char letter) {
        stream.append(letter);

        if (stream.length() > maxLength) {
            stream.deleteCharAt(0);
        }

        TrieNode current = root;

        for (int i = stream.length() - 1; i >= 0; i--) {
            char currentLetter = stream.charAt(i);

            if (!current.children.containsKey(currentLetter)) {
                return false;
            }

            current = current.children.get(currentLetter);

            if (current.isEndOfWord) {
                return true;
            }
        }

        return false;
    }

    private void insert(String word) {
        TrieNode current = root;

        for (int i = word.length() - 1; i >= 0; i--) {
            char letter = word.charAt(i);

            current = current.children.computeIfAbsent(
                    letter,
                    key -> new TrieNode()
            );
        }

        current.isEndOfWord = true;
    }

    static void main() {
        StreamChecker checker = new StreamChecker(new String[]{"cd", "f", "kl"});
        String letters = "abcdefghijkl";
        boolean[] expected = {
                false, false, false, true, false, true,
                false, false, false, false, false, true
        };
        boolean[] results = new boolean[letters.length()];

        for (int i = 0; i < letters.length(); i++) {
            results[i] = checker.query(letters.charAt(i));
        }

        if (!Arrays.equals(expected, results)) {
            throw new AssertionError("Expected " + Arrays.toString(expected)
                    + ", got " + Arrays.toString(results));
        }
        System.out.println(Arrays.toString(results));
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}
