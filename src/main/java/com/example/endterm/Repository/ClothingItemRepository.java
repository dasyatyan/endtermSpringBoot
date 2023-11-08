package com.example.endterm.Repository;

import com.example.endterm.Model.ClothingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
    // Custom query methods
}