package vehicles;

import exceptions.PassengerNotFoundException;
import exceptions.VehicleFullException;
import passengers.Human;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private int maxCapacity;
    private List<T> passengers;

    public Vehicle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.passengers = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void addPassenger(T passenger) throws VehicleFullException {
        if (passengers.size() >= maxCapacity) {
            throw new VehicleFullException("Усі місця зайняті!");
        }
        passengers.add(passenger);
    }

    public void removePassenger(T passenger) throws PassengerNotFoundException {
        if (!passengers.contains(passenger)) {
            throw new PassengerNotFoundException("Такого пасажира немає в транспорті!");
        }
        passengers.remove(passenger);
    }
}