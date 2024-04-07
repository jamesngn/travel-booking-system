package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;

public interface BookingService {
    HotelBookingEntity createHotelBookingWithRooms(CreateHotelBookingRequest request);
}
