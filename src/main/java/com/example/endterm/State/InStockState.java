package com.example.endterm.State;

import com.example.endterm.Model.ClothingItem;

public class InStockState implements AvailabilityState {

    @Override
    public void handleAvailability(ClothingItem item) {
        // Логика для товара в наличии
        System.out.println("Item " + item.getName() + " is in stock.");
        // Здесь может быть логика, связанная с делегированием поведения в случае наличия товара
    }
}