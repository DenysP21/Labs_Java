import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Translator translator = new Translator();
        Scanner scanner = new Scanner(System.in);

        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("we", "ми");
        translator.addWord("study", "вивчаємо");
        translator.addWord("java", "джава");

        System.out.println("=== Консольний Перекладач ===");

        boolean running = true;

        while (running) {
            System.out.println("\n---------------------------------");
            System.out.println("Оберіть дію:");
            System.out.println("1 - Додати нове слово до словника");
            System.out.println("2 - Перекласти фразу");
            System.out.println("0 - Вихід");
            System.out.print("> ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Введіть англійське слово: ");
                    String eng = scanner.nextLine().trim();
                    System.out.print("Введіть переклад українською: ");
                    String ukr = scanner.nextLine().trim();

                    translator.addWord(eng, ukr);
                    System.out.println("Слово успішно додано!");
                    break;

                case "2":
                    System.out.print("Введіть фразу англійською для перекладу:\n> ");
                    String phrase = scanner.nextLine();

                    String result = translator.translatePhrase(phrase);
                    System.out.println("\nРезультат перекладу:\n> " + result);
                    break;

                case "0":
                    System.out.println("Завершення роботи.");
                    running = false;
                    break;

                default:
                    System.out.println("Невідома команда. Будь ласка, введіть 1, 2 або 0.");
                    break;
            }
        }

        scanner.close();
    }
}