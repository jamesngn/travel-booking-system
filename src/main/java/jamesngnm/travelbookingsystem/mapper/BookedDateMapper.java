package jamesngnm.travelbookingsystem.mapper;

import jamesngnm.travelbookingsystem.entity.BookedDate;
import jamesngnm.travelbookingsystem.model.response.BookedDateResponse;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookedDateMapper {
    public List<BookedDateResponse> toResponses(List<BookedDate> bd) {
        List<BookedDateResponse> list = new ArrayList<>();
       bd.forEach(b -> {
            BookedDateResponse bookedDateResponse = new BookedDateResponse();
            bookedDateResponse.setRoomId(b.getRoom().getId());
            bookedDateResponse.setCheckInDate(b.getCheckInDate());
            bookedDateResponse.setCheckOutDate(b.getCheckOutDate());
            list.add(bookedDateResponse);
        });

        return list;
    }
}
