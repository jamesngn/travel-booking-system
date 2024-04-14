package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.model.response.RoomBookingResponse;

import java.util.List;

public class HotelBookingMapper {
    private final RoomBookingMapper roomBookingMapper;
    public HotelBookingMapper() {
        this.roomBookingMapper = new RoomBookingMapper();
    }

    public HotelBookingResponse toHotelBookingResponse(HotelBookingEntity hotelBookingEntity) {
        HotelBookingResponse hotelBookingResponse = new HotelBookingResponse();
        hotelBookingResponse.setHotelId(hotelBookingEntity.getHotel().getId());
        hotelBookingResponse.setCheckInDate(hotelBookingEntity.getCheckinDate());
        hotelBookingResponse.setCheckOutDate(hotelBookingEntity.getCheckoutDate());

        if (hotelBookingEntity.getRoomBookings() == null || hotelBookingEntity.getRoomBookings().isEmpty()) {
            return hotelBookingResponse;
        }

        List<RoomBookingResponse> roomBookingResponses = roomBookingMapper.toRoomBookingResponses(hotelBookingEntity.getRoomBookings());
        hotelBookingResponse.setRooms(roomBookingResponses);


        return hotelBookingResponse;
    }

}
