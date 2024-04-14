package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.UserEntity;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;

public interface UserDAO {
    UserEntity createUser(RegisterUserRequest registerUserRequest);
    UserEntity getUserByEmail(String email);

    UserEntity getUserById(Long id);
}
