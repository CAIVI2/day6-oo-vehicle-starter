package com.afs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {
    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_parking_lot_and_a_car() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    public void should_return_the_packed_car_when_fetch_the_car_given_a_parking_lot_with_a_parked_car() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket ticket = parkingLot.park(car);

        Car fetchedCar = parkingLot.fetch(ticket);

        assertEquals(car, fetchedCar);
    }
}

