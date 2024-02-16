package com.dualproject.ecommerce.Services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dualproject.ecommerce.RoutesPath;
import com.dualproject.ecommerce.Models.Product;
import com.dualproject.ecommerce.Repositories.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepo repository;
    private final String productsImgRoute = RoutesPath.productsImgRoute;

    public void addProduct(Product product){
        repository.save(product);
    }

    // Devuelve el producto si existe o null si no existe
    public Product getProductById(Long id){
        Optional<Product> product = repository.findById(id);
        if(product.isPresent()) return product.get();

        return null;
    }

    public List<Product> getProducts(Long[] ids){
        String[] idsArray = Arrays.stream(ids).map(String::valueOf).toArray(String[]::new);
        return repository.getProductsByIds(idsArray);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public void removeProductById(Long id){
        repository.deleteById(id);
    }

    public void updateProduct(Long id, Product product){
        repository.updateProduct(id, product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getImage());
    }

    public void updateName(Long id, String name){
        repository.updateName(id, name);
    }

    public void updateDescription(Long id, String description){
        repository.updateDescription(id, description);
    }

    public void updatePrice(Long id, int price){
        repository.updatePrice(id, price);;
    }

    public void updateStock(Long id, int stock){
        repository.updateStock(id, stock);
    }

    public void updateImage(Long id, String image){
        repository.updateImage(id, productsImgRoute + image);
    }

}
