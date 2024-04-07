package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "hotels")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity {
    @Id
    private Long id;

    private String location;

    private String name;

    @Column(name = "available_rooms")
    private int availableRooms;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<RoomEntity> rooms;

    public boolean isAvailable(String location, String name, int availableRooms, double price) {
        return false;
    }

}
