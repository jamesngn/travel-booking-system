package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomByTypeResponse {
    private RoomType type;
    private int availableRooms;
    private double price;
}
