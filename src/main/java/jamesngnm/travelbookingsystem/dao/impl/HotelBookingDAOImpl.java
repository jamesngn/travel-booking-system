package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.entity.*;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.UserService;
import jamesngnm.travelbookingsystem.service.impl.HotelServiceImpl;
import jamesngnm.travelbookingsystem.service.impl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingDAOImpl implements HotelBookingDAO {
    private final EntityManagerFactory emf;
    private final UserService userService;
    private final HotelService hotelService;

    public HotelBookingDAOImpl() {

        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
        this.userService = new UserServiceImpl();
        this.hotelService = new HotelServiceImpl();
    }
    @Override
    public HotelBookingEntity createHotelBooking(CreateHotelBookingRequest request) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            UserEntity user = userService.getUserById(request.getUserId());
            HotelEntity hotel = hotelService.getHotelEntityById(request.getHotelId());

            HotelBookingEntity hotelBooking = new HotelBookingEntity();
            hotelBooking.setHotel(hotel);
            hotelBooking.setUser(user);
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

    public List<HotelBookingEntity> getHotelBookingByUserId(Long userId) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HotelBookingEntity> cr = cb.createQuery(HotelBookingEntity.class);
            Root<HotelBookingEntity> hotelBookingEntityRoot = cr.from(HotelBookingEntity.class);
            hotelBookingEntityRoot.fetch("user", JoinType.LEFT);
            hotelBookingEntityRoot.fetch("hotel", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                Predicate userIdPredicate = cb.equal(hotelBookingEntityRoot.get("user").get("id"), userId);
                predicates.add(userIdPredicate);
            }

            TypedQuery<HotelBookingEntity> query = em.createQuery(cr.select(hotelBookingEntityRoot).where(predicates.toArray(new Predicate[0])));
            List<HotelBookingEntity> hotelBookingEntities = query.getResultList();

            return hotelBookingEntities;
        } finally {
            em.close();
        }
    }
}
