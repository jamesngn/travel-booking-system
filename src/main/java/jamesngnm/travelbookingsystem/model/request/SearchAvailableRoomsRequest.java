package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SearchAvailableRoomsRequest {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String location;
    private double minPrice;
    private double maxPrice;
    private String type;
}
