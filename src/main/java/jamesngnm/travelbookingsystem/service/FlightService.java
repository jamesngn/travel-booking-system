package jamesngnm.travelbookingsystem.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.entity.Flight;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    void createFlight(HttpServletRequest request, HttpServletResponse response) throws IOException;
    List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime);
    List<Flight> getFlightsByIds(List<Long> flightIds);

}
