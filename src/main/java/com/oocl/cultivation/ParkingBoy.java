package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    protected ParkingLot currentParkingLot;

    protected String respondMessage;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
        parkingLots.addAll(Stream.of(new ParkingLot(),new ParkingLot()).collect(Collectors.toList()));
        this.currentParkingLot = parkingLots.get(0);
    }

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
        if (this.currentParkingLot.getParkingLot().size() >= 10) {
            if(parkingLots.indexOf(this.currentParkingLot)<parkingLots.size()-1){
                this.currentParkingLot = parkingLots.get(parkingLots.indexOf(this.currentParkingLot)+1);
            }else {
                this.respondMessage = "Not enough position.";
                return null;
            }
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
