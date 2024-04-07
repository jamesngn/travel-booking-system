package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotel_bookings")
public class HotelBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotelBooking", fetch = FetchType.LAZY)
    private List<RoomBookingEntity> roomBookings;


    @Column(name = "check_in_date")
    private LocalDateTime checkinDate;

    @Column(name = "check_out_date")
    private LocalDateTime checkoutDate;

    public void addRoomBooking(RoomBookingEntity roomBooking) {
        if (roomBookings == null) {
            roomBookings = new ArrayList<>();
        }
        roomBookings.add(roomBooking);
    }
}