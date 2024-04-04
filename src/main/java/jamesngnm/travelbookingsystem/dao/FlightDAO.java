package jamesngnm.travelbookingsystem.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO implements GenericDAO<Flight> {
    @Override
    public Flight create(Flight flight) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO flights (origin, destination, departure_time, available_seats, price) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, flight.getOrigin());
            preparedStatement.setString(2, flight.getDestination());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(flight.getDepartureTime()));
            preparedStatement.setInt(4, flight.getAvailableSeats());
            preparedStatement.setDouble(5, flight.getPrice());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    flight.setId(resultSet.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
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
}
