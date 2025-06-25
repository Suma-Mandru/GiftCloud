package com.giftydelivery.controller;

import com.giftydelivery.model.Gift;
import com.giftydelivery.service.GiftService;
import com.giftydelivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
public class GiftController {

    @Autowired
    private GiftService giftService;

    @Autowired
    private UserService userService;

    // ✅ Admin only: Add new gift
    @PostMapping
    public Gift addGift(@RequestBody Gift gift, @RequestHeader("role") String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Access Denied: Admin Only");
        }
        return giftService.addGift(gift);
    }


    // ✅ Open to all: View all gifts
    @GetMapping
    public List<Gift> getAllGifts() {
        return giftService.getAllGifts();
    }

    // ✅ Open to all: View single gift
    @GetMapping("/{id}")
    public Gift getGiftById(@PathVariable("id") Long id) {
        return giftService.getGiftById(id);
    }
    
    // ✅ Admin only: Update gift
    @PutMapping("/{id}")
    public Gift updateGift(@PathVariable("id") Long id, @RequestBody Gift gift, @RequestHeader("role") String role) {
        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Access Denied: Admin Only");
        }
        return giftService.updateGift(id, gift);
    }


    // ✅ Admin only: Delete gift
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGift(@PathVariable("id") Long id) {
        giftService.deleteGift(id);
        return ResponseEntity.ok("Gift deleted successfully");
    }


}
