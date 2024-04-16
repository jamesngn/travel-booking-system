package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomRequest {
    private String name;
    private RoomType roomType;
    private double price;
    private Long hotelId;
}
