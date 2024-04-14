package jamesngnm.travelbookingsystem.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jamesngnm.travelbookingsystem.dao.UserDAO;
import jamesngnm.travelbookingsystem.entity.UserEntity;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;

public class UserDAOImpl implements UserDAO {
    private EntityManagerFactory emf;

    public UserDAOImpl() {
        this.emf = Persistence.createEntityManagerFactory("travel-booking-system");
    }
    @Override
    public UserEntity createUser(RegisterUserRequest registerUserRequest) {
        EntityManager em = emf.createEntityManager();
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(registerUserRequest.getUsername());
            userEntity.setPassword(registerUserRequest.getPassword());
            userEntity.setEmail(registerUserRequest.getEmail());

            em.getTransaction().begin();
            em.persist(userEntity);
            em.getTransaction().commit();

            return userEntity;
        } finally {
            em.close();
        }
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :email", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public UserEntity getUserById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(UserEntity.class, id);
        } finally {
            em.close();
        }
    }
}
