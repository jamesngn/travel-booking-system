package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.request.GetHotelDetailsRequest;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.*;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.impl.HotelServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/hotels", "/hotels/create", "/hotels/search", "/hotel/detail"})
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
        } else if (path.equals("/hotel/detail")) {
            try {
                GetHotelDetailsRequest getHotelDetailsRequest = extractGetHotelDetailsRequest(request);

                HotelDetailResponse hotelDetailResponse = hotelService.getHotelDetail(getHotelDetailsRequest);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(hotelDetailResponse)));
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

    private GetHotelDetailsRequest extractGetHotelDetailsRequest(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;

        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        String jsonString = sb.toString();
        return gson.fromJson(jsonString, GetHotelDetailsRequest.class);
    }
}

// TODO: search room by hotelId, roomId, roomType, roomPrice, roomCapacity

// TODO: make sure if access to not exisiting link, must throw exception created by our own