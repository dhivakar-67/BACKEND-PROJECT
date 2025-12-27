package com.examly.springapp.controller;

import com.examly.springapp.model.Guest;
import com.examly.springapp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestController {
    
    @Autowired
    private GuestService guestService;
    
    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        Guest savedGuest = guestService.saveGuest(guest);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestService.getAllGuests();
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        Optional<Guest> guest = guestService.getGuestById(id);
        return guest.map(g -> new ResponseEntity<>(g, HttpStatus.OK))
                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        Guest updatedGuest = guestService.updateGuest(id, guest);
        return new ResponseEntity<>(updatedGuest, HttpStatus.OK);
    }
    
    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getGuestsByPhone(@PathVariable String phone) {
        List<Guest> guests = guestService.getGuestsByPhone(phone);
        if (guests.isEmpty()) {
            return new ResponseEntity<>("No guest found with phone: " + phone, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Guest>> getGuestsByEmail(@PathVariable String email) {
        List<Guest> guests = guestService.getGuestsByEmail(email);
        return new ResponseEntity<>(guests, HttpStatus.OK);
    }
}
