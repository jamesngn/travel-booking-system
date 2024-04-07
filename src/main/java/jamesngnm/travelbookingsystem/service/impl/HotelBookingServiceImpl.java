package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.CreateRoomBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;

public class HotelBookingServiceImpl implements HotelBookingService {
    private HotelBookingDAO hotelBookingDAO;
    private RoomBookingDAO roomBookingDAO;

    private HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingDAO = new RoomBookingDAOImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        for (Long roomId : request.getRoomIds()) {
            roomBookingDAO.createRoomBooking(roomId, hotelBookingEntity.getId());
        }

        HotelBookingResponse hotelBookingResponse = hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
        return hotelBookingResponse;
    }

    @Override
    public void createRoomBooking(CreateRoomBookingRequest request) {

    }
}
