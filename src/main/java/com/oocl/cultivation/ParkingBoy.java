package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;


public class ParkingBoy implements ManagementStrategy {
    private static final String NOT_ENOUGH_POSITION = "Not enough position.";
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
    private static final String PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";
    List<ParkingLot> parkingLots;
    ParkingLot currentParkingLot;
    String respondMessage;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public CarTicket park(Car car) {
        CarTicket carTicket = new CarTicket();
//        Optional.ofNullable(parkingLots.stream()
//                .filter(parkingLot -> (parkingLot.getParkingLot().size()<parkingLot.getCapacity())).collect(toList())).ifPresent(pa->{
//            currentParkingLot = pa.get(0);
//        });
        
        if (hasEmptyPosition()) {
            currentParkingLot.getParkingLot().put(carTicket, car);
            return carTicket;
        } else {
            this.respondMessage = NOT_ENOUGH_POSITION;
            return null;
        }
    }

    private boolean hasEmptyPosition() {
        boolean flag = false;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getParkingLot().size() < parkingLot.getCapacity()) {
                currentParkingLot = parkingLot;
                flag = true;
                break;
            }
        }
        return flag;
    }

    public Car fetch(CarTicket ticket) {
        Car car = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            if (parkingLot.getParkingLot().get(ticket) != null) {
                car = parkingLot.getParkingLot().get(ticket);
                parkingLot.getParkingLot().remove(ticket);
                break;
            }
            if (parkingLots.indexOf(parkingLot) == parkingLots.size() - 1) {
                this.respondMessage = UNRECOGNIZED_PARKING_TICKET;
            }
        }
        return car;
    }

    public Car fetch() {
        this.respondMessage = PROVIDE_YOUR_PARKING_TICKET;
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

    @Override
    public Car specifyFetch() {
        return this.fetch();
    }
}
