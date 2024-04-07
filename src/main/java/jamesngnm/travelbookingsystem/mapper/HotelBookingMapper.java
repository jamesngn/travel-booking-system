package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;

public class HotelBookingMapper {
    public HotelBookingMapper() {

    }

    public HotelBookingResponse toHotelBookingResponse(HotelBookingEntity hotelBookingEntity) {
        HotelBookingResponse hotelBookingResponse = new HotelBookingResponse();
        hotelBookingResponse.setHotelId(hotelBookingEntity.getHotel().getId());
        hotelBookingResponse.setCheckInDate(hotelBookingEntity.getCheckinDate());
        hotelBookingResponse.setCheckOutDate(hotelBookingEntity.getCheckoutDate());
        //TODO: set rooms
        hotelBookingResponse.setRooms(null);
        return hotelBookingResponse;
    }

}
