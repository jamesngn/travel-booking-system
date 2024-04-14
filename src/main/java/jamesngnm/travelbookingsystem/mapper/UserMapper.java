package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.UserEntity;
import jamesngnm.travelbookingsystem.model.response.LoginUserResponse;
import jamesngnm.travelbookingsystem.model.response.RegisterUserResponse;

public class UserMapper {
    public UserMapper() {

    }
    public RegisterUserResponse mapUserEntityToRegisterUserResponse(UserEntity userEntity) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setEmail(userEntity.getEmail());
        registerUserResponse.setUsername(userEntity.getUsername());
        return registerUserResponse;
    }

    public LoginUserResponse mapUserEntityToLoginUserResponse(UserEntity userEntity) {
        LoginUserResponse loginUserResponse = new LoginUserResponse();
        loginUserResponse.setId(userEntity.getId());
        loginUserResponse.setUsername(userEntity.getUsername());
        loginUserResponse.setEmail(userEntity.getEmail());
        return loginUserResponse;
    }
}
