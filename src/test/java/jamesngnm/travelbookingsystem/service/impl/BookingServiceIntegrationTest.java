package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomBookingDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.TestHotelBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.TestRoomBookingDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.TestRoomDAOImpl;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.request.CreateHotelBookingRequest;
import jamesngnm.travelbookingsystem.model.request.RoomByTypeRequest;
import jamesngnm.travelbookingsystem.model.response.HotelBookingResponse;
import jamesngnm.travelbookingsystem.model.response.RoomBookingResponse;
import jamesngnm.travelbookingsystem.model.response.RoomByTypeResponse;
import jamesngnm.travelbookingsystem.service.HotelBookingService;
import jamesngnm.travelbookingsystem.service.RoomBookingService;
import jamesngnm.travelbookingsystem.service.RoomService;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceIntegrationTest {
    private static HotelBookingService hotelBookingService;
    private static RoomBookingService roomBookingService;

    @BeforeEach
    public void setUp() {
        RoomDAO roomDAO = new TestRoomDAOImpl();
        RoomService roomService = new RoomServiceImpl(roomDAO);
        RoomBookingDAO roomBookingDAO = new TestRoomBookingDAOImpl();
        HotelBookingDAO hotelBookingDAO = new TestHotelBookingDAOImpl();

        roomBookingService = new RoomBookingServiceImpl(roomBookingDAO, roomService);
        hotelBookingService = new HotelBookingServiceImpl(hotelBookingDAO, roomBookingService, roomService);
    }

    @Nested
    @DisplayName("Hotel Booking Tests")
    class HotelBookingTests {
        @Test
        @DisplayName("Should reserve a hotel room")
        void testReserveHotelRoom() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-27T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-30T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act
            HotelBookingResponse response = hotelBookingService.bookHotel(request);

            // Assert
            assertNotNull(response, "Hotel booking response should not be null");
            assertNotNull(response.getHotel(), "Hotel should not be null");
            assertEquals(hotelId, response.getHotel().getId(), "Hotel ID should match the requested hotel ID");

            assertNotNull(response.getRooms(), "Booked rooms should not be null");
            assertEquals(2, response.getRooms().size(), "Number of booked rooms should match the requested quantity");

//            List<String> expectedRoomTypes = roomByTypeRequests.stream()
//                    .flatMap(req -> Stream.generate(req::getType).limit(req.getQuantity()))
//                    .toList();
//
//
//            List<String> actualRoomTypes = response.getRooms().stream()
//                    .map(RoomBookingResponse::getType)
//                    .toList()
//                    .stream()
//                    .map(RoomType::getValue)
//                    .toList();
//
//            assertTrue(actualRoomTypes.containsAll(expectedRoomTypes), "Booked room types should match the requested types");

            assertEquals(checkInDate, response.getCheckInDate(), "Check-in date should match the requested date");
            assertEquals(checkOutDate, response.getCheckOutDate(), "Check-out date should match the requested date");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid hotel ID")
        void testReserveHotelRoomWithInvalidHotelId() {
            // Arrange
            Long hotelId = 100L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-27T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-30T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.HOTEL_NOT_FOUND, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.HOTEL_NOT_FOUND.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid user ID")
        void testReserveHotelRoomWithInvalidUserId() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 100L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-27T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-30T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.USER_NOT_FOUND, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.USER_NOT_FOUND.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid room type")
        void testReserveHotelRoomWithInvalidRoomType() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-27T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-30T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("INVALID", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.ROOM_TYPE_INVALID, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.ROOM_TYPE_INVALID.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid room quantity")
        void testReserveHotelRoomWithInvalidRoomQuantity() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-27T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-30T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 100)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.ROOM_NOT_AVAILABLE, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.ROOM_NOT_AVAILABLE.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid check-in date")
        void testReserveHotelRoomWithInvalidCheckInDate() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2022-04-10T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-04-15T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.HOTEL_CHECK_IN_DATE_IN_THE_PAST, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.HOTEL_CHECK_IN_DATE_IN_THE_PAST.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid check-out date")
        void testReserveHotelRoomWithInvalidCheckOutDate() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-04-30T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2022-04-10T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.HOTEL_CHECK_OUT_DATE_IN_THE_PAST, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.HOTEL_CHECK_OUT_DATE_IN_THE_PAST.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing invalid check-in and check-out dates")
        void testReserveHotelRoomWithInvalidCheckInAndCheckOutDates() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2022-04-10T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2022-04-15T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.HOTEL_CHECK_IN_DATE_IN_THE_PAST, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.HOTEL_CHECK_IN_DATE_IN_THE_PAST.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }

        @Test
        @DisplayName("Should throw an exception when providing checkout date before check-in date")
        void testReserveHotelRoomWithCheckOutDateBeforeCheckInDate() {
            // Arrange
            Long hotelId = 1L;
            Long userId = 2L;
            LocalDateTime checkInDate = LocalDateTime.parse("2024-05-30T08:00:00");
            LocalDateTime checkOutDate = LocalDateTime.parse("2024-05-15T08:00:00");

            List<RoomByTypeRequest> roomByTypeRequests = List.of(
                    new RoomByTypeRequest("SINGLE", 1),
                    new RoomByTypeRequest("DOUBLE", 1)
            );

            CreateHotelBookingRequest request = new CreateHotelBookingRequest();
            request.setHotelId(hotelId);
            request.setUserId(userId);
            request.setCheckInDate(checkInDate);
            request.setCheckOutDate(checkOutDate);
            request.setRoomByTypeRequests(roomByTypeRequests);

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.bookHotel(request);
            });

            assertEquals(BadRequestError.CHECK_IN_DATE_BEFORE_CHECK_OUT_DATE, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.CHECK_IN_DATE_BEFORE_CHECK_OUT_DATE.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }


    }

    @Nested
    @DisplayName("Get Hotel Booking Details Tests")
    class GetHotelBookingDetailsTests {
        @Test
        @DisplayName("Should return hotel booking details by user ID")
        void testSearchHotelBookingDetailsByUserId() {
            // Arrange
            Long userId = 2L;

            // Act
            List<HotelBookingResponse> response = hotelBookingService.searchHotelBookingDetailsByUserId(userId);

            // Assert
            assertNotNull(response, "Hotel booking response should not be null");
            assertFalse(response.isEmpty(), "Hotel booking response should not be empty");
            assertEquals(1, response.size(), "Number of hotel bookings should match the test data");
        }
//        @Test
//        @DisplayName("Should return hotel booking details by ID")
//        void testSearchHotelBookingDetailsById() {
//            // Arrange
//            Long hotelBookingId = 6L;
//
//            // Act
//            HotelBookingResponse response = hotelBookingService.searchHotelBookingDetailsById(hotelBookingId);
//
//            // Assert
//            assertNotNull(response, "Hotel booking response should not be null");
//            assertNotNull(response.getHotel(), "Hotel should not be null");
//            assertEquals(hotelBookingId, response.get(), "Hotel booking ID should match the requested ID");
//        }

        @Test
        @DisplayName("Should throw an exception when providing invalid hotel booking ID")
        void testSearchHotelBookingDetailsByInvalidId() {
            // Arrange
            Long hotelBookingId = 100L;

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                hotelBookingService.searchHotelBookingDetailsById(hotelBookingId);
            });

            assertEquals(BadRequestError.HOTEL_BOOKING_NOT_FOUND, exception.getError(), "Exception error should match the test data");
            assertEquals(BadRequestError.HOTEL_BOOKING_NOT_FOUND.getMessage(), exception.getMessage(), "Exception message should match the test data");
        }
    }
}
