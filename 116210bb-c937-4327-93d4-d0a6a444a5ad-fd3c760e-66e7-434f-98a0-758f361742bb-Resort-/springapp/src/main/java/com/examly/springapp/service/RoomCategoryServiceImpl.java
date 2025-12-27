package com.examly.springapp.service;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {
    
    @Autowired
    private RoomCategoryRepo roomCategoryRepo;
    
    @Override
    public RoomCategory saveRoomCategory(RoomCategory roomCategory) {
        return roomCategoryRepo.save(roomCategory);
    }
    
    @Override
    public List<RoomCategory> getAllRoomCategories() {
        return roomCategoryRepo.findAll();
    }
    
    @Override
    public Optional<RoomCategory> getRoomCategoryById(Long id) {
        return roomCategoryRepo.findById(id);
    }
    
    @Override
    public RoomCategory updateRoomCategory(Long id, RoomCategory roomCategory) {
        roomCategory.setCategoryId(id);
        return roomCategoryRepo.save(roomCategory);
    }
    
    @Override
    public void deleteRoomCategory(Long id) {
        roomCategoryRepo.deleteById(id);
    }
    
    @Override
    public Page<RoomCategory> getRoomCategoriesWithPagination(Pageable pageable) {
        return roomCategoryRepo.findAll(pageable);
    }
}
