package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy implements ManagementStrategy {
    List<ParkingLot> parkingLots;
    ParkingLot currentParkingLot;
    String respondMessage;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        boolean flag = false;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingLot().size() < parkingLot.getCapacity()) {
                currentParkingLot = parkingLot;
                flag = true;
                break;
            }
        }
        if (flag) {
            currentParkingLot.getParkingLot().put(carTicket, car);
            return carTicket;
        } else {
            this.respondMessage = "Not enough position.";
            return null;
        }
    }

    public Car fetch(CarTicket ticket) {
        Car car = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.getParkingLot().get(ticket) != null) {
                car = parkingLot.getParkingLot().get(ticket);
                parkingLot.getParkingLot().remove(ticket);
                break;
            }
            this.respondMessage = "Unrecognized parking ticket.";
        }
        return car;
    }

    public Car fetch() {
        this.respondMessage = "Please provide your parking ticket.";
        return null;
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String getRespondMessage() {
        return this.respondMessage;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingLot getCurrentParkingLot() {
        return currentParkingLot;
    }

    @Override
    public CarTicket specifyParking(Car car) {
        return this.park(car);
    }

    @Override
    public Car specifyFetch(CarTicket carTicket) {
        return this.fetch(carTicket);
    }
}
