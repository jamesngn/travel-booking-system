package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.FlightDAO;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.service.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {
    private final GenericDAO<Flight> flightDAO;

    public FlightServiceImpl() {
        this.flightDAO = new FlightDAO();
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightDAO.create(flight);
    }

    @Override
    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime) {
        List<Flight> allFlights = flightDAO.readAll();
        List<Flight> availableFlights = new ArrayList<>();

        for (Flight flight: allFlights) {
            if (flight.getOrigin().equalsIgnoreCase(origin)
                    && flight.getDestination().equalsIgnoreCase(destination)
                    && flight.getDepartureTime().isEqual(departureTime)
                    && flight.getAvailableSeats() > 0) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    @Override
    public List<Flight> getFlightsByIds(List<Long> flightIds) {
        List<Flight> flights = new ArrayList<>();
        for (Long id: flightIds) {
            flights.add(flightDAO.read(id));
        }
        return flights;
    }
}
