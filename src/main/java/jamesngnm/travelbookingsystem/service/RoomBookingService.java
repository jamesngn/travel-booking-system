package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.request.CreateRoomBookingRequest;

public interface RoomBookingService {
    void reserveRoom(RoomEntity room, HotelBookingEntity hotelBooking);
}
