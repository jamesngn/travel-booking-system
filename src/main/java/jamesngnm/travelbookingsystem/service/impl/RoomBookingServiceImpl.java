package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.RoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.util.Objects;

public class RoomBookingServiceImpl implements RoomBookingService {
    private RoomBookingDAO roomBookingDAO;
    private RoomService roomService;

    public RoomBookingServiceImpl() {
        this.roomService = new RoomServiceImpl();
        this.roomBookingDAO = new RoomBookingDAOImpl();
    }

    @Override
    public void reserveRoom(Long roomId, HotelBookingEntity hotelBooking) {
        // Reserve room
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID not found");
        }
        if (hotelBooking == null) {
            throw new IllegalArgumentException("Hotel booking not found");
        }

        RoomEntity room = roomService.getRoomById(roomId);

        if (!Objects.equals(room.getHotel().getId(), hotelBooking.getHotel().getId())) {
            throw new IllegalArgumentException("Room does not belong to hotel");
        }

        roomBookingDAO.createRoomBooking(room, hotelBooking);
        roomService.addRoomBookedDate(room, hotelBooking.getCheckinDate(), hotelBooking.getCheckoutDate());
    }
}
