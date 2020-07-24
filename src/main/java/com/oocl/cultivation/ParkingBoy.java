package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (this.currentParkingLot.getParkingLot().size() >= 10) {
            this.respondMessage = "Not enough position.";
            return null;
        }
        CarTicket carTicket = new CarTicket();
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
