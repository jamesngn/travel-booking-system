package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.mapper.HotelMapper;
import jamesngnm.travelbookingsystem.model.request.GetHotelDetailsRequest;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.HotelDetailResponse;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private final HotelDAO hotelDAO;
    private final RoomDAO roomDAO;

    private final HotelMapper hotelMapper;

    public HotelServiceImpl() {
        this.hotelDAO = new HotelDAOImpl();
        this.hotelMapper = new HotelMapper();
        this.roomDAO =  new RoomDAOImpl();
    }

    @Override
    public List<SearchHotelResponse> searchHotel(SearchHotelRequest searchHotelRequest) {
        List<HotelEntity> hotels = hotelDAO.searchHotel(searchHotelRequest);
        return hotels.stream()
                .map(hotelMapper::toSearchHotelResponse)
                .collect(Collectors.toList());
    }

    @Override
    public HotelEntity getHotelEntityById(Long hotelId) {
        return hotelDAO.getHotelDetail(hotelId);
    }

    @Override
    public HotelDetailResponse getHotelDetail(GetHotelDetailsRequest getHotelDetailsRequest) {
        HotelEntity hotel = hotelDAO.getHotelDetail(getHotelDetailsRequest.getHotelId());


        SearchAvailableRoomsRequest request = new SearchAvailableRoomsRequest(getHotelDetailsRequest.getHotelId(), getHotelDetailsRequest.getCheckInDate(), getHotelDetailsRequest.getCheckOutDate());


        List<RoomEntity> availableRooms = roomDAO.searchAvailableRooms(request);
        hotel.setRooms(availableRooms);

        return hotelMapper.toHotelDetailResponse(hotel);
    }
}
