package com.examly.springapp.service;

import com.examly.springapp.model.Room;
import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room saveRoom(Room room);
    List<Room> getAllRooms();
    Optional<Room> getRoomById(Long id);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
    List<Room> getRoomsByNumber(String roomNumber);
    List<Room> getRoomsByCategoryName(String categoryName);
}