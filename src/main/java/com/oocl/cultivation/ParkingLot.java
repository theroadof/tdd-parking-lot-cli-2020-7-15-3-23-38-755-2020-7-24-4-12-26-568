package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Comparable<ParkingLot> {
    private final Map<CarTicket, Car> parkingLot;

    private int capacity;

    public ParkingLot(int capacity) {
        this.parkingLot = new HashMap<>();
        this.capacity = capacity;
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }

    @Override
    public int compareTo(ParkingLot o) {
        if (this.capacity - this.parkingLot.size() < o.getCapacity() - o.getParkingLot().size()) {
            return 1;
        } else {
            return -1;
        }
    }

    public int getCapacity() {
        return capacity;
    }
}
