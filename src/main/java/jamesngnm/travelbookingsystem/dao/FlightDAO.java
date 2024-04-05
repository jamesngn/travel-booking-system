package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;

import java.util.List;

public interface FlightDAO {
    FlightEntity create(FlightEntity flight);
    List<FlightEntity> searchFlights(SearchFlightRequest request);
}
