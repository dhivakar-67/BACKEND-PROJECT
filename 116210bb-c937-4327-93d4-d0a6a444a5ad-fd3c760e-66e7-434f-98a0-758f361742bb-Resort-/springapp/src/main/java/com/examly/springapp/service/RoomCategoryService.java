package com.examly.springapp.service;

import com.examly.springapp.model.RoomCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface RoomCategoryService {
    RoomCategory saveRoomCategory(RoomCategory roomCategory);
    List<RoomCategory> getAllRoomCategories();
    Optional<RoomCategory> getRoomCategoryById(Long id);
    RoomCategory updateRoomCategory(Long id, RoomCategory roomCategory);
    void deleteRoomCategory(Long id);
    Page<RoomCategory> getRoomCategoriesWithPagination(Pageable pageable);
}
