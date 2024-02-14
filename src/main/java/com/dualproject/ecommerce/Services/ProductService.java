package com.dualproject.ecommerce.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dualproject.ecommerce.Models.Product;
import com.dualproject.ecommerce.Repositories.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepo repository;

    public void addProduct(Product product){
        repository.save(product);
    }

    public void updateProduct(Long id, Product product){
        repository.updateProduct(id, product.getProduct(), product.getPrice(), product.getStock(), product.getImage());
    }

    public Product getProductById(Long id){
        return repository.findById(id).get();
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public void removeProductById(Long id){
        repository.deleteById(id);
    }

}
