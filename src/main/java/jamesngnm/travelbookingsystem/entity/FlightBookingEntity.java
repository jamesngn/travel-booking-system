package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight_bookings")
public class FlightBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;


    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @OneToMany(mappedBy = "flightBooking", cascade = CascadeType.ALL)
    private List<PassengeEntity> passengers;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;
}
