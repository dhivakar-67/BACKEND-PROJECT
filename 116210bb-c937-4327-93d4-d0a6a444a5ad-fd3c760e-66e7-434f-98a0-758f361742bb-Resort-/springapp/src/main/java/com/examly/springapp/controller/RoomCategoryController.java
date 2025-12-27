package com.examly.springapp.controller;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.service.RoomCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room-categories")
public class RoomCategoryController {
    
    @Autowired
    private RoomCategoryService roomCategoryService;
    
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody RoomCategory category) {
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }
        RoomCategory savedCategory = roomCategoryService.saveRoomCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
    
    @GetMapping
    public ResponseEntity<List<RoomCategory>> getAllCategories() {
        List<RoomCategory> categories = roomCategoryService.getAllRoomCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Optional<RoomCategory> category = roomCategoryService.getRoomCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Room category not found");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RoomCategory> updateCategory(@PathVariable Long id, @RequestBody RoomCategory category) {
        RoomCategory updatedCategory = roomCategoryService.updateRoomCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        roomCategoryService.deleteRoomCategory(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<RoomCategory>> getRoomCategories(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<RoomCategory> result = roomCategoryService.getRoomCategoriesWithPagination(pageable);
        return ResponseEntity.ok(result);
    }
}