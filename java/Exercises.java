import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercises {
    static Map<Integer, Long> change(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        var counts = new HashMap<Integer, Long>();
        for (var denomination : List.of(25, 10, 5, 1)) {
            counts.put(denomination, amount / denomination);
            amount %= denomination;
        }
        return counts;
    }

    // Write your first then lower case function here
    public static Optional<String> firstThenLowerCase(List<String> strings, Predicate<String> predicate) {
        // Use streams to filter and find the first match, then convert to lowercase
        return strings.stream()
                .filter(predicate) // Filter the list based on the predicate
                .findFirst()       // Get the first matching element, if any
                .map(String::toLowerCase); // Convert it to lowercase if found
    }
    // Write your say function here
    public static Say say(String... words) {
        if (words.length == 0) {
            return new Say("");
        }
        return new Say(words[0]);
    }

    // Inner Say class that handles the chaining of words
    public static class Say {
        private StringBuilder phraseBuilder;

        // Constructor to initialize the phrase
        public Say(String word) {
            this.phraseBuilder = new StringBuilder(word);
        }

        // Method to chain words
        public Say and(String word) {
            phraseBuilder.append(" ").append(word);
            return this;
        }

        // Getter for the accumulated phrase
        public String phrase() {
            return phraseBuilder.toString();
        }
    }
    // Write your line count function here
}
class LineCounter {

    public static long countValidLines(String fileName) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            return reader.lines()
                    .filter(line -> !line.trim().isEmpty())  // Ignore empty or whitespace-only lines
                    .filter(line -> !line.trim().startsWith("#"))  // Ignore lines starting with #
                    .count();
        }
    }

    public static void main(String[] args) {
        try {
            long count = countValidLines("example.txt");
            System.out.println("Valid line count: " + count);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}


// Write your Quaternion record class here

// Write your BinarySearchTree sealed interface and its implementations here
