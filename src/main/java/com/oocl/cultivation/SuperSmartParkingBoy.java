package com.oocl.cultivation;

import java.util.Collections;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy() {
        super();
    }

    @Override
    public CarTicket park(Car car) {
        Collections.sort(this.getParkingLots(),new AvailableRateComparator());
        CarTicket carTicket = new CarTicket();
        this.currentParkingLot = this.getParkingLots().get(0);
        if (this.currentParkingLot.getParkingLot().size() >= this.currentParkingLot.getCapacity()) {
            this.respondMessage = "Not enough position.";
            return null;
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }
}
