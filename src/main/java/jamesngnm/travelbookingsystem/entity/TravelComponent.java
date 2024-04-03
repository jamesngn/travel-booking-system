package jamesngnm.travelbookingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


public abstract class TravelComponent {

    private String name;
    private double price;

    public abstract boolean isAvailable(String location, LocalDateTime startDate, LocalDateTime endDate);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}