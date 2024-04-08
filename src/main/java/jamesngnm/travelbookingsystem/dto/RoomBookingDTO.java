package jamesngnm.travelbookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingDTO {
    private Long id;
    private Long roomId;
    private String roomName;
}