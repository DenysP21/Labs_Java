import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Translator {
    private Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public void addWord(String englishWord, String ukrainianWord) {
        dictionary.put(englishWord.toLowerCase(), ukrainianWord);
    }

    public String translatePhrase(String phrase) {
        if (phrase == null || phrase.isEmpty()) {
            return "";
        }

        Matcher matcher = Pattern.compile("[a-zA-Z]+").matcher(phrase);
        StringBuilder translatedPhrase = new StringBuilder();

        while (matcher.find()) {
            String originalWord = matcher.group();
            String engWordForSearch = originalWord.toLowerCase();

            String translatedWord = dictionary.getOrDefault(engWordForSearch, originalWord);

            if (!translatedWord.equals(originalWord)) {
                if (Character.isUpperCase(originalWord.charAt(0))) {
                    translatedWord = translatedWord.substring(0, 1).toUpperCase() + translatedWord.substring(1);
                }
            }

            matcher.appendReplacement(translatedPhrase, translatedWord);
        }

        matcher.appendTail(translatedPhrase);

        return translatedPhrase.toString();
    }
}