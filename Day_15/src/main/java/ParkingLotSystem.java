import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ParkingSpace {
    static class Car {
        private final String licensePlate;

        //public Car(){}
        public Car(String licensePlate) {
            this.licensePlate = licensePlate;
        }

        public String getLicensePlate() {
            return licensePlate;
        }
    }

    static class ParkingLot {
        private static int capacity;
        private final Map<String, Car> spots ;//to track which cars are parked

        public ParkingLot(int capacity) {
            ParkingLot.capacity = capacity;
            spots = new HashMap<>();
        }

        public boolean parkCar(Car car){
            if(spots.size()<=capacity) {
                spots.put(car.getLicensePlate(),car);
                return true;
            }
            return false;
        }

        public boolean removeCar(String licensePlate){
            return spots.remove(licensePlate) != null;
        }

        public boolean isFull(){
            return spots.size() == capacity;
        }

        public int getAvailableSpots(){
            return capacity - spots.size();
        }

        public Map<String, Car> getParkedCars(){
            return Collections.unmodifiableMap(spots);
        }
    }
}

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingSpace.Car carNo1 = new ParkingSpace.Car("TN16078");
        ParkingSpace.Car carNo2 = new ParkingSpace.Car("TN16081");
        ParkingSpace.Car carNo3 = new ParkingSpace.Car("TN16083");
        ParkingSpace.Car carNo4 = new ParkingSpace.Car("TN16072");
        ParkingSpace.Car carNo5 = new ParkingSpace.Car("TN16170");
        ParkingSpace.Car carNo6 = new ParkingSpace.Car("TN16103");
        ParkingSpace.Car carNo7 = new ParkingSpace.Car("TN16065");
        ParkingSpace.ParkingLot park = new ParkingSpace.ParkingLot(7);
        ParkingSpace.Car[] cars = { carNo1, carNo2, carNo3, carNo4, carNo5, carNo6, carNo7 };

        for (ParkingSpace.Car car : cars) {
            boolean parked = park.parkCar(car);
            if (parked) {
                System.out.println("Parked: " + car.getLicensePlate());
            } else {
                System.out.println("Failed to park: " + car.getLicensePlate());
                // Optionally break if you don't want to keep trying:
                break;
            }
        }

        if(park.removeCar("TN16065")){
            System.out.println();
            System.out.println("true");
        }

        if (park.isFull()){
            System.out.println("Parking Lot is full");
        }

        System.out.printf("Available spots to park: %d", park.getAvailableSpots());
    }
}