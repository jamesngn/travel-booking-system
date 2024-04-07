package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotelBookingResponse {
    private Long hotelId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private List<RoomEntity> rooms;

    public void addRoom(RoomEntity room) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }
        this.rooms.add(room);
    }
}
