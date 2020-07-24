package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<CarTicket, Car> parkingLot;

    ParkingLot() {
        this.parkingLot = new HashMap<>();
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }
}
