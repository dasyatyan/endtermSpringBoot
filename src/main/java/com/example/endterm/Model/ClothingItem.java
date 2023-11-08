package com.example.endterm.Model;

import com.example.endterm.Model.Enum.Season;
import com.example.endterm.State.AvailabilityState;
import com.example.endterm.State.InStockState;
import com.example.endterm.State.OutOfStockState;
import jakarta.persistence.*;

@Entity
public class ClothingItem {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String color;
    private boolean availability;

    @Transient // Игнорируется при сохранении в БД, т.к. состояние выражается через availability
    private AvailabilityState state;


    // ... конструкторы, геттеры и сеттеры ...

    public void checkAvailability() {
        if (Boolean.TRUE.equals(availability)) {
            setState(new InStockState());
        } else {
            setState(new OutOfStockState());
        }
        state.handleAvailability(this);
    }

    public void setState(AvailabilityState state) {
        this.state = state;
    }

    public Season getSeasonalClothing() {
        return seasonalClothing;
    }

    public void setSeasonalClothing(Season seasonalClothing) {
        this.seasonalClothing = seasonalClothing;
    }

    @Enumerated(EnumType.STRING)
    private Season seasonalClothing;
}
