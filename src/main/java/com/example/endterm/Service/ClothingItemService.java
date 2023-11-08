package com.example.endterm.Service;

import com.example.endterm.Factory.SeasonalClothingFactory;
import com.example.endterm.Model.ClothingItem;
import com.example.endterm.Model.Enum.Season;
import com.example.endterm.Repository.ClothingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ClothingItemService {

    private final ClothingItemRepository clothingItemRepository;
    private final Map<Season, SeasonalClothingFactory> factoryMap;

    @Autowired
    public ClothingItemService(ClothingItemRepository clothingItemRepository, List<SeasonalClothingFactory> factories) {
        this.clothingItemRepository = clothingItemRepository;
        this.factoryMap = new EnumMap<>(Season.class);
        // Initialize the factory map
        factories.forEach(factory -> factoryMap.put(factory.getSeason(), factory));
    }

    public List<ClothingItem> findAllClothingItems() {
        return clothingItemRepository.findAll();
    }

    public Optional<ClothingItem> findClothingItemById(Long id) {
        return clothingItemRepository.findById(id);
    }

    public ClothingItem saveClothingItem(ClothingItem clothingItem) {
        return clothingItemRepository.save(clothingItem);
    }

    public void deleteClothingItem(Long id) {
        clothingItemRepository.deleteById(id);
    }

    // Use factory to create a ClothingItem based on the season
    public ClothingItem createClothingItemForSeason(Season season) {
        SeasonalClothingFactory factory = factoryMap.get(season);
        if (factory != null) {
            // Assuming createClothingItem method is properly defined in the factory
            ClothingItem item = factory.createClothingItem();
            return saveClothingItem(item); // Persist the new item
        } else {
            throw new IllegalArgumentException("No clothing factory found for season: " + season);
        }
    }

    public void updateItemStock(Long itemId, boolean isInStock) {
        ClothingItem item = clothingItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));

        item.setAvailability(isInStock);
        item.checkAvailability();

        clothingItemRepository.save(item);
    }

    // Other service methods...
}