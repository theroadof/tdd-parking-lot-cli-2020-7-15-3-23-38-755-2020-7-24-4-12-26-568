package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.CarTicket;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.SuperSmartParkingBoy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuperSmartParkingBoyTest {
    @Test
    void should_park_car_in_larger_available_position_rate_parking_lot_when_park_given_car() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
        superSmartParkingBoy.addParkingLot(new ParkingLot(10));
        superSmartParkingBoy.addParkingLot(new ParkingLot(20));
        Car car = new Car();
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                superSmartParkingBoy.getParkingLots().get(1).getParkingLot().put(new CarTicket(), new Car());
            }
            superSmartParkingBoy.getParkingLots().get(0).getParkingLot().put(new CarTicket(), new Car());
        }

        //when
        int size = superSmartParkingBoy.getParkingLots().get(1).getParkingLot().size();
        superSmartParkingBoy.park(car);

        //then
        assertEquals(size + 1, superSmartParkingBoy.getCurrentParkingLot().getParkingLot().size());
    }
}
