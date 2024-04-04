package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


//TO DO: extends from TravelComponent and see if it will create the table automatically or not

@Entity
@Table(name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDateTime departureTime;
    @Column(nullable = false)
    private int availableSeats;
    @Column(nullable = false)
    private Double price;

    public FlightEntity() {
    }

    public FlightEntity(String origin, String destination, LocalDateTime departureTime, int availableSeats, double price) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

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

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

}