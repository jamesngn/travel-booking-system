package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;

import java.util.List;

public interface HotelService {
    List<SearchHotelResponse> searchHotels(SearchHotelRequest searchHotelRequest);

    SearchHotelResponse searchHotelById(Long hotelId);

}
