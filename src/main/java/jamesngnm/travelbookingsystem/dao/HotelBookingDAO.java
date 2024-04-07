package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;

public interface HotelBookingDAO {
    HotelBookingEntity createHotelBooking(CreateHotelBookingRequest request);
}
