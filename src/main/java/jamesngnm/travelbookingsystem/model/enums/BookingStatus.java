package jamesngnm.travelbookingsystem.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum BookingStatus {
    PENDING(0, "Pending"),
    CONFIRMED(1, "Confirmed"),
    CANCELLED(2, "Cancelled");


    private final Integer code;
    private final String value;
    private static Map<Integer, BookingStatus> mapping;

    static {
        intMapping();
    }

    BookingStatus (Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private static void intMapping() {
        mapping = new HashMap<>();
        for (BookingStatus value : values()) {
            mapping.put(value.getCode(), value);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
