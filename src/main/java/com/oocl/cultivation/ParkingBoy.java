package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected ParkingLot currentParkingLot;

    protected String respondMessage;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        parkingLots.addAll(Stream.of(new ParkingLot(), new ParkingLot()).collect(Collectors.toList()));
        this.currentParkingLot = parkingLots.get(0);
    }

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        if (this.currentParkingLot.getParkingLot().size() >= ParkingLot.getCAPACITY()) {
            Collections.sort(this.parkingLots);
            if (this.parkingLots.get(0).getParkingLot().size() < ParkingLot.getCAPACITY()) {
                this.currentParkingLot = this.parkingLots.get(0);
            } else {
                this.respondMessage = "Not enough position.";
                return null;
            }
        } else {
            Collections.sort(this.parkingLots);
            this.currentParkingLot = this.parkingLots.get(0);
        }
        this.currentParkingLot.getParkingLot().put(carTicket, car);
        return carTicket;
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

    public String getRespondMessage() {
        return this.respondMessage;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingLot getCurrentParkingLot() {
        return currentParkingLot;
    }
}
