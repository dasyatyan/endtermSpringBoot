package com.example.endterm.State;

import com.example.endterm.Model.ClothingItem;
import com.example.endterm.State.AvailabilityState;

public class OutOfStockState implements AvailabilityState {

    @Override
    public void handleAvailability(ClothingItem item) {
        // Логика для товара, которого нет в наличии
        System.out.println("Item " + item.getName() + " is out of stock.");
        // Здесь может быть логика, связанная с делегированием поведения в случае отсутствия товара
    }
}