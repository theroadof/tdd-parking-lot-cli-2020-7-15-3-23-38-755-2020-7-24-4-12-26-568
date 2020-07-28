package com.oocl.cultivation;

public class SuperSmartParkingBoy extends ParkingBoy implements ManagementStrategy {

    private static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public SuperSmartParkingBoy() {
        super();
    }

    @Override
    public CarTicket park(Car car) {
        this.getParkingLots().sort(this::sortByAvailabilityRate);
        CarTicket carTicket = new CarTicket();
        this.currentParkingLot = this.getParkingLots().get(0);
        if (this.currentParkingLot.getParkingLot().size() >= this.currentParkingLot.getCapacity()) {
            this.respondMessage = NOT_ENOUGH_POSITION;
            return null;
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

    private int sortByAvailabilityRate(ParkingLot firstParkingLot, ParkingLot secondParkingLot) {
        if ((double)((firstParkingLot.getCapacity() - firstParkingLot.getParkingLot().size()) / firstParkingLot.getCapacity()) < (double)((secondParkingLot.getCapacity() - secondParkingLot.getParkingLot().size()) / secondParkingLot.getCapacity())) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public CarTicket specifyParking(Car car) {
        return this.park(car);
    }
}
