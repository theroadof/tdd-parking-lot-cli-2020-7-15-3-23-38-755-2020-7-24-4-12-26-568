package com.oocl.cultivation;

public interface ManagementStrategy {

    CarTicket specifyParking(Car car);

    Car specifyFetch(CarTicket carTicket);
}
