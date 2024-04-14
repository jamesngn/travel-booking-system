package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingServiceImpl implements HotelBookingService {
    private final HotelBookingDAO hotelBookingDAO;
    private final RoomBookingService roomBookingService;
    private final RoomService roomService;

    private final HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingService = new RoomBookingServiceImpl();
        this.roomService = new RoomServiceImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        // TODO: check if user currently have unfinished booking, use that for hotelBookingEntity, otherwise create new.


        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        List<Long> roomIdsToBook = new ArrayList<>();
        request.getRoomByTypeRequests().forEach(roomByTypeRequest -> {
            SearchAvailableRoomsRequest roomsRequest = new SearchAvailableRoomsRequest(request.getHotelId(),request.getCheckInDate(), request.getCheckOutDate(), roomByTypeRequest.getType(), roomByTypeRequest.getQuantity());
            List<Long> roomId = roomService.getAvailableRoomIds(roomsRequest);
            roomIdsToBook.addAll(roomId);
        });

        roomIdsToBook.forEach(roomId -> {
            roomBookingService.reserveRoom(roomId, hotelBookingEntity);
        });

        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }

    @Override
    public HotelBookingResponse getHotelBookingDetails(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Hotel booking ID not found");
        }

        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.getHotelBookingById(id);
        return hotelBookingMapper.toHotelBookingResponse(hotelBookingEntity);
    }
}
