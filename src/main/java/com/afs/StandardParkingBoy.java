package com.afs;

public class StandardParkingBoy {

    public StandardParkingBoy(ParkingLot parkingLot) {

    }

    public Ticket park(Car car) {
        return new Ticket(1, car, new ParkingLot(10));
    }
}