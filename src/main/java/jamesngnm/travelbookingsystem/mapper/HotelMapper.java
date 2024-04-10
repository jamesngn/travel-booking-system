package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.response.HotelDetailResponse;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor
public class HotelMapper {
    private final RoomMapper roomMapper = new RoomMapper();
    public SearchHotelResponse toSearchHotelResponse(HotelEntity hotel) {
        SearchHotelResponse searchHotelResponse = new SearchHotelResponse();
        searchHotelResponse.setId(hotel.getId());
        searchHotelResponse.setLocation(hotel.getLocation());
        searchHotelResponse.setName(hotel.getName());
        searchHotelResponse.setAvailableRooms(hotel.getAvailableRooms());

        return searchHotelResponse;
    }

    public HotelDetailResponse toHotelDetailResponse(HotelEntity hotel) {
        HotelDetailResponse hotelDetailResponse = new HotelDetailResponse();
        hotelDetailResponse.setHotel(toSearchHotelResponse(hotel));
        hotelDetailResponse.setRooms(roomMapper.toRoomByTypeResponse(hotel.getRooms()));

        return hotelDetailResponse;
    }
}

// TODO: check logic of returning the rooms (check bookedDate) in getting hotel detail. That means need to change the request to include checkin and check out date