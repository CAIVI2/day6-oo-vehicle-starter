package com.afs;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<Ticket, Car> ticketCars = new HashMap<>();

    public ParkingLot(int i) {

    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket(1, car, parkingLot);
        ticketCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCars.remove(ticket);
    }
}

