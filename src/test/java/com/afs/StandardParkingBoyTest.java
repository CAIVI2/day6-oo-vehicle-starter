package com.afs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

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

}