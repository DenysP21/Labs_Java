package model;

import java.util.UUID;

public abstract class PassCard {
    public enum OwnerType { PUPIL, STUDENT, STANDARD }
    public enum CardType { MONTH, DAYS_10, TRIPS_5, TRIPS_10, ACCUMULATIVE }

    protected String id;
    protected OwnerType ownerType;
    protected CardType cardType;

    public PassCard(OwnerType ownerType, CardType cardType) {
        this.id = UUID.randomUUID().toString().substring(0, 6);
        this.ownerType = ownerType;
        this.cardType = cardType;
    }

    public String getId() { return id; }
    public OwnerType getOwnerType() { return ownerType; }
    public CardType getCardType() { return cardType; }

    public abstract boolean validateAndDeduct();
    public abstract String getInfo();
}