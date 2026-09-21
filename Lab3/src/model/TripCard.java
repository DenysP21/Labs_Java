package model;

public class TripCard extends PassCard {
    private int tripsLeft;

    public TripCard(OwnerType ownerType, CardType cardType, int totalTrips) {
        super(ownerType, cardType);
        this.tripsLeft = totalTrips;
    }

    @Override
    public boolean validateAndDeduct() {
        if (tripsLeft > 0) {
            tripsLeft--;
            return true;
        }
        return false;
    }

    @Override
    public String getInfo() {
        return String.format("Картка на поїздки [%s] (Залишок: %d шт)", ownerType, tripsLeft);
    }
}