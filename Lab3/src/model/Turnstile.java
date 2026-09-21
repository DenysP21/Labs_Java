package model;

import java.util.EnumMap;
import java.util.Map;

public class Turnstile {
    private int totalAllowed = 0;
    private int totalDenied = 0;
    private Map<PassCard.CardType, Integer> allowedByType = new EnumMap<>(PassCard.CardType.class);

    public Turnstile() {
        for (PassCard.CardType type : PassCard.CardType.values()) {
            allowedByType.put(type, 0);
        }
    }

    public boolean enter(PassCard card) {
        if (card == null) {
            totalDenied++;
            return false;
        }

        if (card.validateAndDeduct()) {
            totalAllowed++;
            allowedByType.put(card.getCardType(), allowedByType.get(card.getCardType()) + 1);
            return true;
        } else {
            totalDenied++;
            return false;
        }
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== СТАТИСТИКА ТУРНІКЕТА ===\n");
        sb.append("Успішних проходів: ").append(totalAllowed).append("\n");
        sb.append("Відмов: ").append(totalDenied).append("\n");
        sb.append("--- Розбивка за типами карток ---\n");
        for (Map.Entry<PassCard.CardType, Integer> entry : allowedByType.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}