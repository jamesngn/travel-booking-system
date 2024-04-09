package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.response.BookedDateResponse;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RoomMapper {
    private final BookedDateMapper bookedDateMapper = new BookedDateMapper();
    public SearchRoomResponse toResponse(RoomEntity r) {
        Long id = r.getId();
        String name = r.getName();
        RoomType type = r.getType();
        double price = r.getPrice();
        boolean available = r.isAvailable();
        Long hotelId = r.getHotel().getId();
        String location = r.getHotel().getLocation();
        List<BookedDateResponse> list = bookedDateMapper.toResponses(r.getBookedDates());

        return new SearchRoomResponse(id, name, type, price, available, hotelId, location, list);
    }
}
