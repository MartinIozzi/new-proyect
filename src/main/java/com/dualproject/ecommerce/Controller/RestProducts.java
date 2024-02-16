package com.dualproject.ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dualproject.ecommerce.RoutesPath;
import com.dualproject.ecommerce.Models.Product;
import com.dualproject.ecommerce.Services.ProductService;


@RestController
public class RestProducts {

    private final String routeProducts = RoutesPath.routeProducts;

    // IMPORTANTE: Pedir autenticación y rol admin desde Spring Security para Post, Put y Delete
    
    @Autowired
    private ProductService productService;

    // ---------------------------------------- Get Methods ----------------------------------------
    @GetMapping(routeProducts + "/get/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }
    
    @GetMapping(routeProducts + "/get/all/{ids}")
    public List<Product> getProducts(@PathVariable Long[] ids){
        return productService.getProducts(ids);
    }

    @GetMapping(routeProducts + "/get/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // ---------------------------------------- Post Methods ---------------------------------------
    @PostMapping(routeProducts + "/add")
    public void addProduct(@RequestBody Product product){
        product.setImage("/img/products/" + product.getImage()); // Agrega la carpeta de las imagenes al inicio
        productService.addProduct(product);
    }

    // ---------------------------------------- Put Methods ----------------------------------------
    @PutMapping(routeProducts + "/update/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.updateProduct(id, product);
    }

    @PutMapping(value = routeProducts + "/update/{id}", params = "name")
    public void updateProductName(@PathVariable Long id, @RequestParam String name){
        productService.updateName(id, name);
    }
    
    @PutMapping(value = routeProducts + "/update/{id}", params = "description")
    public void updateProductDescription(@PathVariable Long id, @RequestParam String description){
        productService.updateDescription(id, description);
    }

    @PutMapping(value = routeProducts + "/update/{id}", params = "price")
    public void updateProductPrice(@PathVariable Long id, @RequestParam int price){
        productService.updatePrice(id, price);
    }

    @PutMapping(value = routeProducts + "/update/{id}", params = "stock")
    public void updateProductStock(@PathVariable Long id, @RequestParam int stock){
        productService.updateStock(id, stock);
    }

    @PutMapping(value = routeProducts + "/update/{id}", params = "image")
    public void updateProductImage(@PathVariable Long id, @RequestParam String image){
        productService.updateImage(id, image);
    }
    
    // ---------------------------------------- Delete Methods -------------------------------------
    @DeleteMapping(routeProducts + "/delete/{id}")
    public void removeProduct(@PathVariable Long id){
        productService.removeProductById(id);
    }
}
