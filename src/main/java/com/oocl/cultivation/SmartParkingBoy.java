package com.oocl.cultivation;

import java.util.Collections;

public class SmartParkingBoy extends ParkingBoy implements ManagementStrategy {

    private static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public SmartParkingBoy() {
        super();
    }

    @Override
    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        Collections.sort(this.parkingLots);
        this.currentParkingLot = this.parkingLots.get(0);
        if (this.currentParkingLot.getParkingLot().size() >= this.currentParkingLot.getCapacity()) {
            this.respondMessage = NOT_ENOUGH_POSITION;
            return null;
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

    @Override
    public CarTicket specifyParking(Car car) {
        return this.park(car);
    }
}
