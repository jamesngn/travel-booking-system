package jamesngnm.travelbookingsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HotelDetailResponse {
    private SearchHotelResponse hotel;
    private List<RoomByTypeResponse> rooms;
}
