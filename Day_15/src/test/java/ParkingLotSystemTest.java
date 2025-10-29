import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
//    @BeforeAll
//    public static void BeforeAll(){
//        ParkingSpace.
//    }
    @Test
    public void parkingWhenThereIsSpaceAndNot(){
        ParkingSpace.ParkingLot capacity = new ParkingSpace.ParkingLot(2);
        ParkingSpace.Car car1 = new ParkingSpace.Car("TN16078");capacity.parkCar(car1);
        Assertions.assertFalse(capacity.isFull());
        Assertions.assertEquals(1, capacity.getAvailableSpots());
        ParkingSpace.Car car2 = new ParkingSpace.Car("TN16084");capacity.parkCar(car2);
        Assertions.assertTrue(capacity.isFull(), "false");
        Assertions.assertEquals(0, capacity.getAvailableSpots());
    }

    @Test
    public void removingTheCarsWhenThereIsSpaceAndNot(){
        ParkingSpace.ParkingLot capacity = new ParkingSpace.ParkingLot(2);
        ParkingSpace.Car car = new ParkingSpace.Car("TN16130");capacity.parkCar(car);
        Assertions.assertTrue(capacity.removeCar("TN16130"));
        Assertions.assertFalse(capacity.removeCar("TN16081"));
    }
}
