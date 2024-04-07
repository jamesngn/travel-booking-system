package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.CreateRoomBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;

import java.util.List;

public class HotelBookingServiceImpl implements HotelBookingService {
    private HotelBookingDAO hotelBookingDAO;
    private RoomBookingDAO roomBookingDAO;
    private RoomDAO roomDAO;


    private HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingDAO = new RoomBookingDAOImpl();
        this.roomDAO = new RoomDAOImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        List<Long> roomIdsToBook = request.getRoomIds();
        roomIdsToBook.forEach(roomId -> {
            RoomEntity room = roomDAO.searchRoomById(roomId);
            roomBookingDAO.createRoomBooking(room, hotelBookingEntity);
        });
        //TODO: add logic to handle availability of room at hotel

        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }

}
