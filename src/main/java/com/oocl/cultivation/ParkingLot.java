package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot implements Comparable<ParkingLot> {
    private final Map<CarTicket, Car> parkingLot;

    private static final int CAPACITY = 10;

    ParkingLot() {
        this.parkingLot = new HashMap<>();
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }

    @Override
    public int compareTo(ParkingLot o) {
        if(this.parkingLot.size()>o.getParkingLot().size()){
            return 1;
        }else {
            return -1;
        }
    }

    public static int getCAPACITY() {
        return CAPACITY;
    }
}
