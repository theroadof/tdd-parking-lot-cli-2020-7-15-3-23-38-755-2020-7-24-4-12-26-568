package com.oocl.cultivation;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private final Map<CarTicket, Car> parkingLot;

    public ParkingBoy() {
        this.parkingLot = new HashMap<>();
    }

    public CarTicket park(Car car) {
        if (this.parkingLot.size() >= 10) {
            return null;
        }
        CarTicket carTicket = new CarTicket();
        parkingLot.put(carTicket, car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        Car car = parkingLot.get(ticket);
        parkingLot.remove(ticket);
        return car;
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }

    public Car fetch() {
        return null;
    }
}
