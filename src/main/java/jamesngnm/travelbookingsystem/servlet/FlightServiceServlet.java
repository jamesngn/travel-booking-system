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
import jamesngnm.travelbookingsystem.model.request.CreateFlightRequest;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;
import jamesngnm.travelbookingsystem.model.response.CreateFlightResponse;
import jamesngnm.travelbookingsystem.model.response.Response;
import jamesngnm.travelbookingsystem.model.response.SearchFlightResponse;
import jamesngnm.travelbookingsystem.service.FlightService;
import jamesngnm.travelbookingsystem.service.impl.FlightServiceImpl;
import jamesngnm.travelbookingsystem.utils.Constants;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = {"/flights", "/flights/create", "/flights/search", "/flights/{flightId}"})
public class FlightServiceServlet extends HttpServlet {
    private FlightService flightService;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        super.init();
        flightService = new FlightServiceImpl();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/flights/create".equals(path)) {
            try {
                CreateFlightRequest createFlightRequest = extractCreateFlightRequest(request);
                CreateFlightResponse createFlightResponse = flightService.createFlight(createFlightRequest);
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(createFlightResponse)));
            }
            catch (Exception e) {
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(e)));
            }
        } else if ("/flights/search".equals(path)) {
            try {
                SearchFlightRequest searchFlightRequest = extractSearchFlightRequest(request);
                List<SearchFlightResponse> searchFlightResponses = flightService.searchFlights(searchFlightRequest);
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(new Response<>(searchFlightResponses)));
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

    private CreateFlightRequest extractCreateFlightRequest(HttpServletRequest request) {
        CreateFlightRequest createFlightRequest = new CreateFlightRequest();
        createFlightRequest.setOrigin(request.getParameter("origin"));
        createFlightRequest.setDestination(request.getParameter("destination"));
        createFlightRequest.setDepartureTime(LocalDateTime.parse(request.getParameter("departureTime")));
        createFlightRequest.setAvailableSeats(Integer.parseInt(request.getParameter("availableSeats")));
        createFlightRequest.setPrice(Double.parseDouble(request.getParameter("price")));
        return createFlightRequest;
    }

    private SearchFlightRequest extractSearchFlightRequest(HttpServletRequest request) {
        SearchFlightRequest searchFlightRequest = new SearchFlightRequest();

        String origin = request.getParameter("origin");
        if (origin == null || origin.isEmpty()) {
            throw new ResponseException(BadRequestError.FLIGHT_ORIGIN_INVALID);
        }
        searchFlightRequest.setOrigin(origin);

        String destination = request.getParameter("destination");
        if (destination != null && !destination.isEmpty()) {
            searchFlightRequest.setDestination(destination);
        }

        String departureTime = request.getParameter("departureTime");
        if (departureTime != null && !departureTime.isEmpty()) {
            searchFlightRequest.setDepartureTime(LocalDateTime.parse(departureTime));
        }

        String pageSize = request.getParameter("pageSize");
        if (pageSize != null && !pageSize.isEmpty()) {
            searchFlightRequest.setPageSize(Integer.parseInt(pageSize));
        } else {
            searchFlightRequest.setPageSize(Constants.DEFAULT_PAGE_SIZE);
        }

        String pageNumber = request.getParameter("pageNumber");
        if (pageNumber != null && !pageNumber.isEmpty()) {
            searchFlightRequest.setPageNumber(Integer.parseInt(pageNumber));
        } else {
            searchFlightRequest.setPageNumber(Constants.DEFAULT_PAGE_NUMBER);
        }

        return searchFlightRequest;
    }

}

//TO DO: currently flightService methods is doing what flightDAO does; and flightServiceServlet contains the function of flightDAO
