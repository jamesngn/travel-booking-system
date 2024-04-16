package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelBookingServiceImpl implements HotelBookingService {
    private final HotelBookingDAO hotelBookingDAO;
    private final RoomBookingService roomBookingService;
    private final RoomService roomService;
    private final HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl(HotelBookingDAO hotelBookingDAO, RoomBookingService roomBookingService, RoomService roomService) {
        this.hotelBookingDAO = hotelBookingDAO;
        this.roomBookingService = roomBookingService;
        this.roomService = roomService;
        this.hotelBookingMapper = new HotelBookingMapper();
    }

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingService = new RoomBookingServiceImpl();
        this.roomService = new RoomServiceImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        validateRequest(request);


        List<Long> roomIdsToBook = new ArrayList<>();
        request.getRoomByTypeRequests().forEach(roomByTypeRequest -> {

            SearchAvailableRoomsRequest roomsRequest = new SearchAvailableRoomsRequest();
            roomsRequest.setHotelId(request.getHotelId());
            roomsRequest.setCheckInDate(request.getCheckInDate());
            roomsRequest.setCheckOutDate(request.getCheckOutDate());
            roomsRequest.setType(roomByTypeRequest.getType());
            roomsRequest.setQuantity(roomByTypeRequest.getQuantity());

            List<Long> roomId = roomService.getAvailableRoomIds(roomsRequest);
            roomIdsToBook.addAll(roomId);
        });

        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        roomIdsToBook.forEach(roomId -> {
            roomBookingService.reserveRoom(roomId, hotelBookingEntity);
        });

        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }

    @Override
    public HotelBookingResponse searchHotelBookingDetailsById(Long id) {
        validateHotel(id);
        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.getHotelBookingById(id);
        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }

    @Override
    public List<HotelBookingResponse> searchHotelBookingDetailsByUserId(Long userId) {
        validateUser(userId);

        List<HotelBookingEntity> hotelBookingEntities = hotelBookingDAO.getHotelBookingByUserId(userId);
        return hotelBookingEntities.stream().map(hotelBookingMapper::toHotelBookingResponse).collect(Collectors.toList());
    }

    private void validateRequest(CreateHotelBookingRequest request) {
        validateUser(request.getUserId());
        validateHotel(request.getHotelId());
        validateCheckinCheckoutDate(request.getCheckInDate(), request.getCheckOutDate());

        if (request.getRoomByTypeRequests() == null || request.getRoomByTypeRequests().isEmpty()) {
            throw new ResponseException(BadRequestError.ROOM_TYPE_REQUIRED);
        }

        if (request.getRoomByTypeRequests().stream().anyMatch(roomByTypeRequest -> roomByTypeRequest.getType() == null)) {
            throw new ResponseException(BadRequestError.ROOM_TYPE_INVALID);
        }

        if (request.getRoomByTypeRequests().stream().anyMatch(roomByTypeRequest -> roomByTypeRequest.getQuantity() <= 0)) {
            throw new ResponseException(BadRequestError.ROOM_QUANTITY_REQUIRED);
        }
    }

    private void validateCheckinCheckoutDate(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        if (checkInDate == null) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_IN_REQUIRED);
        }

        if (checkOutDate == null) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_OUT_REQUIRED);
        }


        if (checkInDate.isBefore(LocalDateTime.now())) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_IN_DATE_IN_THE_PAST);
        }

        if (checkOutDate.isBefore(LocalDateTime.now())) {
            throw new ResponseException(BadRequestError.HOTEL_CHECK_OUT_DATE_IN_THE_PAST);
        }
        if (checkInDate.isAfter(checkOutDate) || checkInDate.isEqual(checkOutDate)) {
            throw new ResponseException(BadRequestError.CHECK_IN_DATE_BEFORE_CHECK_OUT_DATE);
        }
    }

    private void validateUser(Long userId) {
        if (userId == null) {
            throw new ResponseException(BadRequestError.USER_ID_REQUIRED);
        }

        if (userId <= 0) {
            throw new ResponseException(BadRequestError.USER_NOT_FOUND);
        }
    }

    private void validateHotel(Long hotelId) {
        if (hotelId == null) {
            throw new ResponseException(BadRequestError.HOTEL_ID_REQUIRED);
        }

        if (hotelId <= 0) {
            throw new ResponseException(BadRequestError.HOTEL_NOT_FOUND);
        }
    }
}
