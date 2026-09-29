import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalyzer {

    public static Map<String, Integer> parseTags(String urlString) {
        Map<String, Integer> tagCounts = new HashMap<>();
        Pattern pattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>");

        try {
            System.out.println("Підключення до " + urlString + "...");
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String tag = matcher.group(1).toLowerCase();
                        tagCounts.put(tag, tagCounts.getOrDefault(tag, 0) + 1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка при роботі з URL: " + e.getMessage());
        }
        return tagCounts;
    }

    public static void printSortedByAlphabet(Map<String, Integer> tagCounts) {
        Map<String, Integer> sorted = new TreeMap<>(tagCounts);
        System.out.println("\n--- Теги за алфавітом (лексикографічний порядок) ---");
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue());
        }
    }

    public static void printSortedByFrequency(Map<String, Integer> tagCounts) {
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(tagCounts.entrySet());
        sorted.sort(Map.Entry.comparingByValue());

        System.out.println("\n--- Теги за частотою появи (зростання) ---");
        for (Map.Entry<String, Integer> entry : sorted) {
            System.out.println("<" + entry.getKey() + "> : " + entry.getValue());
        }
    }
}