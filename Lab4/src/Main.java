import exceptions.PassengerNotFoundException;
import exceptions.VehicleFullException;
import passengers.Firefighter;
import passengers.Policeman;
import passengers.RegularPassenger;
import road.Road;
import vehicles.Bus;
import vehicles.FireTruck;
import vehicles.PoliceCar;
import vehicles.Taxi;

public class Main {
    public static void main(String[] args) {
        try {
            Policeman cop = new Policeman("Олександр");
            Firefighter fireman = new Firefighter("Іван");
            RegularPassenger student = new RegularPassenger("Денис");

            Taxi taxi = new Taxi(3);
            PoliceCar policeCar = new PoliceCar(2);
            FireTruck fireTruck = new FireTruck(4);
            Bus bus = new Bus(40);

            taxi.addPassenger(student);
            policeCar.addPassenger(cop);
            fireTruck.addPassenger(fireman);
            bus.addPassenger(student);

            Road road = new Road();
            road.addCarToRoad(taxi);
            road.addCarToRoad(policeCar);
            road.addCarToRoad(fireTruck);
            road.addCarToRoad(bus);

            System.out.println("Всього людей на дорозі: " + road.getCountOfHumans());

            taxi.removePassenger(student);
            taxi.removePassenger(student);

        } catch (VehicleFullException | PassengerNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}