package jamesngnm.travelbookingsystem.model.response;

import jamesngnm.travelbookingsystem.entity.RoomEntity;

import java.util.List;

public class SearchHotelResponse {
    private Long id;
    private String name;
    private String location;
    private String address;
    private String description;
    private int availableRooms;
    private List<RoomEntity> rooms;

    public SearchHotelResponse() {
    }

    public SearchHotelResponse(Long id, String name, String location, String address, String description, int availableRooms, List<RoomEntity> rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.description = description;
        this.availableRooms = availableRooms;
        this.rooms = rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }
}
