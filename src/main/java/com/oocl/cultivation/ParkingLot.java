package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot{
    private final Map<CarTicket, Car> parkingLot;

    private final int capacity;

    public ParkingLot(int capacity) {
        this.parkingLot = new HashMap<>();
        this.capacity = capacity;
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }

    public int getCapacity() {
        return capacity;
    }
}
