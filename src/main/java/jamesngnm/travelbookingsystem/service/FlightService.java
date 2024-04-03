package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    Flight createFlight(Flight flight);
    List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime);
    List<Flight> getFlightsByIds(List<Long> flightIds);

}
