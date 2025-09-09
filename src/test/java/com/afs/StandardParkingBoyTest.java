package com.afs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
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
        Car fetchedCar = null;
        String errorMessage = null;
        try {
            fetchedCar = boy.fetch(wrongTicket);
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();
        }

        assertNull(fetchedCar);
        assertEquals("Unrecognized parking ticket.", errorMessage);
    }

    @Test
    void should_return_nothing_with_error_message_when_fetch_the_car_given_a_parking_lot_and_a_standard_parking_boy_and_a_used_ticket() {
        Car car = new Car("C0");
        ParkingLot parkingLot = new ParkingLot(10);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket = boy.park(car);
        Car fetchedCar1 = null;
        String errorMessage1 = null;
        try {
            fetchedCar1 = boy.fetch(ticket);
        } catch (RuntimeException e) {
            errorMessage1 = e.getMessage();
        }
        Car fetchedCar2 = null;
        String errorMessage2 = null;
        try {
            fetchedCar2 = boy.fetch(ticket);
        } catch (RuntimeException e) {
            errorMessage2 = e.getMessage();
        }

        assertNotNull(fetchedCar1);
        assertNull(errorMessage1);
        assertNull(fetchedCar2);
        assertEquals("Unrecognized parking ticket.",  errorMessage2);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_the_car_given_a_parking_lot_without_any_position_and_a_standard_parking_boy_and_a_car() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot parkingLot = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(parkingLot);

        Ticket ticket1 = null;
        String errorMessage1 = null;
        try {
            ticket1 = boy.park(car1);
        } catch (Exception e) {
            errorMessage1 = e.getMessage();
        }
        Ticket ticket2 = null;
        String errorMessage2 = null;
        try {
            ticket2 = boy.park(car2);
        } catch (Exception e) {
            errorMessage2 = e.getMessage();
        }

        assertNotNull(ticket1);
        assertNull(errorMessage1);
        assertNull(ticket2);
        assertEquals("No available position.", errorMessage2);
    }

    @Test
    void should_park_to_first_parking_lot_when_park_the_car_given_a_standard_parking_boy_who_manage_two_parking_lots_both_with_available_position_and_a_car() {
        Car car = new Car("C0");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));

        Ticket ticket = boy.park(car);
        assertNotNull(ticket);
        assertTrue(lot1.fetch(ticket) != null);
        assertTrue(lot2.fetch(ticket) == null);
    }

    @Test
    void should_park_to_second_parking_lot_when_park_the_car_given_a_standard_parking_boy_who_manage_two_parking_lots_first_is_full_and_second_with_available_position() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));

        boy.park(car1);
        Ticket ticket2 = boy.park(car2);

        assertNotNull(ticket2);
        assertTrue(lot1.fetch(ticket2) == null);
        assertTrue(lot2.fetch(ticket2) != null);
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_the_car_twice_given_a_standard_parking_boy_who_manage_two_parking_lots_both_with_a_parked_car_and_two_parking_ticket() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));

        Ticket ticket1 = boy.park(car1);
        Ticket ticket2 = boy.park(car2);
        Car fetchedCar1 = boy.fetch(ticket1);
        Car fetchedCar2 = boy.fetch(ticket2);

        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing_with_error_message_when_fetch_the_car_given_a_standard_parking_boy_who_manage_two_parking_lots_and_an_unrecognized_ticket() {
        Car car = new Car("C0");
        Car car2 = new Car("C2");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));
        Ticket wrongTicket = new Ticket(1, car2, lot2);

        boy.park(car);
        Car fetchedCar = null;
        String errorMessage = null;
        try {
            fetchedCar = boy.fetch(wrongTicket);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertNull(fetchedCar);
        assertEquals("Unrecognized parking ticket.", errorMessage);
    }

    @Test
    void should_return_nothing_with_error_message_when_fetch_the_car_given_a_standard_parking_boy_who_manage_two_parking_lots_and_an_used_ticket() {
        Car car = new Car("C0");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));

        Ticket ticket = boy.park(car);
        Car fetchedCar1 = null;
        String errorMessage1 = null;
        try {
            fetchedCar1 = boy.fetch(ticket);
        } catch (Exception e) {
            errorMessage1 = e.getMessage();
        }
        Car fetchedCar2 = null;
        String errorMessage2 = null;
        try {
            fetchedCar2 = boy.fetch(ticket);
        } catch (Exception e) {
            errorMessage2 = e.getMessage();
        }

        assertNotNull(fetchedCar1);
        assertNull(errorMessage1);
        assertNull(fetchedCar2);
        assertEquals("Unrecognized parking ticket.", errorMessage2);
    }

    @Test
    void should_return_nothing_with_error_message_when_park_the_car_given_a_standard_parking_boy_who_manage_two_parking_lots_both_without_any_position_and_a_car() {
        Car car1 = new Car("C1");
        Car car2 = new Car("C2");
        Car car3 = new Car("C3");
        ParkingLot lot1 = new ParkingLot(1);
        ParkingLot lot2 = new ParkingLot(1);
        StandardParkingBoy boy = new StandardParkingBoy(Arrays.asList(lot1, lot2));

        boy.park(car1);
        boy.park(car2);
        Ticket ticket3 = null;
        String errorMessage3 = null;
        try {
            ticket3 = boy.park(car3);
        } catch (Exception e) {
            errorMessage3 = e.getMessage();
        }

        assertNull(ticket3);
        assertEquals("No available position.", errorMessage3);
    }
}