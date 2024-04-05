package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.impl.HotelServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/hotels", "/hotels/search", "/hotels/{hotelId}", "/hotels/{hotelId}/rooms", "/hotels/{hotelId}/rooms/{roomId}"})
public class HotelServiceServlet extends HttpServlet {
    private HotelService hotelService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        hotelService = new HotelServiceImpl();
        gson =  new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/hotels/search".equals(path)) {
            try {
                SearchHotelRequest searchHotelRequest = extractSearchHotelRequest(request);
                List<SearchHotelResponse> searchHotelResponses = hotelService.searchHotels(searchHotelRequest);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(searchHotelResponses)));
            } catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        }
    }

    private SearchHotelRequest extractSearchHotelRequest(HttpServletRequest request) {
        SearchHotelRequest searchHotelRequest = new SearchHotelRequest();

        String location = request.getParameter("location");
        if (location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Location is required");
        }
        searchHotelRequest.setLocation(location);

        return searchHotelRequest;
    }
}
