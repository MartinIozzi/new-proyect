package com.dualproject.ecommerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dualproject.ecommerce.Models.Product;

import jakarta.transaction.Transactional;

public interface ProductRepo extends JpaRepository<Product, Long>{
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET product =?2, price =?3, stock =?4, image =?5 WHERE id =?1", nativeQuery = true)
    int updateProduct(Long id, String product, double price, int stock, String image);

}
