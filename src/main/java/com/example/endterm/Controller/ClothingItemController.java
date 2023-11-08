package com.example.endterm.Controller;

import com.example.endterm.Model.ClothingItem;
import com.example.endterm.Model.Enum.Season;
import com.example.endterm.Service.ClothingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clothing")
public class ClothingItemController {

    private final ClothingItemService clothingItemService;

    @Autowired
    public ClothingItemController(ClothingItemService clothingItemService) {
        this.clothingItemService = clothingItemService;
    }

    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllClothingItems() {
        List<ClothingItem> items = clothingItemService.findAllClothingItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothingItem> getClothingItemById(@PathVariable Long id) {
        Optional<ClothingItem> clothingItem = clothingItemService.findClothingItemById(id);
        return clothingItem
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ClothingItem> addClothingItem(@RequestBody ClothingItem clothingItem) {
        ClothingItem newItem = clothingItemService.saveClothingItem(clothingItem);
        return new ResponseEntity<>(newItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClothingItem(@PathVariable Long id) {
        clothingItemService.deleteClothingItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to create a ClothingItem for a specific season
    @PostMapping("/season/{season}")
    public ResponseEntity<ClothingItem> createClothingItemForSeason(@PathVariable Season season) {
        try {
            ClothingItem item = clothingItemService.createClothingItemForSeason(season);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Other controller methods...
}