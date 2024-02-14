package com.dualproject.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dualproject.ecommerce.Models.Product;
import com.dualproject.ecommerce.Services.ProductService;

@RestController
public class Rest {

    private final String routeProducts = "/rest/products";
    
    @Autowired
    private ProductService productService;

    @GetMapping(routeProducts + "/add")
    public void addProduct(Model model, @RequestParam String product, @RequestParam double price, @RequestParam String image, @RequestParam int stock){
        productService.addProduct(new Product(product, price, image, stock));
    }

}
