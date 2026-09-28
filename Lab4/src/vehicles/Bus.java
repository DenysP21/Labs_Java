package vehicles;

import passengers.Human;

public class Bus extends Vehicle<Human> {
    public Bus(int maxCapacity) {
        super(maxCapacity);
    }
}