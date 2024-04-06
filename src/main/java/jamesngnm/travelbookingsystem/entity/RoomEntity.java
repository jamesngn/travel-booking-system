package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.model.enums.RoomType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Table(name = "rooms")
@Entity
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "room_type")
    private RoomType roomType;

    @Column(nullable = false, name = "room_number")
    private String roomNumber;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookedDate> bookedDates;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    public RoomEntity() {
    }

    public RoomEntity(RoomType roomType, String roomNumber, double price, HotelEntity hotel) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.price = price;
        this.hotel = hotel;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public List<BookedDate> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(List<BookedDate> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
