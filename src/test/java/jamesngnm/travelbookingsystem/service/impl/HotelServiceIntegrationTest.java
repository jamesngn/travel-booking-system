package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.HotelDAO;
import jamesngnm.travelbookingsystem.dao.RoomDAO;
import jamesngnm.travelbookingsystem.dao.impl.TestHotelDAOImpl;
import jamesngnm.travelbookingsystem.dao.impl.TestRoomDAOImpl;
import jamesngnm.travelbookingsystem.entity.HotelEntity;
import jamesngnm.travelbookingsystem.entity.RoomEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.model.enums.RoomType;
import jamesngnm.travelbookingsystem.model.request.CreateHotelRequest;
import jamesngnm.travelbookingsystem.model.request.GetHotelDetailsRequest;
import jamesngnm.travelbookingsystem.model.request.SearchHotelRequest;
import jamesngnm.travelbookingsystem.model.response.HotelDetailResponse;
import jamesngnm.travelbookingsystem.model.response.SearchHotelResponse;
import jamesngnm.travelbookingsystem.service.HotelService;
import jamesngnm.travelbookingsystem.service.RoomService;
import jamesngnm.travelbookingsystem.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Integration tests for HotelService")
public class HotelServiceIntegrationTest {
    private static HotelService hotelService;
    private static RoomService roomService;
    private HotelDAO hotelDAO;
    private RoomDAO roomDAO;


    @BeforeEach
    public void setup() {
        hotelDAO = new TestHotelDAOImpl();
        roomDAO = new TestRoomDAOImpl();

        hotelService = new HotelServiceImpl(hotelDAO, roomDAO);
        roomService = new RoomServiceImpl(roomDAO);
    }

    @Nested
    @DisplayName("Create hotel tests")
    class CreateHotelTests {
        @Test
        @DisplayName("Should create a new hotel")
        void testCreateHotel() {

        }

        @Nested
        @DisplayName("Search Hotel Tests")
        class SearchHotelTests {
            @Test
            @DisplayName("Should return hotels when searching by location")
            void testSearchHotelByLocation() {
                // Arrange
                SearchHotelRequest request = new SearchHotelRequest();
                request.setLocation("Brisbane");

                // Act
                List<SearchHotelResponse> response = hotelService.searchHotel(request);

                // Assert
                assertNotNull(response, "Search hotel response should not be null");
                assertFalse(response.isEmpty(), "Search hotel response should not be empty");

                int expectedCount = 1;
                assertEquals(expectedCount, response.size(), "Search hotel response count should match expected count");

                SearchHotelResponse searchHotelResponse = response.get(0);
                assertEquals("Brisbane", searchHotelResponse.getLocation(), "Hotel location should match search criteria");

                boolean allMatchLocation = response.stream()
                        .allMatch(hotel -> hotel.getLocation().equals("Brisbane"));
                assertTrue(allMatchLocation, "All hotels should be in Brisbane");
            }

            @Test
            @DisplayName("Should return empty list when searching for non-existent hotel")
            void testSearchNonExistentHotel() {
                // Arrange
                SearchHotelRequest request = new SearchHotelRequest();
                request.setLocation("Non Existent Location");

                // Act
                List<SearchHotelResponse> response = hotelService.searchHotel(request);

                // Assert
                assertNotNull(response, "Search hotel response should not be null");
                assertTrue(response.isEmpty(), "Search hotel response should be empty");
            }
        }


        @Nested
        @DisplayName("Get Hotel Detail Tests")
        class GetHotelDetailTests {
            @Test
            @DisplayName("Should return hotel entity when providing valid hotel ID")
            void testGetHotelEntityById() {
                // Arrange
                Long hotelId = 1L;

                // Act
                HotelEntity response = hotelService.getHotelEntityById(hotelId);

                // Assert
                assertNotNull(response, "Hotel detail response should not be null");
                assertEquals(hotelId, response.getId(), "Hotel ID should match the requested ID");
            }

            @Test
            @DisplayName("Should return hotel details including rooms when providing valid hotel ID")
            void testGetHotelDetailWithValidId() {
                // Arrange
                Long hotelId = 1L;
                GetHotelDetailsRequest request = new GetHotelDetailsRequest();
                request.setHotelId(hotelId);
                request.setCheckInDate("2024-04-16T00:00:00");
                request.setCheckOutDate("2024-04-23T00:00:00");

                // Act
                HotelDetailResponse response = hotelService.getHotelDetail(request);

                // Assert
                assertNotNull(response, "Hotel detail response should not be null");
                assertEquals(hotelId, response.getHotel().getId(), "Hotel ID should match the requested ID");
                assertEquals("Hilton Hotel", response.getHotel().getName(), "Hotel name should match the test data");
                assertEquals("Melbourne", response.getHotel().getLocation(), "Hotel location should match the test data");
                assertNotNull(response.getRooms(), "Hotel rooms should not be null");
            }

            @Test
            @DisplayName("Should not return hotel not found exception when providing invalid hotel ID")
            void testGetHotelDetailWithInvalidId() {
                // Arrange
                Long hotelId = 100L;
                GetHotelDetailsRequest request = new GetHotelDetailsRequest();
                request.setHotelId(hotelId);
                request.setCheckInDate("2024-04-16T00:00:00");
                request.setCheckOutDate("2024-04-23T00:00:00");

                // Act & Assert
                ResponseException exception = assertThrows(ResponseException.class, () -> {
                    hotelService.getHotelDetail(request);
                });

                // Additional assertions on the exception
                assertEquals(BadRequestError.HOTEL_NOT_FOUND, exception.getError());
                assertEquals(BadRequestError.HOTEL_NOT_FOUND.getMessage(), exception.getMessage());
            }
        }
    }
}