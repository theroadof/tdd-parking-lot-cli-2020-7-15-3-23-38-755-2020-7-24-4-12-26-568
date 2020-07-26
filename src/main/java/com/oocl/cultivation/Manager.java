package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private ManagementStrategy managementStrategy;
    private List<ParkingBoy> parkingBoys;

    public Manager() {
        this.parkingBoys = new ArrayList<>();
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
}
