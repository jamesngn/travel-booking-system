package jamesngnm.travelbookingsystem.entity;

import java.util.List;

public class Booking {
    private Customer customer;
    private List<FlightEntity> flights;
    private HotelEntity hotel;
    private double totalCost;

//    public boolean makeBooking(Customer customer, List<FlightEntity> flights, HotelEntity hotel) {
//        this.customer = customer;
//        this.flights = flights;
//        this.hotel = hotel;
//
//        boolean seatsAvailable = true;
//        boolean roomAvailable = hotel.isAvailable(hotel.getLocation(), flights.get(0).getDepartureTime(), flights.get(flights.size() - 1).getDepartureTime());
//
//        for (FlightEntity flight : flights) {
//            if (!flight.isAvailable(flight.getDestination(), flight.getDepartureTime(), flight.getDepartureTime())) {
//                seatsAvailable = false;
//                break;
//            }
//        }
//
//        if (seatsAvailable && roomAvailable) {
//            calculateTotalCost();
//            // Process payment
//            return true;
//        } else {
//            // Rollback any booked seats or rooms
//            return false;
//        }
//    }

    private void calculateTotalCost() {
//        double flightCost = 0;
//        for (FlightEntity flight : flights) {
//            flightCost += flight.getPrice();
//        }
//        totalCost = flightCost + hotel.getPrice();
    }

    // Getters and setters
}

