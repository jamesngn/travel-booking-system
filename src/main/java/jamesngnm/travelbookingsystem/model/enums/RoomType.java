package jamesngnm.travelbookingsystem.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {
    SINGLE(0, "Single"),
    DOUBLE(1, "Double"),
    TRIPLE(2, "Triple"),
    QUAD(3, "Quad");


    private final Integer code;
    private final String value;
    private static Map<Integer, RoomType> mapping;

    static {
        intMapping();
    }

    RoomType (Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private static void intMapping() {
        mapping = new HashMap<>();
        for (RoomType value : values()) {
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
