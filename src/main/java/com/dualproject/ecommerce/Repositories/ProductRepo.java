package com.dualproject.ecommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dualproject.ecommerce.Models.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
    
}
