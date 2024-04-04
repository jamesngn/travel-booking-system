package jamesngnm.travelbookingsystem.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.dao.FlightDAO;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.service.FlightService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {
    private GenericDAO<Flight> flightDAO;

    public FlightServiceImpl() {
        flightDAO = new FlightDAO();
    }

    @Override
    public void createFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
        int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
        double price = Double.parseDouble(request.getParameter("price"));

        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departureTime);
        flight.setAvailableSeats(availableSeats);
        flight.setPrice(price);

        flightDAO.create(flight);

        response.setContentType("text/plain");
        response.getWriter().write("Flight created successfully");
    }

    @Override
    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime) {
        return null;
    }

    @Override
    public List<Flight> getFlightsByIds(List<Long> flightIds) {
        return null;
    }


}
