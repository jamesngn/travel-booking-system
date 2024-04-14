package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.RoomByTypeRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.impl.HotelBookingServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/bookings", "/bookings/hotel/create", "/bookings/hotel/id/*", "/bookings/hotel/user/*"})
public class BookingServiceServlet extends HttpServlet {
    private HotelBookingService hotelBookingService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        super.init();
        hotelBookingService = new HotelBookingServiceImpl();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/bookings/hotel/create".equals(path)) {
            try {
                CreateHotelBookingRequest createHotelBookingRequest = extractCreateHotelBookingRequestFromBody(request);
                HotelBookingResponse hotelBookingResponse = hotelBookingService.bookHotel(createHotelBookingRequest);

                response.setStatus(HttpServletResponse.SC_CREATED);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(hotelBookingResponse)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        }
    }

    @Override
    protected void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/bookings/hotel/id")) {
            try {
                Long id = Long.parseLong(request.getPathInfo().substring(1));
                HotelBookingResponse hotelBookingResponse = hotelBookingService.searchHotelBookingDetailsById(id);

//                HotelBookingDTO hotelBookingResponse = hotelBookingService.getHotelBookingDetailsV2(id);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(hotelBookingResponse)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        } else if (path.startsWith("/bookings/hotel/user")) {
            try {
                Long userId = Long.parseLong(request.getPathInfo().substring(1));
                List<HotelBookingResponse> hotelBookingResponses = hotelBookingService.searchHotelBookingDetailsByUserId(userId);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(hotelBookingResponses)));
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

    private CreateHotelBookingRequest extractCreateHotelBookingRequestFromBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }

        JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);
        CreateHotelBookingRequest createHotelBookingRequest = new CreateHotelBookingRequest();
        createHotelBookingRequest.setHotelId(jsonObject.get("hotelId").getAsLong());
        createHotelBookingRequest.setUserId(jsonObject.get("userId").getAsLong());
        createHotelBookingRequest.setCheckInDate(LocalDateTime.parse(jsonObject.get("checkInDate").getAsString()));
        createHotelBookingRequest.setCheckOutDate(LocalDateTime.parse(jsonObject.get("checkOutDate").getAsString()));

        JsonArray roomsArray = jsonObject.getAsJsonArray("rooms");
        List<RoomByTypeRequest> roomRequests = new ArrayList<>();
        for (JsonElement roomElement : roomsArray) {
            JsonObject roomObject = roomElement.getAsJsonObject();
            String type = roomObject.get("type").getAsString();
            int quantity = roomObject.get("quantity").getAsInt();
            roomRequests.add(new RoomByTypeRequest(type, quantity));
        }
        createHotelBookingRequest.setRoomByTypeRequests(roomRequests);

        return createHotelBookingRequest;
    }
}

//TODO => flight booking: create flight booking, get flight booking details,
//TODO => hotel booking: get hotel booking details
//TODO => booking: create booking, get booking details

