package jamesngnm.travelbookingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHotelRequest {
    private String location;
    private String name;
    private List<CreateRoomRequest> rooms;
}
