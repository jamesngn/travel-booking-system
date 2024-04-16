package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.RoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.mapper.RoomMapper;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;

    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
        this.roomMapper = new RoomMapper();
    }
    public RoomServiceImpl() {
        this.roomDAO = new RoomDAOImpl();
        this.roomMapper = new RoomMapper();
    }
    @Override
    public RoomEntity getRoomById(Long id) {
        if (id == null) {
            throw new ResponseException(BadRequestError.ROOM_ID_INVALID);
        }

        if (id < 0) {
            throw new ResponseException(BadRequestError.ROOM_ID_INVALID);
        }

        return roomDAO.searchRoomById(id);
    }

    @Override
    public void addRoomBookedDate(RoomEntity room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        if (room == null) {
            throw new ResponseException(BadRequestError.ROOM_REQUIRED);
        }

        validateCheckinCheckoutDate(checkInDate, checkOutDate);

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
    public List<Long> getAvailableRoomIds(SearchAvailableRoomsRequest searchAvailableRoomsRequest) {
        return roomDAO.searchAvailableRooms(searchAvailableRoomsRequest)
                .stream()
                .map(RoomEntity::getId)
                .collect(Collectors.toList());
    }

    private void validateCheckinCheckoutDate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        if (checkInDate == null) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_IN_REQUIRED);
        }

        if (checkOutDate == null) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_OUT_REQUIRED);
        }

        if (checkInDate.isAfter(checkOutDate)) {
            throw new ResponseException(BadRequestError.CHECK_IN_DATE_BEFORE_CHECK_OUT_DATE);
        }
    }
}
