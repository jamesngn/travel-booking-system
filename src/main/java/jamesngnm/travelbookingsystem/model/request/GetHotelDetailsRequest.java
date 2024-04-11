package jamesngnm.travelbookingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetHotelDetailsRequest {
    private Long hotelId;
    private String checkInDate;
    private String checkOutDate;
}
