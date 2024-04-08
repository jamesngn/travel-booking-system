package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.impl.HotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.dto.HotelBookingDTO;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.mapper.HotelBookingMapper;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.RoomBookingService;

import java.util.List;

public class HotelBookingServiceImpl implements HotelBookingService {
    private final HotelBookingDAO hotelBookingDAO;
    private final RoomBookingService roomBookingService;

    private final HotelBookingMapper hotelBookingMapper;

    public HotelBookingServiceImpl() {
        this.hotelBookingDAO = new HotelBookingDAOImpl();
        this.roomBookingService = new RoomBookingServiceImpl();
        this.hotelBookingMapper = new HotelBookingMapper();
    }
    @Override
    public HotelBookingResponse bookHotel (CreateHotelBookingRequest request) {
        HotelBookingEntity hotelBookingEntity = hotelBookingDAO.createHotelBooking(request);

        List<Long> roomIdsToBook = request.getRoomIds();
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


    @Override
    public HotelBookingDTO getHotelBookingDetailsV2(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Hotel booking ID not found");
        }

        HotelBookingDTO dto = hotelBookingDAO.getHotelBookingByIdV2(id);
        return dto;
    }

}
