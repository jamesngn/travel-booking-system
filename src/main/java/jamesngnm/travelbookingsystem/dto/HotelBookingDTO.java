package jamesngnm.travelbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingDTO {
    private Long id;
    private Long hotelId;
    private String hotelName;
    private Long bookingId;
    private List<RoomBookingDTO> roomBookings;
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
}
