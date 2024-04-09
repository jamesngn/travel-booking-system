package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    RoomEntity getRoomById(Long id);

    void addRoomBookedDate(RoomEntity room, LocalDateTime checkInDate, LocalDateTime checkOutDate);

    List<SearchRoomResponse> getAvailableRooms(SearchAvailableRoomsRequest searchAvailableRoomsRequest);

    List<SearchRoomResponse> getAvailableRooms(String checkInDate, String checkOutDate);
}
