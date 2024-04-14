package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.model.request.LoginUserRequest;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;
import jamesngnm.travelbookingsystem.model.response.LoginUserResponse;
import jamesngnm.travelbookingsystem.model.response.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
    LoginUserResponse loginUser(LoginUserRequest loginUserRequest);
}
