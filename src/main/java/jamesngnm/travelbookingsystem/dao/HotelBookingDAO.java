package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.dto.HotelBookingDTO;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;

public interface HotelBookingDAO {
    HotelBookingEntity createHotelBooking(CreateHotelBookingRequest request);

    HotelBookingEntity getHotelBookingById(Long id);

    HotelBookingDTO getHotelBookingByIdV2(Long id);
}
