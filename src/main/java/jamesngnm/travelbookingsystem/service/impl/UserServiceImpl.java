package jamesngnm.travelbookingsystem.service.impl;

import jakarta.persistence.PersistenceException;
import jamesngnm.travelbookingsystem.dao.UserDAO;
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
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
        userMapper = new UserMapper();
    }

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
        userMapper = new UserMapper();
    }
    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateRegisterUserRequest(registerUserRequest);
        try {
            UserEntity userEntity = userDAO.createUser(registerUserRequest);
            return userMapper.mapUserEntityToRegisterUserResponse(userEntity);
        } catch (PersistenceException e) {
            throw new ResponseException(BadRequestError.USER_ALREADY_EXISTS);
        }
    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest loginUserRequest) {
        validateLoginUserRequest(loginUserRequest);
        UserEntity userEntity = userDAO.getUserByEmail(loginUserRequest.getEmail());
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
        if (id == null) {
            throw new ResponseException(BadRequestError.USER_ID_REQUIRED);
        }
        UserEntity user = userDAO.getUserById(id);
        if (user == null) {
            throw new ResponseException(BadRequestError.USER_NOT_FOUND);
        }
        return user;
    }

    private void validateRegisterUserRequest(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest.getUsername() == null || registerUserRequest.getUsername().isEmpty()) {
            throw new ResponseException(BadRequestError.USERNAME_REQUIRED);
        }

        if (registerUserRequest.getPassword() == null || registerUserRequest.getPassword().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_PASSWORD_REQUIRED);
        }

        if (registerUserRequest.getEmail() == null || registerUserRequest.getEmail().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_EMAIL_REQUIRED);
        }

        validateEmail(registerUserRequest.getEmail());

        if (isUserExists(registerUserRequest.getEmail())) {
            throw new ResponseException(BadRequestError.USER_ALREADY_EXISTS);
        }
    }

    private void validateLoginUserRequest(LoginUserRequest loginUserRequest) {
        if (loginUserRequest.getEmail() == null || loginUserRequest.getEmail().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_EMAIL_REQUIRED);
        }

        if (loginUserRequest.getPassword() == null || loginUserRequest.getPassword().isEmpty()) {
            throw new ResponseException(BadRequestError.USER_PASSWORD_REQUIRED);
        }

        validateEmail(loginUserRequest.getEmail());

        if (!isUserExists(loginUserRequest.getEmail())) {
            throw new ResponseException(BadRequestError.USER_NOT_FOUND);
        }
    }

    private void validateEmail(String email) {
        try {
            new InternetAddress(email).validate();
        } catch (AddressException e) {
            throw new ResponseException(BadRequestError.USER_EMAIL_INVALID);
        }
    }

    private boolean isUserExists(String email) {
        return userDAO.getUserByEmail(email) != null;
    }
}
