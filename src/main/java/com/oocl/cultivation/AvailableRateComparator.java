package com.oocl.cultivation;

import java.util.Comparator;

public class AvailableRateComparator implements Comparator<ParkingLot> {

    @Override
    public int compare(ParkingLot o1, ParkingLot o2) {
        if ((o1.getCapacity() - o1.getParkingLot().size()) / o1.getCapacity() < (o2.getCapacity() - o2.getParkingLot().size()) / o2.getCapacity()) {
            return 1;
        } else {
            return -1;
        }
    }
}
