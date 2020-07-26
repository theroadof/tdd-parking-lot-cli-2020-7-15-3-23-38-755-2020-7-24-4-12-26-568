package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private ManagementStrategy managementStrategy;
    private List<ParkingBoy> parkingBoys;
    private ParkingLot parkingLot;

    public Manager() {
        this.parkingBoys = new ArrayList<>();
        parkingLot = new ParkingLot(20);
    }

    public void setManagementStrategy(ManagementStrategy managementStrategy) {
        if (parkingBoys.contains(managementStrategy)) {
            this.managementStrategy = managementStrategy;
        }
    }

    public CarTicket parkingManagement(Car car) {
        return this.managementStrategy.specifyParking(car);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public Car fetchManagement(CarTicket carTicket) {
        return this.managementStrategy.specifyFetch(carTicket);
    }

    public CarTicket park(Car car) {
        if (this.parkingLot.getParkingLot().size() > this.parkingLot.getCapacity()) {
            return null;
        }
        CarTicket ticket = new CarTicket();
        parkingLot.getParkingLot().put(ticket,car);
        return ticket;
    }

    public Car fetch(Car car) {
        return null;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

}
