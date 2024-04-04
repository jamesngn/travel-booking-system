package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.FlightDAO;
import jamesngnm.travelbookingsystem.dao.impl.FlightDAOImpl;
import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.interfaces.GenericDAO;
import jamesngnm.travelbookingsystem.mapper.FlightMapper;
import jamesngnm.travelbookingsystem.model.request.CreateFlightRequest;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;
import jamesngnm.travelbookingsystem.model.response.CreateFlightResponse;
import jamesngnm.travelbookingsystem.model.response.SearchFlightResponse;
import jamesngnm.travelbookingsystem.service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {
    private final FlightMapper mapper;
    private final FlightDAO flightDAO;

    public FlightServiceImpl() {

        flightDAO = new FlightDAOImpl();
        mapper = new FlightMapper();
    }

    @Override
    public CreateFlightResponse createFlight(CreateFlightRequest createFlightRequest) {
        FlightEntity flight = mapper.toEntity(createFlightRequest);
        validateCreateFlightRequest(flight);

        FlightEntity createdFlight = flightDAO.create(flight);
        return mapper.toCreateFlightResponse(createdFlight);
    }


    private void validateCreateFlightRequest(FlightEntity flight) {
        if (flight.getOrigin() == null || flight.getOrigin().isEmpty()) {
            throw new ResponseException(BadRequestError.FLIGHT_ORIGIN_INVALID);
        }
        if (flight.getDestination() == null || flight.getDestination().isEmpty()) {
            throw new ResponseException(BadRequestError.FLIGHT_DESTINATION_INVALID);
        }
        if (flight.getOrigin().equalsIgnoreCase(flight.getDestination())) {
            throw new ResponseException(BadRequestError.FLIGHT_ORIGIN_DESTINATION_SAME);
        }
        if (flight.getDepartureTime() == null) {
            throw new ResponseException(BadRequestError.FLIGHT_DEPARTURE_TIME_INVALID);
        }
        if (flight.getDepartureTime() != null && flight.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new ResponseException(BadRequestError.FLIGHT_DEPARTURE_TIME_IN_THE_PAST);
        }
        if (flight.getAvailableSeats() <= 0) {
            throw new ResponseException(BadRequestError.FLIGHT_AVAILABLE_SEATS_INVALID);
        }
        if (flight.getPrice() == null || flight.getPrice() <= 0) {
            throw new ResponseException(BadRequestError.FLIGHT_PRICE_INVALID);
        }
    }

    @Override
    public List<SearchFlightResponse> searchFlights(SearchFlightRequest searchFlightRequest) {
        List<FlightEntity> flights = flightDAO.searchFlights(searchFlightRequest);

        return flights.stream()
                .map(mapper::toSearchFlightResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightEntity> getFlightsByIds(List<Long> flightIds) {
        return null;
    }


}
