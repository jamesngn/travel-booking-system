package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.RoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.util.Objects;

public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingDAO roomBookingDAO;
    private final RoomService roomService;

    public RoomBookingServiceImpl(RoomBookingDAO roomBookingDAO, RoomService roomService) {
        this.roomBookingDAO = roomBookingDAO;
        this.roomService = roomService;
    }

    public RoomBookingServiceImpl() {
        this.roomService = new RoomServiceImpl();
        this.roomBookingDAO = new RoomBookingDAOImpl();
    }

    @Override
    public void reserveRoom(Long roomId, HotelBookingEntity hotelBooking) {
        // Reserve room
        if (roomId == null) {
            throw new ResponseException(BadRequestError.ROOM_ID_INVALID);
        }
        if (hotelBooking == null) {
            throw new ResponseException(BadRequestError.HOTEL_BOOKING_NOT_FOUND);
        }

        RoomEntity room = roomService.getRoomById(roomId);

        if (!Objects.equals(room.getHotel().getId(), hotelBooking.getHotel().getId())) {
            throw new ResponseException(BadRequestError.ROOM_NOT_BELONG_TO_HOTEL);
        }

        roomBookingDAO.createRoomBooking(room, hotelBooking);
        roomService.addRoomBookedDate(room, hotelBooking.getCheckinDate(), hotelBooking.getCheckoutDate());
    }
}
