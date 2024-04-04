package jamesngnm.travelbookingsystem.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO implements GenericDAO<Flight> {
    private EntityManagerFactory emf;

    public FlightDAO() {

        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }

    @Override
    public void create(Flight flight) {
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
    public Flight read(long id) {
        return null;
    }

    @Override
    public Flight update(Flight flight) {
        return null;
    }

    @Override
    public Flight delete(long id) {
        return null;
    }

    @Override
    public List<Flight> readAll() {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM flights")) {
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getLong("id"));
                flight.setOrigin(resultSet.getString("origin"));
                flight.setDestination(resultSet.getString("destination"));
                flight.setDepartureTime(resultSet.getTimestamp("departure_time").toLocalDateTime());
                flight.setAvailableSeats(resultSet.getInt("available_seats"));
                flight.setPrice(resultSet.getDouble("price"));
                flights.add(flight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void close() {
        emf.close();
    }
}
