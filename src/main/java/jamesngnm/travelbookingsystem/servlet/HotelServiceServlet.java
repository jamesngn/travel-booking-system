package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.request.CreateFlightRequest;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.CreateFlightResponse;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.model.response.SearchFlightResponse;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import jamesngnm.travelbookingsystem.service.FlightService;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.impl.FlightServiceImpl;
import jamesngnm.travelbookingsystem.service.impl.HotelServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/hotels", "/hotels/create", "/hotels/search", "/hotels/{flightId}"})
public class HotelServiceServlet extends HttpServlet {
    private HotelService hotelService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        super.init();
        hotelService = new HotelServiceImpl();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        // Add CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        String path = request.getServletPath();
        if ("/hotels/search".equals(path)) {
            try {
//                SearchFlightRequest searchFlightRequest = extractSearchFlightRequest(request);
//                List<SearchFlightResponse> searchFlightResponses = flightService.searchFlights(searchFlightRequest);

                SearchHotelRequest searchHotelRequest = extractSearchHotelRequest(request);
                List<SearchHotelResponse> searchHotelResponses = hotelService.searchHotel(searchHotelRequest);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(searchHotelResponses)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        }

        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private SearchHotelRequest extractSearchHotelRequest(HttpServletRequest request) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;

    try (BufferedReader reader = request.getReader()) {
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
    }

    String jsonString = sb.toString();
    return gson.fromJson(jsonString, SearchHotelRequest.class);
}
}

// TODO: search room by hotelId, roomId, roomType, roomPrice, roomCapacity
//