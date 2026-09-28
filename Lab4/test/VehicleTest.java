import exceptions.PassengerNotFoundException;
import exceptions.VehicleFullException;
import org.junit.jupiter.api.Test;
import passengers.Firefighter;
import passengers.Policeman;
import passengers.RegularPassenger;
import road.Road;
import vehicles.Bus;
import vehicles.FireTruck;
import vehicles.PoliceCar;
import vehicles.Taxi;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void testCorrectPassengerBoarding() throws VehicleFullException {
        Taxi taxi = new Taxi(3);
        PoliceCar policeCar = new PoliceCar(2);
        FireTruck fireTruck = new FireTruck(4);

        RegularPassenger student = new RegularPassenger("Денис");
        Policeman cop = new Policeman("Олександр");
        Firefighter fireman = new Firefighter("Іван");

        assertEquals(0, taxi.getOccupiedSeats());

        taxi.addPassenger(student);
        policeCar.addPassenger(cop);
        fireTruck.addPassenger(fireman);

        assertEquals(1, taxi.getOccupiedSeats());
        assertEquals(1, policeCar.getOccupiedSeats());
        assertEquals(1, fireTruck.getOccupiedSeats());

    }

    @Test
    public void testVehicleFullExceptionIsThrown() {
        PoliceCar policeCar = new PoliceCar(1);
        Policeman cop1 = new Policeman("Коп 1");
        Policeman cop2 = new Policeman("Коп 2");

        Exception exception = assertThrows(VehicleFullException.class, () -> {
            policeCar.addPassenger(cop1);
            policeCar.addPassenger(cop2);
        });

        assertEquals("Усі місця зайняті!", exception.getMessage());
    }

    @Test
    public void testPassengerNotFoundExceptionIsThrown() throws VehicleFullException {
        Bus bus = new Bus(10);
        RegularPassenger student = new RegularPassenger("Денис");

        bus.addPassenger(student);

        Exception exception = assertThrows(PassengerNotFoundException.class, () -> {
            bus.removePassenger(student);
            bus.removePassenger(student);
        });

        assertEquals("Такого пасажира немає в транспорті!", exception.getMessage());
    }

    @Test
    public void testRoadCountsAllHumansCorrectly() throws VehicleFullException {
        Road road = new Road();

        Taxi taxi = new Taxi(4);
        taxi.addPassenger(new RegularPassenger("Пасажир 1"));
        taxi.addPassenger(new RegularPassenger("Пасажир 2"));

        FireTruck fireTruck = new FireTruck(2);
        fireTruck.addPassenger(new Firefighter("Пожежник 1"));

        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);

        assertEquals(3, road.getCountOfHumans());
    }
}