package jamesngnm.travelbookingsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHotelRequest {
    private String location;
    // TODO: add more fields such as availableFrom, availableTo, price, etc.

}
