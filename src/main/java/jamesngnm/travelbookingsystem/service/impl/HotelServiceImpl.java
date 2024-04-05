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
    private HotelMapper hotelMapper;
    private HotelDAO hotelDAO;

    public HotelServiceImpl() {
        hotelMapper = new HotelMapper();
        hotelDAO = new HotelDAOImpl();
    }

    @Override
    public List<SearchHotelResponse> searchHotels(SearchHotelRequest searchHotelRequest) {
        List<HotelEntity> hotels = hotelDAO.searchHotels(searchHotelRequest);

        return hotels.stream()
                .map(hotelMapper::toSearchHotelResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SearchHotelResponse searchHotelById(Long hotelId) {
        return null;
    }
}
