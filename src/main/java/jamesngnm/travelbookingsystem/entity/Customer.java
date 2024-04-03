package jamesngnm.travelbookingsystem.entity;

import jamesngnm.travelbookingsystem.interfaces.UserManager;

public class Customer implements UserManager {
    private String name;
    private String email;
    private String password;
    private String address;

    @Override
    public void registerUser(String email, String password) {

    }

    @Override
    public void loginUser(String email, String password) {

    }

    @Override
    public void logoutUser() {

    }
}
