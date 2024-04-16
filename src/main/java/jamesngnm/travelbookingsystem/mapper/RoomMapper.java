package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.request.CreateRoomRequest;
import jamesngnm.travelbookingsystem.model.response.BookedDateResponse;
import jamesngnm.travelbookingsystem.model.response.RoomByTypeResponse;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<RoomByTypeResponse> toRoomByTypeResponse(List<RoomEntity> roomEntityList) {
        List<RoomByTypeResponse> list = new ArrayList<>();
        HashMap<RoomType, RoomByTypeResponse> map = new HashMap<>();
        for (RoomEntity r : roomEntityList) {
            RoomType type = r.getType();
            double price = r.getPrice();
            if (map.containsKey(type)) {
                RoomByTypeResponse roomByTypeResponse = map.get(type);
                roomByTypeResponse.setAvailableRooms(roomByTypeResponse.getAvailableRooms() + 1);
                roomByTypeResponse.setPrice(price);
            } else {
                RoomByTypeResponse roomByTypeResponse = new RoomByTypeResponse(type, 1, price);
                map.put(type, roomByTypeResponse);
                list.add(roomByTypeResponse);
            }
        }
        return list;
    }

    public RoomEntity toRoomEntity(CreateRoomRequest roomRequest, HotelEntity hotel) {
        RoomEntity room = new RoomEntity();
        room.setHotel(hotel);
        room.setType(roomRequest.getRoomType());
        room.setPrice(roomRequest.getPrice());
        room.setName(roomRequest.getName());
        room.setBookedDates(new ArrayList<>());

        return room;
    }
}
