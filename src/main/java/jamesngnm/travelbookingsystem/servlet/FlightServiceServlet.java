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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/flights")
public class FlightServiceServlet extends HttpServlet {
    private FlightService flightService;
    @Override
    public void init() throws ServletException {
        super.init();
        flightService = new FlightServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
        int availableSeats = Integer.parseInt(request.getParameter("availableSeats"));
        double price = Double.parseDouble(request.getParameter("price"));

        System.out.println("origin: " + origin);
        System.out.println("destination: " + destination);
        System.out.println("departureTime: " + departureTime);
        System.out.println("availableSeats: " + availableSeats);
        System.out.println("price: " + price);


        Flight flight = flightService.createFlight(new Flight( origin, destination, departureTime, availableSeats, price));

        response.setContentType("application/json");
        response.getWriter().write("{\"id\": " + flight.getId() + "}");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));

        List<Flight> availableFlights = flightService.searchFlights(origin, destination, departureTime);
        String json = new Gson().toJson(availableFlights);

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
