package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class Manager extends ParkingBoy {
    private ManagementStrategy managementStrategy;
    private List<ParkingBoy> parkingBoys;
    private ParkingLot parkingLot;

    public Manager() {
        super();
        this.parkingBoys = new ArrayList<>();
        this.parkingLot = new ParkingLot(20);
        this.addParkingLot(parkingLot);
    }

    public void setManagementStrategy(ManagementStrategy managementStrategy) {
        if (parkingBoys.contains(managementStrategy)) {
            this.managementStrategy = managementStrategy;
        }
    }

    public CarTicket parkingManagement(Car car) {
        return this.managementStrategy.specifyParking(car);
    }

    public Car fetchManagement(CarTicket carTicket) {
        return this.managementStrategy.specifyFetch(carTicket);
    }


    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.parkingBoys.add(parkingBoy);
        parkingBoy.getParkingLots().add(this.parkingLot);
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public String getRespondMessageFrom(ParkingBoy parkingBoy) {
        return parkingBoy.getRespondMessage();
    }

    public Car fetchManagement() {
        return this.managementStrategy.specifyFetch();
    }
}
