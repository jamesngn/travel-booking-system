package jamesngnm.travelbookingsystem.entity;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private List<Flight> flights;
    private List<Hotel> hotels;

    public BookingService() {
        // Initialize flights and hotels
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime) {
        // Search and return available flights
        return null;
    }

    public List<Hotel> searchHotels(String location, LocalDateTime startDate, LocalDateTime endDate) {
        // Search and return available hotels
        return null;
    }

    public Booking makeBooking(Customer customer, List<Flight> flights, Hotel hotel) {
        Booking booking = new Booking();
        if (booking.makeBooking(customer, flights, hotel)) {
            // Persist booking
            return booking;
        }
        return null;
    }
}
