package com.example.endterm.State;

import com.example.endterm.Model.ClothingItem;

public interface AvailabilityState {
    void handleAvailability(ClothingItem item);
}