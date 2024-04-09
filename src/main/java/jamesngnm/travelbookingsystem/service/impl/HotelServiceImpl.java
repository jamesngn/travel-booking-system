package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.mapper.HotelMapper;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import jamesngnm.travelbookingsystem.service.HotelService;

import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;

    private final HotelMapper hotelMapper;

    public HotelServiceImpl() {
        this.hotelDAO = new HotelDAOImpl();
        this.hotelMapper = new HotelMapper();
    }

    @Override
    public List<SearchHotelResponse> searchHotel(SearchHotelRequest searchHotelRequest) {
        List<HotelEntity> hotels = hotelDAO.searchHotel(searchHotelRequest);
        return hotels.stream()
                .map(hotelMapper::toSearchHotelResponse)
                .collect(Collectors.toList());
    }
}
