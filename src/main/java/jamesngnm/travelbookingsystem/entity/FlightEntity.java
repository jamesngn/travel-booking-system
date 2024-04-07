package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


//TO DO: extends from TravelComponent and see if it will create the table automatically or not

@Entity
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String origin;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false, name = "departure_time")
    private LocalDateTime departureTime;
    @Column(nullable = false, name = "available_seats")
    private int availableSeats;
    @Column(nullable = false)
    private Double price;


    public boolean isAvailable(String location, LocalDateTime startDate, LocalDateTime endDate) {
        return false;
    }

}