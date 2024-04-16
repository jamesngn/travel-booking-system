package jamesngnm.travelbookingsystem.service.impl;

import jamesngnm.travelbookingsystem.dao.UserDAO;
import jamesngnm.travelbookingsystem.dao.impl.TestUserDAOImpl;
import jamesngnm.travelbookingsystem.entity.UserEntity;
import jamesngnm.travelbookingsystem.exception.BadRequestError;
import jamesngnm.travelbookingsystem.exception.ResponseException;
import jamesngnm.travelbookingsystem.model.request.LoginUserRequest;
import jamesngnm.travelbookingsystem.model.request.RegisterUserRequest;
import jamesngnm.travelbookingsystem.model.response.LoginUserResponse;
import jamesngnm.travelbookingsystem.model.response.RegisterUserResponse;
import jamesngnm.travelbookingsystem.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceIntegrationTest {
    private static UserService userService;
    private UserDAO userDAO;

    @BeforeEach
    public void setup() {
        userDAO = new TestUserDAOImpl();
        userService = new UserServiceImpl(userDAO);
    }

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {
        @Test
        @DisplayName("Should return user details when login is successful")
        void testLoginSuccess() {
            // Arrange
            String email = "jane@example.com";
            String password = "password456";
            LoginUserRequest request = new LoginUserRequest(email, password);

            // Act
            LoginUserResponse response = userService.loginUser(request);

            //Assert
            assertNotNull(response);
            assertNotNull(response.getId());
            assertNotNull(response.getEmail());
            assertNotNull(response.getUsername());
            assertEquals(email, response.getEmail());
        }

        @Test
        @DisplayName("Should throw an exception when user is not found")
        void testLoginUserNotFound() {
            // Arrange
            String email = "usernotfound@example.com";
            String password = "password456";
            LoginUserRequest request = new LoginUserRequest(email, password);

            // Act and Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> userService.loginUser(request));

            // Additional assertions
            assertEquals(BadRequestError.USER_NOT_FOUND, exception.getError());
            assertEquals(BadRequestError.USER_NOT_FOUND.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when password is invalid")
        void testLoginInvalidPassword() {
            // Arrange
            String email = "jane@example.com";
            String password = "invalidpassword";

            // Act and Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> userService.loginUser(new LoginUserRequest(email, password)));

            // Additional assertions
            assertEquals(BadRequestError.USER_PASSWORD_INVALID, exception.getError());
            assertEquals(BadRequestError.USER_PASSWORD_INVALID.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when email is invalid")
        void testLoginInvalidEmail() {
            // Arrange
            String email = "invalidemail";
            String password = "password456";

            // Act and Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> userService.loginUser(new LoginUserRequest(email, password)));

            // Additional assertions
            assertEquals(BadRequestError.USER_EMAIL_INVALID, exception.getError());
            assertEquals(BadRequestError.USER_EMAIL_INVALID.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when email is empty")
        void testLoginEmptyEmail() {
            // Arrange
            String email = "";
            String password = "password456";

            // Act and Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> userService.loginUser(new LoginUserRequest(email, password)));

            // Additional assertions
            assertEquals(BadRequestError.USER_EMAIL_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USER_EMAIL_REQUIRED.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when password is empty")
        void testLoginEmptyPassword() {
            // Arrange
            String email = "jane@example.com";
            String password = "";

            // Act and Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> userService.loginUser(new LoginUserRequest(email, password)));

            // Additional assertions
            assertEquals(BadRequestError.USER_PASSWORD_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USER_PASSWORD_REQUIRED.getMessage(), exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {
        @Test
        @DisplayName("Should return user details when registration is successful")
        void testRegisterSuccess() {
            // Arrange
            String email = "user1@gmail.com";
            String password = "password123";
            String username = "user1";

            // Act: Register
            RegisterUserRequest request = new RegisterUserRequest(username, password, email);
            RegisterUserResponse response = userService.registerUser(request);

            // Assert
            assertNotNull(response);
            assertNotNull(response.getEmail());
            assertNotNull(response.getUsername());
            assertEquals(email, response.getEmail());
            assertEquals(username, response.getUsername());
        }

        @Test
        @DisplayName("Should throw an exception when user already exists")
        void testRegisterUserAlreadyExists() {
            // Arrange
            String email = "user1@gmail.com";
            String password = "password123";
            String username = "user1";

            // Act & Assertion
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.registerUser(new RegisterUserRequest(username, password, email));
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_ALREADY_EXISTS, exception.getError());
            assertEquals(BadRequestError.USER_ALREADY_EXISTS.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when email is invalid")
        void testRegisterInvalidEmail() {
            // Arrange
            String email = "invalidemail";
            String password = "password123";

            // Act & Assertion
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.registerUser(new RegisterUserRequest("user1", password, email));
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_EMAIL_INVALID, exception.getError());
            assertEquals(BadRequestError.USER_EMAIL_INVALID.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when email is empty")
        void testRegisterEmptyEmail() {
            // Arrange
            String email = "";
            String password = "password123";

            // Act & Assertion
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.registerUser(new RegisterUserRequest("user1", password, email));
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_EMAIL_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USER_EMAIL_REQUIRED.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when password is empty")
        void testRegisterEmptyPassword() {
            // Arrange
            String email = "user1@gmail.com";
            String password = "";

            // Act & Assertion
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.registerUser(new RegisterUserRequest("user1", password, email));
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_PASSWORD_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USER_PASSWORD_REQUIRED.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when username is empty")
        void testRegisterEmptyUsername() {
            // Arrange
            String email = "user1@gmail.com";
            String password = "password123";
            String username = "";

            // Act & Assertion
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.registerUser(new RegisterUserRequest(username, password, email));
            });

            // Additional assertions
            assertEquals(BadRequestError.USERNAME_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USERNAME_REQUIRED.getMessage(), exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Get User Tests")
    class GetUserTests {
        @Test
        @DisplayName("Should return user entity when providing valid user ID")
        void testGetUserEntityById() {
            // Arrange
            Long userId = 1L;

            // Act
            UserEntity response = userService.getUserById(userId);

            // Assert
            assertNotNull(response, "User entity should not be null");
            assertEquals(userId, response.getId(), "User ID should match the requested ID");
            assertNotNull(response.getEmail(), "User email should not be null");
            assertNotNull(response.getUsername(), "User username should not be null");
        }

        @Test
        @DisplayName("Should throw an exception when user is not found")
        void testGetUserEntityByIdNotFound() {
            // Arrange
            Long userId = 100L;

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.getUserById(userId);
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_NOT_FOUND, exception.getError());
            assertEquals(BadRequestError.USER_NOT_FOUND.getMessage(), exception.getMessage());
        }

        @Test
        @DisplayName("Should throw an exception when user ID is null")
        void testGetUserEntityByIdNull() {
            // Arrange
            Long userId = null;

            // Act & Assert
            ResponseException exception = assertThrows(ResponseException.class, () -> {
                userService.getUserById(userId);
            });

            // Additional assertions
            assertEquals(BadRequestError.USER_ID_REQUIRED, exception.getError());
            assertEquals(BadRequestError.USER_ID_REQUIRED.getMessage(), exception.getMessage());
        }
    }

}
