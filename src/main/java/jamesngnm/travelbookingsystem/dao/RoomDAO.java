package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.RoomEntity;

public interface RoomDAO {
    RoomEntity searchRoomById(Long roomId);
}
