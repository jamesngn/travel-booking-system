package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

}
