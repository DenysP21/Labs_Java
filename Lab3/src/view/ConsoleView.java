package view;

import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String getInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    public void showMenu() {
        System.out.println("\n--- ШВИДКІСНИЙ ТРАМВАЙ ---");
        System.out.println("1. Випустити нову картку");
        System.out.println("2. Пройти через турнікет (за ID картки)");
        System.out.println("3. Показати статистику турнікета");
        System.out.println("0. Вихід");
    }
}