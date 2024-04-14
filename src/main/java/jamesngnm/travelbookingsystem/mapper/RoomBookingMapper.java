package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.model.response.RoomBookingResponse;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class RoomBookingMapper {
    public List<RoomBookingResponse> toRoomBookingResponses(List<RoomBookingEntity> roomBookingEntities) {
        return roomBookingEntities.stream()
                .map(this::toRoomBookingResponse)
                .toList();
    }
    public RoomBookingResponse toRoomBookingResponse(RoomBookingEntity roomBookingEntity) {
        RoomBookingResponse roomBookingResponse = new RoomBookingResponse();
        roomBookingResponse.setId(roomBookingEntity.getId());
        roomBookingResponse.setName(roomBookingEntity.getRoom().getName());
        roomBookingResponse.setPrice(roomBookingEntity.getRoom().getPrice());
        roomBookingResponse.setType(roomBookingEntity.getRoom().getType());
        roomBookingResponse.setHotelId(roomBookingEntity.getRoom().getHotel().getId());
        return roomBookingResponse;
    }
}
