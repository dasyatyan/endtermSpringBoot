package com.example.endterm.Factory;

import com.example.endterm.Model.ClothingItem;
import com.example.endterm.Model.Enum.Season;

public class DemiseasolClothingFactory implements SeasonalClothingFactory{
    public Season getSeason() {
        return Season.WINTER;
    }
    @Override
    public ClothingItem createClothingItem() {
        ClothingItem item = new ClothingItem();
        item.setSeasonalClothing(Season.WINTER);
        // Установите другие свойства, специфичные для зимней одежды
        return item;
    }
}
