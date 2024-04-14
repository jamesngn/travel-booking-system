package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.UserEntity;

public interface BookingDAO {
    void createBookingByUserId(UserEntity user);
    
    void getBookingByUserId(Long userID);
}
