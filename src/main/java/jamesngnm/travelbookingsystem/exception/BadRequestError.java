package jamesngnm.travelbookingsystem.exception;

import jakarta.servlet.http.HttpServletResponse;

public enum BadRequestError implements ResponseError {
    UNKNOWN(0, "Unknown error"),
    BAD_REQUEST_ERROR(100, "Bad request error"),
    FLIGHT_ORIGIN_INVALID(101, "Flight origin is required"),
    FLIGHT_DESTINATION_INVALID(102, "Flight destination is required"),
    FLIGHT_DEPARTURE_TIME_INVALID(103, "Flight departure time is required"),
    FLIGHT_AVAILABLE_SEATS_INVALID(104, "Flight available seats is required and must be greater than zero"),
    FLIGHT_PRICE_INVALID(105, "Flight price is required and must be greater than zero"),
    FLIGHT_ORIGIN_DESTINATION_SAME(106, "Flight origin and destination must be different"),
    FLIGHT_DEPARTURE_TIME_IN_THE_PAST(107, "Flight departure time must be in the future"),
    FLIGHT_NOT_FOUND(108, "Flight not found"),
    FLIGHT_ALREADY_EXISTS(109, "Flight already exists"),
    SEARCH_FLIGHT_REQUEST_INVALID(110, "Search flight request is invalid"),

    ;


    private final int errorCode;
    private final String message;

    BadRequestError(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getName() {
        return this.name();
    }

    public Integer getCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public int getStatus() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
}
