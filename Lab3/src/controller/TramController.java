package controller;

import model.*;
import view.ConsoleView;

public class TramController {
    private Turnstile turnstile;
    private CardRegistry registry;
    private ConsoleView view;

    public TramController(Turnstile turnstile, CardRegistry registry, ConsoleView view) {
        this.turnstile = turnstile;
        this.registry = registry;
        this.view = view;
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu();
            String choice = view.getInput("Оберіть дію");

            switch (choice) {
                case "1": issueCardMenu(); break;
                case "2": processTurnstile(); break;
                case "3": view.showMessage(turnstile.getStatistics()); break;
                case "0": running = false; view.showMessage("Завершення роботи."); break;
                default: view.showMessage("Невірний вибір.");
            }
        }
    }

    private void issueCardMenu() {
        String type = view.getInput("Тип (1-Місяць, 2-10 Днів, 3-5 Поїздок, 4-10 Поїздок, 5-Накопичувальна)");
        PassCard newCard = null;

        if (type.equals("5")) {
            double funds = Double.parseDouble(view.getInput("Введіть суму поповнення (грн)"));
            newCard = registry.issueAccumulativeCard(funds);
        } else {
            String ownerStr = view.getInput("Власник (1-Учнівська, 2-Студентська, 3-Звичайна)");
            PassCard.OwnerType owner = (ownerStr.equals("1")) ? PassCard.OwnerType.PUPIL :
                    (ownerStr.equals("2")) ? PassCard.OwnerType.STUDENT : PassCard.OwnerType.STANDARD;

            switch (type) {
                case "1": newCard = registry.issueTimeCard(owner, PassCard.CardType.MONTH); break;
                case "2": newCard = registry.issueTimeCard(owner, PassCard.CardType.DAYS_10); break;
                case "3": newCard = registry.issueTripCard(owner, PassCard.CardType.TRIPS_5); break;
                case "4": newCard = registry.issueTripCard(owner, PassCard.CardType.TRIPS_10); break;
            }
        }

        if (newCard != null) {
            view.showMessage("Картку успішно створено! Ваш ID: " + newCard.getId() + " | " + newCard.getInfo());
        }
    }

    private void processTurnstile() {
        String id = view.getInput("Введіть ID картки");
        PassCard card = registry.getCardById(id);

        if (turnstile.enter(card)) {
            view.showMessage("ПРОХІД ДОЗВОЛЕНО! " + card.getInfo());
        } else {
            view.showMessage("ПРОХІД ЗАБОРОНЕНО! (Картку не знайдено, прострочено або порожній баланс)");
        }
    }
}