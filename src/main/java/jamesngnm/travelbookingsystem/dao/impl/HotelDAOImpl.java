package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.entity.BookedDate;
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
           if (request.getLocation() != null  && request.getCheckInDate() != null && request.getCheckOutDate() != null) {
               return searchHotelsByLocationAndCheckInCheckOutDate(request);
           } else if (request.getLocation() != null) {
               return searchHotelsByLocation(request.getLocation());
           } else {
               return null;
           }
    }

    @Override
    public HotelEntity searchHotelById(Long hotelId) {
        return null;
    }

    //TODO: Complete the logic of this method
    private List<HotelEntity> searchHotelsByLocationAndCheckInCheckOutDate(SearchHotelRequest request) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<HotelEntity> query = em.createQuery("SELECT h FROM HotelEntity h WHERE h.location = :location", HotelEntity.class);
            query.setParameter("location", request.getLocation());
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private List<HotelEntity> searchHotelsByLocation(String location) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<HotelEntity> query = em.createQuery("SELECT h FROM HotelEntity h WHERE h.location = :location", HotelEntity.class);
            query.setParameter("location", location);
            List<HotelEntity> hotels = query.getResultList();

            return hotels;
        } finally {
            em.close();
        }
    }
}
