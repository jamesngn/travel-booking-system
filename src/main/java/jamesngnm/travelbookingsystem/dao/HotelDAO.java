package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;

import java.util.List;

public interface HotelDAO {
    void createHotel(HotelEntity hotel);
    List<HotelEntity> searchHotel(SearchHotelRequest searchHotelRequest);
    HotelEntity getHotelDetail(Long hotelId);
}
