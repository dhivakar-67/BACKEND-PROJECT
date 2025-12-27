package com.examly.springapp.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    
    @Column(nullable = false, unique = true)
    private String roomNumber;
    
    @Column(nullable = false)
    private Integer pricePerNight;
    
    @Column(nullable = false)
    private Boolean available;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private RoomCategory roomCategory;
    
    public Room() {}
    
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    public String getRoomNumber() {
        return roomNumber;
    }
    
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public Integer getPricePerNight() {
        return pricePerNight;
    }
    
    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
    
    public Boolean getAvailable() {
        return available;
    }
    
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
    public RoomCategory getRoomCategory() {
        return roomCategory;
    }
    
    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }
}
