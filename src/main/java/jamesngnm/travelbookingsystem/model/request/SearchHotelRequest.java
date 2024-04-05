package jamesngnm.travelbookingsystem.model.request;

import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class SearchHotelRequest {
    private String location;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private int numberOfGuests;
    private RoomType roomType;
    private double minPrice;
    private double maxPrice;
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
    private Integer pageNumber = Constants.DEFAULT_PAGE_NUMBER;
    
    public SearchHotelRequest(String location, LocalDateTime checkInDate, LocalDateTime checkOutDate, int numberOfGuests, RoomType roomType, double minPrice, double maxPrice, Integer pageSize, Integer pageNumber) {
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuests = numberOfGuests;
        this.roomType = roomType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public SearchHotelRequest() {

    }

    public RoomType getRoomType() {
        return roomType;
    }
    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public String getLocation() {
        return location;
    }


    public int getNumberOfGuests() {
        return numberOfGuests;
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

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}