package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Table(name = "hotels")
@Entity
public class HotelEntity  {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String address;

    @Column(length = 2000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    private List<String> amenities;

    @ElementCollection
    @CollectionTable(name = "images", joinColumns = @JoinColumn(name = "hotel_id"))
    private List<String> images;

    @Column(nullable = false, name = "available_rooms")
    private int availableRooms;

    @OneToMany(mappedBy = "hotels", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomEntity> rooms;

    public HotelEntity(String location, int availableRooms) {
        this.location = location;
        this.availableRooms = availableRooms;
    }

    public HotelEntity() {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
