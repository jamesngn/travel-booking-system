package jamesngnm.travelbookingsystem.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.dao.FlightDAO;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.service.FlightService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightServiceImpl implements FlightService {
    private EntityManagerFactory emf;

    public FlightServiceImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }

    @Override
    public void createFlight(Flight flight) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(flight);
            System.out.println("Added flight: " + flight.toString());
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Handle exception
        } finally {
            em.close();
        }

    }

    @Override
    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureTime) {
        return null;
    }

    @Override
    public List<Flight> getFlightsByIds(List<Long> flightIds) {
        return null;
    }

    public void close() {
        emf.close();
    }
}
