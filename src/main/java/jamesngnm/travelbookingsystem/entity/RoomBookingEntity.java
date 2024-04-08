package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_bookings")
public class RoomBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "hotel_booking_id")
    private HotelBookingEntity hotelBooking;

    public String toString() {
        return "RoomBookingEntity{id=" + this.id + ", room=" + this.room.getName() + ", hotelBooking=" + this.hotelBooking.getId() + "}";
    }
}
