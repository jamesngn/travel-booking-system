package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
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
            throw new ResponseException(BadRequestError.HOTEL_LOCATION_INVALID);
        }
        searchHotelRequest.setLocation(location);

        String checkInDate = request.getParameter("checkInDate");
        if (checkInDate == null || checkInDate.isEmpty()) {
            searchHotelRequest.setCheckInDate(null);
        } else {
            searchHotelRequest.setCheckInDate(LocalDateTime.parse(checkInDate));
        }

        String checkOutDate = request.getParameter("checkOutDate");
        if (checkOutDate == null || checkOutDate.isEmpty()) {
            searchHotelRequest.setCheckOutDate(null);
        } else {
            searchHotelRequest.setCheckOutDate(LocalDateTime.parse(checkOutDate));
        }

        String roomType = request.getParameter("roomType");
        if (roomType == null || roomType.isEmpty()) {
            searchHotelRequest.setRoomType(null);
        } else {
            searchHotelRequest.setRoomType(RoomType.valueOf(roomType));
        }

        String minPrice = request.getParameter("minPrice");
        if (minPrice == null || minPrice.isEmpty()) {
            searchHotelRequest.setMinPrice(0);
        } else {
            searchHotelRequest.setMinPrice(Double.parseDouble(minPrice));
        }

        String maxPrice = request.getParameter("maxPrice");
        if (maxPrice == null || maxPrice.isEmpty()) {
            searchHotelRequest.setMaxPrice(Double.MAX_VALUE);
        } else {
            searchHotelRequest.setMaxPrice(Double.parseDouble(maxPrice));
        }

        String numberOfGuests = request.getParameter("numberOfGuests");
        if (numberOfGuests != null) {
            searchHotelRequest.setNumberOfGuests(Integer.parseInt(numberOfGuests));
        }

        String pageSize = request.getParameter("pageSize");
        if (pageSize != null) {
            searchHotelRequest.setPageSize(Integer.parseInt(pageSize));
        }

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber != null) {
            searchHotelRequest.setPageNumber(Integer.parseInt(pageNumber));
        }

        return searchHotelRequest;
    }
}
