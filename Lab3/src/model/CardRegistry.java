package model;

import java.util.ArrayList;
import java.util.List;

public class CardRegistry {
    private List<PassCard> issuedCards = new ArrayList<>();

    public PassCard issueTimeCard(PassCard.OwnerType owner, PassCard.CardType type) {
        int days = (type == PassCard.CardType.MONTH) ? 30 : 10;
        TimeCard card = new TimeCard(owner, type, days);
        issuedCards.add(card);
        return card;
    }

    public PassCard issueTripCard(PassCard.OwnerType owner, PassCard.CardType type) {
        int trips = (type == PassCard.CardType.TRIPS_10) ? 10 : 5;
        TripCard card = new TripCard(owner, type, trips);
        issuedCards.add(card);
        return card;
    }

    public PassCard issueAccumulativeCard(double funds) {
        AccumulativeCard card = new AccumulativeCard(funds);
        issuedCards.add(card);
        return card;
    }

    public PassCard getCardById(String id) {
        for (PassCard card : issuedCards) {
            if (card.getId().equalsIgnoreCase(id)) return card;
        }
        return null;
    }
}