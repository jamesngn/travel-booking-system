package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;

import java.util.List;

public interface HotelBookingDAO {
    HotelBookingEntity createHotelBooking(CreateHotelBookingRequest request);
    HotelBookingEntity getHotelBookingById(Long id);
    List<HotelBookingEntity> getHotelBookingByUserId(Long userId);
}
