package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_fetch_car_when_specify_given_ticket() {
        //given
        Manager manager = new Manager();
        List<Car> cars = Stream.of(new Car(), new Car(), new Car(), new Car()).collect(Collectors.toList());
        manager.getParkingBoys().add(new ParkingBoy());
        manager.getParkingBoys().add(new SmartParkingBoy());
        manager.getParkingBoys().add(new SuperSmartParkingBoy());

        ParkingBoy parkingBoy = manager.getParkingBoys().get(0);
        ParkingBoy smartParkingBoy = manager.getParkingBoys().get(1);
        ParkingBoy superSmartParkingBoy = manager.getParkingBoys().get(2);

        manager.setManagementStrategy(parkingBoy);
        CarTicket carTicket1 = manager.parkingManagement(cars.get(0));
        manager.setManagementStrategy(smartParkingBoy);
        CarTicket carTicket2 = manager.parkingManagement(cars.get(1));
        manager.setManagementStrategy(superSmartParkingBoy);
        CarTicket carTicket3 = manager.parkingManagement(cars.get(2));

        List<Car> carList = new ArrayList<>();

        //when
        manager.setManagementStrategy(parkingBoy);
        carList.add(manager.fetchManagement(carTicket1));
        manager.setManagementStrategy(smartParkingBoy);
        carList.add(manager.fetchManagement(carTicket2));
        manager.setManagementStrategy(superSmartParkingBoy);
        carList.add(manager.fetchManagement(carTicket3));

        //then
        assertEquals(cars.get(0), carList.get(0));
        assertEquals(cars.get(1), carList.get(1));
        assertEquals(cars.get(2), carList.get(2));
    }

    @Test
    void should_return_ticket_when_park_given_car() {
        //given
        Manager manager = new Manager();
        Car car = new Car();

        //when
        CarTicket ticket = manager.park(car);

        //then
        assertNotNull(ticket);
    }
}
