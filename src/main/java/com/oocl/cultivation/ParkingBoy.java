package com.oocl.cultivation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private Map<CarTicket, Car> parkingLot = new HashMap<>();

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        parkingLot.put(carTicket, car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        return parkingLot.get(ticket);
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }
}
