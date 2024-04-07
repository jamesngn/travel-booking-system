package jamesngnm.travelbookingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelBookingRequest {
    private Long bookingId;
    private Long hotelId;
    private Long userId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private List<Long> roomIds;
}
