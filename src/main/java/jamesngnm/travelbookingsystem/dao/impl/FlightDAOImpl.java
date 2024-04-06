package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.dao.FlightDAO;
import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;

import java.time.LocalDateTime;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    private final EntityManagerFactory emf;

    public FlightDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }

    @Override
    public FlightEntity create(FlightEntity flight) {
        checkFlightAlreadyExists(flight); // Validate flight before persisting to database

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            // Handle exception
        } finally {
            em.close();
        }

        return flight;
    }

    public List<FlightEntity> searchFlights(SearchFlightRequest request) {
        if (request.getOrigin() != null && request.getDestination() != null && request.getDepartureTime() != null) {
            return searchFlightsByOriginDestinationAndDepartureTime(request);
        } else if (request.getOrigin() != null && request.getDestination() != null) {
            return searchFlightsByOriginAndDestination(request.getOrigin(), request.getDestination(), request.getPageNumber(), request.getPageSize());
        } else if (request.getOrigin() != null && request.getDepartureTime() != null) {
            return searchFlightsByOriginAndDepartureTime(request.getOrigin(), request.getDepartureTime(), request.getPageNumber(), request.getPageSize());
        } else if (request.getOrigin() != null) {
            return searchFlightsByOriginOnly(request.getOrigin(), request.getPageNumber(), request.getPageSize());
        } else {
            throw new ResponseException(BadRequestError.SEARCH_FLIGHT_REQUEST_INVALID);
        }
    }

    private List<FlightEntity> searchFlightsByOriginOnly(String origin, int pageNumber, int pageSize) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FlightEntity> query = em.createQuery("SELECT f FROM FlightEntity f WHERE f.origin = :origin AND f.availableSeats > 0", FlightEntity.class);
            query.setParameter("origin", origin);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private List<FlightEntity> searchFlightsByOriginAndDepartureTime(String origin, LocalDateTime departureTime, int pageNumber, int pageSize) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FlightEntity> query = em.createQuery("SELECT f FROM FlightEntity f WHERE f.origin = :origin AND f.departureTime = :departureTime AND f.availableSeats > 0", FlightEntity.class);
            query.setParameter("origin", origin);
            query.setParameter("departureTime", departureTime);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }


    private List<FlightEntity> searchFlightsByOriginAndDestination(String origin, String destination, int pageNumber, int pageSize) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FlightEntity> query = em.createQuery("SELECT f FROM FlightEntity f WHERE f.origin = :origin AND f.destination = :destination AND f.availableSeats > 0", FlightEntity.class);
            query.setParameter("origin", origin);
            query.setParameter("destination", destination);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private List<FlightEntity> searchFlightsByOriginDestinationAndDepartureTime(SearchFlightRequest request) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FlightEntity> query = em.createQuery("SELECT f FROM FlightEntity f WHERE f.origin = :origin AND f.destination = :destination AND f.departureTime = :departureTime AND f.availableSeats > 0", FlightEntity.class);
            query.setParameter("origin", request.getOrigin());
            query.setParameter("destination", request.getDestination());
            query.setParameter("departureTime", request.getDepartureTime());

            int pageNumber = request.getPageNumber();
            int pageSize = request.getPageSize();
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private void checkFlightAlreadyExists(FlightEntity flight) {
        // Check if flight is already in the database by checking origin, destination, and departure time
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FlightEntity> query = em.createQuery("SELECT f FROM FlightEntity f WHERE f.origin = :origin AND f.destination = :destination AND f.departureTime = :departureTime", FlightEntity.class);
            query.setParameter("origin", flight.getOrigin());
            query.setParameter("destination", flight.getDestination());
            query.setParameter("departureTime", flight.getDepartureTime());

            List<FlightEntity> existingFlights = query.getResultList();
            if (!existingFlights.isEmpty()) {
                throw new ResponseException(BadRequestError.FLIGHT_ALREADY_EXISTS);
            }
        } finally {
            em.close();
        }
    }

    public void close() {
        emf.close();
    }
}
