package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;

import java.util.List;

public class HotelDAOImpl implements HotelDAO {
    private EntityManagerFactory emf;

    public HotelDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }
    @Override
    public List<HotelEntity> searchHotel(SearchHotelRequest searchHotelRequest) {
        EntityManager em = emf.createEntityManager();
        try {
            // Query database for hotels based on searchHotelRequest
            TypedQuery<HotelEntity> query = em.createQuery("SELECT h FROM HotelEntity h WHERE h.location = :location", HotelEntity.class);
            query.setParameter("location", searchHotelRequest.getLocation());

            List<HotelEntity> hotels = query.getResultList();

            return hotels;
        } finally {
            em.close();
        }
    }

    @Override
    public HotelEntity getHotelDetail(Long hotelId) {
        EntityManager em = emf.createEntityManager();
        try {
            // Query database for hotel based on hotelId
            TypedQuery<HotelEntity> query = em.createQuery("SELECT h FROM HotelEntity h WHERE h.id = :id", HotelEntity.class);
            query.setParameter("id", hotelId);

            HotelEntity hotel = query.getSingleResult();

            return hotel;
        } finally {
            em.close();
        }
    }
}
