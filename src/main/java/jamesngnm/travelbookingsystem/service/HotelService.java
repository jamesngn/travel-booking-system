package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.HotelDetailResponse;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;

import java.util.List;

public interface HotelService {
    List<SearchHotelResponse> searchHotel(SearchHotelRequest searchHotelRequest);
    HotelDetailResponse getHotelDetail(Long hotelId);
}
