package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SearchAvailableRoomsRequest {
    private Long hotelId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private String location;
    private double minPrice;
    private double maxPrice;
    private String type;
    private int quantity;

    public SearchAvailableRoomsRequest(Long hotelId, String checkInDate, String checkOutDate) {
        this.hotelId = hotelId;
        this.checkInDate = LocalDateTime.parse(checkInDate);
        this.checkOutDate = LocalDateTime.parse(checkOutDate);
    }

    public SearchAvailableRoomsRequest(Long hotelId, LocalDateTime checkInDate, LocalDateTime checkOutDate, String type, int quantity) {
        this.hotelId = hotelId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.type = type;
        this.quantity = quantity;
    }
}
