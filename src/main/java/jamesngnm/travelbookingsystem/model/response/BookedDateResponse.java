package jamesngnm.travelbookingsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedDateResponse {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private Long roomId;
}
