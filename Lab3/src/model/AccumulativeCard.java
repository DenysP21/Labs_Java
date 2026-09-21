package model;

public class AccumulativeCard extends PassCard {
    private double balance;
    private static final double TRAM_FARE = 8.0;

    public AccumulativeCard(double initialBalance) {
        super(OwnerType.STANDARD, CardType.ACCUMULATIVE);
        this.balance = initialBalance;
    }

    public void addFunds(double amount) {
        this.balance += amount;
    }

    @Override
    public boolean validateAndDeduct() {
        if (balance >= TRAM_FARE) {
            balance -= TRAM_FARE;
            return true;
        }
        return false;
    }

    @Override
    public String getInfo() {
        return String.format("Накопичувальна картка [STANDARD] (Баланс: %.2f грн)", balance);
    }
}