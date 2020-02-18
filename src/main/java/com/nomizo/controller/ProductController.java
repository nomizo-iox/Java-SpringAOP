package com.nomizo.controller;

import com.nomizo.model.Product;
import com.nomizo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public List<Product> saveProduct(@RequestBody List<Product> products) {
        List<Product> productList = productService.addProduct(products);
        return productList;
    }

    @GetMapping("/findProducts")
    public List<Product> getProducts() {
        List<Product> products = productService.findAllProducts();
        return products;
    }

}
