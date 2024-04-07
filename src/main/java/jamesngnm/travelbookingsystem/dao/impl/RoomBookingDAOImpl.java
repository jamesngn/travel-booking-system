package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomBookingEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;

public class RoomBookingDAOImpl implements RoomBookingDAO {
    private final EntityManagerFactory emf;

    public RoomBookingDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }
    @Override
    public void createRoomBooking(Long roomId, Long hotelBookingId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            RoomEntity room = em.find(RoomEntity.class, roomId);
            if (room == null) {
                throw new IllegalArgumentException("Room not found");
            }

            HotelBookingEntity hotelBooking = em.find(HotelBookingEntity.class, hotelBookingId);
            if (hotelBooking == null) {
                throw new IllegalArgumentException("Hotel booking not found");
            }

            // Persist room booking
            RoomBookingEntity rbe = new RoomBookingEntity();
            rbe.setRoom(room);
            rbe.setCheckInDate(null);
            rbe.setCheckOutDate(null);
            rbe.setHotelBooking(hotelBooking);

            em.persist(rbe);
            tx.commit();
        } finally {
            em.close();
        }
    }
}
