import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] words = {"Київський", "політехнічний", "інститут", "імені", "Ігоря", "Сікорського"};

        double averageLength = Arrays.stream(words)
                .mapToInt(String::length)
                .average()
                .orElse(0);

        System.out.println("Середня довжина: " + averageLength + " символів\n");

        String[] shorterWords = Arrays.stream(words)
                .filter(s -> s.length() < averageLength)
                .toArray(String[]::new);

        String[] longerWords = Arrays.stream(words)
                .filter(s -> s.length() > averageLength)
                .toArray(String[]::new);

        if (shorterWords.length == 0 && longerWords.length == 0) {
            System.out.println("Усі рядки мають однакову довжину (або масив порожній). Немає слів, менших чи більших за середню.");
        } else {
            System.out.println("Менші за середню: " + Arrays.toString(shorterWords));
            System.out.println("Більші за середню: " + Arrays.toString(longerWords));
        }
    }
}