package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;

public class HotelMapper {

    public HotelMapper() {

    }

    public SearchHotelResponse toSearchHotelResponse(HotelEntity hotel) {
        // Map HotelEntity to SearchHotelResponse
        SearchHotelResponse searchHotelResponse = new SearchHotelResponse();
        searchHotelResponse.setId(hotel.getId());
        searchHotelResponse.setDescription(hotel.getDescription());
        searchHotelResponse.setAddress(hotel.getAddress());
        searchHotelResponse.setLocation(hotel.getLocation());
        searchHotelResponse.setName(hotel.getName());
        searchHotelResponse.setAvailableRooms(hotel.getAvailableRooms());
        searchHotelResponse.setRooms(hotel.getRooms());

        return searchHotelResponse;
    }
}
