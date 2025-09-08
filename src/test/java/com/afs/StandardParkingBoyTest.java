package com.afs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_parking_lot_and_a_standard_parking_boy_and_a_car() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket = boy.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_the_parked_car_when_fetch_the_car_given_a_parking_lot_with_a_parked_car_and_a_standard_parking_boy_and_a_ticket() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);
        Ticket ticket = boy.park(car);

        Car fetchedCar = boy.fetch(ticket);

        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_the_car_twice_given_a_parking_lot_with_two_parked_cars_and_a_standard_parking_boy_and_two_parking_tickets() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket1 = boy.park(car1);
        Ticket ticket2 = boy.park(car2);
        Car fetchedCar1 = boy.fetch(ticket1);
        Car fetchedCar2 = boy.fetch(ticket2);

        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_with_error_message_when_fetch_the_car_given_a_parking_lot_and_a_standard_parking_boy_and_a_wrong_parking_ticket() {
        Car car = new Car("C0");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);
        Ticket wrongTicket = new Ticket(1, car2, parkingLot);

        boy.park(car);
        Car fetchedCar = boy.fetch(wrongTicket);

        assertNull(fetchedCar);
        assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }

    @Test
    void should_return_nothing_with_error_message_when_fetch_the_car_given_a_parking_lot_and_a_standard_parking_boy_and_a_used_ticket() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket = boy.park(car);
        Car fetchedCar1 = boy.fetch(ticket);
        Car fetchedCar2 = boy.fetch(ticket);

        assertNotNull(fetchedCar1);
        assertNull(fetchedCar2);
        assertTrue(outputStream.toString().contains("Unrecognized parking ticket."));
    }

    @Test
    void should_return_nothing_with_error_message_when_park_the_car_given_a_parking_lot_without_any_position_and_a_standard_parking_boy_and_a_car() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket1 = boy.park(car1);
        Ticket ticket2 = boy.park(car2);

        assertNotNull(ticket1);
        assertNull(ticket2);
        assertTrue(outputStream.toString().contains("No available position."));
    }
}