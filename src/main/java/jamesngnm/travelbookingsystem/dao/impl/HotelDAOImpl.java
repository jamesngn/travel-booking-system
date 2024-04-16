package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;

import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HotelDAOImpl implements HotelDAO {
    private final EntityManagerFactory emf;

    public HotelDAOImpl(String persistenceUnitName) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public HotelDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }

    @Override
    public void createHotel(HotelEntity hotel) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hotel);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @Override
    public List<HotelEntity> searchHotel(@NotNull SearchHotelRequest searchHotelRequest) {
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

            if (query.getResultList().isEmpty()) {
                throw new ResponseException(BadRequestError.HOTEL_NOT_FOUND);
            }

            HotelEntity hotel = query.getSingleResult();

            return hotel;
        } finally {
            em.close();
        }
    }
}
