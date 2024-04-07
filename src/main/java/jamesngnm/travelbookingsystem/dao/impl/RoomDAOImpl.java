package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.entity.RoomEntity;

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
}
