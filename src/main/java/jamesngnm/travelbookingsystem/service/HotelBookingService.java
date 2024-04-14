package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;

public interface HotelBookingService {
    HotelBookingResponse bookHotel(CreateHotelBookingRequest request);
    HotelBookingResponse getHotelBookingDetails(Long id);
}
