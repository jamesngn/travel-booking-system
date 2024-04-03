package jamesngnm.travelbookingsystem.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum FlightStatus {
    SCHEDULED(0, "Scheduled"),
    DELAYED(1, "Delayed"),
    CANCELLED(2, "Cancelled");
    
    private final Integer code;
    private final String value;
    private static Map<Integer, FlightStatus> mapping;

    static {
        intMapping();
    }

    FlightStatus (Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    
    private static void intMapping() {
        mapping = new HashMap<>();
        for (FlightStatus value : values()) {
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
