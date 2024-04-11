package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomDAO {
    RoomEntity searchRoomById(Long roomId);

    void creatBookedDate(RoomEntity room, BookedDate bookedDate);

    List<RoomEntity> searchAvailableRooms (SearchAvailableRoomsRequest searchAvailableRoomsRequest);

}
