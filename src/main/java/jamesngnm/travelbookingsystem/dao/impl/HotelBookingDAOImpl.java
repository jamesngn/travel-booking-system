package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
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
            hotelBooking.setUser(null);
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
            hbe.setUser(null);
            hbe.getRoomBookings().forEach(roomBooking -> {
                roomBooking.setHotelBooking(null);
                roomBooking.getRoom().setHotel(null);
                roomBooking.getRoom().getBookedDates().forEach(bd -> {
                    bd.setRoom(null);
                });
            });

            return hbe;
        } finally {
            em.close();
        }
    }
}
