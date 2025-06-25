package com.giftydelivery.service;

import com.giftydelivery.model.Gift;
import com.giftydelivery.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService {

    @Autowired
    private GiftRepository giftRepository;

    public Gift addGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Gift getGiftById(Long id) {
        return giftRepository.findById(id).orElse(null);
    }

    public Gift updateGift(Long id, Gift updatedGift) {
        Gift gift = getGiftById(id);
        if (gift != null) {
            gift.setName(updatedGift.getName());
            gift.setCategory(updatedGift.getCategory());
            gift.setPrice(updatedGift.getPrice());
            gift.setStock(updatedGift.getStock());
            gift.setRating(updatedGift.getRating());
            return giftRepository.save(gift);
        }
        return null;
    }

    public void deleteGift(Long id) {
        giftRepository.deleteById(id);
    }

    // ✅ Reduce stock when an order is placed
    public void reduceStock(Long giftId, int quantity) {
        Gift gift = getGiftById(giftId);
        if (gift == null) {
            throw new RuntimeException("Gift not found");
        }
        if (gift.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for gift: " + gift.getName());
        }
        gift.setStock(gift.getStock() - quantity);
        giftRepository.save(gift);
    }
}
