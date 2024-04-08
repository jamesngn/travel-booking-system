package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dto.HotelBookingDTO;
import jamesngnm.travelbookingsystem.dto.RoomBookingDTO;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HotelBookingDAOImpl implements HotelBookingDAO {
    private final EntityManagerFactory emf;

    public HotelBookingDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }
    @Override
    public HotelBookingEntity createHotelBooking(CreateHotelBookingRequest request) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            HotelEntity hotel = em.find(HotelEntity.class, request.getHotelId());
            if (hotel == null) {
                throw new IllegalArgumentException("Hotel not found");
            }

            //TODO: Add booking entity
//            BookingEntity booking = em.find(BookingEntity.class, request.getBookingId());
//            if (booking == null) {
//                throw new IllegalArgumentException("Booking not found");
//            }

            HotelBookingEntity hotelBooking = new HotelBookingEntity();
            hotelBooking.setHotel(hotel);
            hotelBooking.setBooking(null);
            hotelBooking.setCheckinDate(request.getCheckInDate());
            hotelBooking.setCheckoutDate(request.getCheckOutDate());

            // Persist hotel booking
            em.persist(hotelBooking);
            tx.commit();

            return hotelBooking;
        } finally {
            em.close();
        }
    }

    @Override
    public HotelBookingEntity getHotelBookingById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            HotelBookingEntity hbe = em.find(HotelBookingEntity.class, id);
            if (hbe == null) {
                throw new IllegalArgumentException("Hotel booking not found");
            }

            //avoid circular reference
            hbe.getHotel().setRooms(null);
            hbe.getRoomBookings().forEach(roomBooking -> {
                roomBooking.setHotelBooking(null);
                roomBooking.getRoom().setHotel(null);
            });

            return hbe;
        } finally {
            em.close();
        }
    }

    @Override
    public HotelBookingDTO getHotelBookingByIdV2(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            HotelBookingEntity hbe = em.find(HotelBookingEntity.class, id);
            if (hbe == null) {
                throw new IllegalArgumentException("Hotel booking not found");
            }



            HotelBookingDTO dto = getHotelBookingDTO(hbe);


            return dto;
        } finally {
            em.close();
        }
    }

    @NotNull
    private static HotelBookingDTO getHotelBookingDTO(HotelBookingEntity hbe) {
        HotelBookingDTO dto = new HotelBookingDTO();
        dto.setId(hbe.getId());
        dto.setHotelId(hbe.getHotel().getId());
        dto.setHotelName(hbe.getHotel().getName());
        dto.setBookingId(hbe.getBooking().getId());
        dto.setCheckinDate(hbe.getCheckinDate());
        dto.setCheckoutDate(hbe.getCheckoutDate());

        List<RoomBookingDTO> roomBookingDTOs = new ArrayList<>();
        for (RoomBookingEntity roomBooking : hbe.getRoomBookings()) {
            RoomBookingDTO roomBookingDTO = new RoomBookingDTO();
            roomBookingDTO.setId(roomBooking.getId());
            roomBookingDTO.setRoomId(roomBooking.getRoom().getId());
            roomBookingDTO.setRoomName(roomBooking.getRoom().getName());
            roomBookingDTOs.add(roomBookingDTO);
        }
        dto.setRoomBookings(roomBookingDTOs);
        return dto;
    }
}
