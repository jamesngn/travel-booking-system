package jamesngnm.travelbookingsystem.entity;

import java.time.LocalDateTime;

public class Hotel extends TravelComponent {
    private String location;
    private int availableRooms;

    @Override
    public boolean isAvailable(String location, LocalDateTime startDate, LocalDateTime endDate) {
        // Check hotel availability based on location, startDate, endDate, and availableRooms
        return false;
    }


    // Getters and setters for location and availableRooms
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getAvailableRooms() {
        return availableRooms;
    }
    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

}
