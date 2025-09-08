package com.afs;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Integer capacity;
    private final Map<Ticket, Car> ticketCars = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (ticketCars.size() >= capacity) {
            return null;
        }
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket(1, car, parkingLot);
        ticketCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketCars.remove(ticket);
    }
}

