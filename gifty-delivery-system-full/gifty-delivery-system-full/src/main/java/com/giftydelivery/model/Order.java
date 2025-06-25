package com.giftydelivery.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;           // Email of the user placing the order
    private Long giftId;                // ID of the gift being ordered
    private int quantity;               // Quantity of gift items ordered

    private LocalDateTime scheduledTime;         // When the order should be delivered
    private LocalDateTime createdAt = LocalDateTime.now();  // Order creation time
    private String status = "PENDING";           // Default status

    // === Getters ===
    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getGiftId() {
        return giftId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getStatus() {
        return status;
    }

    // === Setters ===
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
