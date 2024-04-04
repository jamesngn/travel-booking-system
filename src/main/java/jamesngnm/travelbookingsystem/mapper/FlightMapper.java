package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.model.request.CreateFlightRequest;
import jamesngnm.travelbookingsystem.model.response.CreateFlightResponse;
import jamesngnm.travelbookingsystem.model.response.SearchFlightResponse;

public class FlightMapper {
    public FlightMapper() {

    }
    public CreateFlightResponse toCreateFlightResponse(FlightEntity flight) {
        CreateFlightResponse createFlightResponse = new CreateFlightResponse();
        createFlightResponse.setId(flight.getId());
        createFlightResponse.setOrigin(flight.getOrigin());
        createFlightResponse.setDestination(flight.getDestination());
        createFlightResponse.setDepartureTime(flight.getDepartureTime());
        createFlightResponse.setAvailableSeats(flight.getAvailableSeats());
        createFlightResponse.setPrice(flight.getPrice());
        return createFlightResponse;
    }

    public FlightEntity toEntity(CreateFlightRequest createFlightRequest) {
        FlightEntity flight = new FlightEntity();
        flight.setOrigin(createFlightRequest.getOrigin());
        flight.setDestination(createFlightRequest.getDestination());
        flight.setDepartureTime(createFlightRequest.getDepartureTime());
        flight.setAvailableSeats(createFlightRequest.getAvailableSeats());
        flight.setPrice(createFlightRequest.getPrice());
        return flight;
    }

    public SearchFlightResponse toSearchFlightResponse(FlightEntity flight) {
        SearchFlightResponse searchFlightResponse = new SearchFlightResponse();
        searchFlightResponse.setOrigin(flight.getOrigin());
        searchFlightResponse.setDestination(flight.getDestination());
        searchFlightResponse.setDepartureTime(flight.getDepartureTime());
        searchFlightResponse.setAvailableSeats(flight.getAvailableSeats());
        searchFlightResponse.setPrice(flight.getPrice());
        return searchFlightResponse;
    }
}
