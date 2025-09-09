package com.afs;

import java.util.Arrays;
import java.util.List;

public class StandardParkingBoy {
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = Arrays.asList(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.park(car))
                .filter(ticket -> ticket != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No available position."));
    }

    public Car fetch(Ticket ticket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(ticket))
                .filter(car -> car != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unrecognized parking ticket."));
    }
}