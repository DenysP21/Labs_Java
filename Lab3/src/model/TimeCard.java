package model;

import java.time.LocalDate;

public class TimeCard extends PassCard {
    private LocalDate expirationDate;

    public TimeCard(OwnerType ownerType, CardType cardType, int daysValid) {
        super(ownerType, cardType);
        this.expirationDate = LocalDate.now().plusDays(daysValid);
    }

    @Override
    public boolean validateAndDeduct() {
        return !LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public String getInfo() {
        return String.format("Картка на час [%s] (Діє до: %s)", ownerType, expirationDate);
    }
}