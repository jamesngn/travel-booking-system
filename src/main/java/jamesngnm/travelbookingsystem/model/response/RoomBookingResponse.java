package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingResponse {
    private Long id;
    private String name;
    private double price;
    private RoomType type;
    private Long hotelId;
}
