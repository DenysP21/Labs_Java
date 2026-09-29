import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CryptoManager {

    public static void encryptToFile(String text, String filePath, int key) {
        try (CryptoWriter writer = new CryptoWriter(new FileWriter(filePath), key)) {
            writer.write(text);
            System.out.println("Текст успішно зашифровано та збережено у файл: " + filePath);
        } catch (IOException e) {
            System.out.println("Помилка під час шифрування: " + e.getMessage());
        }
    }

    public static String decryptFromFile(String filePath, int key) {
        StringBuilder decryptedText = new StringBuilder();
        try (CryptoReader reader = new CryptoReader(new FileReader(filePath), key)) {
            int c;
            while ((c = reader.read()) != -1) {
                decryptedText.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Помилка під час дешифрування: " + e.getMessage());
        }
        return decryptedText.toString();
    }
}