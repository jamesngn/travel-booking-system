package jamesngnm.travelbookingsystem.interfaces;

public interface UserManager {
    void registerUser(String email, String password);
    void loginUser(String email, String password);
    void logoutUser();
}
