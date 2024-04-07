package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.adapter.LocalDateAdapter;
import jamesngnm.travelbookingsystem.adapter.LocalDateTimeAdapter;
import jamesngnm.travelbookingsystem.entity.HotelBookingEntity;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.service.BookingService;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.impl.FlightServiceImpl;
import jamesngnm.travelbookingsystem.service.impl.HotelBookingServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/bookings", "/bookings/hotel/create"})
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
        createHotelBookingRequest.setBookingId(jsonObject.get("bookingId").getAsLong());
        createHotelBookingRequest.setHotelId(jsonObject.get("hotelId").getAsLong());
        createHotelBookingRequest.setUserId(jsonObject.get("userId").getAsLong());
        createHotelBookingRequest.setCheckInDate(LocalDateTime.parse(jsonObject.get("checkInDate").getAsString()));
        createHotelBookingRequest.setCheckOutDate(LocalDateTime.parse(jsonObject.get("checkOutDate").getAsString()));

        JsonArray roomsArray = jsonObject.getAsJsonArray("rooms");
        List<Long> roomIds = new ArrayList<>();
        for (JsonElement roomElement : roomsArray) {
            roomIds.add(roomElement.getAsLong());
        }
        createHotelBookingRequest.setRoomIds(roomIds);

        return createHotelBookingRequest;
    }

}

//TODO => flight booking: create flight booking, get flight booking details,
//TODO => hotel booking: get hotel booking details
//TODO => booking: create booking, get booking details

