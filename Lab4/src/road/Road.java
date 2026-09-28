package road;

import vehicles.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<?>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<?> vehicle) {
        carsInRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<?> vehicle : carsInRoad) {
            count += vehicle.getOccupiedSeats();
        }
        return count;
    }
}