package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.RoomEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomDAO {
    RoomEntity searchRoomById(Long roomId);

    void creatBookedDate(RoomEntity room, BookedDate bookedDate);

    List<RoomEntity> searchAvailableRooms (LocalDateTime checkInDate, LocalDateTime checkOutDate);
}
