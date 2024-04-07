package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;

import java.util.List;

public interface HotelDAO {
    List<HotelEntity> searchHotel(SearchHotelRequest searchHotelRequest);
}
