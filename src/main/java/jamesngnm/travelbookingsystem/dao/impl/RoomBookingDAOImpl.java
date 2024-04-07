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
    public RoomBookingEntity createRoomBooking(RoomEntity room, HotelBookingEntity hotelBookingEntity) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            if (room == null) {
                throw new IllegalArgumentException("Room not found");
            }

            if (hotelBookingEntity == null) {
                throw new IllegalArgumentException("Hotel booking not found");
            }

            // Persist room booking
            RoomBookingEntity rbe = new RoomBookingEntity();
            rbe.setRoom(room);
            rbe.setCheckInDate(null);
            rbe.setCheckOutDate(null);
            rbe.setHotelBooking(null);

            hotelBookingEntity.addRoomBooking(rbe);

            em.persist(rbe);
            tx.commit();

            return rbe;
        } finally {
            em.close();
        }
    }
}