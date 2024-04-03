package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.model.enums.FlightStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight extends TravelComponent {
    @Id
    private Long id;
    @Column
    private String origin;
    @Column
    private String destination;
    @Column(name= "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "available_seats")
    private int availableSeats;
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    public Flight() {
    }

    public Flight(String origin, String destination, LocalDateTime departureTime, int availableSeats, double price) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.setPrice(price);
    }


    @Override
    public boolean isAvailable(String location, LocalDateTime startDate, LocalDateTime endDate) {
        return false;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

}