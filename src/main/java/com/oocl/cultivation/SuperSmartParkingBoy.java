package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy() {
        super();
    }

    @Override
    public CarTicket park(Car car) {
        this.getParkingLots().sort(new AvailableRateComparator());
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