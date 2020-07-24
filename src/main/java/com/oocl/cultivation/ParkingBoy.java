package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private final Map<CarTicket, Car> parkingLot;

    private String respondMessage;

    public ParkingBoy() {
        this.parkingLot = new HashMap<>();
    }

    public CarTicket park(Car car) {
        if (this.parkingLot.size() >= 10) {
            return null;
        }
        CarTicket carTicket = new CarTicket();
        parkingLot.put(carTicket, car);
        return carTicket;
    }

    public Car fetch(CarTicket ticket) {
        Car car = parkingLot.get(ticket);
        if(car==null){
            this.respondMessage = "Unrecognized parking ticket.";
        }
        parkingLot.remove(ticket);
        return car;
    }

    public Map<CarTicket, Car> getParkingLot() {
        return parkingLot;
    }

    public Car fetch() {
        this.respondMessage="Please provide your parking ticket.";
        return null;
    }

    public String getRespondMessage() {
        return this.respondMessage;
    }
}
