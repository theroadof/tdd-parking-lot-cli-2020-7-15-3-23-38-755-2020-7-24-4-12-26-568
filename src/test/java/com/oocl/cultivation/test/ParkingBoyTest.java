package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.CarTicket;
import com.oocl.cultivation.ParkingBoy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {

    @Test
    void should_return_car_ticket_when_park_given_car() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        CarTicket carTicket = parkingBoy.park(car);

        //then
        assertNotNull(carTicket);
    }

    @Test
    void should_return_car_when_fetch_given_car_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy();
        CarTicket ticket = parkingBoy.park(car);

        //when
        Car myCar = parkingBoy.fetch(ticket);

        //then
        assertNotNull(car);
        assertEquals(car, myCar);
    }

    @Test
    void should_park_multiple_cars_when_park_given_multiple_cars() {
        //given
        List<Car> cars = Stream.of(new Car(), new Car(), new Car()).collect(Collectors.toList());
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        cars.forEach(car -> {
            CarTicket ticket = parkingBoy.park(car);
        });

        //then
        assertEquals(cars.size(), parkingBoy.getParkingLot().size());
    }

    @Test
    void should_fetch_right_cars_when_fetch_given_multiple_tickets() {
        //given
        List<Car> cars = Stream.of(new Car(), new Car(), new Car()).collect(Collectors.toList());
        ParkingBoy parkingBoy = new ParkingBoy();
        List<CarTicket> tickets = new ArrayList<>();
        cars.forEach(car -> {
            tickets.add(parkingBoy.park(car));
        });
        boolean isCorrespond = true;

        //when
        for (int i = 0; i < cars.size(); i++) {
            if (!parkingBoy.fetch(tickets.get(i)).equals(cars.get(i))) {
                isCorrespond = false;
            }
        }

        //then
        assertTrue(isCorrespond);
    }

    @Test
    void should_return_no_car_when_fetch_given_wrong_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        CarTicket wrongTicket = new CarTicket();
        parkingBoy.park(new Car());

        //when
        Car car = parkingBoy.fetch(wrongTicket);

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetch_given_no_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(new Car());

        //when
        Car car = parkingBoy.fetch();

        //then
        assertNull(car);
    }

    @Test
    void should_return_no_car_when_fetch_given_used_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        CarTicket carTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(carTicket);

        //when
        Car newCar = parkingBoy.fetch(carTicket);

        //then
        assertNull(newCar);
    }

    @Test
    void should_return_no_ticket_when_park_given_car_and_parking_lot_has_no_position() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        for(int i=0;i<10;i++){
            parkingBoy.park(new Car());
        }

        //when
        CarTicket ticket = parkingBoy.park(new Car());

        //then
        assertNull(ticket);
    }

    @Test
    void should_return_message_when_park_given_wrong_ticket_or_used_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        CarTicket ticket = parkingBoy.park(new Car());
        CarTicket illegalTicket = new CarTicket();
        Car car = parkingBoy.fetch(ticket);

        //when then
        parkingBoy.fetch(ticket);
        assertEquals("Unrecognized parking ticket.",parkingBoy.getRespondMessage());

        parkingBoy.fetch(illegalTicket);
        assertEquals("Unrecognized parking ticket.",parkingBoy.getRespondMessage());
    }

    @Test
    void should_return_provide_ticket_message_when_park_given_no_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        parkingBoy.fetch();

        //then
        assertEquals("Please provide your parking ticket.",parkingBoy.getRespondMessage());
    }

    @Test
    void should_return_no_position_message_when_no_position_park_given_car() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        for(int i=0;i<10;i++){
            parkingBoy.park(new Car());
        }

        //when
        parkingBoy.park(new Car());

        //then
        assertEquals("Not enough position.",parkingBoy.getRespondMessage());
    }
}
