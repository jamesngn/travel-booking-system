package jamesngnm.travelbookingsystem.model.response;

import java.time.LocalDateTime;

public class SearchFlightResponse {
    private Long id;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private Integer availableSeats;
    private Double price;

    public SearchFlightResponse() {
    }

    public SearchFlightResponse(Long id, String origin, String destination, LocalDateTime departureTime, Integer availableSeats, Double price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.availableSeats = availableSeats;
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public Integer getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
