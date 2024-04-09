package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;

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
            RoomEntity room = em.find(RoomEntity.class, roomId);
            if (room == null) {
                throw new IllegalArgumentException("Room not found");
            }

            //avoid circular reference
            room.getHotel().setRooms(null);
            if (room.getBookedDates() != null) {
                room.getBookedDates().forEach(bookedDate -> bookedDate.setRoom(null));
            }

            return room;
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
                                ")";

            TypedQuery<RoomEntity> query = em.createQuery(queryString, RoomEntity.class);
            query.setParameter("checkInDate", checkInDate);
            query.setParameter("checkOutDate", checkOutDate);

            List<RoomEntity> availableRooms = query.getResultList();

            // Detach the hotel entity from each room to avoid EAGER loading issues
            availableRooms.forEach(room -> {
                if (room.getBookedDates() != null) {
                    room.getBookedDates().forEach(bookedDate -> bookedDate.setRoom(null));
                }
            });

            return availableRooms;
        } finally {
            em.close();
        }
    }

    @Override
    public List<RoomEntity> searchAvailableRooms(SearchAvailableRoomsRequest searchAvailableRoomsRequest) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<RoomEntity> cr = cb.createQuery(RoomEntity.class);
            Root<RoomEntity> roomEntityRoot = cr.from(RoomEntity.class);
            roomEntityRoot.fetch("hotel", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            if (searchAvailableRoomsRequest.getLocation() != null) {
                Predicate locationPredicate = cb.equal(roomEntityRoot.get("hotel").get("location"), searchAvailableRoomsRequest.getLocation());
                predicates.add(locationPredicate);
            }

            if (searchAvailableRoomsRequest.getCheckInDate() != null && searchAvailableRoomsRequest.getCheckOutDate() != null) {
                Join<RoomEntity, BookedDate> bookedDateJoin = roomEntityRoot.join("bookedDates", JoinType.LEFT);

                Predicate checkInDatePredicate = cb.lessThanOrEqualTo(bookedDateJoin.get("checkOutDate"), searchAvailableRoomsRequest.getCheckInDate());
                Predicate checkOutDatePredicate = cb.greaterThanOrEqualTo(bookedDateJoin.get("checkInDate"), searchAvailableRoomsRequest.getCheckOutDate());

                Predicate bookedDatePredicate = cb.or(checkInDatePredicate, checkOutDatePredicate);
                Predicate noBookedDatesPredicate = cb.isEmpty(roomEntityRoot.get("bookedDates"));

                predicates.add(cb.or(bookedDatePredicate, noBookedDatesPredicate, cb.isNull(bookedDateJoin.get("checkInDate"))));
            }

            if (searchAvailableRoomsRequest.getMinPrice() > 0) {
                Predicate minPricePredicate = cb.ge(roomEntityRoot.get("price"), searchAvailableRoomsRequest.getMinPrice());
                predicates.add(minPricePredicate);
            }

            if (searchAvailableRoomsRequest.getMaxPrice() > 0) {
                Predicate maxPricePredicate = cb.le(roomEntityRoot.get("price"), searchAvailableRoomsRequest.getMaxPrice());
                predicates.add(maxPricePredicate);
            }

            if (searchAvailableRoomsRequest.getType() != null) {
                RoomType roomType = RoomType.valueOf(searchAvailableRoomsRequest.getType());
                Predicate roomTypePredicate = cb.equal(roomEntityRoot.get("type"), roomType);
                predicates.add(roomTypePredicate);
            }

            cr.select(roomEntityRoot).where(predicates.toArray(new Predicate[0]));

            TypedQuery<RoomEntity> query = em.createQuery(cr);
            List<RoomEntity> availableRooms = query.getResultList();

            return availableRooms;
        } finally {
            em.close();
        }
    }
}
