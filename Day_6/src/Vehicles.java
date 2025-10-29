import java.util.ArrayList;
import java.util.List;

abstract class Vehicle {
    String make;
    String model;

    abstract double maxSpeed();
    String getDetails() {
        return make + " : " + model;
    }
}

interface Refuelled {
    double liter = 9;

    void refuel(double liters);
    default boolean checkFuelLevel(double fuelLevel) {
        return fuelLevel >= liter;
    }
}

interface Charged {
    void charge(double kWh);
}

class Car extends Vehicle implements Refuelled {

    Car() {
        make = "Honda";
        model = "Civic";
    }

    double maxSpeed() {
        return 310;
    }

    public void refuel(double liters) {
        System.out.printf("Refueling %.1f liters...\n", liters);
    }

    public void checkFuelLevel(Refuelled fuelLevel) {
        if (fuelLevel.checkFuelLevel(liter)) {
            System.out.println("Fuel level is OK!");
        } else {
            System.out.println("Fuel level is low!");
        }
    }
}

class ElectricCar extends Car implements Charged {
    ElectricCar() {
        make = "Tesla";
        model = "Model S";
    }

    double maxSpeed() {
        return 350;
    }

    public void charge(double kWh) {
        System.out.println("Charging " + kWh + " kWh...");
    }
}

public class Vehicles {
    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Car());
        vehicleList.add(new ElectricCar());
        System.out.println();
        System.out.println("Vehicle List : ");
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getDetails());
            System.out.println("Max Speed : " + vehicle.maxSpeed());
            if (vehicle instanceof Refuelled) {
                ((Refuelled) vehicle).refuel((Math.random() * 10 + 10));
                ((Refuelled) vehicle).checkFuelLevel(Refuelled.liter);
            }
            System.out.println();
        }
    }
}