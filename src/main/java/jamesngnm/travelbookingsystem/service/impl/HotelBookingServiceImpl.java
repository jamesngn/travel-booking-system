package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.CreateRoomBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.util.List;

public class HotelBookingServiceImpl implements HotelBookingService {
    private HotelBookingDAO hotelBookingDAO;
    private RoomBookingService roomBookingService;
    private RoomService roomService;


    private HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingService = new RoomBookingServiceImpl();
        this.roomService = new RoomServiceImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        List<Long> roomIdsToBook = request.getRoomIds();
        roomIdsToBook.forEach(roomId -> {
            RoomEntity room = roomService.getRoomById(roomId);
            roomBookingService.reserveRoom(room, hotelBookingEntity);
        });
        //TODO: add logic to handle availability of room at hotel

        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }

}
