package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.mapper.RoomMapper;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;
    private RoomMapper roomMapper;
    public RoomServiceImpl() {
        this.roomDAO = new RoomDAOImpl();
        this.roomMapper = new RoomMapper();
    }
    @Override
    public RoomEntity getRoomById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Room ID is required");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Room ID must be positive");
        }

        return roomDAO.searchRoomById(id);
    }

    @Override
    public void addRoomBookedDate(RoomEntity room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }

        if (checkInDate == null) {
            throw new IllegalArgumentException("Check-in date is required");
        }

        if (checkOutDate == null) {
            throw new IllegalArgumentException("Check-out date is required");
        }

        if (checkInDate.isAfter(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        BookedDate bookedDate = new BookedDate();
        bookedDate.setCheckInDate(checkInDate);
        bookedDate.setCheckOutDate(checkOutDate);
        bookedDate.setRoom(room);

        roomDAO.creatBookedDate(room, bookedDate);
        // Set room to null to avoid circular reference
        bookedDate.setRoom(null);
    }

    @Override
    public List<SearchRoomResponse> getAvailableRooms(SearchAvailableRoomsRequest searchAvailableRoomsRequest) {
        List<RoomEntity> roomEntityList = roomDAO.searchAvailableRooms(searchAvailableRoomsRequest);

        return roomEntityList.stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SearchRoomResponse> getAvailableRooms(String checkInDate, String checkOutDate) {
        if (checkInDate == null) {
            throw new IllegalArgumentException("Check-in date is required");
        }

        if (checkOutDate == null) {
            throw new IllegalArgumentException("Check-out date is required");
        }

        LocalDateTime checkInDateTime = LocalDateTime.parse(checkInDate);
        LocalDateTime checkOutDateTime = LocalDateTime.parse(checkOutDate);

        if (checkInDateTime.isAfter(checkOutDateTime)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        List<RoomEntity> roomEntityList = roomDAO.searchAvailableRooms(checkInDateTime, checkOutDateTime);

        return roomEntityList.stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }
}
