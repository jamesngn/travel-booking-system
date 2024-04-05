package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;

import java.lang.reflect.Type;
import java.util.List;

public class HotelDAOImpl implements HotelDAO {
    private final EntityManagerFactory emf;

    public HotelDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }


    @Override
    public List<HotelEntity> searchHotels(SearchHotelRequest request) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<HotelEntity> query = em.createQuery("SELECT h FROM HotelEntity h WHERE h.location = :location", HotelEntity.class);

            query.setParameter("location", request.getLocation());

            List<HotelEntity> hotels = query.getResultList();

            hotels.forEach(hotel -> {
                TypedQuery<RoomEntity> roomQuery = em.createQuery("SELECT r FROM RoomEntity r WHERE r.hotel.id = :hotelId", RoomEntity.class);
                roomQuery.setParameter("hotelId", hotel.getId());
                List<RoomEntity> rooms = roomQuery.getResultList();

            });

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public HotelEntity searchHotelById(Long hotelId) {
        return null;
    }
}
