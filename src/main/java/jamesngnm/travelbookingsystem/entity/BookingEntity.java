package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<FlightBookingEntity> flightBookings;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<HotelBookingEntity> hotelBookings;

}

//TODO: need user first => need to do registration, login
//TODO: create UI on react