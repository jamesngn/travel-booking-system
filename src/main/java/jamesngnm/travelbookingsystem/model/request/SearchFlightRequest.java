package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.utils.Constants;

import java.time.LocalDateTime;

public class SearchFlightRequest {
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    private Integer pageNumber = Constants.DEFAULT_PAGE_NUMBER;

    public SearchFlightRequest() {
    }

    public SearchFlightRequest(String origin, String destination, LocalDateTime departureTime, Integer pageSize, Integer pageNumber) {
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
