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
    HOTEL_LOCATION_INVALID(111, "Hotel location is required"),
    HOTEL_CHECK_IN_REQUIRED(112, "Hotel check-in date is required"),
    HOTEL_CHECK_OUT_REQUIRED(113, "Hotel check-out date is required"),
    HOTEL_ID_REQUIRED(114, "Hotel ID is required"),
    HOTEL_NOT_FOUND(115, "Hotel not found"),
    HOTEL_BOOKING_NOT_FOUND(116, "Hotel booking not found"),
    CHECK_IN_DATE_BEFORE_CHECK_OUT_DATE(117, "Check-in date must be before check-out date"),
    HOTEL_CHECK_IN_DATE_IN_THE_PAST(118, "Hotel check-in date must be in the future"),
    HOTEL_CHECK_OUT_DATE_IN_THE_PAST(119, "Hotel check-out date must be in the future"),
    ROOM_TYPE_REQUIRED(120, "Room type is required"),
    ROOM_TYPE_INVALID(121, "Room type is invalid"),
    ROOM_QUANTITY_REQUIRED(122, "Room quantity is required and must be greater than zero"),
    ROOM_NOT_AVAILABLE(123, "Room is not available for the selected dates"),
    ROOM_REQUIRED(124, "Room is required"),
    ROOM_ID_INVALID(125, "Room ID is invalid"),
    ROOM_NOT_FOUND(126, "Room not found"),
    ROOM_NOT_BELONG_TO_HOTEL(127, "Room does not belong to the hotel"),




    USER_NOT_FOUND(200, "User not found"),
    USER_ALREADY_EXISTS(201, "User already exists"),
    USER_EMAIL_INVALID(202, "User email is invalid"),
    USER_PASSWORD_INVALID(203, "User password is invalid"),
    USER_EMAIL_REQUIRED(204, "User email is required"),
    USER_PASSWORD_REQUIRED(205, "User password is required"),
    USERNAME_REQUIRED(206, "Username is required"),
    USER_ID_REQUIRED(207, "User ID is required"),

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
