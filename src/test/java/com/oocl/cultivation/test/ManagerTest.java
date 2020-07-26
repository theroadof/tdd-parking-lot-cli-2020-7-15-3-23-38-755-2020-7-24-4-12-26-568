package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManagerTest {
    @Test
    void should_specify_parking_boy_to_park_when_specify_given_car() {
        //given
        Manager manager = new Manager();
        manager.getParkingBoys().add(new ParkingBoy());
        manager.getParkingBoys().add(new SmartParkingBoy());
        manager.getParkingBoys().add(new SuperSmartParkingBoy());

        ParkingBoy notThisBoy = new ParkingBoy();
        ParkingBoy parkingBoy = manager.getParkingBoys().get(0);
        ParkingBoy smartParkingBoy = manager.getParkingBoys().get(1);
        ParkingBoy superSmartParkingBoy = manager.getParkingBoys().get(2);

        //when
        manager.setManagementStrategy(parkingBoy);
        manager.parkingManagement(new Car());
        manager.setManagementStrategy(smartParkingBoy);
        manager.parkingManagement(new Car());
        manager.setManagementStrategy(superSmartParkingBoy);
        manager.parkingManagement(new Car());
        manager.setManagementStrategy(notThisBoy);
        manager.parkingManagement(new Car());

        ParkingLot notThisParkingLot = notThisBoy.getCurrentParkingLot();
        ParkingLot parkingLot = parkingBoy.getCurrentParkingLot();
        ParkingLot smartParkingLot = smartParkingBoy.getCurrentParkingLot();
        ParkingLot superSmartParkingLot = superSmartParkingBoy.getCurrentParkingLot();

        //then
        assertEquals(1, parkingLot.getParkingLot().size());
        assertEquals(1, smartParkingLot.getParkingLot().size());
        assertEquals(1, superSmartParkingLot.getParkingLot().size());
        assertEquals(0, notThisParkingLot.getParkingLot().size());
    }

}
