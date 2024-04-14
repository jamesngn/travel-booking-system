package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;

import java.util.List;

public interface HotelBookingService {
    HotelBookingResponse bookHotel(CreateHotelBookingRequest request);
    HotelBookingResponse searchHotelBookingDetailsById(Long id);
    List<HotelBookingResponse> searchHotelBookingDetailsByUserId(Long userId);
}
