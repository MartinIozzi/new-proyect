package com.dualproject.ecommerce.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String product;
    private double price;
    private String image;
    private int stock;

    public Product(String product, double price, String image, int stock){
        this.product = product;
        this.price = price;
        this.image = image;
        this.stock = stock;
    }
}
