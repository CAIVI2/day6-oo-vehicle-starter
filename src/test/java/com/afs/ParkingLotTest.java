package com.afs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void should_return_the_right_car_with_each_ticket_when_fetch_the_car_twice_given_a_parking_lot_with_two_parked_car_and_two_parking_tickets() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(10);

        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
        Car fetchedCar1 = parkingLot.fetch(ticket1);
        Car fetchedCar2 = parkingLot.fetch(ticket2);

        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    public void should_return_nothing_when_fetch_the_car_given_a_parking_lot_and_a_wrong_parking_ticket() {
        Car car = new Car("C0");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(10);
        Ticket wrongTicket = new Ticket(1, car2, parkingLot);

        Ticket rightTicket = parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(wrongTicket);

        assertNull(fetchedCar);
    }

    @Test
    public void should_return_nothing_when_fetch_the_car_given_a_parking_lot_and_a_used_parking_ticket() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);

        Ticket ticket = parkingLot.park(car);
        Car fetchedCar1 = parkingLot.fetch(ticket);
        Car fetchedCar2 = parkingLot.fetch(ticket);

        assertEquals(car, fetchedCar1);
        assertNull(fetchedCar2);
    }

    @Test
    public void should_return_nothing_when_park_the_car_given_a_parking_lot_without_any_position() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(1);

        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);

        assertNotNull(ticket1);
        assertNull(ticket2);
    }
}

