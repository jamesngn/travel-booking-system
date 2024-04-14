package jamesngnm.travelbookingsystem.service.impl;

import jakarta.persistence.PersistenceException;
import jamesngnm.travelbookingsystem.dao.impl.UserDAOImpl;
import jamesngnm.travelbookingsystem.entity.UserEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.mapper.UserMapper;
import jamesngnm.travelbookingsystem.model.request.LoginUserRequest;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;
import jamesngnm.travelbookingsystem.model.response.LoginUserResponse;
import jamesngnm.travelbookingsystem.model.response.RegisterUserResponse;
import jamesngnm.travelbookingsystem.service.UserService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class UserServiceImpl implements UserService {
    private final UserDAOImpl userDAOImpl;
    private final UserMapper userMapper;

    public UserServiceImpl() {
        userDAOImpl = new UserDAOImpl();
        userMapper = new UserMapper();
    }
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateRegisterUserRequest(registerUserRequest);
        try {
            UserEntity userEntity = userDAOImpl.createUser(registerUserRequest);
            return userMapper.mapUserEntityToRegisterUserResponse(userEntity);
        } catch (PersistenceException e) {
            throw new ResponseException(BadRequestError.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) {
        UserEntity userEntity = userDAOImpl.getUserByEmail(loginUserRequest.getEmail());
        if (userEntity == null) {
            throw new ResponseException(BadRequestError.USER_NOT_FOUND);
        }

        if (!userEntity.getPassword().equals(loginUserRequest.getPassword())) {
            throw new ResponseException(BadRequestError.USER_PASSWORD_INVALID);
        }

        return userMapper.mapUserEntityToLoginUserResponse(userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userDAOImpl.getUserById(id);
    }

    private void validateRegisterUserRequest(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest.getUsername() == null || registerUserRequest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (registerUserRequest.getPassword() == null || registerUserRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (registerUserRequest.getEmail() == null || registerUserRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        try {
            new InternetAddress(registerUserRequest.getEmail()).validate();
        } catch (AddressException e) {
            throw new ResponseException(BadRequestError.USER_EMAIL_INVALID);
        }
    }
}
