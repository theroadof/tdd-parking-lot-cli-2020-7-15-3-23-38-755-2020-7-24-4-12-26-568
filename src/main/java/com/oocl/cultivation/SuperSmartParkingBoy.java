package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy implements ManagementStrategy {
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

    @Override
    public CarTicket specifyParking(Car car) {
        return this.park(car);
    }

    @Override
    public Car specifyFetch(CarTicket carTicket) {
        return super.specifyFetch(carTicket);
    }
}
