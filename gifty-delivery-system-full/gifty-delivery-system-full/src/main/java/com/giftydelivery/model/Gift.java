package com.giftydelivery.model;

import jakarta.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private int stock;
    private double price;
    private double rating;

    // Getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setStock(int stock) { this.stock = stock; }
    public void setPrice(double price) { this.price = price; }
    public void setRating(double rating) { this.rating = rating; }
}
