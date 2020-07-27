package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    @Test
    void should_specify_parking_boy_to_park_when_specify_given_car() {
        //given
        Manager manager = new Manager();
        manager.addParkingBoy(new ParkingBoy());
        manager.addParkingBoy(new SmartParkingBoy());
        manager.addParkingBoy(new SuperSmartParkingBoy());

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

        ParkingLot parkingLot = parkingBoy.getCurrentParkingLot();
        ParkingLot smartParkingLot = smartParkingBoy.getCurrentParkingLot();
        ParkingLot superSmartParkingLot = superSmartParkingBoy.getCurrentParkingLot();

        //then
        assertEquals(1, parkingLot.getParkingLot().size());
        assertEquals(2, smartParkingLot.getParkingLot().size());
        assertEquals(2, superSmartParkingLot.getParkingLot().size());
    }

    @Test
    void should_fetch_car_when_specify_given_ticket() {
        //given
        Manager manager = new Manager();
        List<Car> cars = Stream.of(new Car(), new Car(), new Car(), new Car()).collect(Collectors.toList());
        manager.addParkingBoy(new ParkingBoy());
        manager.addParkingBoy(new SmartParkingBoy());
        manager.addParkingBoy(new SuperSmartParkingBoy());

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

    @Test
    void should_fetch_car_when_fetch_given_ticket() {
        //given
        Manager manager = new Manager();
        Car car = new Car();
        CarTicket carTicket = manager.park(car);

        //when
        Car myCar = manager.fetch(carTicket);

        //then
        assertEquals(car, myCar);
    }

    @Test
    void should_return_message_when_specify_park_given_car() {
        //given
        Manager manager = new Manager();
        manager.getParkingLots().forEach(parkingLot -> {
            for(int i=0;i<parkingLot.getCapacity();i++){
                parkingLot.getParkingLot().put(new CarTicket(),new Car());
            }
        });

        manager.addParkingBoy(new ParkingBoy());
        manager.addParkingBoy(new SmartParkingBoy());
        manager.addParkingBoy(new SuperSmartParkingBoy());

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

        //then
        assertEquals("Not enough position.", manager.getRespondMessageFrom(parkingBoy));
        assertEquals("Not enough position.", manager.getRespondMessageFrom(smartParkingBoy));
        assertEquals("Not enough position.", manager.getRespondMessageFrom(superSmartParkingBoy));
    }

    @Test
    void should_return_unrecognized_ticket_message_when_specify_fetch_given_wrong_ticket() {
        //given
        Manager manager = new Manager();
        manager.addParkingBoy(new ParkingBoy());
        ParkingBoy parkingBoy = manager.getParkingBoys().get(0);
        CarTicket ticket = parkingBoy.park(new Car());
        parkingBoy.fetch(ticket);

        //when
        manager.setManagementStrategy(parkingBoy);
        Car car = manager.fetchManagement(ticket);

        //then
        assertNull(car);
        assertEquals("Unrecognized parking ticket.", manager.getRespondMessageFrom(parkingBoy));
    }

    @Test
    void should_return_provide_ticket_message_when_specify_fetch_given_wrong_ticket() {
        //given
        Manager manager = new Manager();
        manager.addParkingBoy(new ParkingBoy());
        ParkingBoy parkingBoy = manager.getParkingBoys().get(0);

        //when
        manager.setManagementStrategy(parkingBoy);
        Car car = manager.fetchManagement();

        //then
        assertNull(car);
        assertEquals("Please provide your parking ticket.", manager.getRespondMessageFrom(parkingBoy));
    }
}
