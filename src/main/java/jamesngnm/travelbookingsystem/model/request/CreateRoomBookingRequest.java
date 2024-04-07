package jamesngnm.travelbookingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomBookingRequest {
    private Long roomId;
    private Long hotelBookingId;
}
