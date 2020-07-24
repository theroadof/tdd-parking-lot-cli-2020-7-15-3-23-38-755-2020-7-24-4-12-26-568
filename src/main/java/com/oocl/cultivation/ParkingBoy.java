package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private ParkingLot parkingLot;

    private String respondMessage;

    public ParkingBoy() {
        this.parkingLot = new ParkingLot();
    }

    public CarTicket park(Car car) {
        if (this.parkingLot.getParkingLot().size() >= 10) {
            this.respondMessage = "Not enough position.";
            return null;
        }
        CarTicket carTicket = new CarTicket();
        this.parkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        Car car = parkingLot.getParkingLot().get(ticket);
        if (car == null) {
            this.respondMessage = "Unrecognized parking ticket.";
        }
        parkingLot.getParkingLot().remove(ticket);
        return car;
    }

    public Car fetch() {
        this.respondMessage = "Please provide your parking ticket.";
        return null;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public String getRespondMessage() {
        return this.respondMessage;
    }
}
