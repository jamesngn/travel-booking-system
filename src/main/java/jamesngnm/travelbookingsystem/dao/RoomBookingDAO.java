package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;

public interface RoomBookingDAO {
    RoomBookingEntity createRoomBooking(RoomEntity room, HotelBookingEntity hotelBookingEntity);
}
