package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomByTypeRequest {
    private String type;
    private int quantity;
}
