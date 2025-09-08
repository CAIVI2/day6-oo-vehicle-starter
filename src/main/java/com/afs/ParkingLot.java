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
            System.out.println("No available position.");
            return null;
        }
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = new Ticket(1, car, parkingLot);
        ticketCars.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        if (!ticketCars.containsKey(ticket)) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }
        return ticketCars.remove(ticket);
    }
}

