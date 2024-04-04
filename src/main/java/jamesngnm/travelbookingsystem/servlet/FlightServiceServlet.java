package jamesngnm.travelbookingsystem.servlet;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jamesngnm.travelbookingsystem.entity.Flight;
import jamesngnm.travelbookingsystem.service.FlightService;
import jamesngnm.travelbookingsystem.service.impl.FlightServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = {"/flights", "/flights/create"})
public class FlightServiceServlet extends HttpServlet {
    private FlightService flightService;
    @Override
    public void init() throws ServletException {
        super.init();
        flightService = new FlightServiceImpl();
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/flights/create".equals(path)) {
            createFlight(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private void createFlight(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
        int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
        double price = Double.parseDouble(request.getParameter("price"));

        Flight flight = new Flight();
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureTime(departureTime);
        flight.setAvailableSeats(availableSeats);
        flight.setPrice(price);

        flightService.createFlight(flight);

        response.setContentType("text/plain");
        response.getWriter().write("Flight created successfully");
    }
}
