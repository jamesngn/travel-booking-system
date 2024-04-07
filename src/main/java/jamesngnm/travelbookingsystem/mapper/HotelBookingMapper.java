package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
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

        if (hotelBookingEntity.getRoomBookings() == null || hotelBookingEntity.getRoomBookings().isEmpty()) {
            return hotelBookingResponse;
        }
        hotelBookingEntity.getRoomBookings().forEach(roomBookingEntity -> {
            RoomEntity room = roomBookingEntity.getRoom();
            room.setHotel(null);
            hotelBookingResponse.addRoom(room);
        });
        return hotelBookingResponse;
    }

}
