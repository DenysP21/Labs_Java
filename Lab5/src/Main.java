import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("Лабораторна робота №5 (IO Streams)");
            System.out.println("1. Завдання 1: Пошук рядка з максимальною кількістю слів");
            System.out.println("2. Завдання 3: Шифрування та дешифрування файлу");
            System.out.println("3. Завдання 4: Аналіз HTML-тегів за URL");
            System.out.println("0. Вихід");
            System.out.print("Оберіть дію: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("\nВведіть шлях до файлу та його ім'я: ");
                    String filePath = scanner.nextLine();
                    java.util.List<String> results = FileAnalyzer.findLinesWithMaxWords(filePath);

                    if (results != null) {
                        if (results.isEmpty()) {
                            System.out.println("Файл порожній або не містить слів.");
                        } else {
                            System.out.println("Рядок (рядки) з максимальною кількістю слів:");
                            for (String r : results) {
                                System.out.println("- " + r);
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.print("\nВведіть текст для шифрування: ");
                    String textToEncrypt = scanner.nextLine();

                    System.out.print("Введіть шлях до файлу та його ім'я для збереження: ");
                    String cryptoFile = scanner.nextLine();

                    System.out.print("Введіть числовий ключ шифрування: ");
                    try {
                        int key = Integer.parseInt(scanner.nextLine());

                        CryptoManager.encryptToFile(textToEncrypt, cryptoFile, key);
                        String decrypted = CryptoManager.decryptFromFile(cryptoFile, key);

                        System.out.println("Розшифрований текст із файлу:\n" + decrypted);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: Ключ має бути цілим числом!");
                    }
                    break;

                case "3":
                    System.out.print("\nВведіть URL для аналізу ");
                    String url = scanner.nextLine();

                    java.util.Map<String, Integer> tags = HtmlAnalyzer.parseTags(url);

                    if (tags != null && !tags.isEmpty()) {
                        System.out.println("\nЯк вивести результати?");
                        System.out.println("1. За алфавітом (лексикографічний порядок)");
                        System.out.println("2. За частотою появи (зростання)");
                        System.out.println("3. Обидва варіанти");
                        System.out.print("Ваш вибір: ");
                        String sortChoice = scanner.nextLine();

                        if (sortChoice.equals("1") || sortChoice.equals("3")) {
                            HtmlAnalyzer.printSortedByAlphabet(tags);
                        }
                        if (sortChoice.equals("2") || sortChoice.equals("3")) {
                            HtmlAnalyzer.printSortedByFrequency(tags);
                        }
                    } else {
                        System.out.println("Тегів не знайдено або сторінка порожня.");
                    }
                    break;
            }
        }
        scanner.close();
    }
}