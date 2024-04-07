package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private double price;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @ToString.Exclude
    private HotelEntity hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookedDate> bookedDates;

    public void addBookedDate(BookedDate bookedDate) {
        bookedDates.add(bookedDate);
    }
}
