package jamesngnm.travelbookingsystem.service;

import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.model.request.CreateFlightRequest;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;
import jamesngnm.travelbookingsystem.model.response.CreateFlightResponse;
import jamesngnm.travelbookingsystem.model.response.SearchFlightResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    CreateFlightResponse createFlight(CreateFlightRequest createFlightRequest);
    List<SearchFlightResponse> searchFlights(SearchFlightRequest searchFlightRequest);
    List<FlightEntity> getFlightsByIds(List<Long> flightIds);

}
