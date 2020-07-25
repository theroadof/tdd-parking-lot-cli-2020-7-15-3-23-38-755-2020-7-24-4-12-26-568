package com.oocl.cultivation;

import java.util.Collections;

public class SmartParkingBoy extends ParkingBoy {
    @Override
    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        Collections.sort(this.parkingLots);
        this.currentParkingLot = this.parkingLots.get(0);
        if (this.currentParkingLot.getParkingLot().size() >= ParkingLot.getCAPACITY()) {
            this.respondMessage = "Not enough position.";
            return null;
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

}
