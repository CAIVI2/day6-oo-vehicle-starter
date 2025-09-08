package com.afs;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

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
        return IntStream.rangeClosed(1, capacity).boxed()
                .filter(position -> ticketCars.keySet().stream().noneMatch(ticket -> ticket.position() == position))
                .findFirst()
                .map(position -> {
                    Ticket ticket = new Ticket(position, car, this);
                    ticketCars.put(ticket, car);
                    return ticket;
                })
                .orElse(null);
    }

    public Car fetch(Ticket ticket) {
        if (!ticketCars.containsKey(ticket)) {
            System.out.println("Unrecognized parking ticket.");
            return null;
        }
        return ticketCars.remove(ticket);
    }
}

