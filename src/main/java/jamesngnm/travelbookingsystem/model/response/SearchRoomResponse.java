package jamesngnm.travelbookingsystem.model.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRoomResponse {
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private double price;
    private boolean available;
    private Long hotelId;
    private String location;
    private List<BookedDateResponse> bookedDates;
}
