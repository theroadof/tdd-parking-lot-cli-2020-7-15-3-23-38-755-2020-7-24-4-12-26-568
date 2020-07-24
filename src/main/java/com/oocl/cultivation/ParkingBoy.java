package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private ParkingLot currentParkingLot;

    private String respondMessage;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLot());
        this.currentParkingLot = parkingLots.get(0);
    }

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        if (this.currentParkingLot.getParkingLot().size() >= 10) {
            this.parkingLots.add(new ParkingLot());
            this.currentParkingLot = parkingLots.get(parkingLots.size() - 1);
            this.respondMessage = "Not enough position.";
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        Car car = currentParkingLot.getParkingLot().get(ticket);
        if (car == null) {
            this.respondMessage = "Unrecognized parking ticket.";
        }
        currentParkingLot.getParkingLot().remove(ticket);
        return car;
    }

    public Car fetch() {
        this.respondMessage = "Please provide your parking ticket.";
        return null;
    }

    public ParkingLot getParkingLot() {
        return currentParkingLot;
    }

    public String getRespondMessage() {
        return this.respondMessage;
    }
}
