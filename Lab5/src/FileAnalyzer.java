import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyzer {

    public static List<String> findLinesWithMaxWords(String filePath) {
        List<String> maxLines = new ArrayList<>();
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                int currentWordCount = line.trim().isEmpty() ? 0 : words.length;

                if (currentWordCount > 0) {
                    if (currentWordCount > maxWords) {
                        maxWords = currentWordCount;
                        maxLines.clear();
                        maxLines.add(line);
                    } else if (currentWordCount == maxWords) {
                        maxLines.add(line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
            return null;
        }

        return maxLines;
    }
}