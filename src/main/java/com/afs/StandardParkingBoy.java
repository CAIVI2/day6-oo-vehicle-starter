package com.afs;

public class StandardParkingBoy {
    private ParkingLot parkingLot;

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }
}