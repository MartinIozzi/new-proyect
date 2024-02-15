package com.dualproject.ecommerce.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dualproject.ecommerce.Models.Product;

import jakarta.transaction.Transactional;

public interface ProductRepo extends JpaRepository<Product, Long>{

    @Query(value = "SELECT * FROM product WHERE id IN (?1)", nativeQuery = true)
    List<Product> getProductsByIds(String[] idList);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET name =?2, description =?3, price =?4, stock =?5, image =?6 WHERE id =?1", nativeQuery = true)
    int updateProduct(Long id, String name,String description, double price, int stock, String image);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET name=?2 WHERE id =?1", nativeQuery = true)
    int updateName(Long id, String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET description =?2 WHERE id =?1", nativeQuery = true)
    int updateDescription(Long id, String description);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET price=?2 WHERE id =?1", nativeQuery = true)
    int updatePrice(Long id, int price);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET stock=?2 WHERE id =?1", nativeQuery = true)
    int updateStock(Long id, int stock);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET image=?2 WHERE id =?1", nativeQuery = true)
    int updateImage(Long id, String image);

}
