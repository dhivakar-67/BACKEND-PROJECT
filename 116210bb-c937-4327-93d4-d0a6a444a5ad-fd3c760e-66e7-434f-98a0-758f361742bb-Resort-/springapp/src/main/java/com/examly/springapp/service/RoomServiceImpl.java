package com.examly.springapp.service;

import com.examly.springapp.model.Room;
import com.examly.springapp.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    
    @Autowired
    private RoomRepo roomRepo;
    
    @Override
    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }
    
    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }
    
    @Override
    public Optional<Room> getRoomById(Long id) {
        return roomRepo.findById(id);
    }
    
    @Override
    public Room updateRoom(Long id, Room room) {
        room.setRoomId(id);
        return roomRepo.save(room);
    }
    
    @Override
    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
    
    @Override
    public List<Room> getRoomsByNumber(String roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }
    
    @Override
    public List<Room> getRoomsByCategoryName(String categoryName) {
        return roomRepo.findByRoomCategoryCategoryName(categoryName);
    }
}
