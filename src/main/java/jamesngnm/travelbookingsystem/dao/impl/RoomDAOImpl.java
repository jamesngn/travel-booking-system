package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    private EntityManagerFactory emf;

    public RoomDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }
    @Override
    public RoomEntity searchRoomById(Long roomId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(RoomEntity.class, roomId);
        } finally {
            em.close();
        }
    }

    @Override
    public void creatBookedDate(RoomEntity room, BookedDate bookedDate) {
        EntityManager em = emf.createEntityManager();
        try {
            room.addBookedDate(bookedDate);
            em.getTransaction().begin();
            em.merge(room);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<RoomEntity> searchAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        EntityManager em = emf.createEntityManager();
        try {
            String queryString = "SELECT r FROM RoomEntity r " +
                    "LEFT JOIN FETCH r.hotel h " +
                    "WHERE r.id NOT IN (" +
                    "    SELECT bd.room.id FROM BookedDate bd " +
                    "    WHERE bd.checkInDate < :checkOutDate AND bd.checkOutDate > :checkInDate" +
                    ") AND r.available = true";

            TypedQuery<RoomEntity> query = em.createQuery(queryString, RoomEntity.class);
            query.setParameter("checkInDate", checkInDate);
            query.setParameter("checkOutDate", checkOutDate);

            List<RoomEntity> availableRooms = query.getResultList();

            // Detach the hotel entity from each room to avoid lazy loading issues
            availableRooms.forEach(room -> {
                room.setHotel(null);
                if (room.getBookedDates() != null) {
                    room.getBookedDates().forEach(bookedDate -> bookedDate.setRoom(null));
                }
            });

            return availableRooms;
        } finally {
            em.close();
        }
    }
}
