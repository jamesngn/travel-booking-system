package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.entity.BookingEntity;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;

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
}
