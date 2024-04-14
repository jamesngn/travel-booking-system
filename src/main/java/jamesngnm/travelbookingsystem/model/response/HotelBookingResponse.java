package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingResponse {
    private Long hotelId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private List<RoomBookingResponse> rooms;
}
