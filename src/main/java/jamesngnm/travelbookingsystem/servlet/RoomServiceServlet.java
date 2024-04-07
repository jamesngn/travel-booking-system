package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.service.RoomService;
import jamesngnm.travelbookingsystem.service.impl.RoomServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/rooms/", "/rooms/create", "/rooms/search", "/rooms/search-available"})
public class RoomServiceServlet extends HttpServlet {
    private RoomService roomService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        super.init();
        roomService = new RoomServiceImpl();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/rooms/search-available".equals(path)) {
            try {
                String checkInDate = request.getParameter("checkInDate");
                String checkOutDate = request.getParameter("checkOutDate");
                List<RoomEntity> availableRooms = roomService.getAvailableRooms(checkInDate, checkOutDate);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(availableRooms)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
