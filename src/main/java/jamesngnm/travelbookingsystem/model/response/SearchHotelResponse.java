package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHotelResponse {
    private Long id;

    private String location;

    private String name;

    private int availableRooms;

    private List<SearchRoomResponse> rooms;
}
