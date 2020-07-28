package com.oocl.cultivation;

public class SmartParkingBoy extends ParkingBoy implements ManagementStrategy {

    private static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public SmartParkingBoy() {
        super();
    }

    @Override
    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        this.parkingLots.sort(this::sortByAvailability);
        this.currentParkingLot = this.parkingLots.get(0);
        if (this.currentParkingLot.getParkingLot().size() >= this.currentParkingLot.getCapacity()) {
            this.respondMessage = NOT_ENOUGH_POSITION;
            return null;
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

    private int sortByAvailability(ParkingLot firstParkingLot, ParkingLot secondParkingLot){
        if (firstParkingLot.getCapacity() - firstParkingLot.getParkingLot().size() < secondParkingLot.getCapacity() - secondParkingLot.getParkingLot().size()) {
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
