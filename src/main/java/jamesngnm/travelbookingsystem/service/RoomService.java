package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.RoomEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    RoomEntity getRoomById(Long id);

    void addRoomBookedDate(RoomEntity room, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    List<RoomEntity> getAvailableRooms(String checkInDate, String checkOutDate);
}
