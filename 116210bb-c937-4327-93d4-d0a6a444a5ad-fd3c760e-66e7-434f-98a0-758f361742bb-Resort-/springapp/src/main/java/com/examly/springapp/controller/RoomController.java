package com.examly.springapp.controller;

import com.examly.springapp.model.Room;
import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.service.RoomService;
import com.examly.springapp.service.RoomCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private RoomCategoryService roomCategoryService;
    
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        if (room.getRoomCategory() != null && room.getRoomCategory().getCategoryId() != null) {
            Optional<RoomCategory> category = roomCategoryService.getRoomCategoryById(room.getRoomCategory().getCategoryId());
            if (category.isPresent()) {
                room.setRoomCategory(category.get());
            }
        }
        Room savedRoom = roomService.saveRoom(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Optional<Room> room = roomService.getRoomById(id);
        return room.map(r -> new ResponseEntity<>(r, HttpStatus.OK))
                  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        if (room.getRoomCategory() != null && room.getRoomCategory().getCategoryId() != null) {
            Optional<RoomCategory> category = roomCategoryService.getRoomCategoryById(room.getRoomCategory().getCategoryId());
            if (category.isPresent()) {
                room.setRoomCategory(category.get());
            }
        }
        Room updatedRoom = roomService.updateRoom(id, room);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }
    
    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<?> getRoomByNumber(@PathVariable String roomNumber) {
        List<Room> rooms = roomService.getRoomsByNumber(roomNumber);
        if (rooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No room found with number: " + roomNumber);
        }
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Room>> getRoomsByCategoryName(@PathVariable String categoryName) {
        List<Room> rooms = roomService.getRoomsByCategoryName(categoryName);
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}