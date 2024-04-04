package jamesngnm.travelbookingsystem.dao;

import jamesngnm.travelbookingsystem.entity.FlightEntity;
import jamesngnm.travelbookingsystem.model.request.SearchFlightRequest;

import java.util.List;

public interface FlightDAO {
    public FlightEntity create(FlightEntity flight);
    public List<FlightEntity> searchFlights(SearchFlightRequest request);
}
