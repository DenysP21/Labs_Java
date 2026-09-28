package vehicles;

import passengers.Human;

public class Taxi extends Car<Human> {
    public Taxi(int maxCapacity) {
        super(maxCapacity);
    }
}