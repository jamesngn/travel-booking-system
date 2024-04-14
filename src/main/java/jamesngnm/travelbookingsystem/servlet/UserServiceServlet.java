package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.request.LoginUserRequest;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;
import jamesngnm.travelbookingsystem.model.response.LoginUserResponse;
import jamesngnm.travelbookingsystem.model.response.RegisterUserResponse;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.service.UserService;
import jamesngnm.travelbookingsystem.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/user/login", "/user/register"})
public class UserServiceServlet extends HttpServlet {
    private UserService userService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserServiceImpl();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/user/register".equals(path)) {
            try {
                RegisterUserRequest registerUserRequest = extractRegisterUserRequest(request);
                RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(registerUserResponse)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        }
        else if ("/user/login".equals(path)) {
            try {
                LoginUserRequest loginUserRequest = extractLoginUserRequest(request);
                LoginUserResponse loginUserResponse = userService.loginUser(loginUserRequest);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(loginUserResponse)));
            } catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private RegisterUserRequest extractRegisterUserRequest(HttpServletRequest request) throws IOException {
        return gson.fromJson(request.getReader(), RegisterUserRequest.class);
    }

    private LoginUserRequest extractLoginUserRequest(HttpServletRequest request) throws IOException {
        return gson.fromJson(request.getReader(), LoginUserRequest.class);
    }


}
