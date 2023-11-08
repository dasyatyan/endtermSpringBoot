package com.example.endterm.Factory;

import com.example.endterm.Model.ClothingItem;
import com.example.endterm.Model.Enum.Season;

public interface SeasonalClothingFactory {
    ClothingItem createClothingItem();

    Season getSeason();
}
