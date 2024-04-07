package jamesngnm.travelbookingsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightResponse {
    private Long id;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private int availableSeats;
    private double price;
}
