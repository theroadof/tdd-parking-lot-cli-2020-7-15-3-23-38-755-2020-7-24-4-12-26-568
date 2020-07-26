package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.CarTicket;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartParkingBoyTest {
    @Test
    void should_park_car_in_more_empty_position_parking_lot_when_park_given_car() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        smartParkingBoy.addParkingLot(new ParkingLot(10));
        smartParkingBoy.addParkingLot(new ParkingLot(20));
        Car car = new Car();
        smartParkingBoy.getParkingLots().get(1).getParkingLot().put(new CarTicket(), new Car());
        smartParkingBoy.getParkingLots().get(0).getParkingLot().put(new CarTicket(), new Car());
        smartParkingBoy.getParkingLots().get(0).getParkingLot().put(new CarTicket(), new Car());
        int size = smartParkingBoy.getParkingLots().get(0).getParkingLot().size();

        //when
        smartParkingBoy.park(car);

        //then
        assertEquals(size, smartParkingBoy.getCurrentParkingLot().getParkingLot().size());
    }
}
