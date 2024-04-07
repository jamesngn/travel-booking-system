package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HotelMapper {
    public SearchHotelResponse toSearchHotelResponse(HotelEntity hotel) {
        SearchHotelResponse searchHotelResponse = new SearchHotelResponse();
        searchHotelResponse.setId(hotel.getId());
        searchHotelResponse.setLocation(hotel.getLocation());
        searchHotelResponse.setName(hotel.getName());
        searchHotelResponse.setAvailableRooms(hotel.getAvailableRooms());
        searchHotelResponse.setRooms(hotel.getRooms());
        return searchHotelResponse;
    }
}
