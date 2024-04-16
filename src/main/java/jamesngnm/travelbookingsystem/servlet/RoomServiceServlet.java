package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.request.SearchAvailableRoomsRequest;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.model.response.SearchRoomResponse;
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
                SearchAvailableRoomsRequest searchAvailableRoomsRequest = extractSearchRoomRequest(request);
                List<SearchRoomResponse> availableRooms = roomService.getAvailableRooms(searchAvailableRoomsRequest);
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

    private SearchAvailableRoomsRequest extractSearchRoomRequest(HttpServletRequest request) {
        // Extract search room request
        SearchAvailableRoomsRequest searchAvailableRoomsRequest = new SearchAvailableRoomsRequest();

        String hotelId = request.getParameter("hotelId");
        if (hotelId != null && !hotelId.isEmpty()) {
            searchAvailableRoomsRequest.setHotelId(Long.parseLong(hotelId));
        }

        String checkInDate = request.getParameter("checkInDate");
        if (checkInDate != null && !checkInDate.isEmpty()) {
            searchAvailableRoomsRequest.setCheckInDate(LocalDateTime.parse(checkInDate));
        }

        String checkOutDate = request.getParameter("checkOutDate");
        if (checkOutDate != null && !checkOutDate.isEmpty()) {
            searchAvailableRoomsRequest.setCheckOutDate(LocalDateTime.parse(checkOutDate));
        }

        String location = request.getParameter("location");
        if (location != null && !location.isEmpty()) {
            searchAvailableRoomsRequest.setLocation(location);
        }

        String minPrice = request.getParameter("minPrice");
        if (minPrice != null && !minPrice.isEmpty()) {
            searchAvailableRoomsRequest.setMinPrice(Double.parseDouble(minPrice));
        }

        String maxPrice = request.getParameter("maxPrice");
        if (maxPrice != null && !maxPrice.isEmpty()) {
            searchAvailableRoomsRequest.setMaxPrice(Double.parseDouble(maxPrice));
        }

        String quantity = request.getParameter("quantity");
        if (quantity != null && !quantity.isEmpty()) {
            searchAvailableRoomsRequest.setQuantity(Integer.parseInt(quantity));
        }

        String type = request.getParameter("type");
        if (type != null && !type.isEmpty()) {
            searchAvailableRoomsRequest.setType(type);
        }

        return searchAvailableRoomsRequest;
    }


}
