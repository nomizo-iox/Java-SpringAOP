package com.nomizo.service;

import com.nomizo.db.ProductRepository;
import com.nomizo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void initDB() {
        productRepository.saveAll(Stream.of(new Product(101,"Book", 499),
                new Product(102, "MackBook Pro 13", 3000))
                .collect(Collectors.toList()));
    }

    public List<Product> addProduct (List<Product> products){
        return productRepository.saveAll(products);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
}
